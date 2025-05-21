<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>About Us - Natura Skincare</title>
  <link rel="stylesheet" type="text/css" href="/CollegeTutorial/css/aboutUs.css" />
  <link rel="stylesheet" type="text/css" href="/CollegeTutorial/css/footer.css" />
  <%@ include file="header.jsp" %>
</head>
<body>

<section class="about-us">
  <div class="about-container">
    <div class="about-header">
      <h1>About Us</h1>
      <p>Natural beauty, powered by nature.</p>
    </div>

    <div class="about-content">
      <div class="about-image">
        <img src="/CollegeTutorial/images/2nd_Home_Page.jpg" alt="Our Skincare Journey">
      </div>
      <div class="about-text">
        <h2>Welcome to Natura Skincare</h2>
        <p>
  			At Natura Skincare, we believe that skincare should be pure, gentle, and effective. 
  			Our mission is to bring you the finest Korean skincare products, carefully selected to enhance your natural beauty.
		</p>
		<p>
 			We partner with trusted Korean brands known for their clean, cruelty-free, and innovative formulations. 
  			Each product we offer is chosen with care to ensure quality, transparency, and results you can feel good about.
		</p>
		<p>
  			Whether you're just starting your skincare journey or are a beauty enthusiast, we're here to help you glow — naturally.
  			Thank you for choosing us to be part of your routine.
		</p>

      </div>
    </div>

    <div class="about-values">
      <h2>What We Stand For</h2>
      <ul>
        <li>100% Natural Ingredients</li>
        <li>Cruelty-Free Products</li>
        <li>Eco-Friendly Packaging</li>
        <li>Dermatologically Tested</li>
        <li>Customer Satisfaction</li>
      </ul>
    </div>

    <div class="about-cta">
      <a href="${pageContext.request.contextPath}/product">
        <button class="shop-btn">Explore Our Products</button>
      </a>
    </div>
  </div>
</section>
</body>
<%@ include file="footer.jsp" %>
</html>
