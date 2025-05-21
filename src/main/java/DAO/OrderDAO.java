package DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import models.OrderModel;
import models.OrderProductModel;

public class OrderDAO {
    private Connection con;

    // Default constructor for fetching order data
    public OrderDAO() {
        try {
            this.con = DatabaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Overloaded constructor for use with servlet when connection is passed directly
    public OrderDAO(Connection conn) {
        this.con = conn;
    }

    // ✅ Create a new order and return generated order_id
    public int createOrder(OrderModel order) throws SQLException {
        String sql = "INSERT INTO `order` (user_id, total, date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getUserId());
            stmt.setDouble(2, order.getTotal());
            stmt.setTimestamp(3, Timestamp.valueOf(order.getDate()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return generated order_id
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        }
    }

    // ✅ Get list of all ordered products by a user
    public List<OrderProductModel> getOrderProductsByUserId(int userId) throws SQLException {
        List<OrderProductModel> orderProducts = new ArrayList<>();

        String sql = "SELECT o.order_id, p.productName, op.quantity, op.price, o.total " +
                     "FROM `order` o " +
                     "JOIN order_product op ON o.order_id = op.order_id " +
                     "JOIN product p ON op.product_id = p.product_id " +
                     "WHERE o.user_id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                double total = rs.getDouble("total");

                OrderProductModel opm = new OrderProductModel(orderId, productName, quantity, price, total);
                orderProducts.add(opm);
            }
        }

        return orderProducts;
    }
}
