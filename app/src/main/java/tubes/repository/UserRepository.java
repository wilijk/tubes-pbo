package tubes.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tubes.config.DatabaseConnection;
import tubes.exception.TransactionException;
import tubes.model.User;
import tubes.model.enumm.Role;

public class UserRepository {

    public void registerCustomer(String username, String password, String confirmPassword) throws Exception {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty())
            throw new TransactionException("Username dan Password wajib diisi!");

        if (!password.equals(confirmPassword))
            throw new TransactionException("Konfirmasi password tidak cocok!");

        String checkSql = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                throw new TransactionException("Username sudah digunakan! Pilih username lain!");
            }
        }
        String insertSql = "INSERT INTO users (username, password, role) VALUES(?, ?, 'CUSTOMER')";
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password);
            insertStmt.executeUpdate();
        }
    }

    public User login(String username, String password) throws Exception {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")));
            } else {
                throw new TransactionException("Username atau password salah!");
            }
        }
    }
}