package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {


        UserDao userDao = new UserDaoJDBCImpl();



        userDao.saveUser("Менделев", "Таблица", (byte) 35);
        userDao.saveUser("Билл Гейтс", "основатель Microsoft", (byte) 20);
        userDao.saveUser("Михаил Калашников", "оруженый конструктор", (byte) 27);
        userDao.saveUser("Стив Джобс", "основатель Apple", (byte) 21);

      userDao.removeUserById(1);






    }


}



