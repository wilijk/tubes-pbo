package tubes.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    private DatabaseConnection() {
        // Private constructor (Singleton Pattern)
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Register driver PostgreSQL
                Class.forName("org.postgresql.Driver");

                String url = AppConfig.getDbUrl();
                String user = AppConfig.getDbUser();
                String password = AppConfig.getDbPassword();

                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver PostgreSQL tidak ditemukan!", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}