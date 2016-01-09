package service;

import models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class AuthenticationService {
    final private UserService userService = new UserService();

    public Optional<User> authenticate(String username, String password) {
        final Optional<User> userOpt = Optional.ofNullable(userService.getUserByUsername(username));
        return userOpt.filter(user -> BCrypt.checkpw(password, user.passwordHash));
    }
}
