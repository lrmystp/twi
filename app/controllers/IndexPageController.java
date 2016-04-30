package controllers;

import models.Tweet;
import models.User;
import play.mvc.*;

import service.TweetService;
import service.UserService;
import views.html.*;

import java.util.List;
import java.util.Optional;

public class IndexPageController extends Controller {
    private final UserService userService = new UserService();
    private final TweetService tweetService = new TweetService();

    public Result index() {
        final Optional<Long> userIdOpt = Optional.ofNullable(session("userId")).map(Long::parseLong);

        if (!userIdOpt.isPresent()) {
            return ok(welcome.render());
        }

        final User user = userIdOpt.map(userService::getUserById).get();

        final List<Tweet> timeline = tweetService.getTimeline(user.userId);

        return ok(index.render(user, timeline));

    }
}