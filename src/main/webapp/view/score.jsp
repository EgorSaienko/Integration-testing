<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Scores</title>
    <!-- Підключення Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
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
            background-color: #4caf50;
            color: white;
            padding: 10px;
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        .list, .list td, .list th {
            border: 1px solid black;
            padding: 10px;
            text-align: left;
        }

        /* Ширина стовпця для кнопок */
        .actions {
            text-align: center;
            width: 150px;
        }

        /* Заповнення комірок для Score та ECTS */
        .list td input[type="text"] {
            width: 100%;
            box-sizing: border-box;
            border: none;
            background-color: transparent;
            font-size: 16px;
            text-align: center;
        }

        /* Групування кнопок */
        .btn-group {
            display: flex;
            justify-content: center;
            gap: 5px;
        }

        /* Стилі кнопок */
        button {
            background-color: #4caf50;
            color: white;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
            border-radius: 4px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 12px;
        }

        button i {
            margin-right: 5px;
        }

        button.delete {
            background-color: #f44336;
        }

        button:hover {
            background-color: #45a049;
        }

        button.delete:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
<div id="page">
    <h1>Scores of <c:out value="${user.getId()}"/> <c:out value="${user.getName()}"/> <c:out value="${user.getSurname()}"/></h1>

    <div id="content">
        <form method="post" action="updateScores">
            <table class="list">
                <tr>
                    <th>Discipline</th>
                    <th>Score</th>
                    <th>ECTS</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="disc" items="${scores}">
                    <tr>
                        <td><c:out value="${disc.getTitle()}"/></td>
                        <td>
                            <input type="text" name="score_num_${disc.getId()}" value="${disc.getScore_num()}"/>
                        </td>
                        <td>
                            <input type="text" name="score_l_${disc.getId()}" value="${disc.getScore_l()}"/>
                        </td>
                        <td class="actions">
                            <div class="btn-group">
                                <button type="submit" name="update" value="${disc.getId()}">
                                    <i class="fas fa-sync-alt"></i> Update
                                </button>
                                <button type="submit" name="delete" value="${disc.getId()}" class="delete">
                                    <i class="fas fa-trash-alt"></i> Delete
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</div>
</body>
</html>
