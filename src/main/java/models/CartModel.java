package models;

import java.util.List;

public class CartModel {
    private int cartId;
    private int userId;
    private String data;
    private List<CartProductModel> products;

    // Getters and Setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<CartProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<CartProductModel> products) {
        this.products = products;
    }
}
