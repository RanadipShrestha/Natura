package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.AdminDashboardDAO;
import DAO.CartDAO;
import DAO.IndexModelDAO;
import DAO.ReportDAO;
import DAO.productModelDAO;
import database.DatabaseConnection;
import models.IndexModel;
import models.ProductModel;
import models.RegisterModel;
import models.ReportItemModel;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddToCartServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            System.out.println("Session is null or user not logged in.");
            response.sendRedirect("Login");
            return;
        }

        int userId = (int) session.getAttribute("user_id");
        String productIdStr = request.getParameter("productId");
        String quantityStr = request.getParameter("quantity");

        System.out.println("User ID: " + userId);
        System.out.println("Received productId: " + productIdStr);
        System.out.println("Received quantity: " + quantityStr);

        if (productIdStr == null || quantityStr == null || productIdStr.isEmpty() || quantityStr.isEmpty()) {
            System.out.println("Missing parameters. Redirecting to Index.");
            response.sendRedirect("Index");
            return;
        }

        try {
            int productId = Integer.parseInt(productIdStr);
            int quantity = Integer.parseInt(quantityStr);

            Connection conn = DatabaseConnection.getConnection();
            CartDAO cartDAO = new CartDAO(conn);

            int cartId = cartDAO.getOrCreateCartId(userId);
            if (cartDAO.isProductInCart(cartId, productId)) {
                cartDAO.updateProductQuantity(cartId, productId, quantity);
                System.out.println("Updated quantity for product ID: " + productId);
            } else {
                cartDAO.addProductToCart(cartId, productId, quantity);
                System.out.println("Added new product ID: " + productId);
            }

            response.sendRedirect("Index?message=ProductAdded");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while adding to cart.");
            response.sendRedirect("Index?error=true");
        }
    }
}
