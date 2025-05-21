<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "models.IndexModel" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Natura Skincare</title>
  <link rel="stylesheet" type="text/css" href="/CollegeTutorial/css/style.css" />
  <link rel="stylesheet" type="text/css" href="/CollegeTutorial/css/footer.css" />
  <%@ include file="header.jsp" %>
</head>
<body>
  <section class="hero">
    <div class="hero-text">
      <h1>Glow Naturally,<br>Shine Confidently</h1>
      <p>Discover skincare that nourishes, protects,<br>because your skin deserves the best.</p>
      <a href="${pageContext.request.contextPath}/product">
        <button class="shop-btn">Shop Now</button>
      </a>
    </div>
    <div class="hero-image">
      <div class="hero-images">
        <div class="hero-top-images">
          <img src="/CollegeTutorial/images/1st-imagehomepage.webp" alt="Image 1">
          <img src="/CollegeTutorial/images/2nd_Home_Page.jpg" alt="Image 2">
        </div>
        <div class="hero-bottom-image">
          <img src="/CollegeTutorial/images/3rdpic-homepage.png" alt="Image 3">
        </div>
      </div>
    </div>
  </section>

  <!-- ✅ Popular Categories Section -->
  <section class="popular-categories">
    <h2>SHOP BY CATEGORIES</h2>
    <h3>Popular Categories</h3>
    <div class="categories">
      <%
        ArrayList<IndexModel> categoryList = (ArrayList<IndexModel>) request.getAttribute("categoryList");

        if (categoryList != null && !categoryList.isEmpty()) {
          for (IndexModel im : categoryList) {
      %>
        <div class="category">
          <img src="<%= im.getCategoryImage() %>" alt="Category Image">
          <p><%= im.getCategoryName() %></p>
          <a href="product?categoryId=<%= im.getCategory_id() %>">
            <button class="explore">Explore Now</button>
          </a>
        </div>
      <%
          }
        } else {
      %>
        <p>Nothing has been loaded</p>
      <%
        }
      %>
    </div>
  </section>

  <!-- ✅ About Section -->
  <section class="about-section">
    <div class="about-grid">
      <div class="about-image">
        <img src="/CollegeTutorial/images/9.jpg" alt="Cleaning Face">
      </div>
      <div class="about-text">
        <h3>All Natural Skincare</h3>
        <p>We believe in the power of nature. Our products are carefully crafted to give your skin gentle and effective care.</p>
        <a href="/CollegeTutorial/pages/blog.jsp">
            <button class="explore">Explore More</button>
          </a>
      </div>
      <div class="about-text">
        <h3>We're</h3>
        <p>proud to have a growing community of skincare lovers who trust and use our products daily.</p>
        <a href="/CollegeTutorial/pages/aboutUs.jsp">
            <button class="explore">Learn More</button>
          </a>
      </div>
      <div class="about-image">
        <img src="/CollegeTutorial/images/anuaserumface.jpg" alt="Applying Cream">
      </div>
    </div>
  </section>

</body>
<%@ include file="footer.jsp" %>
</html>
