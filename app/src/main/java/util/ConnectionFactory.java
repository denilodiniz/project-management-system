package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/project-management-system?characterEncoding=utf8";
    public static final String USER = "root";
    public static final String PASS = "123456789";
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }
        catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        }
    }
    
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Database error when closing connection", e);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Database error when closing connection", e);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet result) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Database error when closing connection", e);
        }
    }
  
}
