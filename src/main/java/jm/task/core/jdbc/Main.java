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


        UserServiceImpl userService = new UserServiceImpl();
////
////        userService.createUsersTable();
//
////        userService.saveUser("Менделев", "Таблица", (byte) 35);
////        userService.saveUser("Билл Гейтс", "основатель Microsoft", (byte) 20);
////        userService.saveUser("Михаил Калашников", "оруженый конструктор", (byte) 27);
////        userService.saveUser("Стив Джобс", "основатель Apple", (byte) 21);
//
//        userService.removeUserById(3);


        System.out.println(userService.getAllUsers());


    }


}



