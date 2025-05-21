package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.*;
import database.DatabaseConnection;
import models.*;

@WebServlet("/BuyNowServlet")
public class BuyNowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("Login");
            return;
        }

        int userId = (int) session.getAttribute("user_id");

        try (Connection conn = DatabaseConnection.getConnection()) {
            CartDAO cartDAO = new CartDAO(conn);
            OrderDAO orderDAO = new OrderDAO(conn);
            OrderProductDAO orderProductDAO = new OrderProductDAO(conn);

            int cartId = cartDAO.getOrCreateCartId(userId);
            List<CartProductModel> cartProducts = cartDAO.getCartProducts(cartId);

            if (cartProducts == null || cartProducts.isEmpty()) {
                response.sendRedirect("userDashboard?error=EmptyCart");
                return;
            }

            // Calculate total
            double total = 0;
            for (CartProductModel cp : cartProducts) {
                total += cp.getPrice() * cp.getQuantity();
            }

            // Create order
            OrderModel order = new OrderModel(0, userId, total, LocalDateTime.now());
            int orderId = orderDAO.createOrder(order);

            // Save each cart item to order_product
            for (CartProductModel cp : cartProducts) {
                OrderProductEntity op = new OrderProductEntity(orderId, cp.getProductId(), cp.getQuantity(), cp.getPrice());
                orderProductDAO.insertOrderProduct(op);
            }

            // Clear the cart
            cartDAO.clearCart(cartId);

            response.sendRedirect("userDashboard?message=OrderPlaced");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("userDashboard?error=OrderFailed");
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
