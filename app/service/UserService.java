package service;

import com.avaje.ebean.Model.Finder;
import models.User;

public class UserService {
    private static Finder<Long, User> finder = new Finder<>(User.class);

    public User getUserById(long userId) {
        return finder.byId(userId);
    }

    public User getUserByUsername(String username) {
        return finder.where().eq("username", username).findUnique();
    }
}
