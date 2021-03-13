package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.Arrays;

public class Main {
    private static UserServiceImpl userService = new UserServiceImpl();
//    private static UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
/*
    create table usertable or check of exists
 */
        userService.createUsersTable();

/*
    insert row in usertable
 */
        userService.saveUser("Lizz", "von Neiman", (byte) 31);
        userService.saveUser("Boss", "von Neiman", (byte) 13);
        userService.saveUser("Ross", "von Neiman", (byte) 38);
        userService.saveUser("Doss", "von Neiman", (byte) 55);


/*
    get all Users from table usertable
 */
        userService.getAllUsers();

/*
    delete user on id from usertable
 */
        userService.removeUserById(11);

/*
    delete users from usertable
 */
        userService.cleanUsersTable();

/*
    delete usertable from userbase
 */
        userService.dropUsersTable();

    }
}
