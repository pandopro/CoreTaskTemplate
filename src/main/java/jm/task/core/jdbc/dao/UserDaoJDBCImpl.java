package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USER(id int not null auto_increment, name varchar(10) not null, lastname varchar(20) not null, age int not null,  PRIMARY KEY (ID))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS USER");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMySQLConnection()) {
            String sql = "INSERT INTO USER (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMySQLConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM USER WHERE ID = ?");
            statement.setInt(1, (int) id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        try (Connection connection = Util.getMySQLConnection()) {

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM user";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                userList.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
//                System.out.println("--------------------");
//                System.out.println("id: " + rs.getString(1));
//                System.out.println("name: " + rs.getString(2));
//                System.out.println("lastname: " + rs.getString(3));
//                System.out.println("age: " + rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE USER");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
