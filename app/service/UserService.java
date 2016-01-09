package service;

import com.avaje.ebean.Model.Finder;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

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
}
