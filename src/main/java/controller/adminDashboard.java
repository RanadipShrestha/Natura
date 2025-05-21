package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.AdminDashboardDAO;
import DAO.IndexModelDAO;
import DAO.RegisterModelDAO;
import DAO.ReportDAO;
import DAO.productModelDAO;
import models.RegisterModel;
import models.ReportItemModel;
import models.IndexModel;
import models.ProductModel;

@WebServlet("/adminDashboard")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
maxFileSize = 1024 * 1024 * 5,    // 5MB
maxRequestSize = 1024 * 1024 * 10) // 10MB
public class adminDashboard extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public adminDashboard() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
    	
    	if ("report".equals(action)) {
    		ReportDAO dao = new ReportDAO();
    		List<ReportItemModel> reportList = dao.getReportItems();
    		
    		request.setAttribute("orderReports", reportList);
    		request.getRequestDispatcher("/pages/report.jsp").forward(request, response);
    		return; 
    	}
    	
        AdminDashboardDAO dashboardDAO = new AdminDashboardDAO();
        productModelDAO productDAO = new productModelDAO();
        IndexModelDAO catogaryDAO = new IndexModelDAO();


        try {
            // Get total users from AdminDashboardDAO
            int totalUsers = dashboardDAO.getTotalUsers();
            
            // Get user list from AdminDashboardDAO
            ArrayList<RegisterModel> userList = dashboardDAO.getAllUsers();

            // Get total products from productModelDAO
            int totalProducts = productDAO.getAllProducts().size();
            
            // NEW: Get product list
            ArrayList<ProductModel> productList = productDAO.getAllProducts(); 
            
            // NEW: Get product list
            ArrayList<IndexModel> catogaryList = catogaryDAO.getAllCategory(); 

            // Set attributes to request scope
            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("totalProducts", totalProducts);
            request.setAttribute("userList", userList); 
            request.setAttribute("productList", productList);
            request.setAttribute("catogaryList", catogaryList);

            // Forward to Dashboard.jsp
            request.getRequestDispatcher("pages/Dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDashboardDAO dashboardDAO = new AdminDashboardDAO();
        RegisterModelDAO registerDAO = new RegisterModelDAO();
        IndexModelDAO catogaryDAO = new IndexModelDAO();
        productModelDAO productDAO = new productModelDAO();
        
        
        String action = request.getParameter("action");
        String userIdParam = request.getParameter("user_id");

        if ("deleteUser".equals(action) && userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);
                boolean deleted = dashboardDAO.deleteUser(userId);
                if (deleted) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?message=userDeleted");
                } else {
                	response.sendRedirect(request.getContextPath() + "/pages/databaseError.jsp?error=dbError");
                }
                return; // ✅ Prevents further processing
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/adminDashboard?error=invalidUserId");
                return;
            }
        }

        if ("updateUser".equals(action)) {
            try {
                int userId = Integer.parseInt(request.getParameter("user_id"));
                String fname = request.getParameter("first_name");
                String lname = request.getParameter("last_name");
                String username = request.getParameter("username");
                String dob = request.getParameter("dob");
                String gender = request.getParameter("gender");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone_number");
                int isAdmin = Integer.parseInt(request.getParameter("is_admin"));

                if (registerDAO.isUserNameExist(username, userId)) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=userNameExists");
                    return;
                }

                boolean success = dashboardDAO.updateUserDetails(userId, fname, lname, username, dob, gender, email, phone, isAdmin);
                if (success) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?message=updateSuccessfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=updateFailed");
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/pages/databaseError.jsp?error=dbError");
                return;
            }
        }

        if ("registerUser".equals(action)) {
            try {
                String userIdParameter = request.getParameter("user_id");
                Integer userId = null;
                if (userIdParameter != null && !userIdParameter.trim().isEmpty()) {
                    userId = Integer.parseInt(userIdParameter);
                }

                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String username = request.getParameter("username");
                String birthday = request.getParameter("birthday");
                String gender = request.getParameter("gender");
                String email = request.getParameter("email");
                String phonenumber = request.getParameter("phonenumber");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                
                if (registerDAO.isUserNameExists( username)) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=userNameExists");
                    return;
                }

                RegisterModel registration = new RegisterModel(userId, fname, lname, username, birthday, gender, email, phonenumber, password2, false, LocalDateTime.now(), LocalDateTime.now());

                boolean hasInserted = registerDAO.addRegistrationDetail(registration);
                if (hasInserted) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?message=userRegisterSuccessfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=insertFail");
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/pages/databaseError.jsp?error=dbError");
                return;
            }
            
        }
        //Category Section (Delete Category)
        if ("deleteCategory".equals(action)) {
            String categoryId = request.getParameter("category_id");

            if (categoryId != null && !categoryId.trim().isEmpty()) {
                try {
                    // Call the DAO to delete the category using categoryId
                    boolean isDeleted = dashboardDAO.deleteCategory(categoryId.trim());

                    if (isDeleted) {
                        response.sendRedirect(request.getContextPath() + "/adminDashboard?message=categorDeleted");
                        return;
                    } else {
                        response.sendRedirect(request.getContextPath() + "/adminDashboard?error=categoryNotFound");
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/pages/databaseError.jsp?error=dbError");
                    return;
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/adminDashboard?error=categoryIdNotFound");
                return;
            }
        }
        
        if ("addCategory".equals(action)) {
            try {
                // Get request parameters
                String categoryName = request.getParameter("categoryName");
                String categoryDate = request.getParameter("categoryDate");

                // Validate inputs
                if (categoryName == null || categoryName.trim().isEmpty()) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=CategoryNameRequired");
                    return;
                }

                // Validate and format date
                Date parsedDate = null;
                try {
                    if (categoryDate != null && !categoryDate.trim().isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        sdf.setLenient(false); // This will make sure date is strictly validated
                        parsedDate = sdf.parse(categoryDate);
                    } else {
                        // Set current date as default if not provided
                        parsedDate = new Date();
                    }
                } catch (ParseException e) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=InvalidDateFormat");
                    return;
                }

                // Handle image upload
                Part imageFilePart = request.getPart("categoryImage");
                if (imageFilePart == null || imageFilePart.getSize() == 0) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=ImageRequired");
                    return;
                }

                // Process file upload
                String uploadedFileName = Paths.get(imageFilePart.getSubmittedFileName()).getFileName().toString();
                String relativeImagePath = "images/" + uploadedFileName;
                String absoluteImagePath = getServletContext().getRealPath("/") + relativeImagePath;

                // Create directory if needed
                File imageUploadDirectory = new File(getServletContext().getRealPath("/images/"));
                if (!imageUploadDirectory.exists()) {
                    imageUploadDirectory.mkdirs();
                }

                // Save the file
                imageFilePart.write(absoluteImagePath);

                // Format date for database
                SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dbDateFormat.format(parsedDate);

                // Add to database
                boolean isCategoryAdded = dashboardDAO.addCategory(
                    categoryName.trim(),
                    formattedDate,  // Use the properly formatted date
                    relativeImagePath
                );

                if (isCategoryAdded) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?message=CategoryAddedSuccessfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=FailedToAddCategory");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/adminDashboard?error=Error: " + e.getMessage());
            }
            return;
        }
  
        if ("deleteProduct".equals(action)) {
            String productId = request.getParameter("product_id");

            if (productId != null && !productId.trim().isEmpty()) {
                try {
                    
                    boolean isDeleted = dashboardDAO.deleteProduct(productId.trim());

                    if (isDeleted) {
                        response.sendRedirect(request.getContextPath() + "/adminDashboard?message=productDeleted");
                        return;
                    } else {
                        response.sendRedirect(request.getContextPath() + "/adminDashboard?error=productNotFound");
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/pages/databaseError.jsp?error=dbError");
                    return;
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/adminDashboard?error=productMissingId");
                return;
            }
        }
        
        
        if ("addProduct".equals(action)) {
            try {
                // Get request parameters
                String productName = request.getParameter("productName");
                String description = request.getParameter("description");
                String priceStr = request.getParameter("price");
                String quantityUnit = request.getParameter("quantityUnit");
                String manufactureDate = request.getParameter("manufactureDate");
                String expiryDate = request.getParameter("expiryDate");
                String categoryIdStr = request.getParameter("categoryId");
                String keyFeature = request.getParameter("keyFeature");

                // Validate inputs
                if (productName == null || productName.trim().isEmpty()) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=ProductNameRequired");
                    return;
                }

                // Validate and format dates
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                
                String formattedManufactureDate = "";
                String formattedExpiryDate = "";
                
                try {
                    if (manufactureDate != null && !manufactureDate.trim().isEmpty()) {
                        Date mfgDate = sdf.parse(manufactureDate);
                        formattedManufactureDate = sdf.format(mfgDate);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/adminDashboard?error=ManufactureDateRequired");
                        return;
                    }
                    
                    if (expiryDate != null && !expiryDate.trim().isEmpty()) {
                        Date expDate = sdf.parse(expiryDate);
                        formattedExpiryDate = sdf.format(expDate);
                        
                        // Check if expiry date is after manufacture date
                        if (expDate.before(sdf.parse(formattedManufactureDate))) {
                            response.sendRedirect(request.getContextPath() + "/adminDashboard?error=ExpiryDateMustBeAfterManufactureDate");
                            return;
                        }
                    } else {
                        response.sendRedirect(request.getContextPath() + "/adminDashboard?error=ExpiryDateIsRequired");
                        return;
                    }
                } catch (ParseException e) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=InvalidDateFormat");
                    return;
                }

                // Validate price
                int price = 0;
                if (priceStr == null || priceStr.trim().isEmpty()) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=PriceIsRequired");
                    return;
                }

                // Check length before parsing
                if (priceStr.trim().length() > 10) { // 10 digits max for int
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=PriceIsTooLong");
                    return;
                }

                try {
                    price = Integer.parseInt(priceStr.trim());
                    if (price <= 0) {
                        response.sendRedirect(request.getContextPath() + "/adminDashboard?error=PriceMustBePositive");
                        return;
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=InvalidPriceFormat");
                    return;
                }

                // Validate category ID
                int categoryId = 0;
                try {
                    categoryId = Integer.parseInt(categoryIdStr);
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=InvalidCategory");
                    return;
                }

                // Handle image upload
                Part imageFilePart = request.getPart("productImage");
                if (imageFilePart == null || imageFilePart.getSize() == 0) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=ProductImageIsRequired");
                    return;
                }

                // Process file upload
                String uploadedFileName = Paths.get(imageFilePart.getSubmittedFileName()).getFileName().toString();
                String relativeImagePath = "images/products/" + uploadedFileName;
                String absoluteImagePath = getServletContext().getRealPath("/") + relativeImagePath;

                // Create directory if needed
                File imageUploadDirectory = new File(getServletContext().getRealPath("/images/products/"));
                if (!imageUploadDirectory.exists()) {
                    imageUploadDirectory.mkdirs();
                }

                // Save the file
                imageFilePart.write(absoluteImagePath);

                // Create ProductModel
                ProductModel product = new ProductModel(
                    0, // product_id will be auto-generated
                    productName.trim(),
                    description != null ? description.trim() : "",
                    price,
                    relativeImagePath,
                    quantityUnit != null ? quantityUnit.trim() : "",
                    formattedManufactureDate,
                    formattedExpiryDate,
                    categoryId,
                    null, // category_name not needed for insertion
                    keyFeature != null ? keyFeature.trim() : ""
                );

                // Add to database
                boolean isProductAdded = dashboardDAO.addProduct(product);

                if (isProductAdded) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?message=ProductAddedSuccessfully");
                } else {
                    // Delete the uploaded image if product addition failed
                    new File(absoluteImagePath).delete();
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=FailedToAddProduct");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/adminDashboard?error=Error: " + e.getMessage());
            }
            return;
        }

        if ("editProduct".equals(action)) {
            try {
                // Get product ID
                int productId = Integer.parseInt(request.getParameter("product_id"));
                
                // Get existing product
                ProductModel existingProduct = productDAO.getProductById(productId);
                if (existingProduct == null) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=product_not_found");
                    return;
                }
                
                // Get request parameters
                String productName = request.getParameter("productName");
                String description = request.getParameter("description");
                String priceStr = request.getParameter("price");
                String quantityUnit = request.getParameter("quantityUnit");
                String manufactureDate = request.getParameter("manufactureDate");
                String expiryDate = request.getParameter("expiryDate");
                String categoryIdStr = request.getParameter("categoryId");
                String keyFeature = request.getParameter("keyFeature");
                
                // Validate inputs
                if (productName == null || productName.trim().isEmpty()) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=ProductNameIsRequired");
                    return;
                }
                
                // Validate and format dates
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                
                String formattedManufactureDate = existingProduct.getManufacture_date();
                String formattedExpiryDate = existingProduct.getExpiry_date();
                
                try {
                    if (manufactureDate != null && !manufactureDate.trim().isEmpty()) {
                        Date mfgDate = sdf.parse(manufactureDate);
                        formattedManufactureDate = sdf.format(mfgDate);
                    }
                    
                    if (expiryDate != null && !expiryDate.trim().isEmpty()) {
                        Date expDate = sdf.parse(expiryDate);
                        formattedExpiryDate = sdf.format(expDate);
                        
                        // Check if expiry date is after manufacture date
                        if (expDate.before(sdf.parse(formattedManufactureDate))) {
                            response.sendRedirect(request.getContextPath() + "/adminDashboard?error=ExpiryDateMustBeAfterManufactureDate");
                            return;
                        }
                    }
                } catch (ParseException e) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=InvalidDateFormat");
                    return;
                }
                
                // Validate price
                int price = existingProduct.getPrice();
                try {
                    if (priceStr != null && !priceStr.trim().isEmpty()) {
                        price = Integer.parseInt(priceStr);
                        if (price <= 0) {
                            response.sendRedirect(request.getContextPath() + "/adminDashboard?error=PriceMustBePositive");
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=InvalidPriceFormat");
                    return;
                }
                
                // Validate category ID
                int categoryId = existingProduct.getCategory_id();
                try {
                    if (categoryIdStr != null && !categoryIdStr.trim().isEmpty()) {
                        categoryId = Integer.parseInt(categoryIdStr);
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=InvalidCategory");
                    return;
                }
                
                // Handle image upload
                String imagePath = existingProduct.getImage();
                Part imageFilePart = request.getPart("productImage");
                if (imageFilePart != null && imageFilePart.getSize() > 0) {
                    // Get the filename and create the appropriate path
                    String uploadedFileName = Paths.get(imageFilePart.getSubmittedFileName()).getFileName().toString();
                    String relativeImagePath = "images/products/" + uploadedFileName;
                    String absoluteImagePath = getServletContext().getRealPath("/") + relativeImagePath;
                    
                    // Create directory if needed
                    File imageUploadDirectory = new File(getServletContext().getRealPath("/images/products/"));
                    if (!imageUploadDirectory.exists()) {
                        imageUploadDirectory.mkdirs();
                    }
                    
                    // Save the file to the server
                    imageFilePart.write(absoluteImagePath);
                    
                    // Delete old image if it exists and is different from new one
                    if (imagePath != null && !imagePath.isEmpty() && !imagePath.equals(relativeImagePath)) {
                        File oldImage = new File(getServletContext().getRealPath("/") + imagePath);
                        if (oldImage.exists()) {
                            oldImage.delete();
                        }
                    }
                    
                    imagePath = relativeImagePath;
                }
                
                // Update product model
                ProductModel updatedProduct = new ProductModel(
                    productId,
                    productName.trim(),
                    description != null ? description.trim() : "",
                    price,
                    imagePath,
                    quantityUnit != null ? quantityUnit.trim() : "",
                    formattedManufactureDate,
                    formattedExpiryDate,
                    categoryId,
                    null, // category_name not needed for update
                    keyFeature != null ? keyFeature.trim() : ""
                );
                
                // Update product in database
                boolean isUpdated = dashboardDAO.editProduct(updatedProduct);
                
                if (isUpdated) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?message=productUpdated");
                } else {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?error=product_update_failed");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/adminDashboard?error=exception_occurred");
            }
            return;
        } 
        
    }  
    
}