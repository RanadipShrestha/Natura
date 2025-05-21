package DAO;

import models.CartModel;
import models.CartProductModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private Connection conn;

    public CartDAO(Connection conn) {
        this.conn = conn;
    }

    // Get cart for a user with all products
    public CartModel getCartByUserId(int userId) throws SQLException {
        CartModel cart = null;

        String cartQuery = "SELECT * FROM cart WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(cartQuery)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cart = new CartModel();
                cart.setCartId(rs.getInt("cart_id"));
                cart.setUserId(rs.getInt("user_id"));

                // Fetch cart products
                List<CartProductModel> cartProducts = getCartProductsByCartId(cart.getCartId());
                cart.setProducts(cartProducts);
            }
        }

        return cart;
    }

    // Private method to get cart products by cart ID
    private List<CartProductModel> getCartProductsByCartId(int cartId) throws SQLException {
        List<CartProductModel> productList = new ArrayList<>();

        String productQuery = "SELECT cp.*, p.productName, p.price FROM cart_product cp " +
                              "JOIN product p ON cp.product_id = p.product_id " +
                              "WHERE cp.cart_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(productQuery)) {
            stmt.setInt(1, cartId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CartProductModel cp = new CartProductModel();
                cp.setCartProductId(rs.getInt("cart_product_id"));
                cp.setCartId(rs.getInt("cart_id"));
                cp.setProductId(rs.getInt("product_id"));
                cp.setQuantity(rs.getInt("quantity"));
                cp.setProductName(rs.getString("productName"));
                cp.setPrice(rs.getDouble("price"));
                productList.add(cp);
            }
        }

        return productList;
    }

    // **New public method to get cart products by cart ID**
    public List<CartProductModel> getCartProducts(int cartId) throws SQLException {
        return getCartProductsByCartId(cartId);
    }

    // Get or create a cart ID for a user
    public int getOrCreateCartId(int userId) throws SQLException {
        // Check if cart exists
        String selectSQL = "SELECT cart_id FROM cart WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(selectSQL)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cart_id");
            }
        }

        // Create new cart
        String insertSQL = "INSERT INTO cart (user_id, date) VALUES (?, CURRENT_DATE)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }

        throw new SQLException("Failed to create or retrieve cart.");
    }

    // Check if a product is already in the cart
    public boolean isProductInCart(int cartId, int productId) throws SQLException {
        String sql = "SELECT * FROM cart_product WHERE cart_id = ? AND product_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartId);
            stmt.setInt(2, productId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    // Add a new product to the cart
    public void addProductToCart(int cartId, int productId, int quantity) throws SQLException {
        String sql = "INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
        }
    }

    // Update quantity of a product already in the cart
    public void updateProductQuantity(int cartId, int productId, int quantity) throws SQLException {
        String sql = "UPDATE cart_product SET quantity = quantity + ? WHERE cart_id = ? AND product_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, cartId);
            stmt.setInt(3, productId);
            stmt.executeUpdate();
        }
    }

    // Clear all products from a cart
    public void clearCart(int cartId) throws SQLException {
        String sql = "DELETE FROM cart_product WHERE cart_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartId);
            stmt.executeUpdate();
        }
    }

}
