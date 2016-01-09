package form;

import play.data.validation.Constraints.*;

public class RegisterForm {
    @Required
    public String username;

    @Required
    public String password;
}
