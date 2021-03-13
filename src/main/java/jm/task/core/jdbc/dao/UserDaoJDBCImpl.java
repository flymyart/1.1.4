package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private  long id;
    private String name;
    private String lastName;
    private byte age;
    private String CREATE = "CREATE TABLE usertable (\n" +
            "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
            "    name VARCHAR(30),\n" +
            "    lastname VARCHAR(30),\n" +
            "    age INT\n" +
            ")";
    private String DROP = "DROP TABLE usertable";
    private String DELETE = "DELETE FROM usertable where id=" + id;
    private String QUERY = "SELECT * FROM usertable";
    private String CLEAN = "DELETE FROM usertable";
    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        util.getConnection();

        try(Statement statement = util.getConnection().createStatement()) {
            try {
                if(statement.execute(QUERY)) {
                    System.out.println("Table already exists");
                }
            } catch (Exception e) {
                statement.execute(CREATE);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        util.getConnection();

        try(Statement statement = util.getConnection().createStatement()) {
            try {
                statement.execute(DROP);
            } catch (Exception e) {
                System.out.println("Table is not exists");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        util.getConnection();

        try(Statement statement = util.getConnection().createStatement()) {
            statement.execute("INSERT INTO usertable (name, lastname, age) VALUES ('" +
                    name + "', '" + lastName + "', " + age + ")");
            System.out.println("User с именем " + name + " успешно добавлен");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        util.getConnection();

        try(Statement statement = util.getConnection().createStatement()) {
            statement.execute(DELETE);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        util.getConnection();
        List<User> users = new ArrayList<>();

        try(Statement statement = util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERY);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                System.out.println(user);
                users.add(user);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        util.getConnection();

        try(Statement statement = util.getConnection().createStatement()) {
            statement.execute(CLEAN);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
