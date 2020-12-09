<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 19.11.2020
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Магазин</title>
</head>
<body>
<%@ page import="pojo.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="pojo.Product" %>
<% Person person = (Person) session.getAttribute("currentUser");
    if (person == null) {
        session.setAttribute("currentUser", null);
        application.getRequestDispatcher("/Error").forward(request, response);
    } else {
    List<Product> storageOfProducts2 = (List<Product>) session.getAttribute("storageOfProducts");
    String message = (String) session.getAttribute("shopMessage");%>
<p align="center"><%= message%>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th width="25%">Название</th>
        <th width="25%">Описание</th>
        <th width="5%">Цена</th>
        <th width="20%">Добавить в корзину</th>
    </tr>
    <%
        for (int i = 0; i < storageOfProducts2.size(); i++) {
            if (storageOfProducts2.get(i).getCount() > 0) {
    %>
    <tr>
        <td><%= storageOfProducts2.get(i).getName()%>
        </td>
        <td><%= storageOfProducts2.get(i).getDescription()%>
        </td>
        <td><%= String.format("%.2f", storageOfProducts2.get(i).getPrice())%>
        </td>
        <td>
            <form action='/shop' method='POST'>
                <input name="count"/>
                <input type="hidden" name="idProduct" value="<%=storageOfProducts2.get(i).getId() %>"/>
                <input type='submit' value='Добавить'/>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<p><a href="/basket">В корзину</a></p>
<p><a href="/mainPage.jsp">На главную страницу</a></p>
<%}%>
</body>
</html>