package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.productModelDAO;
import models.ProductModel;

@WebServlet("/product")
public class product extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private productModelDAO dao;

    public void init() {
        dao = new productModelDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        String categoryIdParam = request.getParameter("categoryId");

        ArrayList<ProductModel> productList = new ArrayList<>();

        try {
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                productList = dao.searchProductsByName(searchQuery);
            } else if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
                int categoryId = Integer.parseInt(categoryIdParam);
                productList = dao.getProductsByCategory(categoryId);
            } else {
                productList = dao.getAllProducts();
            }

            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/pages/product.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
