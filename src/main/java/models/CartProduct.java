package models;

public class CartProduct {
    private int cartProductId;
    private int cartId;
    private int productId;
    private int quantity;
    
    

    public CartProduct(int cartProductId, int cartId, int productId, int quantity) {
		super();
		this.cartProductId = cartProductId;
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
	}
	// Getters and Setters
    public int getCartProductId() { return cartProductId; }
    public void setCartProductId(int cartProductId) { this.cartProductId = cartProductId; }

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
