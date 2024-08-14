package jm.task.core.jdbc.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String dbURL= "jdbc:mysql://localhost:3306/mytest";
    private static final String dbUsername = "root";
    private static final String dbPassword = "89991554251";

    public static Connection getConnection(){
        Connection connection;

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
