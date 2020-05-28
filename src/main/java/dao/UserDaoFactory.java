package dao;

public class UserDaoFactory {
    public static UserDAO getUserDao(){
        return new UserJdbcDAOImpl();
    }

}
