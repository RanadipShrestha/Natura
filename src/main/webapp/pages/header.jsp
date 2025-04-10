<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/header.css" />
</head>
<body>
<header class="header">
        <div class="logo">
          <span class="natura-text">Natura</span><span class="dot">.</span>
        </div>
        <nav class="nav-links">
          <a href="../HTML/index.html">Home</a>
          <a href="../HTML/product.html">Product</a>
          <a href="../HTML/Blog.html">Blog</a>
          <a href="../HTML/ContactUS.html">Contact Us</a>
          <a href="../HTML/AboutUs.html">About Us</a>
        </nav>
        <div class="icons">
          <div class="search-container">
            <input type="text" id="search-input" placeholder="Search..." />
            <button class="icon-button" onclick="searchFunction()">🔍</button>
          </div>
          <button class="icon-button">🛒</button>
        </div>
      </header>
</body>
</html>