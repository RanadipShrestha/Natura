package controller;

import DAO.ReportDAO;
import models.ReportItemModel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReportServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportDAO reportdao = new ReportDAO();
        List<ReportItemModel> reportList = reportdao.getReportItems();

        // Debug print
        System.out.println("Report List Size: " + reportList.size());

        request.setAttribute("orderReports", reportList);

        // Forward to JSP page to display
        request.getRequestDispatcher("/pages/report.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
