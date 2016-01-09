package controllers;

import models.User;
import play.*;
import play.mvc.*;

import views.html.*;

import java.util.Optional;

public class IndexController extends Controller {
    public Result index() {
        Optional<Long> userIdOpt = Optional.ofNullable(session("userId")).map(Long::parseLong);

        if (!userIdOpt.isPresent()) {
            return ok(welcome.render());
        }

        User user = userIdOpt.map(l -> User.find.byId(l)).get();

        return ok(index.render());

    }
}