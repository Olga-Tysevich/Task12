<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static com.example.tables.utils.Constants.*" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.example.tables.dto.TableDTO" %>
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="java.util.List" %>
<head>
    <meta charset="UTF-8">
    <link href="/static/css/style.css" rel="stylesheet"/>
    <title>Список столов</title>
</head>
<body>
<section>
    <div class=" container">

        <%
            Page<TableDTO> pageForDisplay = (Page<TableDTO>) request.getAttribute(PAGE);
            List<TableDTO> list = pageForDisplay.getContent();
            int pageNumber = pageForDisplay.getNumber() + 1;
            int maxPageNumber = pageForDisplay.getTotalPages();
            String sortDir = (String) request.getAttribute(SORT_DIR);
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
                    <a href="<%=String.format(FIND_PATTERN, pageNumber, SIZE, sortDir, keyword)%>">Размер</a>
                </th>
                <th>
                    <a href="<%=String.format(FIND_PATTERN, pageNumber, BRAND, sortDir, keyword)%>">Бренд</a>
                </th>
                <th>
                    <a href="<%=String.format(FIND_PATTERN, pageNumber, COLOR, sortDir, keyword)%>">Цвет</a>
                </th>
                <th>
                    <a href="<%=String.format(FIND_PATTERN, pageNumber, MATERIAL, sortDir, keyword)%>">Материал</a>
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

        <div class="button-container">
        <%if (maxPageNumber > FIRST_PAGE) {%>

            <a href="<%=String.format(FIND_PATTERN, FIRST_PAGE, sortField, sortDir, keyword)%>">Первая</a>

            <%if(pageNumber == FIRST_PAGE) {%>
                <%for (int i = pageNumber + 1; i < Math.min(pageNumber + 5, maxPageNumber); i++) {%>
                    <a href="<%=String.format(FIND_PATTERN, i, sortField, sortDir, keyword)%>"><%=i%></a>
                <%}}%>

            <%if (pageNumber != FIRST_PAGE) {
                for (int i = Math.max(pageNumber - 5, FIRST_PAGE + 1); i < Math.max(pageNumber, FIRST_PAGE); i++) {%>
            <a href="<%=String.format(FIND_PATTERN, i, sortField, sortDir, keyword)%>"><%=i%></a>
            <%}}%>

            <%if (pageNumber != maxPageNumber && pageNumber != FIRST_PAGE){%>

                <%for (int i = pageNumber; i < Math.min(pageNumber + 5, maxPageNumber); i++) {%>
                    <a href="<%=String.format(FIND_PATTERN, i, sortField, sortDir, keyword)%>"><%=i%></a>
                <%}%>

            <%}%>

            <%if (pageNumber != maxPageNumber) {%>
            <a href="<%=String.format(FIND_PATTERN, maxPageNumber , sortField, sortDir, keyword)%>">Последняя: <%=maxPageNumber%></a>
            <%}
            }%>
    </div>
</div>
</section>
</body>