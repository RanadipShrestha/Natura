package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.CartDAO;
import DAO.OrderDAO;
import DAO.RegisterModelDAO;
import models.*;

@WebServlet("/userDashboard")
public class userDashboard extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RegisterModelDAO registerDAO;
    private OrderDAO orderDAO;

    public void init() {
        registerDAO = new RegisterModelDAO();
        orderDAO = new OrderDAO();
    }

    public userDashboard() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        int userId = (int) session.getAttribute("user_id");

        try {
            // Fetch user data
            RegisterModel currentUser = registerDAO.getUserById(userId);
            if (currentUser == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
                return;
            }
            session.setAttribute("currentUser", currentUser);
            request.setAttribute("user", currentUser);

            // Fetch order products
            List<OrderProductModel> orderProducts = orderDAO.getOrderProductsByUserId(userId);
            request.setAttribute("orderProducts", orderProducts);

            // Fetch cart info
            Connection conn = database.DatabaseConnection.getConnection(); // use your actual DB utility class
            CartDAO cartDAO = new CartDAO(conn);
            CartModel userCart = cartDAO.getCartByUserId(userId);
            request.setAttribute("userCart", userCart);

            // Forward to JSP
            request.getRequestDispatcher("/pages/userDashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/Login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        int userId = Integer.parseInt(request.getParameter("user_id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");

        try {
            boolean updated = registerDAO.updateUserProfile(
                userId, firstName, lastName, dob, gender, email, phoneNumber);

            if (updated) {
                RegisterModel updatedUser = registerDAO.getUserById(userId);
                session.setAttribute("currentUser", updatedUser);
                request.setAttribute("user", updatedUser);
                request.setAttribute("updateStatus", "success");
            } else {
                request.setAttribute("updateStatus", "error");
                request.setAttribute("errorMessage", "Failed to update profile. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("updateStatus", "error");
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("updateStatus", "error");
            request.setAttribute("errorMessage", "An unexpected error occurred");
        }
        // Forward back to the dashboard page
        doGet(request, response); // reload updated data
    }
}
