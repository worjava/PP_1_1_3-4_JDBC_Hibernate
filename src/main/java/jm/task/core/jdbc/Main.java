package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {


        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.dropUsersTable();//
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Михаил Калашников", "оруженый конструктор", (byte) 27);
        userDaoHibernate.removeUserById(1);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.saveUser("Михаил Калашников", "оруженый конструктор", (byte) 27);


//userService.saveUser("Менделев", "Таблица", (byte) 35);
//userService.saveUser("Билл Гейтс", "основатель Microsoft", (byte) 20);
//userService.saveUser("Михаил Калашников", "оруженый конструктор", (byte) 27);
//userService.saveUser("Стив Джобс", "основатель Apple", (byte) 21);
//
//userService.getAllUsers();


    }


}



