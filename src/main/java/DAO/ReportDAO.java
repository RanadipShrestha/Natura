package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DatabaseConnection;
import models.ReportItemModel;

public class ReportDAO {
    private Connection con;

    // Constructor to establish database connection
    public ReportDAO() {
        try {
            this.con = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch report data
    public List<ReportItemModel> getReportItems() {
        List<ReportItemModel> reportList = new ArrayList<>();

        if (con == null) {
            System.out.println("Database connection is not available.");
            return reportList;
        }

        // Correct query with actual fields
        String query = "SELECT o.order_id, o.date AS orderDate, u.first_name, u.last_name, " +
                       "p.productName, p.price, op.quantity, (p.price * op.quantity) AS totalPrice " +
                       "FROM `order` o " +
                       "JOIN order_product op ON o.order_id = op.order_id " +
                       "JOIN product p ON op.product_id = p.product_id " +
                       "JOIN `user` u ON o.user_id = u.user_id " +
                       "ORDER BY o.date DESC";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                double totalPrice = rs.getDouble("totalPrice");
                String orderDate = rs.getString("orderDate");

                ReportItemModel item = new ReportItemModel(
                    fullName, productName, quantity, price, totalPrice, orderDate
                );
                reportList.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportList;
    }
}
