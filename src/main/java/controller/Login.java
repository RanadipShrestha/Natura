package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.LoginModelDAO;
import models.LoginModel;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Integer userId = (Integer) session.getAttribute("user_id");
			
			if (userId != null) {
				System.out.print(userId);
			}
			else {
				System.out.print("No user id found");
			}
		}
		else {
			System.out.print("Session Null");
		}
		System.out.println("Login Servlet: doGet() called.");
		request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userIdParemeter = request.getParameter("user_id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Integer userID = null;
		
		if (userIdParemeter != null && !userIdParemeter.trim().isEmpty()) {
			try {
				userID = Integer.parseInt(userIdParemeter);
				
			}catch(NumberFormatException e1){
				response.sendRedirect(request.getContextPath() + "/pages/login.jsp?error=incorrectUserID");
				return;
			}
		}

		System.out.println("Login Servlet: doPost() called.");
		System.out.println("Received username: " + username);
		System.out.println("Received password: " + password);

		LoginModelDAO dao = new LoginModelDAO();

		try {
			LoginModel user = dao.getUser(username, password);

			HttpSession session = request.getSession();
			System.out.println("Session ID: " + session.getId());
			System.out.println("Is new session: " + session.isNew());

			if (user != null) {
				System.out.println("Login successful for user: " + user.getUsername());
				System.out.println("Is Admin: " + user.isAdmin());
				System.out.println("user_id"+ user.getUser_id());
				
				session.setAttribute("user_id", user.getUser_id());
				session.setAttribute("username", user.getUsername());
				session.setAttribute("is_admin", user.isAdmin());

				System.out.println("Session attribute 'username': " + session.getAttribute("username"));
				System.out.println("Session attribute 'is_admin': " + session.getAttribute("is_admin"));

				
				  if (user.isAdmin()) { System.out.println("Redirecting to admin dashboard.");
				  	response.sendRedirect(request.getContextPath() + "/adminDashboard"); }
				  else { System.out.println("Redirecting to product page.");
				  	response.sendRedirect(request.getContextPath() + "/Index"); }
				 
			} else {
				System.out.println("Login failed. User not found.");
				session.setAttribute("loginError", "Invalid username or password!");
				System.out.println("Session attribute 'loginError': " + session.getAttribute("loginError"));
				response.sendRedirect(request.getContextPath() + "/Login");
			}
		} catch (SQLException e) {
			System.out.println("SQLException occurred:");
			e.printStackTrace();

			HttpSession session = request.getSession();
			session.setAttribute("loginError", "Database error occurred. Please try again.");
			System.out.println("Session attribute 'loginError' (SQL exception): " + session.getAttribute("loginError"));
			response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
		}
	}
}
