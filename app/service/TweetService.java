package service;

import models.Tweet;
import models.User;

public class TweetService {
    final private UserService userService = new UserService();

    public void tweet(Long userId, String content) {
        final User author = userService.getUserById(userId);

        Tweet tweet = new Tweet();
        tweet.author = author;
        tweet.content = content;
        tweet.save();
    }
}
