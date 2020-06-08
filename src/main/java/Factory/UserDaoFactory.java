package Factory;

import dao.UserDAO;
import dao.UserHibernateDAOImpl;
import dao.UserJdbcDAOImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
    public static UserDaoFactory instance = new UserDaoFactory();
    public Properties properties = new Properties();

    private UserDAO getUserDao(String daotype) {
        if (daotype.equalsIgnoreCase("Userhibernatedao")) {
            return new UserHibernateDAOImpl();
        } else if (daotype.equalsIgnoreCase("Userjdbcdao")) {
            return new UserJdbcDAOImpl();
        }
        return null;
    }

    public UserDAO getUserDao() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
            return getUserDao(properties.getProperty("daotype"));
        } catch (IOException e) {
        }
        return null;
    }
}
