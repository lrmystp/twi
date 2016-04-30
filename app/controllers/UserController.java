package controllers;

import models.User;
import play.*;
import play.mvc.*;

import service.FollowService;
import service.UserService;
import views.html.*;

import java.util.Optional;

public class UserController extends Controller {
    private final UserService userService = new UserService();
    private final FollowService followService = new FollowService();

    public Result profile(String username) {
        final Optional<User> sessionUserOpt = Optional.ofNullable(session("userId"))
                .map(Long::parseLong)
                .map(userService::getUserById);

        if (!sessionUserOpt.isPresent()) {
            return redirect("/");
        }

        final User sessionUser = sessionUserOpt.get();
        final User targetUser = userService.getUserByUsername(username);

        final boolean isMe = sessionUser.userId.equals(targetUser.userId);
        final boolean following = 0 < sessionUser.following.stream()
                .map(user -> user.followee.userId)
                .filter(userId -> userId.equals(targetUser.userId))
                .count();
        final boolean followed = 0 < sessionUser.followers.stream()
                .map(user -> user.follower.userId)
                .filter(userId -> userId.equals(targetUser.userId))
                .count();

        return ok(profile.render(targetUser, isMe, following, followed));
    }

    public Result allUsers() {
        return ok(user_list.render());
    }

    public Result following(String username) {
        return ok(user_list.render());
    }

    public Result followers(String username) {
        return ok(user_list.render());
    }

    public Result follow(String username) {
        final Optional<User> sessionUserOpt = Optional.ofNullable(session("userId"))
                .map(Long::parseLong)
                .map(userService::getUserById);

        if (!sessionUserOpt.isPresent()) {
            return redirect("/");

        }

        final User sessionUser = sessionUserOpt.get();
        final User targetUser = userService.getUserByUsername(username);

        if (targetUser == null) {
            return redirect("/");
        }

        followService.follow(sessionUser, targetUser);

        return redirect("/user/" + username);
    }

    public Result unfollow(String username) {
        final Optional<User> sessionUserOpt = Optional.ofNullable(session("userId"))
                .map(Long::parseLong)
                .map(userService::getUserById);

        if (!sessionUserOpt.isPresent()) {
            return redirect("/");

        }

        final User sessionUser = sessionUserOpt.get();
        final User targetUser = userService.getUserByUsername(username);

        if (targetUser == null) {
            return redirect("/");
        }

        followService.unfollow(sessionUser, targetUser);

        return redirect("/user/" + username);
    }

}
