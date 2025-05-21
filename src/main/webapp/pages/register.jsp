<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="/CollegeTutorial/css/register.css" />
</head>
<body>

<div class="register-box">
    <h2>Register Form</h2>

    <% 
        String error = request.getParameter("error"); 
        if (error != null) { 
    %>
        <div style="color: white; background-color: red; padding: 10px; margin: 10px 0; border-radius: 5px; text-align: center;">
            <% if (error.equals("name")) { %>
                Invalid Name.
            <% } else if (error.equals("username")) { %>
                Invalid Username.
            <% } else if (error.equals("DOB")) { %>
                Invalid Date of Birth.
            <% } else if (error.equals("gender")) { %>
                Invalid Gender.
            <% } else if (error.equals("email")) { %>
                Invalid Email.
            <% } else if (error.equals("phone")) { %>
                Invalid Phone Number.
            <% } else if (error.equals("password1")) { %>
                Password must be strong.
            <% } else if (error.equals("password2")) { %>
                Invalid Retyped Password.
            <% } else if (error.equals("passwordMismatch")) { %>
                Password and Retyped Password do not match!
            <% } else if (error.equals("insertFail")) { %>
                Registration failed. Please try again.
            <% } else if (error.equals("userNameExists")) { %>
                Username is already used by someone else.
            <% } %>
        </div>
    <% } %>

    <form action="${pageContext.request.contextPath}/register" method="POST">
    <input type="hidden" value="">
        <div class="row">
            <div class="col">
                <label for="fname">First Name:</label> 
                <input type="text" id="fname" name="fname">
            </div>
            <div class="col">
                <label for="lname">Last Name:</label> 
                <input type="text" id="lname" name="lname">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <label for="username">User Name:</label> 
                <input type="text" id="username" name="username">
            </div>
            <div class="col">
                <label for="birthday">Birthday:</label> 
                <input type="date" id="birthday" name="birthday" class="birthday-input">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <label for="gender">Gender:</label>
                <input type="text" id="gender" name="gender" placeholder="Male">
            </div>
            <div class="col">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <label for="phonenumber">Phone Number:</label>
                <input type="text" id="phonenumber" name="phonenumber">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <label for="password1">Password:</label>
                <input type="password" id="password1" name="password1">
            </div>
            <div class="col">
                <label for="password2">Retype Password:</label>
                <input type="password" id="password2" name="password2">
            </div>
        </div>
        <div class="register-btn">
            <button type="submit" class="register-button">Submit</button>
        </div>
    </form>
    <p>Have an account? <a href="${pageContext.request.contextPath}/Login">Login</a></p>
</div>

</body>
</html>
