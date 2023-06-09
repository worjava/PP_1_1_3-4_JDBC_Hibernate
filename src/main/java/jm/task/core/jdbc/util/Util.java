package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {


    private static final String PASSWORD = "root";
    private static final String UserName = "root";

    private static final String URL = "jdbc:mysql://localhost:3306/game";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //com.mysql.cj.jdbc.Driver;


    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(URL, UserName, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/game");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.current_session_context_class", "thread");

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        return sessionFactory;
    }
}


// реализуйте настройку соеденения с БД



