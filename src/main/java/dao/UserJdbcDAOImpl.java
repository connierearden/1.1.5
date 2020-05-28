package dao;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAOImpl implements UserDAO {
    private Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("users?").          //db name
                    append("user=root&").          //login
                    append("password=12345&").     //password
                    append("serverTimezone=UTC");       //timezone


            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public List<User> getAllUsers () throws SQLException {
        Statement stmt = getMysqlConnection().createStatement();
        ResultSet result = stmt.executeQuery("select * from user");
        List<User> list = new ArrayList<>();
        while (result.next()) {
            list.add(new User(result.getLong(1), result.getString(2), result.getInt(3), result.getString(4)));
        }
        result.close();
        stmt.close();

        return list;
    }
    public void addUser (User user) throws SQLException {
        PreparedStatement stmt = getMysqlConnection().prepareStatement("insert into user values (?,?,?,?)");
        stmt.setLong(1, user.getId());
        stmt.setString(2, user.getName());
        stmt.setInt(3, user.getAge());
        stmt.setString(4, user.getPassword());
        stmt.executeUpdate();
        stmt.close();
    }
    public void deleteUser(Long id) throws SQLException {
        Statement stmt = getMysqlConnection().createStatement();
        stmt.executeUpdate("delete from user where id = '"+ id+"'");
        stmt.close();
    }

    public User getUserById (long id) throws SQLException {
        Statement stmt = getMysqlConnection().createStatement();
        ResultSet result = stmt.executeQuery("select * from user where id = '"+ id+"'");
        result.next();
        User user = new User (result.getLong(1), result.getString("name"), result.getInt("age"), result.getString("password"));
        result.close();stmt.close();
        return user;
    }
    public void updateUser (Long id, String name, int age, String password) throws SQLException {
        PreparedStatement stmt = getMysqlConnection().prepareStatement("update user u set  u.name = ?, u.age = ?, u.password = ?  where u.id = ?");
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setString(3, password);
        stmt.setLong(4, id);
        stmt.executeUpdate();
        stmt.close();

    }

}
