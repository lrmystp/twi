package controllers;

import models.User;
import play.*;
import play.mvc.*;

import service.UserService;
import views.html.*;

import java.util.Optional;

public class IndexPageController extends Controller {
    private final UserService userService = new UserService();

    public Result index() {
        final Optional<Long> userIdOpt = Optional.ofNullable(session("userId")).map(Long::parseLong);

        if (!userIdOpt.isPresent()) {
            return ok(welcome.render());
        }

        final User user = userIdOpt.map(userService::getUserById).get();

        return ok(index.render(user));

    }
}