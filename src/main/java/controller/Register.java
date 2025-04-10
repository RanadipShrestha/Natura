package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.RegisterModelDAO;
import models.RegisterModel;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RegisterModelDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	dao = new RegisterModelDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("pages/register.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fname = request.getParameter("fname"); 
		String lname = request.getParameter("lname");
		String username = request.getParameter("username");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phonenumber = request.getParameter("phonenumber");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		if(!isValidName(fname)){
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp?error=name");
			return;
		}
		if(!isValidName(lname)) {
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp?error=name");
			return;
		}
		if(!isValidUsername(username)){
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp?error=username");
			return;
		}
		if(!isValidDob(birthday)) {
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp?error=DOB");
			return;
		}
		if(!isValidGender(gender)){
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp?error=gender");
			return;
		}
		if(!isValidEmail(email)) {
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp?error=email");
			return;
		}
		if(!isValidPhoneNumber(phonenumber)) {
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp?error=phone");
			return;
		}
		if(!isValidPassword1(password1)) {
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp?error=password1");
			return;
		}
		if(!isValidPassword2(password2)) {
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp?error=password2");
			return;
		}

		
		RegisterModel registration = new RegisterModel(fname,lname,username,birthday,gender,email,phonenumber,password2, false, LocalDateTime.now(), LocalDateTime.now());
		
		try {
			boolean hasInserted = dao.addRegistrationDetail(registration);
			
			if(hasInserted) {
				System.out.println("Inserted Successfully");
				response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
			}else {
				System.out.println("Unsuccessfully Inserted");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	private boolean isValidName(String name) {
		return !name.matches(".*\\d.*") && !name.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?/~`].*");
	}
	
	private boolean isValidUsername(String username) {
		return !username.matches(".*\\d.*") && !username.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?/~`].*");
	}
	
	private boolean isValidDob(String DOB) {
		return DOB.matches("\\d{4}-\\d{2}-\\d{2}");

	}
	private boolean isValidPhoneNumber(String phone) {
		return phone.matches("\\d{10}");
	}
	private boolean isValidGender(String gender) {
		return !gender.matches(".*\\d.*") && !gender.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?/~`].*");
	}
	
	private boolean isValidEmail(String email) {
		 return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}
	
	private boolean isValidPassword1(String password1) {
	    return password1.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$");
	}

	private boolean isValidPassword2(String password2) {
	    return password2.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$");
	}
}
