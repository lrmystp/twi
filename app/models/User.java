package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String username;

    @Constraints.Required
    public String passwordHash;

    @Constraints.Required
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date createdAt;

    public static Finder<Long, User> find = new Finder<>(User.class);
}
