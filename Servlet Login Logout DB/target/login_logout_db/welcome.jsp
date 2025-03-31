<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <div class="card mt-5">
            <div class="card-body text-center">
                <h1 class="card-title">Hello, ${user.fullname}!</h1>
                <p class="card-text">Welcome</p>
                <a href="logout" class="btn btn-danger">Logout</a>
            </div>
        </div>
    </div>
</body>
</html>