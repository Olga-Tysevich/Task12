<%@ page import="static com.example.tables.utils.Constants.ERROR_MESSAGE" %>
<%@ page import="static com.example.tables.utils.Constants.FIRST_PAGE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="/css/style.css">
        <title>Ошибка</title>
    </head>

    <body>
        <h1>Что-то пошло не так</h1>

        <form action="/tables" method="get">
            <input class="button add" type="submit" value="Вернуться к списку">
        </form>

    </body>

</html>