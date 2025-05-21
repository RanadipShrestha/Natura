package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import database.DatabaseConnection;
import models.RegisterModel;
import utility.EncryptDecrypt;

public class RegisterModelDAO {
    private Connection con;

    // Constructor to establish database connection
    public RegisterModelDAO() {
        try {
            this.con = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

 // For checking if username exists for a new user (no userId needed)
    public boolean isUserNameExists(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM User WHERE username = ?"; // Checking if username already exists

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username); // Set the username parameter
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, username exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if username does not exist
    }

    // For checking if username exists excluding the current user (for updates)
    public boolean isUserNameExist(String username, int userId) throws SQLException {
        String query = "SELECT COUNT(*) FROM User WHERE username = ? AND user_id != ?"; // Exclude current user during update

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username); // Set the username parameter
            ps.setInt(2, userId); // Set the userId parameter
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, username exists for another user
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if username does not exist or is the current user's username
    }



    // Method to add registration details to the database
    public boolean addRegistrationDetail(RegisterModel register) throws SQLException {
        boolean hasUpdated = false;
        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
        String encryptPassword = encryptDecrypt.encrypt(register.getPassword2()); // Encrypt password

        if (con == null) {
            System.out.println("Nothing is being inserted into the database");
            return hasUpdated;
        }

        String query = "INSERT INTO User(first_name, last_name, username, dob, gender, email, phone_number, password) VALUES (?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, register.getFname()); // Set first name
            ps.setString(2, register.getLname()); // Set last name
            ps.setString(3, register.getUsername()); // Set username
            ps.setString(4, register.getBirthday()); // Set date of birth
            ps.setString(5, register.getGender()); // Set gender
            ps.setString(6, register.getEmail()); // Set email
            ps.setString(7, register.getPhonenumber()); // Set phone number
            ps.setString(8, encryptPassword); // Set encrypted password

            hasUpdated = ps.executeUpdate() > 0; // Execute the insert query and check if any rows are updated
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hasUpdated; // Return true if registration is successful, otherwise false
    }
    
    public RegisterModel getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM user WHERE user_id = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Integer id = rs.getInt("user_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String username = rs.getString("username");
                String birthday = rs.getString("dob"); // Assuming it's stored as a string
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phonenumber = rs.getString("phone_number");
                boolean is_admin = rs.getBoolean("is_admin");
                LocalDateTime createdAt = rs.getTimestamp("User_create_date").toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp("User_account_update").toLocalDateTime();

                return new RegisterModel(
                    id,
                    fname,
                    lname,
                    username,
                    birthday,
                    gender,
                    email,
                    phonenumber,
                    null, // password2 is set to null for security
                    is_admin,
                    createdAt,
                    updatedAt
                );
            }
        }
        return null;
    }
    
    public boolean updateUserProfile(int user_id, String fname, String lname, String birthday, String gender, String email, String phonenumber) throws SQLException {
        String query = "UPDATE user SET first_name = ?, last_name = ?, dob = ?, gender = ?, email = ?, phone_number = ?, User_account_update = NOW() WHERE user_id = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, fname);         // first_name
            stmt.setString(2, lname);         // last_name
            stmt.setString(3, birthday);      // dob
            stmt.setString(4, gender);        // gender
            stmt.setString(5, email);         // email
            stmt.setString(6, phonenumber);   // phone_number
            stmt.setInt(7, user_id);          // user_id (WHERE clause)

            return stmt.executeUpdate() > 0;
        }
    }
}
