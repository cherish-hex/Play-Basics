package services;

import dao.UserDAO;
import models.User;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by cherish.sham on 21/2/2017.
 */
public class UserService {

    private final UserDAO dao;

    @Inject
    public UserService(final UserDAO userDAO) {
        this.dao = userDAO;
    }

    public List<User> getUserDetails() {
        return dao.getAllUsers();
    }

    public User getUser(String name) {
        return dao.getUser(name);
    }

    public String AddUser(User u){
        return dao.addUser(u);
    }

    public String UpdateUser(User u) {
        return dao.updateUser(u);
    }

    public String deleteUser(Long id) {
        return dao.deleteUser(id);
    }
}
