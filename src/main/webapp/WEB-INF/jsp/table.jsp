<%@ page import="com.example.tables.dto.TableDTO" %>
<%@ page import="static com.example.tables.utils.Constants.TABLE" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.example.tables.enums.Color" %>
<%@ page import="com.example.tables.enums.Material" %>
<%@ page import="static com.example.tables.utils.Constants.ERROR_MESSAGE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title>Студент</title>
    </head>
    <body>

        <section>

            <%
                TableDTO table = (TableDTO) request.getAttribute(TABLE);
                String action = table == null? "/tables/table-create" : "/tables/table-update";
                String errorMessage = (String) request.getAttribute(ERROR_MESSAGE);
            %>

            <div class="container hidden" id="student">

                <div class="button-container">
                    <h1>Стол</h1>
                </div>

                <form action = "<%=action%>" method="post" id="saveOrUpdate">
                    <div class="form-container">
                        <%if (table != null && table.getId() != null) {%>
                        <input type="hidden" name="id" value="<%=table.getId()%>"/>
                        <%}%>

                        <label class="form-el">Размер</label>
                        <input class="form-el" type="text" name="size" placeholder="Введите размер" value="<%=(table != null && table.getSize() != null)?
                            table.getSize() : StringUtils.EMPTY%>"/>

                        <label class="form-el">Бренд</label>
                        <input class="form-el" type="text" name="brand" placeholder="Введите бренд" value="<%=table != null?
                            StringUtils.defaultIfBlank(table.getBrand(), StringUtils.EMPTY) : StringUtils.EMPTY%>"/>



                        <label class="form-el">Цвет</label>
                        <select class="f-form " name="color" size="1">
                            <%for (Color color : Color.values()) {%>
                            <option value="<%=color%>" <%if (table != null && color.equals(table.getColor())) {%>selected<%}%>>
                                <%=color%>
                            </option>
                            <%}%>
                        </select>

                        <label class="form-el">Материал</label>
                        <select class="f-form " name="material" size="1">
                            <%for (Material material : Material.values()) {%>
                            <option value="<%=material%>" <%if (table != null && material.equals(table.getMaterial())) {%>selected<%}%>>
                                <%=material%>
                            </option>
                            <%}%>
                        </select>
                    </div>

                    <%if (StringUtils.isNotBlank(errorMessage)) {%>
                    <div class="button-container">
                        <h1 style="color: red"><%=errorMessage%></h1>
                    </div>
                    <%}%>

                    <div class="button-container">
                        <input class="button" type="submit" value="Сохранить" form="saveOrUpdate"/>
                        <a class="button" href="/tables">Отменить</a>
                    </div>
                </form>

            </div>

        </section>
    </body>
</html>
