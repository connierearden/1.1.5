package dao;

import models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;

    void addUser(User user) throws SQLException;

    void deleteUser(Long id) throws SQLException;

    User getUserById(long id) throws SQLException;

    void updateUser(Long id, String name, int age, String password, String role) throws SQLException;

    User getUserByLogin(String name, String password);
}
