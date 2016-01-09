package controllers;

import form.LoginForm;
import models.User;
import play.data.Form;
import play.mvc.*;

import service.AuthenticationService;
import views.html.*;

import java.util.Optional;

public class AuthenticationController extends Controller {
    final private AuthenticationService authService = new AuthenticationService();

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

        final Optional<User> authUserOpt = authService.authenticate(userData.username, userData.password);

        if (authUserOpt.isPresent()) {
            session("userId", String.valueOf(authUserOpt.get().userId));
            return redirect("/");
        }

        return loginPage("username and password does not match.");
    }

    public Result logout() {
        session().clear();
        return redirect("/login");
    }

}

