<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>404 - Page Not Found</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #e6f9e6; /* light green background */
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            color: #2e7d32; /* darker green text */
            text-align: center;
        }
        .container {
            max-width: 600px;
        }
        h1 {
            font-size: 6rem;
            color: #43a047; /* medium green */
        }
        h2 {
            font-size: 2rem;
            margin-bottom: 10px;
        }
        p {
            font-size: 1rem;
            color: #4e944f;
            margin-bottom: 20px;
        }
        a {
            text-decoration: none;
            background-color: #66bb6a;
            color: white;
            padding: 12px 24px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: #388e3c;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>404</h1>
        <h2>Page Not Found</h2>
        <p>The page you are looking for might have been moved, deleted, or doesn't exist.</p>
        <a href="/CollegeTutorial/Index">Return to Homepage</a>
    </div>
</body>
</html>
