package controllers;

import form.RegisterForm;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import service.UserService;
import views.html.*;

import java.util.Optional;

public class RegistrationController extends Controller {
    private final UserService userService = new UserService();

    public Result registerPage() {
        final Form<RegisterForm> regForm = Form.form(RegisterForm.class);

        return ok(register.render(regForm, ""));
    }

    public Result registerComplete() {
        final Form<RegisterForm> regForm = Form.form(RegisterForm.class).bindFromRequest();

        if (regForm.hasErrors()) {
            return ok(register.render(regForm, "Please fill the form correctly."));
        }

        final RegisterForm regData = regForm.get();

        final String username = regData.username;
        final String password = regData.password;

        final Optional<User> userOpt = Optional.ofNullable(userService.getUserByUsername(regData.username));

        if (userOpt.isPresent()) {
            return ok(register.render(regForm, "This username already exists."));
        }

        User newUser = new User();
        newUser.username = username;
        newUser.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(5));

        newUser.save();

        return redirect("/login");
    }

}
