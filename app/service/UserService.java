package service;

import com.avaje.ebean.Model.Finder;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class UserService {
    final private static Finder<Long, User> userFinder = new Finder<>(User.class);

    public User getUserById(long userId) {
        return userFinder.byId(userId);
    }

    public User getUserByUsername(String username) {
        return userFinder.where().eq("username", username).findUnique();
    }

    public void addNewUser(String username, String password) {
        User newUser = new User();
        newUser.username = username;
        newUser.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(5));
        newUser.save();
    }

    public Optional<User> authenticate(String username, String password) {
        final Optional<User> userOpt = Optional.ofNullable(this.getUserByUsername(username));
        return userOpt.filter(user -> BCrypt.checkpw(password, user.passwordHash));
    }
}
