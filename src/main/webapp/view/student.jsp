<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Students</title>
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
        }

        h1 {
            color: #333;
            background-color: #4caf50;
            color: white;
            padding: 10px;
            margin-bottom: 20px;
            text-align: center;
        }

        .welcome-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            font-size: 18px;
            color: #333;
        }

        .welcome-section p {
            margin: 0;
            font-size: 20px;
            font-weight: bold;
        }

        .logout-btn {
            display: inline-flex;
            align-items: center;
            background-color: #d9534f;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            font-size: 16px;
            text-decoration: none;
            cursor: pointer;
        }

        .logout-btn:hover {
            background-color: #c9302c;
        }

        .logout-btn i {
            margin-right: 8px;
            font-size: 18px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        label {
            font-weight: bold;
            color: #555;
        }

        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .button-signup {
            display: inline-block;
            background-color: #4caf50; /* Зелена заливка */
            color: white; /* Текст білого кольору */
            padding: 10px 20px; /* Відступи для кнопки */
            border: none; /* Без рамки */
            border-radius: 4px; /* Заокруглені краї */
            text-decoration: none; /* Прибираємо підкреслення тексту */
            font-size: 16px; /* Розмір тексту */
            font-weight: bold; /* Жирний текст */
            text-align: center; /* Вирівнювання тексту */
            cursor: pointer; /* Індикатор кнопки */
            margin-bottom: 20px; /* Відступ знизу */
        }

        .button-signup:hover {
            background-color: #45a049; /* Темніший зелений на наведення */
        }

    </style>
    <!-- Підключення Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
<div id="page">
    <h1><%= (new java.util.Date()).toLocaleString() %></h1>

    <sec:authorize access="isAuthenticated()">
        <div class="welcome-section">
            <sec:authentication property="principal.username" var="username"/>
            <p>Welcome, <c:out value="${username}" />!</p>
            <a href="http://localhost:8080/logout" class="logout-btn">
                <i class="fas fa-sign-out-alt"></i> Log out
            </a>
        </div>
    </sec:authorize>

    <sec:authorize access="hasAuthority('ADMIN')">
        <a href="http://localhost:8080/registration" class="button-signup">
            To sign-up new user
        </a>
        <form method="post" action="StudentAdd">
            <table>
                <tr>
                    <td><label for="name">Name</label></td>
                    <td><input id="name" type="text" name="name"></td>
                </tr>
                <tr>
                    <td><label for="surname">Surname</label></td>
                    <td><input id="surname" type="text" name="surname"></td>
                </tr>
                <tr>
                    <td><label for="age">Age</label></td>
                    <td><input id="age" type="text" name="age"></td>
                </tr>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input id="email" type="email" name="email"></td>
                </tr>
                <tr>
                    <td><label for="group">Group</label></td>
                    <td><input id="group" type="text" name="group"></td>
                </tr>
                <tr>
                    <td><label for="faculty">Faculty</label></td>
                    <td><input id="faculty" type="text" name="faculty"></td>
                </tr>
            </table>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" name="send" value="Send">
        </form>
    </sec:authorize>



    <sec:authorize access="isAuthenticated()">
        <c:if test="${students.size() > 0}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Age</th>
                    <th>Email</th>
                    <th>Group</th>
                    <th>Faculty</th>
                    <th>Scores</th>
                </tr>
                <c:forEach var="student" items="${students}">
                    <c:if test="${student.getEmail() eq username || username eq 'admin'}">
                        <tr>
                            <td><c:out value="${student.getId()}"/></td>
                            <td><c:out value="${student.getName()}"/></td>
                            <td><c:out value="${student.getSurname()}"/></td>
                            <td><c:out value="${student.getAge()}" /></td>
                            <td><c:out value="${student.getEmail()}"/></td>
                            <td><c:out value="${student.getGroup()}"/></td>
                            <td><c:out value="${student.getFaculty()}"/></td>
                            <td><a href="/UserContent?id2=${student.getId()}">scores</a></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:if>
    </sec:authorize>
</div>
</body>
</html>
