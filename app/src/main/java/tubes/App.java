package tubes;

import tubes.config.Database;

import java.sql.Connection;
import java.sql.SQLException;


public class App {
    public static void main(String[] args) {

        try (Connection conn = Database.connect()) {
            
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Berhasil terhubung ke PostgreSQL!");
            } else {
                System.out.println("❌ Koneksi gagal (conn bernilai null).");
            }

        } catch (SQLException e) {
            System.out.println("❌ Terjadi error SQL saat koneksi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}