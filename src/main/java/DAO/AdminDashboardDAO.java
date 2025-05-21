package DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import models.IndexModel;
import models.ProductModel;
import models.RegisterModel;
import models.ReportItemModel;
import utility.EncryptDecrypt;

public class AdminDashboardDAO {
    private Connection con;

    public AdminDashboardDAO() {
        try {
            this.con = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Get total number of users
    public int getTotalUsers() throws SQLException {
        int totalUsers = 0;
        String query = "SELECT COUNT(*) AS total FROM user"; // Table name = user

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                totalUsers = rs.getInt("total");
            }
        }
        return totalUsers;
    }

    // Get all users
    public ArrayList<RegisterModel> getAllUsers() throws SQLException {
        ArrayList<RegisterModel> userList = new ArrayList<>();
        String query = "SELECT * FROM user";

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	Integer userId = rs.getInt("user_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String username = rs.getString("username");
                String birthday = rs.getString("dob");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phonenumber = rs.getString("phone_number");
                String password2 = rs.getString("password");
                boolean is_admin = rs.getBoolean("is_admin");
                LocalDateTime user_create_date = rs.getTimestamp("User_create_date").toLocalDateTime();
                LocalDateTime user_account_update = rs.getTimestamp("User_account_update").toLocalDateTime();

                RegisterModel user = new RegisterModel(userId,fname, lname, username, birthday, gender, email, phonenumber,
                        password2, is_admin, user_create_date, user_account_update);
                userList.add(user);
            }
        }
        return userList;
    }

    public boolean updateUserDetails(int userId, String fname, String lname, String username, String dob,
            String gender, String email, String phone, int isAdmin) throws SQLException {
				String sql = "UPDATE user SET first_name=?, last_name=?, username=?, dob=?, gender=?, email=?, phone_number=?, is_admin=?, User_account_update=NOW() WHERE user_id=?";
				try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, fname);
				stmt.setString(2, lname);
				stmt.setString(3, username);
				stmt.setString(4, dob);
				stmt.setString(5, gender);
				stmt.setString(6, email);
				stmt.setString(7, phone);
				stmt.setInt(8, isAdmin);
				stmt.setInt(9, userId);
				return stmt.executeUpdate() > 0;
				}
			}

    public boolean deleteUser(int user_id) {
    	boolean deleteSuccessfully = false;
    	if (con == null) {
    		System.out.print("Trun on the database");
    		return deleteSuccessfully;
    	}
    	String query = "Delete from user where user_id = ?";
    	try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, user_id);
            deleteSuccessfully = stmt.executeUpdate() > 0;
            

    	}catch(SQLException e) {
            e.printStackTrace();
        }
		return deleteSuccessfully;
    }
    
    public boolean deleteCategory(String categoryID) throws SQLException {
        String sql = "DELETE FROM category WHERE category_id = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, categoryID);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    public IndexModel getCategoryById(int categoryId) {
        String selectQuery = "SELECT * FROM category WHERE category_id = ?";
        
        try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, categoryId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int retrievedCategoryId = resultSet.getInt("category_id");
                    String retrievedCategoryName = resultSet.getString("categoryName");
                    String retrievedImagePath = resultSet.getString("categoryImage");
                    String retrievedDate = resultSet.getString("date");
                    
                    return new IndexModel(
                        retrievedCategoryId,
                        retrievedCategoryName,
                        retrievedImagePath,
                        retrievedDate
                    );
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
    
    public boolean addCategory(String categoryName, String categoryDate, String categoryImage) throws SQLException {
        String query = "INSERT INTO category (categoryName, date, categoryImage) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, categoryName);
            stmt.setString(2, categoryDate);      // Date should come second
            stmt.setString(3, categoryImage);     // Image should come third
            stmt.executeUpdate();
            return true;
        }
    }
    
    public boolean addProduct(ProductModel product) throws SQLException {
        String sql = "INSERT INTO Product (productName, description, price, image, quantity_unit, " +
                    "manufacture_date, expiry_date, category_id, key_feature) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getPrice());
            stmt.setString(4, product.getImage());
            stmt.setString(5, product.getQuantity_unit());
            stmt.setString(6, product.getManufacture_date());
            stmt.setString(7, product.getExpiry_date());
            stmt.setInt(8, product.getCategory_id());
            stmt.setString(9, product.getKey_feature());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    public boolean deleteProduct(String productID) throws SQLException {
        String sql = "DELETE FROM product WHERE product_id = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, productID);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    public boolean editProduct(ProductModel product) throws SQLException {
        String sql = "UPDATE Product SET productName=?, description=?, price=?, image=?, quantity_unit=?, " +
                    "manufacture_date=?, expiry_date=?, category_id=?, key_feature=? WHERE product_id=?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getPrice());
            stmt.setString(4, product.getImage());
            stmt.setString(5, product.getQuantity_unit());
            stmt.setString(6, product.getManufacture_date());
            stmt.setString(7, product.getExpiry_date());
            stmt.setInt(8, product.getCategory_id());
            stmt.setString(9, product.getKey_feature());
            stmt.setInt(10, product.getProduct_id());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public ProductModel getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM Product WHERE product_id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new ProductModel(
                    rs.getInt("product_id"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getString("image"),
                    rs.getString("quantity_unit"),
                    rs.getString("manufacture_date"),
                    rs.getString("expiry_date"),
                    rs.getInt("category_id"),
                    null, // category_name not stored in Product table
                    rs.getString("key_feature")
                );
            }
            return null;
        }
        
    }
}
