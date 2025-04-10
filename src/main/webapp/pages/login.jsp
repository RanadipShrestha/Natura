<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="../css/login.css" />
</head>
<body style="background-color:purple;">
<% String error = request.getParameter("error"); %>
<%if (error != null && error.equals("username")){ %>
	<p>Invalid User Name </p>
<% }%>
<%if (error != null && error.equals("password")){ %>
	<p>Invalid Password </p>
<% }%>
	<div class="login-box">
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/Login" method="post">
            <div class="row">
                <div class="col">
                    <label for="username">Username:</label> 
                    <input type="text" id="username" name="username">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="password">Password:</label> 
                    <input type="password" id="password" name="password">
                </div>
            </div>
            <button type="submit" class="login-button">Login</button>
        </form>
    </div>
</body>
</html>