package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/myDbTest";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
