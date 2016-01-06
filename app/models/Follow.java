package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
public class Follow extends Model {
    @Id
    public Long followId;

    @ManyToOne
    @JoinColumn(name = "userId")
    User follower;

    @ManyToOne
    @JoinColumn(name = "userId")
    User followee;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    public Date createdAt;

    public static Finder<Long, Follow> find = new Finder<>(Follow.class);
}
