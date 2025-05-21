<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Server Error</title>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .error-container {
            text-align: center;
            padding: 40px 30px;
            border: 2px solid #dc3545;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 8px 16px rgba(0,0,0,0.1);
            max-width: 500px;
            width: 90%;
        }

        .error-title {
            font-size: 36px;
            color: #dc3545;
            margin-bottom: 20px;
        }

        .error-message {
            font-size: 18px;
            color: #333;
            margin-bottom: 25px;
        }

        .back-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #dc3545;
            color: #fff;
            text-decoration: none;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }

        .back-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-title">Server Error</div>
        <div class="error-message">Something went wrong on our end. Please try again later.</div>
        <a href="/CollegeTutorial/adminDashboard" class="back-btn">Go Back to Home</a>
    </div>
</body>
</html>
