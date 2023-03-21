package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {


    private final String PASSWORD = "root";
    private final String UserName = "root";

    private final String URL = "jdbc:mysql://localhost:3306/game";

    private final String DRIVER = "com.mysql.cj.jdbc.Driver"; //com.mysql.cj.jdbc.Driver;

    public  Connection getConnection() {
        try {
            Class.forName(DRIVER);
            System.out.println("Connection OK");
            return  DriverManager.getConnection(URL, UserName, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }
    }


}



// реализуйте настройку соеденения с БД



