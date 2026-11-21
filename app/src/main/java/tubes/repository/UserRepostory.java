package tubes.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tubes.config.DatabaseConnection;
import tubes.exception.TransactionException;

public class UserRepostory {
    


    public void registerCustomer(String username, String password, String confirmPassword) throws Exception {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty())
            throw new TransactionException("Username dan Password wajib diisi!");
        
        if (!password.equals(confirmPassword))
            throw new TransactionException("Konfirmasi password tidak cocok!");
        
        String checkSql = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                throw new TransactionException("Username sudah digunakan! Pilih username lain!");
            }
        }
        String insertSql = "INSERT INTO users (username, password, role) VALUES(?, ?, 'CUSTOMER')";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password);
            insertStmt.executeUpdate();
        }

    }
}
