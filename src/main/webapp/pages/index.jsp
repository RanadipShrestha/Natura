<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Natura Skincare</title>
  <link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<%@ include file="header.jsp" %>
<body>
  <section class="hero">
    <div class="hero-text">
      <h1>Glow Naturally,<br>Shine Confidently</h1>
      <p>Discover skincare that nourishes, protects,<br>because your skin deserves the best.</p>
      <button class="shop-btn">Shop Now</button>
    </div>
    <div class="hero-image">
        <div class="hero-images">
            <div class="hero-top-images">
              <img src="../Image/1st-imagehomepage.webp" alt="Image 1">
              <img src="../Image/2nd_Home_Page.jpg" alt="Image 2">
            </div>
            <div class="hero-bottom-image">
              <img src="../Image/3rdpic-homepage.png" alt="Image 3">
            </div>
          </div>
    </div>
  </section>
  <section class="popular-categories">
    <h2>SHOP BY CATEGORIES</h2>
    <h3>Popular Categories</h3>
    <div class="categories">
      <div class="category">
        <img src="../Image/Anua_Serum.jpg" alt="Serum">
        <p>Serum</p>
        <button>Explore Now</button>
      </div>
      <div class="category">
        <img src="../Image/Anua_Mask.jpg" alt="Mask">
        <p>Masks</p>
        <button>Explore Now</button>
      </div>
      <div class="category">
        <img src="../Image/Moisturizer_Anua.jpg" alt="Moisturizer">
        <p>Moisturizer</p>
        <button>Explore Now</button>
      </div>
      <div class="category">
        <img src="../Image/Tonner_Anua.jpg" alt="Toner">
        <p>Toner</p>
        <button>Explore Now</button>
      </div>
      <div class="category">
        <img src="../Image/Cleanser_Anua.jpg" alt="Cleanser">
        <p>Cleanser</p>
        <button>Explore Now</button>
      </div>
    </div>
  </section>

  <section class="about-section">
    <div class="about-grid">
      <div class="about-image">
        <img src="../Image/9.jpg" alt="Cleaning Face">
      </div>
      <div class="about-text">
        <h3>All Natural Skincare</h3>
        <p>We believe in the power of nature. Our products are carefully crafted to give your skin gentle and effective care.</p>
        <button>Explore More</button>
      </div>
      <div class="about-text">
        <h3>We're</h3>
        <p>proud to have a growing community of skincare lovers who trust and use our products daily.</p>
        <button>Learn More</button>
      </div>
      <div class="about-image">
        <img src="../Image/anuaserumface.jpg" alt="Applying Cream">
      </div>
    </div>
  </section>
</body>
<%@ include file="footer.jsp" %>
</html>