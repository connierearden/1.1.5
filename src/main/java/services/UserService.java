package services;

import dao.UserDAO;
import dao.UserDaoFactory;
import models.User;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public static final UserService INSTANCE = new UserService();
    private UserService(){
        userDAO = UserDaoFactory.getUserDao();
    }
    public User getUserById (Long id) {
        User user = null;
        try {
            if (id != null) {
                user = userDAO.getUserById(id);
            }
        } catch (SQLException ignored) {
        }
        return user;
    }

    public List<User> getAllUsers(){
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public void updateUser(String id, String name,String age, String password){
        if (name.isEmpty() && password.isEmpty()) {
            return;
        }
        try {
            userDAO.updateUser(
                    new User(
                            Long.parseLong(id),
                            name,
                            Integer.parseInt(age),
                            password
                    )

            );
        } catch (SQLException ignored) {
        }
    }
    public void addUser(User user) {
        try {
            if (user.getName().isEmpty() || user.getPassword().isEmpty()) {
                userDAO.addUser(user);
            }
        } catch (SQLException ignored) {}
    }

    public void deleteUser(String name) {
        try {
            userDAO.deleteUser(name);
        } catch (SQLException ignored) {}
    }
}
