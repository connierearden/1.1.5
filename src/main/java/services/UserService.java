package services;

import Factory.UserDaoFactory;
import dao.UserDAO;
import models.User;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserService {

    public static final UserService INSTANCE = new UserService();
    private UserDAO userDAO;

    private UserService() {
        userDAO = UserDaoFactory.instance.getUserDao();
    }

    public User getUserById(Long id) {
        User user = null;
        try {

            if (id != null) {
                user = userDAO.getUserById(id);
            }
        } catch (SQLException ignored) {
        }
        return user;
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public void updateUser(Long id, String name, int age, String password, String role) {
        if (name.isEmpty() && password.isEmpty()) {
            return;
        }

        try {
            userDAO.updateUser(id, name, age, password, role);
        } catch (SQLException ignored) {
        }
    }

    public void addUser(User user) {
        try {
            if (!user.getName().isEmpty() || !user.getPassword().isEmpty()) {
                userDAO.addUser(user);
            }
        } catch (SQLException ignored) {
        }
    }

    public void deleteUser(Long id) {
        try {
            if (id != null) {
                userDAO.deleteUser(id);
            }
        } catch (SQLException ignored) {
        }
    }

    public User getUserByLogin(String name, String password) {
        return userDAO.getUserByLogin(name, password);
    }

    public boolean isAdmin(User user) {
        if (user != null) {
            return user.getRole().equalsIgnoreCase("admin");
        }
        return false;
    }
}
