package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.EncryptAndDecrypt;
import database.DatabaseConnection;
import models.RegisterModel;

public class RegisterModelDAO {
	private Connection con;
	
	public RegisterModelDAO() {
		try {
			this.con = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean addRegistrationDetail(RegisterModel register) throws SQLException {
		boolean hasUpdated = false;
		EncryptAndDecrypt encryptDecrypt = new EncryptAndDecrypt();
		String encryptPassword = encryptDecrypt.encrypt(register.getPassword2());
		if(con == null) {
			System.out.println("Nothing is being inserted in the database");
			return hasUpdated;
		}
		String query = "Insert into register(first_name,last_name,username,dob,gender,email,phone_number,password) VALUES (?,?,?,?,?,?,?,?)";
		try(PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, register.getFname());
			ps.setString(2, register.getLname());
			ps.setString(3, register.getUsername());
			ps.setString(4, register.getBirthday());
			ps.setString(5,register.getGender());
			ps.setString(6, register.getEmail());
			ps.setString(7,register.getPhonenumber());
			ps.setString(8, encryptPassword);
			
			hasUpdated = ps.executeUpdate() > 0;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return hasUpdated;
	}
}
