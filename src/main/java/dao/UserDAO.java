package dao;

import models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;
    void addUser(User user) throws SQLException;
    void deleteUser(String userName) throws SQLException;
    User getUserById(long id) throws SQLException;
    void updateUser(User user) throws SQLException;
}
