package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tweet extends Model {
    @Id
    public Long tweetId;

    @ManyToOne
    @JoinColumn(name = "author")
    public User author;

    @Column(nullable = false)
    public String content;

    @Column(nullable = false)
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    public Date createdAt = new Date();

    public static Finder<Long, Tweet> find = new Finder<>(Tweet.class);
}
