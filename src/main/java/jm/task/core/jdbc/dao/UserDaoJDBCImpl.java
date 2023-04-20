package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS myDbTest.users (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NULL, " +
                "lastName VARCHAR(45) NULL, " +
                "age INT NULL, " +
                "UNIQUE INDEX id_UNIQUE (id ASC));";


        try (Statement statement = Util.connection.createStatement()) {
            statement.execute(createTable);
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS myDbTest.users;";
        try (Statement statement = Util.connection.createStatement()) {
            statement.execute(dropTable);
            System.out.println("Table dropped.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO myDbTest.users (name, lastName, age) VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = Util.connection.prepareStatement(saveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем %s добавлен в базу данных \n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        String getAll = "SELECT * FROM myDbTest.users;";
        List<User> userList = new ArrayList();
        try (Statement statement = Util.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()) {
                Long id = (long) resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = (byte) resultSet.getInt("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String cleanTable = "DELETE FROM myDbTest.users;";
        try (Statement statement = Util.connection.createStatement()) {
            statement.execute(cleanTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
