package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Follow extends Model {
    @Id
    public Long followId;

    @ManyToOne
    @JoinColumn(name = "follower")
    public User follower;

    @ManyToOne
    @JoinColumn(name = "followee")
    public User followee;

    @Column(nullable = false)
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    public Date createdAt;

    public static Finder<Long, Follow> find = new Finder<>(Follow.class);
}
