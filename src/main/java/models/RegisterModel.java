package models;

import java.time.LocalDateTime;

public class RegisterModel {
	private String fname;
	private String lname;
	private String username;
	private String birthday;
	private String gender;
	private String email;
	private String phonenumber;
	private String password2;
	private boolean is_admin;
	private LocalDateTime User_create_date;
	private LocalDateTime User_accound_update;
	public RegisterModel(String fname, String lname, String username, String birthday, String gender, String email,
			String phonenumber, String password2, boolean is_admin, LocalDateTime user_create_date,
			LocalDateTime user_accound_update) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password2 = password2;
		this.is_admin = is_admin;
		User_create_date = user_create_date;
		User_accound_update = user_accound_update;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public boolean isIs_admin() {
		return is_admin;
	}
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}
	public LocalDateTime getUser_create_date() {
		return User_create_date;
	}
	public void setUser_create_date(LocalDateTime user_create_date) {
		User_create_date = user_create_date;
	}
	public LocalDateTime getUser_accound_update() {
		return User_accound_update;
	}
	public void setUser_accound_update(LocalDateTime user_accound_update) {
		User_accound_update = user_accound_update;
	}
}
