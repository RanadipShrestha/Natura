package models;

public class LoginModel {
	private int user_id;
	private String username;
	private String password;
	private boolean isAdmin;
	public LoginModel(int user_id, String username, String password, boolean isAdmin) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
