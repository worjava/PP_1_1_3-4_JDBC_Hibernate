package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.H2Dialect;

import java.sql.Connection;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT exists" +
                                      " users (id INT PRIMARY KEY  AUTO_INCREMENT ," +
                                      "name TEXT, lastname TEXT,age INT)").executeUpdate();

            session.getTransaction().commit();
        }


    }

    @Override
    public void dropUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users ").executeUpdate();

            session.getTransaction().commit();

        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            session.getTransaction().commit();
        }


    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList;

        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            userList = session.createQuery("FROM User ", User.class).list();


        }

        userList.stream().forEach(System.out::println);

        return userList;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            session.createQuery("DELETE from User ").executeUpdate();

            session.getTransaction().commit();

        }


    }
}
