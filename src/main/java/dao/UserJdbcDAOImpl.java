package dao;

import models.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserJdbcDAOImpl implements UserDAO {
    private static Connection connection = DBHelper.INSTANCE.getConnection();

    @Override
    public List<User> getAllUsers() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("select * from user");
        List<User> list = new ArrayList<>();
        while (result.next()) {
            list.add(new User(
                    result.getLong(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getString(4),
                    result.getString(5)));

        }
        result.close();
        stmt.close();

        return list;
    }

    @Override
    public void addUser(User user) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("insert into user values (?,?,?,?,?)");
        stmt.setLong(1, user.getId());
        stmt.setString(2, user.getName());
        stmt.setInt(3, user.getAge());
        stmt.setString(4, user.getPassword());
        stmt.setString(5, user.getRole());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("delete from user where id = '" + id + "'");
        stmt.close();
    }

    @Override
    public User getUserById(long id) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("select * from user where id = '" + id + "'");
        result.next();
        User user = new User(
                result.getLong(1),
                result.getString(2),
                result.getInt(3),
                result.getString(4),
                result.getString(5));
        result.close();
        stmt.close();
        return user;
    }

    @Override
    public void updateUser(Long id, String name, int age, String password, String role) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("update user u set  u.name = ?, u.age = ?, u.password = ?, u.role = ?  where u.id = ?");
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setString(3, password);
        stmt.setLong(5, id);
        stmt.setString(4, role);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public User getUserByLogin(String name, String password) {
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from user u where u.name = ? and u.password = ?");
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            result.next();
            User user = new User(
                    result.getLong(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getString(4),
                    result.getString(5)
            );
            result.close();
            stmt.close();
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
