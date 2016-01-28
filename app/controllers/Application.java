package controllers;

import models.User;
import play.mvc.*;

import service.UserService;
import views.html.*;

public class Application extends Controller {
    private final UserService userService = new UserService();

    public Result profile(String username) {
        User user = userService.getUserByUsername(username);
        return ok(profile.render(user));
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
}
