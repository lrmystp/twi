package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tweet extends Model {
    @Id
    public Long id;

    @Constraints.Required
    @OneToOne(mappedBy="User")
    public Long userId;

    @Constraints.Required
    public String content;

    @Constraints.Required
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date createdAt;

    public static Finder<Long, Tweet> find = new Finder<>(Tweet.class);
}
