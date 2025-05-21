<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Our Blog - Natura Skincare</title>
  <link rel="stylesheet" href="/CollegeTutorial/css/blog.css">
  <%@ include file="header.jsp" %>
</head>
<body>

<section class="blog-section">
  <div class="blog-header">
    <h1>Our Blog</h1>
    <p>Tips, tutorials, and stories for naturally beautiful skin.</p>
  </div>

  <div class="blog-container">
    <!-- Blog Post Card Start -->
    <div class="blog-card">
      <img src="/CollegeTutorial/images/Blog-one.jpg" alt="Blog 1">
      <div class="blog-content">
        <h2>5 Everyday Habits That Improve Your Skin</h2>
        <p>Explore natural ways to enhance your skin health through simple daily routines.</p>
        <span class="read-more" onclick="toggleContent(this)">Read More</span>
        <div class="full-content">
          <p>
            Here’s a deep dive into habits like drinking water, using sunscreen daily, getting enough sleep, choosing gentle cleansers, 
            and moisturizing with natural oils that nourish your skin from within.
          </p>
        </div>
      </div>
    </div>

    <div class="blog-card">
      <img src="/CollegeTutorial/images/Blog-2.jpg" alt="Blog 2">
      <div class="blog-content">
        <h2>Why Natural Ingredients Matter</h2>
        <p>Discover why choosing skincare with natural ingredients is better for your skin and the planet.</p>
        <span class="read-more" onclick="toggleContent(this)">Read More</span>
        <div class="full-content">
          <p>
            Natural ingredients like aloe vera, turmeric, and tea tree oil are powerful yet gentle. Learn how they help your skin stay clear 
            and glowing without harsh chemicals.
          </p>
        </div>
      </div>
    </div>

    <div class="blog-card">
      <img src="/CollegeTutorial/images/Moisturizer_Anua.jpg" alt="Blog 3">
      <div class="blog-content">
        <h2>How to Build a Skincare Routine</h2>
        <p>A step-by-step guide to build your perfect skincare routine for glowing, healthy skin.</p>
        <span class="read-more" onclick="toggleContent(this)">Read More</span>
        <div class="full-content">
          <p>
            From cleansing to moisturizing, this guide helps you build a complete skincare routine that fits your skin type and lifestyle. 
            Don’t forget SPF!
          </p>
        </div>
      </div>
    </div>
    <!-- Add more blog cards as needed -->
  </div>
</section>

<script>
  function toggleContent(button) {
    const content = button.nextElementSibling;
    if (content.style.display === "block") {
      content.style.display = "none";
      button.textContent = "Read More";
    } else {
      content.style.display = "block";
      button.textContent = "Read Less";
    }
  }
</script>

<%@ include file="footer.jsp" %>
</body>
</html>
