<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add New User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        #page {
            background-color: #fff;
            margin: 20px auto;
            padding: 20px;
            width: 80%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1 {
            background-color: #4caf50;
            color: white;
            padding: 10px;
            text-align: center;
            margin-bottom: 20px;
            border-radius: 4px;
        }

        label {
            font-weight: bold;
            color: #555;
            display: block;
            margin-top: 15px;
        }

        .password-container {
            position: relative;
        }

        input[type="text"], input[type="password"], select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="password"] {
            padding-right: 40px; /* Додаткове місце для іконки */
        }

        .toggle-password {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            cursor: pointer;
            font-size: 18px;
            color: #4caf50;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            margin-top: 20px;
            cursor: pointer;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .form-container {
            margin-top: 20px;
        }

        .form-title {
            font-size: 18px;
            color: #333;
            margin-bottom: 10px;
            text-align: center;
        }
    </style>
    <!-- Підключення Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <script>
        function togglePasswordVisibility() {
            const passwordInput = document.getElementById('password');
            const toggleIcon = document.getElementById('togglePasswordIcon');
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                toggleIcon.classList.remove('fa-eye-slash');
                toggleIcon.classList.add('fa-eye');
            } else {
                passwordInput.type = 'password';
                toggleIcon.classList.remove('fa-eye');
                toggleIcon.classList.add('fa-eye-slash');
            }
        }
    </script>
</head>
<body>
<div id="page">
    <!-- Заголовок із кнопкою назад -->
    <h1 style="position: relative; text-align: center;">
        <a href="http://localhost:8080/?continue" style="position: absolute; top: 50%; left: 10px; transform: translateY(-50%); text-decoration: none; color: white; font-size: 24px;">
            <i class="fas fa-arrow-left"></i>
        </a>
        Add New User
    </h1>

    <div class="form-container">
        <form action="signup" method="post">
            <div>
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Enter email" required>
            </div>
            <div>
                <label for="password">Password:</label>
                <div class="password-container">
                    <input type="password" id="password" name="password" placeholder="Enter password" required>
                    <i id="togglePasswordIcon" class="fas fa-eye-slash toggle-password" onclick="togglePasswordVisibility()"></i>
                </div>
            </div>
            <div>
                <label for="role">Role:</label>
                <select id="role" name="role" required>
                    <option value="STUDENT">Student</option>
                    <option value="TEACHER">Teacher</option>
                    <option value="ADMIN">Admin</option>
                </select>
            </div>
            <div>
                <input type="submit" value="Add User">
            </div>
        </form>
    </div>
</div>
</body>
</html>
