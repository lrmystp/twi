package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User extends Model {
    @Id
    public Long userId;

    @Column(unique = true, nullable = false)
    public String username;

    @Column(nullable = false)
    public String passwordHash;

    @Column(nullable = false)
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date createdAt = new Date();

    @OneToMany(mappedBy = "author")
    public List<Tweet> tweets;

    @OneToMany(mappedBy = "follower")
    public List<Follow> following;

    @OneToMany(mappedBy = "followee")
    public List<Follow> followers;

    public static Finder<Long, User> find = new Finder<>(User.class);
}
