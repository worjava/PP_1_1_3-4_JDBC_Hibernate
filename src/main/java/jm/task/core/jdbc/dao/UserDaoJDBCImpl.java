package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl   implements UserDao  {
    public UserDaoJDBCImpl() {

    }

    Connection connection = getConnection();

    public void createUsersTable() {
        String sql = """
         CREATE TABLE users(id INT PRIMARY KEY  AUTO_INCREMENT,name TEXT, lastname TEXT,age INT)""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Exception в методе createUsersTable " + e);
        }

    }

    public void dropUsersTable() {
        String sql = """
                DROP TABLE IF EXISTS users """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {


        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем- " + name + " добавлен в базу данных");
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String sql = """
                DELETE FROM users WHERE id  =?      """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception в методе remove " + e);
        }


    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = """
                                
                select *from users""";

        try (var statement = connection.createStatement()) {

            try (var resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId((long) resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge((byte) resultSet.getInt("age"));
                    userList.add(user);
                }

//                    int id = Integer.parseInt(resultset.getObject(1).toString());
//                    String name = resultset.getObject(2).toString();
//                    String lastname = resultset.getObject(3).toString();
//                    int age = Integer.parseInt(resultset.getObject(4).toString());
//                    User user = new User((long) id,name,lastname, (byte) age);
//                    userList.add(user);
            }


            return userList;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
    }
    public void cleanUsersTable() {
        String sql = """
                TRUNCATE TABLE users
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL Exception в методе cleanUsersTable " + e);
        }
    }
}