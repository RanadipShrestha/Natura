<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="models.OrderModel" %>
<%@ page import="models.OrderProductModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ page import="models.RegisterModel" %>
<%
    RegisterModel user = (RegisterModel) session.getAttribute("currentUser");
    
%>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>User Dashboard | GlowUp Skincare</title>
  <style>
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
    }

    body {
      display: flex;
      min-height: 100vh;
      background: linear-gradient(to right, #fff7f7, #fff0ee);
      color: #333;
    }

    .dashboard-container {
      display: flex;
      width: 100%;
    }

    .sidebar {
      width: 250px;
      background: #fbd6d2;
      padding: 2rem 1.5rem;
      display: flex;
      flex-direction: column;
      box-shadow: 2px 0 10px rgba(0, 0, 0, 0.05);
      border-top-right-radius: 20px;
      border-bottom-right-radius: 20px;
    }

    .logo {
      font-size: 2.2rem;
      font-weight: 600;
      color: #d17b6a;
      margin-bottom: 3rem;
      text-align: center;
    }

    .nav-menu a {
      display: block;
      text-decoration: none;
      color: #333;
      padding: 1rem 1.2rem;
      border-radius: 12px;
      margin-bottom: 1rem;
      transition: all 0.3s ease;
      font-weight: 500;
      cursor: pointer;
    }

    .nav-menu a:hover,
    .nav-menu a.active {
      background-color: #ffdfdb;
      color: #d17b6a;
      font-weight: 600;
    }

    .main-content {
      flex: 1;
      padding: 2.5rem 3rem;
    }

    .section {
      display: none;
    }

    .section.active {
      display: block;
    }

    .dashboard-header h1 {
      font-size: 2.2rem;
      margin-bottom: 0.5rem;
      color: #d17b6a;
    }

    .dashboard-header p {
      color: #777;
      margin-bottom: 2rem;
      font-size: 1rem;
    }

    .stats-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
      gap: 2rem;
    }

    .stat-card {
      background-color: #fff;
      padding: 2rem;
      border-radius: 20px;
      box-shadow: 0 8px 20px rgba(0,0,0,0.05);
      text-align: center;
      transition: transform 0.3s ease, box-shadow 0.3s ease;
    }

    .stat-card:hover {
      transform: translateY(-5px);
      box-shadow: 0 10px 30px rgba(0,0,0,0.1);
    }

    .stat-card h3 {
      font-size: 1.1rem;
      color: #d17b6a;
      margin-bottom: 0.8rem;
    }

    .stat-card p {
      font-size: 2.4rem;
      font-weight: 600;
    }

    .update-button {
      background: #d17b6a;
      color: white;
      padding: 0.8rem 1.5rem;
      border: none;
      border-radius: 10px;
      font-size: 1rem;
      cursor: pointer;
      margin-bottom: 1rem;
    }

    .update-button:hover {
      background: #ba6659;
    }

    .profile-update {
      display: none;
      background: #fff;
      padding: 2rem;
      border-radius: 20px;
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.05);
      max-width: 600px;
      margin-top: 1rem;
    }

    .profile-update h2 {
      color: #d17b6a;
      margin-bottom: 1.5rem;
      font-size: 1.6rem;
    }

    .profile-update form {
      display: grid;
      gap: 1.2rem;
    }

    .profile-update label {
      font-weight: 600;
      display: block;
      margin-bottom: 0.3rem;
    }

    .profile-update input,
    .profile-update select,
    .profile-update textarea {
      padding: 0.8rem;
      border-radius: 10px;
      border: 1px solid #ccc;
      width: 100%;
    }

    .profile-update button {
      background: #d17b6a;
      color: white;
      padding: 0.8rem 1.2rem;
      border: none;
      border-radius: 10px;
      font-size: 1rem;
      cursor: pointer;
      margin-top: 1rem;
    }

    .profile-update button:hover {
      background: #ba6659;
    }

    .profile-info p {
      margin-bottom: 0.8rem;
      font-size: 1.1rem;
    }

    .profile-info strong {
      color: #d17b6a;
    }
    
    .details {
  margin-top: 2rem;
}

.recent-orders {
  background: #fff;
  padding: 2rem;
  border-radius: 20px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.05);
  overflow-x: auto;
}

.recent-orders h2 {
  color: #d17b6a;
  margin-bottom: 1.5rem;
}

.recent-orders table {
  width: 100%;
  border-collapse: collapse;
  font-size: 1rem;
}

.recent-orders th,
.recent-orders td {
  text-align: left;
  padding: 1rem;
  border-bottom: 1px solid #eee;
}

.recent-orders th {
  background-color: #fff0ee;
  color: #d17b6a;
  font-weight: 600;
}

.recent-orders tr:hover {
  background-color: #fff7f7;
}

.recent-orders img {
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 8px;
}

/* Order Section Table Styling */
.table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1rem;
  font-size: 1rem;
  color: #444;
}

.table thead {
  background-color: #fbd6d2;
  color: #d17b6a;
  font-weight: 600;
}

.table th,
.table td {
  padding: 12px 15px;
  border-bottom: 1px solid #f0e6e5;
  text-align: left;
  vertical-align: middle;
}

.table tbody tr:hover {
  background-color: #fff0ee;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.table tbody tr:nth-child(even) {
  background-color: #fff7f7;
}

.table tbody td {
  /* subtle shadow for cell content */
  box-shadow: inset 0 -1px 0 rgba(255, 223, 219, 0.2);
}

.alert {
  background-color: #fff0ee;
  border: 1px solid #d17b6a;
  color: #d17b6a;
  padding: 1rem 1.5rem;
  border-radius: 15px;
  margin-top: 1rem;
  font-weight: 500;
  text-align: center;
}
 

    @media (max-width: 768px) {
      .sidebar {
        display: none;
      }

      .main-content {
        padding: 1.5rem;
      }
    }
  </style>
</head>
<body>
  <div class="dashboard-container">
    <aside class="sidebar">
      <h2 class="logo">Natura.</h2>
      <nav class="nav-menu">
        <a onclick="showSection('dashboard')" class="active">Dashboard</a>
        <a onclick="showSection('profile')">Profile</a>
        <a onclick="showSection('order')">Order</a>
        <a onclick="showSection('cart')">Cart</a>
        <a href="/CollegeTutorial/Login" id="logoutBtn">Logout</a>
      </nav>
    </aside>

    <main class="main-content">
      <!-- Dashboard Section -->
      <section class="section active" id="dashboard">
        <div class="dashboard-header">
          <h1>Welcome back, <%= user.getFname() %></h1>
          <p>Here's your glowing journey start with Us</p>
        </div>
      </section>

      <!-- Profile Section -->
      <section class="section" id="profile">
        <div class="profile-info">
          <h2 style="color:#d17b6a; margin-bottom:1rem;">Profile Info</h2>
          <p><strong>First Name:</strong> <%= user.getFname() %></p>
          <p><strong>Last Name:</strong> <%= user.getLname() %></p>
          <p><strong>Email:</strong> <%= user.getEmail() %></p>
          <p><strong>Phone:</strong> <%= user.getPhonenumber() %></p>
          <p><strong>Birthday:</strong> <%= user.getBirthday() %></p>
          <p><strong>Member Since:</strong> <%= user.getUser_create_date() %></p>
          <p><strong>Updated At:</strong> <%= user.getUser_account_update() %></p>
        </div>
        
        <button class="update-button" onclick="toggleProfileForm()">Update Profile</button>

        <div class="profile-update" id="profileForm">
          <h2>Edit Your Details</h2>
          <form action="${pageContext.request.contextPath}/userDashboard" method="POST">
            <div>
              <label for="first_name">First Name</label>
              <input type="text" id="first_name" name="first_name" value="<%= user.getFname() %>" required />
            </div>
            <div>
              <label for="last_name">Last Name</label>
              <input type="text" id="last_name" name="last_name" value="<%= user.getLname() %>" required />
            </div>
            <div>
              <label for="dob">Birthday</label>
              <input type="date" id="dob" name="dob" value="<%= user.getBirthday() %>" required />
            </div>
            <div>
              <label for="gender">Gender</label>
              <select id="gender" name="gender" required>
                <option value="Male" <%= "Male".equals(user.getGender()) ? "selected" : "" %>>Male</option>
                <option value="Female" <%= "Female".equals(user.getGender()) ? "selected" : "" %>>Female</option>
                <option value="Other" <%= "Other".equals(user.getGender()) ? "selected" : "" %>>Other</option>
              </select>
            </div>
            <div>
              <label for="email">Email</label>
              <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required />
            </div>
            <div>
              <label for="phone_number">Phone</label>
              <input type="tel" id="phone_number" name="phone_number" value="<%= user.getPhonenumber() %>" required />
            </div>
            <input type="hidden" name="user_id" value="<%= user.getUser_id() %>" />
            <button type="submit">Save Changes</button>
          </form>
        </div>
      </section>
      
      <!-- Order Section -->
<section class="section" id="order">
  <div class="dashboard-header">
    <h1>Recent Orders</h1>
    <p>Track the products you've purchased with full details.</p>
  </div>

 <%
        List<OrderProductModel> orderProducts = (List<OrderProductModel>) request.getAttribute("orderProducts");

        if (orderProducts != null && !orderProducts.isEmpty()) {
    %>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>Order ID</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price (Per Unit)</th>    
            <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (OrderProductModel item : orderProducts) {
        %>
            <tr>
                <td><%= item.getOrderId() %></td>
                <td><%= item.getProductName() %></td>
                <td><%= item.getQuantity() %></td>
                <td>$<%= item.getPrice() %></td>
                <td>$<%= item.getTotal() %></td> <!-- Showing total per order -->
            </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <%
        } else {
    %>
        <div class="alert alert-info">No orders found.</div>
    <%
        }
    %>
  
</section>

<!-- Cart Section -->
<section class="section" id="cart">
  <div class="dashboard-header">
    <h1>Your Cart</h1>
    <p>Review the items you have added to your cart.</p>
  </div>

  <%
    models.CartModel userCart = (models.CartModel) request.getAttribute("userCart");

    if (userCart != null && userCart.getProducts() != null && !userCart.getProducts().isEmpty()) {
  %>

  <table class="table table-bordered table-striped">
    <thead class="table-dark">
      <tr>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Price (Each)</th>
        <th>Subtotal</th>
      </tr>
    </thead>
    <tbody>
      <%
        double cartTotal = 0;
        for (models.CartProductModel product : userCart.getProducts()) {
          double subtotal = product.getQuantity() * product.getPrice();
          cartTotal += subtotal;
      %>
      <tr>
        <td><%= product.getProductName() %></td>
        <td><%= product.getQuantity() %></td>
        <td>$<%= String.format("%.2f", product.getPrice()) %></td>
        <td>$<%= String.format("%.2f", subtotal) %></td>
      </tr>
      <%
        }
      %>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="3" style="text-align: right; font-weight: bold;">Total:</td>
        <td>$<%= String.format("%.2f", cartTotal) %></td>
      </tr>
    </tfoot>
  </table>

  <%
    } else {
  %>
    <div class="alert alert-info">Your cart is empty.</div>
  <%
    }
  %>
  
  <form action="${pageContext.request.contextPath}/BuyNowServlet" method="post">
    <input type="hidden" name="user_id" value="<%= user.getUser_id() %>">
    <button type="submit" class="update-button">Buy Now</button>
</form>


</section>

      
    </main>
  </div>

  <script>
    function showSection(sectionId) {
      const sections = document.querySelectorAll('.section');
      sections.forEach(section => section.classList.remove('active'));
      document.getElementById(sectionId).classList.add('active');

      const links = document.querySelectorAll('.nav-menu a');
      links.forEach(link => link.classList.remove('active'));
      event.target.classList.add('active');

      if (sectionId !== "profile") {
        document.getElementById('profileForm').style.display = 'none';
      }
    }

    function toggleProfileForm() {
      const form = document.getElementById('profileForm');
      form.style.display = form.style.display === 'block' ? 'none' : 'block';
    }
  </script>
</body>
</html>