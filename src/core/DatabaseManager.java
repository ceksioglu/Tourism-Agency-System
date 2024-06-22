package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class that manages the database connection for the application.
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private static final String URL = "jdbc:postgresql://localhost:5432/tourism_agency";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    /**
     * Private constructor to prevent instantiation from other classes.
     */
    private DatabaseManager() {}

    /**
     * Returns the single instance of the DatabaseManager.
     *
     * @return The single instance of DatabaseManager.
     */
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /**
     * Returns a connection to the database.
     *
     * @return A Connection object to the database.
     * @throws SQLException If a database access error occurs or the url is null.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
