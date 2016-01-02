package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Application extends Controller {

    public Result index() {
        Date now = new Date();

        ArrayList<String> strs = new ArrayList<>(Arrays.asList("hoge", "poyo", "nyan"));

        String date = now.toString();

        return ok(index.apply(date, strs));
    }

    public Result login() {
        return ok(login.apply());
    }

    public Result register() {
        return ok(register.apply());
    }

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
