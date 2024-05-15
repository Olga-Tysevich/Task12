<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
        <link rel="stylesheet" href="../css/style.css">
        <title>Стартовая страница</title>
    </head>

    <body>
        <h1><%=request.getAttribute("errorPage")%>%></h1>

        <form action="list" method="post">
            <input type="hidden" name="page" value="<%=request.getAttribute("pageNumber")%>">
            <input class="button add" type="submit" value="Вернуться к списку">
        </form>

    </body>

</html>