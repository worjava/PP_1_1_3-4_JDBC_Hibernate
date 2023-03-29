package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;


public class UserServiceImpl  implements UserService {
    private UserDao userDao = new UserDaoHibernateImpl();


    public void createUsersTable() {
        System.out.println("Создание таблицы пользователей...");
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        System.out.println("Удаление таблицы пользователей...");
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        System.out.println("Сохранение пользователя...");
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        System.out.println("Удаление пользователя по ID...");
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        System.out.println("Получение всех пользователей...");
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        System.out.println("Очистка таблицы пользователей...");
        userDao.cleanUsersTable();
    }
}
