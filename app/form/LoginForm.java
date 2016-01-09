package form;

import play.data.validation.Constraints.*;

public class LoginForm {
    @Required
    public String username;

    @Required
    public String password;
}
