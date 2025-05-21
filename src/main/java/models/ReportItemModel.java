package models;

public class ReportItemModel {
    private String userName;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;
    private String orderDate;

    // Constructor
    public ReportItemModel(String userName, String productName, int quantity, double price, double totalPrice, String orderDate) {
        this.userName = userName;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    // ✅ Add these getter methods
    public String getUserName() {
        return userName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
