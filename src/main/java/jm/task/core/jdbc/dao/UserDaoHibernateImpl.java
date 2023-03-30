package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.dialect.H2Dialect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT exists" +
                                      " users (id INT PRIMARY KEY  AUTO_INCREMENT ," +
                                      "name TEXT, lastname TEXT,age INT)").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();

            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            try {
                User user = new User(name, lastName, age);
                session.save(user);
                transaction.commit();
            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                    ex.printStackTrace();
                }
            }
        }
    }


    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
                ex.printStackTrace();
            }

        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> userList = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            userList = session.createQuery("FROM User", User.class).list();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
                ex.printStackTrace();
            }
        }
        return userList;
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            session.createQuery("DELETE from User ").executeUpdate();

            session.getTransaction().commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
                ex.printStackTrace();


            }
        }
    }
}
