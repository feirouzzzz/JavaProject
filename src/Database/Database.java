package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/javaproject";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private Connection connection;

    public Database() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the MySQL database!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
