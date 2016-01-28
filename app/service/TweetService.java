package service;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Model.Finder;
import models.Tweet;
import models.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TweetService {
    final private UserService userService = new UserService();
    final private static Finder<Long, Tweet> tweetFinder = new Finder<>(Tweet.class);

    final private static int TIMELINE_TWEETS = 20;

    public void tweet(long userId, String content) {
        final User author = userService.getUserById(userId);

        Tweet tweet = new Tweet();
        tweet.author = author;
        tweet.content = content;
        tweet.save();
    }

    public List<Tweet> getTimeline(long userId) {
        final User user = userService.getUserById(userId);

        final Set<Long> followingIds = user.following.stream()
                .map(u -> u.followee.userId)
                .collect(Collectors.toSet());

        final List<Tweet> tweets = tweetFinder.where()
                .disjunction()
                    .add(Expr.eq("author.userId", userId))
                    .add(Expr.in("author.userId", followingIds))
                .setMaxRows(TIMELINE_TWEETS)
                .orderBy("createdAt DESC")
                .findList();

        return tweets;
    }

}
