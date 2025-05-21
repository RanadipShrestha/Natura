package models;

import java.time.LocalDateTime;

public class OrderModel {
    private int orderId;
    private int userId;
    private double total;
    private LocalDateTime date;

    public OrderModel() {}

    public OrderModel(int orderId, int userId, double total, LocalDateTime date) {
        this.orderId = orderId;
        this.userId = userId;
        this.total = total;
        this.date = date;
    }

    // Getters
    public int getOrderId() { return orderId; }
    public int getUserId() { return userId; }
    public double getTotal() { return total; }
    public LocalDateTime getDate() { return date; }

    // Setters
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setTotal(double total) { this.total = total; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
