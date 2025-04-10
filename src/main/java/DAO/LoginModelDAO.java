package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;

public class LoginModelDAO {
    private Connection connection;

    public LoginModelDAO() {
        try {
			this.connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Method to validate user credentials
    public boolean validateUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM register WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) { //prepareStatement vana ko select queary vaya ra
            stmt.setString(1, username); //stmt variable name
            stmt.setString(2, password);
            
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next(); // Returns true if user exists with matching credentials
        }
    }
}
