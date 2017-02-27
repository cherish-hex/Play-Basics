package dao;

import models.User;

import java.util.List;

/**
 * Created by cherish.sham on 21/2/2017.
 */
public class UserDAO {

    public List<User> getAllUsers(){

        //return new ArrayList<User>(Arrays.asList(new User("cherish","cherish")));
        return User.find.all();
    }

    public User getUser(String name) {

        return User.find.where().eq("name",name).findUnique();
    }

    public String addUser(User user){
        User.db().save(user);
        return "success";
    }

    public String updateUser(User u) {
        User user = User.find.where().eq("id",u.getId()).findUnique();
        user.setName(u.getName());
        user.setPassword(u.getPassword());
        user.update();
        return "success";
    }

    public String deleteUser(Long id) {
        User user = User.find.where().eq("id",id).findUnique();
        if(user != null) {
            user.delete();
            return "Success";
        }
        else return "failure";

    }
}
