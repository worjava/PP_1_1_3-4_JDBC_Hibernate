package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.Connection;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSession;

public class UserDaoHibernateImpl implements UserDao {


public UserDaoHibernateImpl() {
}


@Override
public void createUsersTable() {
   Session session = getSession();
    try {
        session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT exists" +
                                  " users (id INT PRIMARY KEY  AUTO_INCREMENT ," +
                                  "name TEXT, lastname TEXT,age INT)").executeUpdate();
        session.getTransaction().commit();

    } finally {
        session.close();
    }

}

@Override
public void dropUsersTable() {
    Session session = getSession();
    try {
        session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS users ").executeUpdate();
        session.getTransaction().commit();
    } finally {
        session.close();
    }


}

@Override
public void saveUser(String name, String lastName, byte age) {
    Session session = getSession();
    try {
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
    } finally {
        session.close();
    }
}

@Override
public void removeUserById(long id) {
    Session session = getSession();
    try {
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.remove(user);
        session.getTransaction().commit();
    } finally {
        session.close();
    }


}

@Override
public List<User> getAllUsers() {
    Session session = getSession();
    List<User> userList;
    try {
        session.beginTransaction();
        userList = session.createQuery("FROM User ", User.class).list();

    } finally {
        session.close();
    }
    userList.stream().forEach(System.out::println);

    return userList;
}

@Override
public void cleanUsersTable() {
    Session session = getSession();
    try {
        session.beginTransaction();
        session.createQuery("DELETE from User ").executeUpdate();
        session.getTransaction().commit();
    } finally {
        session.close();
    }


}
}
