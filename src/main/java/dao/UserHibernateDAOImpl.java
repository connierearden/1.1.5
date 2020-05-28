package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAOImpl implements UserDAO {
    private static SessionFactory sessionFactory;

    public Session getSession() {
        if (sessionFactory==null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory.openSession();
    }

    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "12345");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }
    private SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    @Override
    public List<User> getAllUsers() {
        Session session = getSession();
        List<User> list = session.createQuery("from User").list();
        session.close();
        return list;
    }

    @Override
    public void addUser(User user) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteUser(Long id) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
            Query query = session.createQuery("delete from User u where where u.id = :id");
            query.setParameter("id", id);
            tx.commit();
        session.close();
    }

    @Override
    public User getUserById(long id) {
        Session session = getSession();
        return session.load(User.class,id);
    }

    @Override
    public void updateUser(User user) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        //make with map
        Query query = session.createQuery("update User u set u.name = :name where u.id = :id");
        query.setParameter("name", user.getName());
        query.setParameter("id", user.getId());
        query.executeUpdate();
        tx.commit();
    }
}
