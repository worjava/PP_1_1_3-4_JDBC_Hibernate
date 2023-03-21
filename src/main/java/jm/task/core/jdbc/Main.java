package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

//      userDao.createUsersTable();

        userDao.saveUser("Менделев", "Таблица", (byte) 35);
        userDao.saveUser("Билл Гейтс", "основатель Microsoft", (byte) 20);
        userDao.saveUser("Михаил Калашников", "оруженый конструктор", (byte) 27);
        userDao.saveUser("Стив Джобс", "основатель Apple", (byte) 21);

//            userDao.removeUserById(1);
     //   userDao.getAllUsers();
     userDao.cleanUsersTable();
//
//    userDao.dropUsersTable();

    }


}



