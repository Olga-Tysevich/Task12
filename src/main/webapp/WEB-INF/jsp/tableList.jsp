<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="com.example.tables.dto.TableDTO" %>
<%@ page import="static com.example.tables.utils.Constants.FIND_PATTERN" %>
<%@ page import="static com.example.tables.utils.Constants.PAGE" %>
<%@ page import="static com.example.tables.utils.Constants.*" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.example.tables.enums.Color" %>
<%@ page import="com.example.tables.enums.Material" %>
<head>
    <meta charset="UTF-8">
    <link href="/css/style.css" rel="stylesheet"/>
    <title>Список столов</title>
</head>
<body>
<section>
    <div class=" container">

        <%
            Page<TableDTO> pageForDisplay = (Page<TableDTO>) request.getAttribute(PAGE);
            List<TableDTO> list = pageForDisplay.getContent();
            int pageNumber = pageForDisplay.getNumber();
            int maxPageNumber = pageForDisplay.getTotalPages();
            String sortDir = (String) request.getAttribute(SORT_DIR);
            String currentSortDir =  sortDir.equals(ASC_SORT) ? DESC_SORT : ASC_SORT;
            String sortField = (String) request.getAttribute(SORT_FIELD);
            String keyword = StringUtils.defaultIfBlank((String) request.getAttribute(KEYWORD), StringUtils.EMPTY);
        %>

        <div class="header-container">
            <h1>Список столов</h1>
        </div>

        <form action="/tables/page/<%=pageNumber%>" method="get">
            <input type="hidden" name="<%=SORT_DIR%>" value="<%=sortDir%>"/>
            <input type="hidden" name="<%=SORT_FIELD%>" value="<%=sortField%>"/>

             <input class="search" type="search" name="<%=KEYWORD%>" placeholder="Найти по размеру, бренду, цвету, материалу"
                       value="<%=keyword%>"/>
            <input class="button light" type="submit" value="Найти">
        </form>

    </div>

    <div class="container">

        <table>
            <thead>
            <tr>
                <th>
                    <a href="<%=String.format(FIND_PATTERN, pageNumber, SIZE, currentSortDir) + String.format(KEYWORD_PATTERN, keyword)%>">Размер</a>
                </th>
                <th>
                    <a href="<%=String.format(FIND_PATTERN, pageNumber, BRAND, currentSortDir) + String.format(KEYWORD_PATTERN, keyword)%>">Бренд</a>
                </th>
                <th>
                    <a href="<%=String.format(FIND_PATTERN, pageNumber, COLOR, currentSortDir) + String.format(KEYWORD_PATTERN, keyword)%>">Цвет</a>
                </th>
                <th>
                    <a href="<%=String.format(FIND_PATTERN, pageNumber, MATERIAL, currentSortDir) + String.format(KEYWORD_PATTERN, keyword)%>">Материал</a>
                </th>
                <th class="menu">Управление</th>
            </tr>
            </thead>

            <% for (TableDTO table : list) { %>
            <tr>
                <td><%=table.getSize()%>
                </td>
                <td><%=table.getBrand()%>
                </td>
                <td class="number"><%=table.getColor()%>
                </td>
                <td class="number"><%=table.getMaterial()%>
                </td>
                <td>
                    <div class="button-container">
                        <a class="button add" href="/tables/table-update/<%=table.getId()%>">Изменить</a>
                        <a class="button add" href="/tables/table-delete/<%=table.getId()%>">Удалить</a>
                    </div>
                </td>
            </tr>
            <% } %>
        </table>

        <div class="footer">
            <a class="button add" href="/tables/table-create">Добавить</a>
        </div>

<%--                <input class="button light" type="submit" name="button" value="Предыдущая">--%>

        <div class="button-container">
        <%if (maxPageNumber > 1) {%>

            <%if (pageNumber != FIRST_PAGE) {%>
            <a href="<%=String.format(FIND_PATTERN, FIRST_PAGE, sortField, sortDir) + String.format(KEYWORD_PATTERN, keyword)%>">Первая</a>
            <%}%>

            <%for (int i = pageNumber + 1; i < Math.min(pageNumber + 5, maxPageNumber - 1); i++) {%>
            <a href="<%=String.format(FIND_PATTERN, i, sortField, sortDir) + String.format(KEYWORD_PATTERN, keyword)%>"><%=i + 1%></a>
            <%}%>

            <%if (pageNumber != maxPageNumber - 1) {%>
            <a href="<%=String.format(FIND_PATTERN, maxPageNumber - 1 , sortField, sortDir) + String.format(KEYWORD_PATTERN, keyword)%>">Последняя</a>
            <%}%>

        <%}%>
    </div>
</section>
</body>