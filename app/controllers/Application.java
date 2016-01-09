package controllers;

import models.User;
import play.mvc.*;

import views.html.*;

import java.util.*;
import java.util.stream.Collectors;

public class Application extends Controller {

    public Result profile(String username) {
        return ok(profile.render(username.equals("kuwa") ? username : null));
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
