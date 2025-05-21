package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.IndexModelDAO;
import models.IndexModel;

@WebServlet("/Index")
public class Index extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IndexModelDAO dao;

    public void init() {
        dao = new IndexModelDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<IndexModel> categoryList = dao.getAllCategory();
            System.out.println("Category List Size: " + categoryList.size());
            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error");
            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
