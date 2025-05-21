<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Simple Cart</title>
  
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f5f5f5;
    }
    
    .cart {
      max-width: 600px;
      margin: auto;
      margin-top: 20px;
      margin-bottom: 20px;
      background: white;
      padding: 20px;
      border-radius: 8px;
    }
    .product {
      display: flex;
      justify-content: space-between;
      margin-bottom: 15px;
      padding-bottom: 10px;
      border-bottom: 1px solid #ddd;
    }
    .product img {
      width: 60px;
      height: 60px;
      object-fit: cover;
      border-radius: 6px;
    }
    .info {
      flex-grow: 1;
      margin-left: 15px;
    }
    .controls {
      display: flex;
      align-items: center;
      gap: 10px;
    }
    .controls button {
      width: 30px;
      height: 30px;
    }
    .summary {
      margin-top: 20px;
      font-weight: bold;
      text-align: right;
    }
    .checkout {
      margin-top: 20px;
      width: 100%;
      padding: 10px;
      background: green;
      color: white;
      border: none;
      cursor: pointer;
      border-radius: 5px;
    }
  </style>
  <%@ include file="header.jsp" %>
</head>
<body>
<!-- Cart Section -->
<section class="section" id="cart">
  <div class="dashboard-header">
    <h1>Your Cart</h1>
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
</section>

<script>
  function changeQty(button, change) {
    const qtyElem = button.parentElement.querySelector('.qty');
    let qty = parseInt(qtyElem.textContent) + change;
    qty = qty < 0 ? 0 : qty;
    qtyElem.textContent = qty;
    updateTotal();
  }

  function updateTotal() {
    let total = 0;
    document.querySelectorAll('.product').forEach(p => {
      const price = parseFloat(p.dataset.price);
      const qty = parseInt(p.querySelector('.qty').textContent);
      total += price * qty;
    });
    document.querySelector('.summary').textContent = 'Total: $' + total.toFixed(2);
  }

  function checkout() {
    let items = [];
    document.querySelectorAll('.product').forEach(p => {
      const name = p.querySelector('.info div').textContent;
      const qty = parseInt(p.querySelector('.qty').textContent);
      if (qty > 0) items.push({ name, qty });
    });
    if (items.length === 0) {
      alert("Your cart is empty.");
    } else {
      alert("Checking out " + items.length + " item(s).");
    }
  }
</script>

<%@ include file="footer.jsp" %>
</body>
</html>
