package com.talent_insider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // JDBC URL, username, and password for connecting to MySQL database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/talent_insider";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ansh@123";

    private DatabaseConnection() {
        super();
        // This constructor is intentionally left empty
    }

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
