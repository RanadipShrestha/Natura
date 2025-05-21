package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
import models.LoginModel;
import utility.EncryptDecrypt;

public class LoginModelDAO {
    private Connection connection;

    public LoginModelDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Main method to get the user and their is_admin status
    public LoginModel getUser(String username, String password) throws SQLException {
        LoginModel user = null;

        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
        
        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
        
        String encryptedPassword = encryptDecrypt.encrypt(password);

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, encryptedPassword);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	int userID = rs.getInt("user_id");
                boolean isAdmin = rs.getBoolean("is_admin");
                user = new LoginModel(userID, username, password, isAdmin);
            }
            rs.close();
        }

        return user;
    }

    // Check if username exists
    public boolean usernameExists(String username) throws SQLException {
        String sql = "SELECT * FROM User WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}
