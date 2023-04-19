package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Util {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/myDbTest";
     private static final String DB_USERNAME = "root";
     private static final String DB_PASSWORD = "root";
     public static Connection connection;
     public static Statement statement;



}
