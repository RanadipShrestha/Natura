<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "models.ProductModel" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/CollegeTutorial/css/product.css" />
<%@ include file="header.jsp" %>
</head>
<body>
<section class="products-section">
  <h2>SHOP ALL PRODUCTS</h2>
  <div class="product-grid">

  	<%
  	ArrayList<ProductModel> productList = (ArrayList<ProductModel>) request.getAttribute("productList");
  	if (productList !=null && !productList.isEmpty()){
  	%>
  	
    <%for (ProductModel pm:productList) {%>
    <div class="product-card">
      <img src="<%=pm.getImage() %>" alt="Product">
      <h3><%=pm.getProductName() %></h3>
      
      <p class="price">Rs : <%=pm.getPrice() %></p>
      
		<a href="/CollegeTutorial/pages/product_detail.jsp?id=<%=pm.getProduct_id() %>" class="btn">
		View
		</a>

    </div>
    <%
  	}
  	}else{
  		%>
  		<p>There is no product.</p>
    <%} %>
    
  </div>
</section>
</body>
<%@ include file="footer.jsp" %>
<script>
  const addToCartButtons = document.querySelectorAll('.btn');
  addToCartButtons.forEach(button => {
    button.addEventListener('click', function() {
      if (this.textContent.trim() === "Add To Cart") {
        alert("Product is added to cart");
      }
    });
  });
</script>
</html>