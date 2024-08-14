package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age INT NOT NULL)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE)) {
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() throws SQLException {
        String DROP_TABLE = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE)) {
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String INSERT_USER = "INSERT INTO users(name, lastName, age) VALUES (?,?,?);";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) throws SQLException {
        String REMOVE_USER = "DELETE FROM users WHERE id = ?;";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() throws SQLException {
        String GET_ALL_USERS="SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
            connection.setAutoCommit(false);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getNString("name");
                String lastName = rs.getNString("lastName");
                byte age = rs.getByte("age");

                users.add(new User(name, lastName, age));
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        String DELETE_USERS = "DELETE FROM Users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS)) {
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
