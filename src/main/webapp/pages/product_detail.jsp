<%@ page import="DAO.productModelDAO" %>
<%@ page import="models.ProductModel" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/CollegeTutorial/css/product_detail.css" />
    <title>Product Detail Page</title>
    <%@ include file="header.jsp" %>
</head>

<%
    int productId = 0;
    ProductModel product = null;

    try {
        productId = Integer.parseInt(request.getParameter("id"));
        productModelDAO dao = new productModelDAO();
        product = dao.getProductById(productId);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<body>
<% if (product != null) { %>
    <div class="container">
        <div class="left-section">
            <img src="<%= request.getContextPath() + "/" + product.getImage() %>" alt="Product Image" class="product-image">
            <p>Image Path: <%= product.getImage() %></p>
        </div>

        <div class="right-section">
            <h1 class="product-name"><%= product.getProductName() %></h1>
            <p class="product-price">RS <%= product.getPrice() %></p>
            <p class="product-description"><%= product.getDescription() %></p>

            <!-- Quantity Selection -->
            <div class="quantity-section">
                <button class="quantity-btn" onclick="updateQuantity(-1)">-</button>
                <input type="number" id="quantityInput" name="quantity" class="quantity-input" value="1" min="1" readonly>
                <button class="quantity-btn" onclick="updateQuantity(1)">+</button>
            </div>

            <!-- Buttons for Add to Cart and Buy Now -->
            <div class="btn-group">
                <%
                    Integer userId = (Integer) session.getAttribute("user_id");
                    if (userId != null) {
                %>
                    <!-- Add to Cart Form -->
                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post" onsubmit="syncQuantityToForm('formQuantity')">
                        <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                        <input type="hidden" id="formQuantity" name="quantity" value="1">
                        <button type="submit" class="action-btn add-cart">Add to Cart</button>
                    </form>
                <%
                    } else {
                %>
                    <a href="<%= request.getContextPath() %>/Login" class="action-btn add-cart">Add to Cart</a>
                <%
                    }
                %>

                <!-- Buy Now Form -->
                <form action="buyNowServlet" method="post" onsubmit="syncQuantityToForm('buyFormQuantity')">
                    <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                    <input type="hidden" id="buyFormQuantity" name="quantity" value="1">
                    <button type="submit" class="action-btn buy-now">Buy Now</button>
                </form>
            </div>

            <!-- Key Features Section -->
            <div class="key-features">
                <h2>Key Features</h2>
                <%= product.getKey_feature() %>
            </div>
        </div>
    </div>

    <!-- JavaScript for quantity control -->
    <script>
        function updateQuantity(change) {
            const input = document.getElementById("quantityInput");
            let current = parseInt(input.value) || 1;
            current += change;
            if (current < 1) current = 1;
            input.value = current;
        }

        function syncQuantityToForm(hiddenFieldId) {
            const quantity = document.getElementById("quantityInput").value;
            document.getElementById(hiddenFieldId).value = quantity;
        }
    </script>
<% } else { %>
    <h2 style="text-align:center;">Product Not Found</h2>
<% } %>
</body>

<%@ include file="footer.jsp" %>
</html>
