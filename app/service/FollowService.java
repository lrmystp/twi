package service;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Model.Finder;
import models.Follow;
import models.User;

public class FollowService {
    private Finder<Long, Follow> followFinder = new Finder<>(Follow.class);

    public void follow(User follower, User followee) {
        final boolean followExists = 0 != followFinder.where()
                .conjunction()
                    .add(Expr.eq("follower.userId", follower.userId))
                    .add(Expr.eq("followee.userId", followee.userId))
                .findRowCount();

        if (followExists) {
            return;
        }

        final Follow follow = new Follow();
        follow.follower = follower;
        follow.followee = followee;
        follow.save();
    }

    public void unfollow(User follower, User followee) {
        final Follow targetFollow = followFinder.where()
                .conjunction()
                    .add(Expr.eq("follower.userId", follower.userId))
                    .add(Expr.eq("followee.userId", followee.userId))
                .findUnique();

        if (targetFollow == null) {
            return;
        }

        targetFollow.delete();
    }
}
