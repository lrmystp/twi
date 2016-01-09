package controllers;

import form.LoginForm;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.data.Form;
import play.mvc.*;

import service.UserService;
import views.html.*;

import java.util.Optional;

public class AuthenticationController extends Controller {
    private final UserService userService = new UserService();

    public Result loginPage() {
        return loginPage("");
    }

    public Result loginPage(String errorMessage) {
        final Optional<String> sessionUserId = Optional.ofNullable(session("userId"));

        if (sessionUserId.isPresent()) {
            return redirect("/");
        }

        final Form<LoginForm> loginForm = Form.form(LoginForm.class);
        return ok(login.render(loginForm, errorMessage));
    }

    public Result loginComplete() {
        final Form<LoginForm> loginForm = Form.form(LoginForm.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return loginPage("Please fill form correctly.");
        }

        final LoginForm userData = loginForm.get();

        final String username = userData.username;
        final String password = userData.password;

        final Optional<User> userOpt = Optional.ofNullable(userService.getUserByUsername(username));

        final boolean authenticated = userOpt
                    .map(u -> BCrypt.checkpw(password, u.passwordHash))
                    .orElse(false);

        if (authenticated) {
            session("userId", String.valueOf(userOpt.get().userId));
            return redirect("/");
        }

        return loginPage("username and password does not match.");
    }

    public Result logout() {
        session().clear();
        return redirect("/login");
    }

}

