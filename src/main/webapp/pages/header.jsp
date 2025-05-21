<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/CollegeTutorial/css/header.css" />
</head>
<body>
<header class="header">
        <div class="logo">
     		<img src="/CollegeTutorial/images/logo.png" alt="Italian Trulli">
        </div>
        <nav class="nav-links">
          <a href="${pageContext.request.contextPath}/Index">Home</a>
          <a href="${pageContext.request.contextPath}/product">Product</a>
          <a href="/CollegeTutorial/pages/blog.jsp">Blog</a>
          <a href="/CollegeTutorial/pages/contactUs.jsp">Contact Us</a>
          <a href="/CollegeTutorial/pages/aboutUs.jsp">About Us</a>
        </nav>
        <div class="icons">
          <div class="search-container">
			<form class="search-container" action="${pageContext.request.contextPath}/product" method="get">
			  <input type="text" name="search" id="search-input" placeholder="Search..." required />
			  <button class="icon-button" type="submit">🔍</button>
			</form>
          </div>
          <button class="icon-button">🛒</button>
        </div>
        <a href="/CollegeTutorial/Login">
            <button class="icon-button">Login</button>
          </a>
          <a href="/CollegeTutorial/userDashboard">
            <button class="icon-button">Dashboard</button>
          </a>
      </header>
</body>
</html>