<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/CollegeTutorial/css/login.css" />
</head>
<body>

<div class="login-box">
    <h2>Login</h2>
    <% 
        String errorMessage = null;
        if (session != null) {
            errorMessage = (String) session.getAttribute("loginError");
            if (errorMessage != null) {
    %>
        <div class="error"><%= errorMessage %></div>
    <%
                session.removeAttribute("loginError");
            }
        }
    %>
    <form action="${pageContext.request.contextPath}/Login" method="post">
        <label for="username">Username:</label> 
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label> 
        <input type="password" id="password" name="password" required>

        <button type="submit" class="login-button">Login</button>
    </form>
    <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a></p>
</div>
</body>
</html>
