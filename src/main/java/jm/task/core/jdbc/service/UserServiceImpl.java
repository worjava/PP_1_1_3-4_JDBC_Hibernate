package jm.task.core.jdbc.service;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;


public class UserServiceImpl  implements UserService {
 private   UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();


    public void createUsersTable() {

        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {

        userDaoJDBC.dropUsersTable();
    }


    public void saveUser(String name, String lastName, byte age) {


        userDaoJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {

        userDaoJDBC.removeUserById(id);

    }

    public List<User> getAllUsers()  {


        return userDaoJDBC.getAllUsers();


}
        public void cleanUsersTable() {

        userDaoJDBC.cleanUsersTable();

    }


}
