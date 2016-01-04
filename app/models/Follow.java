package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Follow extends Model {
    @Id
    public Long id;

    @Constraints.Required
    @OneToOne(mappedBy="User")
    public Long follower;

    @Constraints.Required
    @OneToOne(mappedBy="User")
    public Long followee;

    @Constraints.Required
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date createdAt;

    public static Finder<Long, Follow> find = new Finder<>(Follow.class);
}
