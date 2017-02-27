package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by cherish.sham on 21/2/2017.
 */

@Entity
public class User extends Model {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String password;

    public User(String name,String password){
        this.name = name;
        this.password = password;
    }
    public static Finder<Long, User> find = new Finder<Long,User>(User.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
