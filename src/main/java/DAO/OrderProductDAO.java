package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.OrderProductEntity;

public class OrderProductDAO {
    private Connection conn;

    public OrderProductDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertOrderProduct(OrderProductEntity orderProduct) throws SQLException {
        String sql = "INSERT INTO order_product (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderProduct.getOrderId());
            stmt.setInt(2, orderProduct.getProductId());
            stmt.setInt(3, orderProduct.getQuantity());
            stmt.setDouble(4, orderProduct.getPrice());
            stmt.executeUpdate();
        }
    }
}
