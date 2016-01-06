package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
public class Tweet extends Model {
    @Id
    public Long tweetId;

    @ManyToOne
    @JoinColumn(name = "userId")
    public User author;

    public String content;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    public Date createdAt;

    public static Finder<Long, Tweet> find = new Finder<>(Tweet.class);
}
