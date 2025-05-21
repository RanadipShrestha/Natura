package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import database.DatabaseConnection;
import models.ProductModel;

public class productModelDAO {
    private Connection con;

    public productModelDAO() {
        try {
            this.con = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all products
    public ArrayList<ProductModel> getAllProducts() throws SQLException {
        ArrayList<ProductModel> productList = new ArrayList<>();
        String query = "SELECT p.*, c.categoryName FROM Product p " +
                "JOIN Category c ON p.category_id = c.category_id";

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                
                String quantityUnit = rs.getString("quantity_unit");
                String manufactureDate = rs.getString("manufacture_date");
                String expiryDate = rs.getString("expiry_date");
                int categoryID = rs.getInt("category_id");
                String categoryName = rs.getString("categoryName");
                String keyFeature = rs.getString("key_feature");

                ProductModel product = new ProductModel(productID, productName, description, price, image,
                        quantityUnit, manufactureDate, expiryDate, categoryID,categoryName, keyFeature);

                productList.add(product);
            }
        }

        return productList;
    }

    // Get products by category ID
    public ArrayList<ProductModel> getProductsByCategory(int categoryId) throws SQLException {
        ArrayList<ProductModel> productList = new ArrayList<>();
        String query = "SELECT p.*, c.categoryName FROM Product p " +
                "JOIN Category c ON p.category_id = c.category_id " +
                "WHERE p.category_id = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                
                String quantityUnit = rs.getString("quantity_unit");
                String manufactureDate = rs.getString("manufacture_date");
                String expiryDate = rs.getString("expiry_date");
                int catID = rs.getInt("category_id");
                String categoryName = rs.getString("categoryName");
                String keyFeature = rs.getString("key_feature");

                ProductModel product = new ProductModel(productID, productName, description, price, image,
                        quantityUnit, manufactureDate, expiryDate, catID,categoryName, keyFeature);

                productList.add(product);
            }
        }

        return productList;
    }

    // ✅ NEW: Get a single product by product ID
    public ProductModel getProductById(int productId) throws SQLException {
        ProductModel product = null;
        String query = "SELECT p.*, c.categoryName FROM Product p " +
                "JOIN Category c ON p.category_id = c.category_id " +
                "WHERE p.product_id = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String image = rs.getString("image");
               
                String quantityUnit = rs.getString("quantity_unit");
                String manufactureDate = rs.getString("manufacture_date");
                String expiryDate = rs.getString("expiry_date");
                int categoryID = rs.getInt("category_id");
                String categoryName = rs.getString("categoryName");
                String keyFeature = rs.getString("key_feature");

                product = new ProductModel(productId, productName, description, price, image, 
                        quantityUnit, manufactureDate, expiryDate, categoryID,categoryName, keyFeature);
            }
        }
        return product;
    }
    
    public ArrayList<ProductModel> searchProductsByName(String name) throws SQLException {
        ArrayList<ProductModel> list = new ArrayList<>();
        String sql = "SELECT p.*, c.categoryName FROM Product p " +
                     "JOIN Category c ON p.category_id = c.category_id " +
                     "WHERE p.productName LIKE ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String quantityUnit = rs.getString("quantity_unit");
                String manufactureDate = rs.getString("manufacture_date");
                String expiryDate = rs.getString("expiry_date");
                int categoryID = rs.getInt("category_id");
                String categoryName = rs.getString("categoryName");
                String keyFeature = rs.getString("key_feature");

                ProductModel product = new ProductModel(productID, productName, description, price, image,
                        quantityUnit, manufactureDate, expiryDate, categoryID, categoryName, keyFeature);
                list.add(product);
            }
        }
        return list;
    }

    
}
