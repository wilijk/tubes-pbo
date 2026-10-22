package tubes;

import tubes.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("Koneksi ke Supabase PostgreSQL berhasil!");
            }
        } catch (SQLException e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
}