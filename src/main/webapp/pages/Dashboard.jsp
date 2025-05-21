<%@ page import="java.util.List" %>
<%@ page import="models.RegisterModel" %>
<%@ page import="models.ProductModel" %>
<%@ page import="models.IndexModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Natura. Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="/CollegeTutorial/css/Dashboard.css" />

</head>
<body>

    <div class="sidebar">
        <h2>Natura. Admin</h2>
        <ul>
            <li class="nav-link active" data-target="dashboardSection">Dashboard</li>
            <li class="nav-link" data-target="productsSection">Products</li>
            <li class="nav-link" data-target="categoriesSection">Categories</li>
            <li class="nav-link" data-target="usersSection">Users</li>
            <li class="nav-link">
    <a href="adminDashboard?action=report" style=" color:white ">Report</a>
</li>
        </ul>
    </div>

    <div class="main-content">
    <% 
        String error = request.getParameter("error"); 
        if (error != null) { 
    %>
        <div id="errorMessage" style="color: white; background-color: red; padding: 10px; margin: 10px 0; border-radius: 5px; text-align: center;">
            <% if (error.equals("adminDashboardError")) { %>
                Invalid Name.
            <% } else if (error.equals("userNameExists")) { %>
                User Name Already Exists.
                
                
            <% } else if (error.equals("insertFail")) { %>
                Your enter Enter Details is not valid. 
            <% } else if (error.equals("dbError")) { %>
                Server Error!!!!!
            <% } else if (error.equals("updateFailed")) { %>
                User Update Failed.
            <% } else if (error.equals("userDeleteFailed")) { %>
                User Delete Failed.
            <% } else if (error.equals("invalidUserId")) { %>
                There is no id. 
            <% } else if (error.equals("categoryNotFound")) { %>
                category Not Found. 
            <% } else if (error.equals("CategoryNameRequired")) { %>
                Category Name is Required. 
            <% } else if (error.equals("categoryIdNotFound")) { %>
                Category Not Found.   
            <% } else if (error.equals("InvalidDateFormat")) { %>
                Invalid date format. Use YYYY-MM-DD
            <% } else if (error.equals("ImageRequired")) { %>
                To add Category You need Image.
            <% } else if (error.equals("productNotFound")) { %>
                Product Not Found. 
            <% } else if (error.equals("productMissingId")) { %>
                Product Id is Missing.
            <% } else if (error.equals("ProductNameRequired")) { %>
                Product Name Required.   
            <% } else if (error.equals("ManufactureDateRequired")) { %>
                Manufacture Date is Required.
            <% } else if (error.equals("ExpiryDateMustBeAfterManufactureDate")) { %>
                Expiry Date Must Be After Manufacture Date.
            <% } else if (error.equals("ExpiryDateIsRequired")) { %>
                Expiry date is required. 
            <% } else if (error.equals("PriceIsRequired")) { %>
                Price is required.
            <% } else if (error.equals("PriceIsTooLong")) { %>
                Price is too long.
            <% } else if (error.equals("PriceMustBePositive")) { %>
                Price must be positive.
            <% } else if (error.equals("InvalidPriceFormat")) { %>
                Invalid price format.
            <% } else if (error.equals("InvalidCategory")) { %>
                Invalid category.
            <% } else if (error.equals("ProductImageIsRequired")) { %>
                Product image is required.
            <% } else if (error.equals("FailedToAddProduct")) { %>
                Failed to add product.  
            <% } else if (error.equals("product_not_found")) { %>
                product not found.
            <% } else if (error.equals("ProductNameIsRequired")) { %>
                Product name is required.
            <% } else if (error.equals("product_update_failed")) { %>
                product update failed.
            <% } %>
        </div>
    <% } %>
    
    <% 
        String message = request.getParameter("message"); 
        if (message != null) { 
    %>
        <div id="successMessage" style="color: white; background-color: green; padding: 10px; margin: 10px 0; border-radius: 5px; text-align: center;">
            <% if (message.equals("userDeleted")) { %>
                User Deleted Successfully.
            <% } else if (message.equals("userRegisterSuccessfully")) { %>
                Account Created Successfully.
            <% } else if (message.equals("updateSuccessfully")) { %>
                User Update Successfully.
            <% } else if (message.equals("userDeleted")) { %>
                User Deleted successfully. 
            <% } else if (message.equals("categorDeleted")) { %>
                Category Deleted successfully.
            <% } else if (message.equals("CategoryAddedSuccessfully")) { %>
                Category Added Successfully.   
            <% } else if (message.equals("categorDeleted")) { %>
                Category Deleted Successfully.   
            <% } else if (message.equals("productDeleted")) { %>
                Product Deleted Successfully.
            <% } else if (message.equals("productUpdated")) { %>
                Product Update Successfully.
            <% } else if (message.equals("ProductAddedSuccessfully")) { %>
                Product Added Successfully.
            <% } %>
        </div>
    <% } %>
        <div class="header">
            <h1>Admin Dashboard</h1>
            <div class="admin-info">
                <span>Welcome, Admin</span>
                <a href="/CollegeTutorial/Login">
                    <button id="logoutBtn">Logout</button>
                </a>
            </div>
        </div>

        <!-- Dashboard Section -->
        <div id="dashboardSection" class="section active">
            <div class="cards">
                <div class="card blue">
                    <h3>Total Products</h3>
                    <p>${totalProducts}</p>
                </div>
                <div class="card skyblue">
                    <h3>Total Users</h3>
                    <p>${totalUsers}</p>
                </div>
            </div>
        </div>

        <!-- Products Section -->
        <div id="productsSection" class="section">
            <h2>Product List</h2>
       		<button id="addProductBtn">Add Product</button>
            <table>
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Manufacture Date</th>
                        <th>Expire Date</th>
                        <th>Key Feature</th>
                        <th>Image</th>
                        <th>Category Id</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<ProductModel> productList = (List<ProductModel>) request.getAttribute("productList");
                        if (productList != null) {
                            for (ProductModel product : productList) {
                    %>
                    <tr>
                        <td><%= product.getProductName() %></td>
                        <td><%= product.getDescription() %></td>
                        <td><%= product.getPrice() %></td>
                        <td><%= product.getQuantity_unit() %></td>
                        <td><%= product.getManufacture_date() %></td>
                        <td><%= product.getExpiry_date() %></td>
                        <td><%= product.getKey_feature() %></td>
                        <%-- <td><%= product.getImage() %></td> --%>
                        <td><img src="<%= product.getImage() %>" alt="Category Image" style="width: 60px; height: auto;"></td>
                        <td><%= product.getCategory_name() %></td>
                        <td>
                            <button class="edit-btn product-edit-btn " style= "margin-bottom: 20px">Edit</button>
	                            <form method="POST" action="${pageContext.request.contextPath}/adminDashboard">
				                <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
				                <input type="hidden" name="action" value="deleteProduct">
			                <button type="submit">Delete</button>
			            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        
        <!-- Add Product Modal -->
		<div id="addProductModal" class="modal" style="display: none;">
		    <div class="modal-content">
		        <span class="close" onclick="document.getElementById('addProductModal').style.display='none'">&times;</span>
		        <h2>Add Product</h2>
		        <form id="addProductForm" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/adminDashboard">
		            <input type="hidden" name="action" value="addProduct">
		            
		            <div class="row">
		                <div class="col">
		                    <label for="productName">Product Name*</label>
		                    <input type="text" class="form-control" id="productName" name="productName" required maxlength="255">
		                </div>
		                <div class="col">
		                    <label for="price">Price*</label>
		                    <input type="number" class="form-control" id="price" name="price" step="0.01" min="0" required>
		                </div>
		            </div>
		
		            
		            <div class="row">
		                <div class="col">
		                    <label for="quantityUnit">Quantity Unit*</label>
		                    <input type="number" class="form-control" id="quantityUnit" name="quantityUnit" min="1" required>
		                </div>
		                <div class="col">
		                    <label for="manufactureDate">Manufacture Date*</label>
		                    <input type="date" class="form-control" id="manufactureDate" name="manufactureDate" required>
		                </div>
		                <div class="col">
		                    <label for="expiryDate">Expiry Date*</label>
		                    <input type="date" class="form-control" id="expiryDate" name="expiryDate" required>
		                </div>
		            </div>
		            
		            <div class="row">
		                <div class="col">
						    <label for="category">Category*</label>
						    <select class="form-control" id="category" name="categoryId" required>
						        <%
						            List<models.IndexModel> categoryList = (List<models.IndexModel>) request.getAttribute("catogaryList");
						            if (categoryList != null) {
						                for (models.IndexModel category : categoryList) {
						        %>
						            <option value="<%= category.getCategory_id() %>"><%= category.getCategoryName() %></option>
						        <%
						                }
						            }
						        %>
						    </select>
						</div>
		                
		                <div class="col">
		                    <label for="keyFeature">Key Feature*</label>
		                    <input type="text" class="form-control" id="keyFeature" name="keyFeature" required maxlength="255">
		                </div>
		            </div>
		            
		            <div class="row">
		            <div class="col">
		                <label for="productImage">Product Image*</label>
		                <input type="file" class="form-control-file" id="productImage" name="productImage" accept="image/*" required>
		                
		                <div id="imagePreview" class="mt-2">
		                 </div>
		                </div>
		                
		                <div class="row">
		                <div class="col">
		                <label for="description">Description*</label>
		                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
		                 </div>
		            </div>
		            </div>
		            
		            <button type="submit" class="btn btn-primary" >Add Product</button>
		        </form>
		    </div>
		</div>
        
      <!-- Edit Product Modal -->
<div id="editProductModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Edit Product</h2>
        <form id="editProductForm" method="POST" action="${pageContext.request.contextPath}/adminDashboard" enctype="multipart/form-data">
            <input type="hidden" name="action" value="editProduct">
            <input type="hidden" id="editProductId" name="product_id">
            
            <div class="row">
                <div class="col">
                    <label>Current Image:</label>
                    <img id="currentProductImage" src="" style="max-width: 200px; display: block; margin: 10px 0;">
                </div>
            </div>
            
            <div class="row">
                <div class="col">
                    <label for="editProductImage">Update Image (leave blank to keep current):</label>
                    <input type="file" id="editProductImage" name="productImage" accept="image/*">
                    <small>Allowed formats: JPG, PNG, GIF (Max 2MB)</small>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <label for="editProductName">Product Name*</label>
                    <input type="text" id="editProductName" name="productName" required maxlength="255">
                </div>
                <div class="col">
                    <label for="editDescription">Description*</label>
                    <textarea id="editDescription" name="description" rows="3" required></textarea>
                </div>
            </div>
            
            <div class="row">
                <div class="col">
                    <label for="editPrice">Price*</label>
                    <input type="number" step="0.01" id="editPrice" name="price" min="0.01" required>
                </div>
                <div class="col">
                    <label for="editQuantityUnit">Quantity Unit*</label>
                    <input type="text" id="editQuantityUnit" name="quantityUnit" required>
                </div>
            </div>
            
            <div class="row">
                <div class="col">
                    <label for="editManufactureDate">Manufacture Date*</label>
                    <input type="date" id="editManufactureDate" name="manufactureDate" required>
                </div>
                <div class="col">
                    <label for="editExpiryDate">Expiry Date*</label>
                    <input type="date" id="editExpiryDate" name="expiryDate" required>
                </div>
            </div>
            
            <div class="row">
                <div class="col">
                    <label for="editCategory">Category*</label>
                    <select id="editCategory" name="categoryId" required>
                        <%
                            
                            if (categoryList != null) {
                                for (IndexModel category : categoryList) {
                        %>
                            <option value="<%= category.getCategory_id() %>"><%= category.getCategoryName() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <div class="col">
                    <label for="editKeyFeature">Key Feature</label>
                    <input type="text" id="editKeyFeature" name="keyFeature">
                </div>
            </div>
            
            <div class="form-footer">
                <button type="button" class="btn-cancel" onclick="closeModal()">Cancel</button>
                <button type="submit" class="btn-save">Update Product</button>
            </div>
        </form>
    </div>
</div>




		<!-- Categories Section -->
		<div id="categoriesSection" class="section">
		    <h2>Category List</h2>
		    <button id="addCategoryBtn">Add Category</button>
		    <table>
		        <thead>
		            <tr>
		               <th>Name</th>
		               <th>Date</th>
		               <th>Image</th>
		               <th>Actions</th>
		            </tr>
		        </thead>
		        <tbody>
		            <%
		                List<models.IndexModel> categoryLists = (List<models.IndexModel>) request.getAttribute("catogaryList");
		                if (categoryLists != null) {
		                    for (models.IndexModel category : categoryLists) {
		            %>
		            <tr>
					    <td><%= category.getCategoryName() %></td>
					    <td><%= category.getDate() %></td>
					    <td><img src="<%= category.getCategoryImage() %>" alt="Category Image" style="width: 60px; height: auto;"></td>
					    <td>
					        <form action="${pageContext.request.contextPath}/adminDashboard" method="POST">
					            <input type="hidden" name="category_id" value="<%= category.getCategory_id() %>">
					            <input type="hidden" name="action" value="deleteCategory">
					            <button type="submit">Delete</button>
					        </form>
					    </td>
					</tr>
		            <%
		                    }
		                }
		            %>
		        </tbody>
		    </table>
		</div>

		<!-- Add Category Modal -->
		<div id="addCategoryModal" class="modal" style="display: none;">
		    <div class="modal-content">
		        <span class="close" onclick="document.getElementById('addCategoryModal').style.display='none'">&times;</span>
		        <h2>Add Category</h2>
		        <form id="addCategoryForm" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/adminDashboard">
		            <input type="hidden" name="action" value="addCategory">
		            <div class="row">
		                <div class="col">
		                    <label for="addCategoryName">Category Name:</label>
		                    <input type="text" id="addCategoryName" name="categoryName" required>
		                </div>
		                <div class="col">
		                    <label for="addCategoryDate">Date:</label>
		                    <input type="date" id="addCategoryDate" name="categoryDate" required>
		                </div>
		                <div class="col">
		                    <label for="addCategoryImage">Category Image:</label>
		                    <input type="file" id="addCategoryImage" name="categoryImage" accept="image/*" required>
		                    <div id="newImagePreview" style="margin-top: 10px;"></div>
		                </div>
		            </div>
		            <div class="edit-product">
		                <button type="submit" class="edit-product">Add Category</button>
		            </div>
		        </form>
		    </div>
		</div>

	
		
        <!-- Orders Section -->
		<div id="ordersSection" class="section">
		    <h2>Orders Section</h2>
		    <table>
		        <thead>
		            <tr>
		                <th>Name</th>
		                <th>Product</th>
		                <th>Date</th>
		                <th>Price</th>
		                <th>Quantity</th>
		                <th>Image</th>
		                <th>Action</th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr>
		                <td>Binita</td>
		                <td>Face Wash</td>
		                <td>2025-04-20</td>
		                <td>Rs 100</td>
		                <td>2</td>
		                <td><img src="../images/placeholder.jpg" alt="Face Wash"></td>
		                <td><button class="cancel-button">Cancel</button></td>
		            </tr>
		            <tr>
		                <td>Nehisha</td>
		                <td>Moisturizer</td>
		                <td>2025-04-22</td>
		                <td>Rs 90</td>
		                <td>4</td>
		                <td><img src="../images/placeholder.jpg" alt="Moisturizer"></td>
		                <td><button class="cancel-button">Cancel</button></td>
		            </tr>
		        </tbody>
		    </table>
		</div>


        <!-- Users Section -->
        <div id="usersSection" class="section">
            <h2>Users Section</h2>
            <button onclick="document.getElementById('registerUserModal').style.display='block'">Register User</button>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Username</th>
                        <th>DOB</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Gender</th>
                        <th>Is Admin</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
			    <%
			        List<RegisterModel> userList = (List<RegisterModel>) request.getAttribute("userList");
			        if (userList != null) {
			            for (RegisterModel user : userList) {
			    %>
			    <tr data-userid="<%= user.getUser_id() %>">
			        <td><%= user.getFname() + " " + user.getLname() %></td>
			        <td><%= user.getUsername() %></td>
			        <td><%= user.getBirthday() %></td>
			        <td><%= user.getEmail() %></td>
			        <td><%= user.getPhonenumber() %></td>
			        <td><%= user.getGender() %></td>
			        <td><%= user.isIs_admin() %></td>
			        <td>
			            <button class="edit-btn user-edit-btn">Edit</button>
			            <form method="POST" action="${pageContext.request.contextPath}/adminDashboard">
			                <input type="hidden" name="user_id" value="<%= user.getUser_id() %>">
			                <input type="hidden" name="action" value="deleteUser">
			                <button type="submit">Delete</button>
			            </form>
			        </td>
			    </tr>
			    <%
			            }
			        }
			    %>
			   </tbody>
			</table>
			</div>
	
	    	<!-- Edit User Modal -->
			<div id="editUserModal" class="modal">
			    <div class="modal-content">
			        <span class="close" onclick="document.getElementById('editUserModal').style.display='none'">&times;</span>
			        <h2>Edit User</h2>
			        <form id="editUserForm" method="POST" action="${pageContext.request.contextPath}/adminDashboard">
			            <input type="hidden" id="edit_user_id" name="user_id">
			            <input type="hidden" name="action" value="updateUser">
			            
			            <div class="row">
			                <div class="col">
			                    <label for="edit_first_name">First Name:</label>
			                    <input type="text" id="edit_first_name" name="first_name">
			                </div>
			                <div class="col">
			                    <label for="edit_username">Username:</label>
			                    <input type="text" id="edit_username" name="username">
			                </div>
			            </div>
			            
			            <div class="row">
			                <div class="col">
			                    <label for="edit_last_name">Last Name:</label>
			                    <input type="text" id="edit_last_name" name="last_name">
			                </div>
			                <div class="col">
			                    <label for="edit_dob">Date of Birth:</label>
			                    <input type="date" id="edit_dob" name="dob">
			                </div>
			            </div>
			            
			            <div class="row">
			                <div class="col">
			                    <label for="edit_gender">Gender:</label>
			                    <select id="edit_gender" name="gender">
			                        <option value="male">Male</option>
			                        <option value="female">Female</option>
			                    </select>
			                </div>
			                <div class="col">
			                    <label for="edit_email">Email:</label>
			                    <input type="email" id="edit_email" name="email">
			                </div>
			            </div>
			            
			            <div class="row">
			                <div class="col">
			                    <label for="edit_phone">Phone:</label>
			                    <input type="text" id="edit_phone" name="phone_number">
			                </div>
			                <div class="col">
			                    <label for="edit_is_admin">Is Admin:</label>
			                    <select id="edit_is_admin" name="is_admin">
			                        <option value="0">No</option>
			                        <option value="1">Yes</option>
			                    </select>
			                </div>
			            </div>
			            
			            <div class="edit-user">
			                <button type="submit">Update</button>
			            </div>
			        </form>
			    </div>
			</div>
        
			<div id="registerUserModal" class="modal">
			    <div class="modal-content">
			        <span class="close" onclick="document.getElementById('registerUserModal').style.display='none'">&times;</span>
			        <h2>Register New User</h2>
			        <form action="${pageContext.request.contextPath}/adminDashboard" method="POST">
			        <input type="hidden" name="action" value="registerUser">
			            <div class="row">
			                <div class="cols">
			                    <label for="fname">First Name:</label>
			                    <input type="text" id="fname" name="fname" required>
			                </div>
			                <div class="cols">
			                    <label for="lname">Last Name:</label>
			                    <input type="text" id="lname" name="lname" required>
			                </div>
			            </div>
			            <div class="row">
			                <div class="cols">
			                    <label for="username">Username:</label>
			                    <input type="text" id="username" name="username" required>
			                </div>
			                <div class="cols">
			                    <label for="birthday">Birthday:</label>
			                    <input type="date" id="birthday" name="birthday" required>
			                </div>
			            </div>
			            <div class="row">
			            <div class="col">
			                    <label for="edit_gender">Gender:</label>
			                    <select id="edit_gender" name="gender">
			                        <option value="male">Male</option>
			                        <option value="female">Female</option>
			                    </select>
			                </div>
			                <div class="cols">
			                    <label for="phonenumber">Phone:</label>
			                    <input type="text" id="phonenumber" name="phonenumber" required>
			                </div>
			            </div>
			                <div class="cols">
			                    <label for="email">Email:</label>
			                    <input type="email" id="email" name="email" required>
			                </div>
			            <div class="row">
			                <div class="cols">
			                    <label for="password1">Password:</label>
			                    <input type="password" id="password1" name="password1" required>
			                </div>
			                <div class="cols">
			                    <label for="password2">confirm Password:</label>
			                    <input type="password" id="password2" name="password2" required>
			                </div>
			            </div>
			            <div class="edit-product">
			                <button type="submit">Register</button>
			            </div>
			        </form>
			    </div>
			</div>
			
			
				<!-- Report Section -->
			<div id="reportSection" class="section">
			    <h2>Sales Report</h2>
			
			    <div class="report-filters">
			        <form id="reportFilterForm">
			            <div class="filter-row">
			                <div>
			                    <label for="startDate">From:</label>
			                    <input type="date" id="startDate" name="startDate" required>
			                </div>
			                <div>
			                    <label for="endDate">To:</label>
			                    <input type="date" id="endDate" name="endDate" required>
			                </div>
			                <button type="submit" id="generateReportBtn" class="filter-btn">Generate Report</button>
			            </div>
			        </form>
			    </div>

			    <!-- Report Table (hidden initially) -->
			    <div id="reportTableContainer" style="display: none; margin-top: 20px;">
			        <table border="1" cellpadding="8" cellspacing="0">
			            <thead>
			                <tr>
			                    <th>Name</th>
			                    <th>Product Name</th>
			                    <th>Quantity</th>
			                    <th>Total Price</th>
			                    <th>Date</th>
			                </tr>
			            </thead>
			            <tbody id="reportTableBody">
			                <!-- Dynamic rows go here -->
			                <tr>
			                    <td>John Doe</td>
			                    <td>Product A</td>
			                    <td>2</td>
			                    <td>$40</td>
			                    <td>2025-05-03</td>
			                </tr>
			            </tbody>
			        </table>
			    </div>
			</div>
    </div>
    
    
    <!-- JS -->
    <script>
    document.getElementById("reportFilterForm").addEventListener("submit", function(e) {
        e.preventDefault();

        document.getElementById("reportTableContainer").style.display = "block";

        
    });
    // Sidebar navigation
    const navLinks = document.querySelectorAll('.nav-link');
    const sections = document.querySelectorAll('.section');

    navLinks.forEach(link => {
        link.addEventListener('click', () => {
            navLinks.forEach(l => l.classList.remove('active'));
            sections.forEach(sec => sec.classList.remove('active'));

            link.classList.add('active');
            const target = link.getAttribute('data-target');
            document.getElementById(target).classList.add('active');
        });
    });

    
    document.querySelectorAll(".user-edit-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            // Show modal
            document.getElementById("editUserModal").style.display = "block";

            // Always select "No" for is_admin by default
            const select = document.getElementById("is_admin");
            select.value = "0"; // Force selection to "No"
        });
    });
    
    

// Close modal when clicking outside
window.addEventListener('click', function(event) {
    if (event.target === document.getElementById('editCategoryModal')) {
        closeCategoryModal();
    }
});

 // Show correct section on sidebar click
    document.querySelectorAll(".nav-link").forEach(link => {
        link.addEventListener("click", () => {
            document.querySelectorAll(".section").forEach(section => section.classList.remove("active"));
            document.getElementById(link.dataset.target).classList.add("active");
        });
    });
 
 // Handle User Edit Button Click
    document.querySelectorAll('.user-edit-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const row = this.closest('tr');
            const userId = row.dataset.userid;
            const cells = row.querySelectorAll('td');

            // Extract first and last name from full name
            const fullName = cells[0].innerText.trim().split(' ');
            const firstName = fullName[0];
            const lastName = fullName.length > 1 ? fullName.slice(1).join(' ') : '';

            // Populate the form
            document.getElementById('edit_user_id').value = userId;
            document.getElementById('edit_first_name').value = firstName;
            document.getElementById('edit_last_name').value = lastName;
            document.getElementById('edit_username').value = cells[1].innerText;
            document.getElementById('edit_dob').value = cells[2].innerText;
            document.getElementById('edit_email').value = cells[3].innerText;
            document.getElementById('edit_phone').value = cells[4].innerText;

            // Set gender dropdown
            const gender = cells[5].innerText.toLowerCase();
            document.getElementById('edit_gender').value = gender;

            // Set is_admin dropdown
            const isAdmin = cells[6].innerText.trim().toLowerCase() === 'true' ? '1' : '0';
            document.getElementById('edit_is_admin').value = isAdmin;

            // Show the modal
            document.getElementById('editUserModal').style.display = 'block';
        });
    });

    // Handle product edit modal
    function closeModal() {
    document.getElementById('editProductModal').style.display = 'none';
}

 // Handle product edit button clicks
    document.querySelectorAll('.product-edit-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const row = this.closest('tr');
            
            // Get product ID from hidden input in the row
            const productId = row.querySelector('input[name="product_id"]').value;
            
            // Get all product data from the table row
            const productData = {
                id: productId,
                name: row.cells[0].textContent.trim(),
                description: row.cells[1].textContent.trim(),
                price: row.cells[2].textContent.trim().replace(/[^\d.]/g, ''),
                quantity: row.cells[3].textContent.trim(),
                mfgDate: row.cells[4].textContent.trim(),
                expDate: row.cells[5].textContent.trim(),
                feature: row.cells[6].textContent.trim(),
                image: row.cells[7].querySelector('img').src,
                category: row.cells[8].textContent.trim()
            };

            // Populate the edit form
            document.getElementById('editProductId').value = productData.id;
            document.getElementById('editProductName').value = productData.name;
            document.getElementById('editDescription').value = productData.description;
            document.getElementById('editPrice').value = productData.price;
            document.getElementById('editQuantityUnit').value = productData.quantity;
            document.getElementById('editManufactureDate').value = productData.mfgDate;
            document.getElementById('editExpiryDate').value = productData.expDate;
            document.getElementById('editKeyFeature').value = productData.feature;
            document.getElementById('currentProductImage').src = productData.image;

            // Set the correct category in dropdown
            const categorySelect = document.getElementById('editCategory');
            for (let i = 0; i < categorySelect.options.length; i++) {
                if (categorySelect.options[i].text === productData.category) {
                    categorySelect.selectedIndex = i;
                    break;
                }
            }

            // Show the modal
            document.getElementById('editProductModal').style.display = 'block';
        });
    });

    // Close modal function
    function closeModal() {
        document.getElementById('editProductModal').style.display = 'none';
        document.getElementById('editProductForm').reset();
    }

    // Close modal when clicking outside
    window.addEventListener('click', function(event) {
        if (event.target === document.getElementById('editProductModal')) {
            closeModal();
        }
    });

    
    // Close Modals on Outside Click
    window.onclick = function(event) {
        document.querySelectorAll('.modal').forEach(modal => {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        });
    }
    
    function toggleRegisterForm() {
        const formDiv = document.getElementById("registerUserForm");
        formDiv.style.display = formDiv.style.display === "none" ? "block" : "none";
    }
    
    document.getElementById("addCategoryBtn").addEventListener("click", function () {
        document.getElementById("addCategoryModal").style.display = "block";
    });
    
    document.getElementById("addProductBtn").addEventListener("click", function () {
        document.getElementById("addProductModal").style.display = "block";
    });
    
    
    document.getElementById("reportFilterForm").addEventListener("submit", function(e) {
        e.preventDefault();

        // Simulate fetching data (you can replace this with actual AJAX call)
        document.getElementById("reportTableContainer").style.display = "block";
    });
    
    
    setTimeout(() => {
        const errorDiv = document.getElementById("errorMessage");
        const successDiv = document.getElementById("successMessage");
        if (errorDiv) errorDiv.style.display = "none";
        if (successDiv) successDiv.style.display = "none";
    }, 3000); // 3000ms = 3 seconds
</script>
</body>
</html>
