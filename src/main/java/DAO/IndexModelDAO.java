package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DatabaseConnection;
import models.IndexModel;

public class IndexModelDAO {
    private Connection con;

    public IndexModelDAO() {
        try {
            this.con = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
        	// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<IndexModel> getAllCategory() throws SQLException {
        ArrayList<IndexModel> categoryList = new ArrayList<>();

        String query = "SELECT * FROM category";

        if (con == null) {
            System.out.println(" Connection is null inside getAllCategory()");
            return categoryList;
        }

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int categoryID = rs.getInt("category_id");
                String categoryName = rs.getString("categoryName");
                String date = rs.getString("date");
                String categoryImage = rs.getString("categoryImage");

                IndexModel index = new IndexModel(categoryID, categoryName, date, categoryImage);
                categoryList.add(index);
            }
        }
        return categoryList;
    }
    
    
}
