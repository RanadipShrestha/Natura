package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private final static String databaseName = "natura";
	private final static String username = "root";
	private final static String password = "";
	private final static String jdbcUrl = "jdbc:mysql://localhost:3306/" + databaseName;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(jdbcUrl,username,password);
		if(con == null) {
			System.out.println("Failed to Connect to Database");
		}else {
			System.out.println("Successfully Database Connected");
		}
		return con;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
	}
}
