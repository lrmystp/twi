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

}
