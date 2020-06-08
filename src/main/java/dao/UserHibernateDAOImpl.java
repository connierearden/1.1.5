package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import javax.persistence.Query;
import java.util.List;

public class UserHibernateDAOImpl implements UserDAO {
    private static SessionFactory sessionFactory;

    public Session getSession() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory.openSession();
    }


    private SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.INSTANCE.getConfiguration();
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
        /*Query query = session.createQuery("delete from User u where u.id = :id");
        query.setParameter("id", id);*/
        session.delete(session.get(User.class, id));
        tx.commit();
        session.close();
    }

    @Override
    public User getUserById(long id) {
        Session session = getSession();
        return session.load(User.class, id);
    }

    @Override
    public void updateUser(Long id, String name, int age, String password, String role) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        //make with map
        Query query = session.createQuery("update User u set  u.name = :name, u.age = :age, u.password = :password, u.role = :role  where u.id = :id");
        query.setParameter("name", name);
        query.setParameter("age", age);
        query.setParameter("password", password);
        query.setParameter("role", role);
        query.setParameter("id", id);
        query.executeUpdate();
        tx.commit();
    }

    public User getUserByLogin(String name, String password) {
        try {
            Session session = getSession();
            Query query = session.createQuery("from User u where u.name = :name and u.password = :pass");
            query.setParameter("name", name);
            query.setParameter("pass", password);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
