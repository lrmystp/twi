package controllers;

import models.Tweet;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import service.TweetService;
import service.UserService;
import views.html.*;

import java.util.Optional;

public class TweetController extends Controller {
    final private TweetService tweetService = new TweetService();
    final private UserService userService = new UserService();

    public Result tweet() {
        final Optional<Long> userIdOpt = Optional.ofNullable(session("userId"))
                .map(Long::parseLong);
        final Optional<User> userOpt = userIdOpt.map(userService::getUserById);

        final Form<Tweet> tweetForm = Form.form(Tweet.class).bindFromRequest();

        if (!userOpt.isPresent()) {
            session().clear();
            return redirect("/login");
        }

        if (!tweetForm.hasErrors()) {
            final String content = tweetForm.get().content;
            tweetService.tweet(userIdOpt.get(), content);
        }

        return redirect("/");
    }
}
