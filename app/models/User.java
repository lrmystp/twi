package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class User extends Model {
    @Id
    public Long userId;

    public String username;

    public String passwordHash;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date createdAt;

    @OneToMany(mappedBy = "author")
    public List<Tweet> tweets;

    @OneToMany(mappedBy = "follower")
    public List<Follow> following;

    @OneToMany(mappedBy = "followee")
    public List<Follow> followers;

    public static Finder<Long, User> find = new Finder<>(User.class);
}
