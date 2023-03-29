package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;
import static jm.task.core.jdbc.util.Util.getSession;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = getSession();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT exists" +
                                      " users (id INT PRIMARY KEY  AUTO_INCREMENT ," +
                                      "name TEXT, lastname TEXT,age INT)").executeUpdate();
            System.out.println("Таблица users создана");
            session.getTransaction().commit();
        }


    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = getSession();
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users ").executeUpdate();
            System.out.println("Таблица users удалена");
            session.getTransaction().commit();

        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = getSession();
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);
            System.out.println("сохранен пользователь " +user) ;
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = getSession();
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.remove(user);
            System.out.println("удален пользователь c номером id  "+id    );
            session.getTransaction().commit();
        }


    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList;
        SessionFactory sessionFactory = getSession();
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            userList = session.createQuery("FROM User ", User.class).list();


        }
        System.out.println("Cписок пользователей");
        userList.stream().forEach( System.out::println);

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = getSession();
        try (sessionFactory){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            session.createQuery("DELETE from User ").executeUpdate();
            System.out.println("Таблица очищена");
            session.getTransaction().commit();

        }


    }
}
