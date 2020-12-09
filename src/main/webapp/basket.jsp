<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 20.11.2020
  Time: 01:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Моя корзина</title>
</head>
<body>
<%@page import="pojo.Basket, pojo.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<% Person person = (Person) session.getAttribute("currentUser");
//    if (StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null) {
//        session.setAttribute("currentUser", null);
//        application.getRequestDispatcher("/Error").forward(request, response);
//    }
    List<Basket> basket = (List<Basket>) session.getAttribute("productsInBasket"); %>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th width="25%">Название</th>
        <th width="25%">Описание</th>
        <th width="5%">Цена</th>
        <th width="5%">Количество</th>
        <th width="5%">Итого</th>
        <th width="20%">Изменить количество</th>
        <th width="5%">Удалить</th>
    </tr>
    <%
        for (int i = 0; i < basket.size(); i++) {
    %>
    <tr>
        <td><%= basket.get(i).getProduct().getName()%>
        </td>
        <td><%= basket.get(i).getProduct().getDescription()%>
        </td>
        <td><%= basket.get(i).getProduct().getPrice()%>
        </td>
        <td><%= basket.get(i).getQuantity()%>
        </td>
        <td><%= String.format("%.2f", basket.get(i).getProduct().getPrice() * basket.get(i).getQuantity())%>
        </td>
        <td>
            <form action='/basket' method='POST'>
                <input type="hidden" name="operationInBasket" value="modifyProduct">
                <input name="count" value="<%= basket.get(i).getQuantity()%>"/>
                <input type="hidden" name="idProduct" value="<%=basket.get(i).getProduct().getId() %>"/>
                <input type='submit' value='Изменить'/>
            </form>
        </td>
        <td>
            <form action='/basket' method='POST'>
                <input type="hidden" name="operationInBasket" value="deleteProduct">
                <input type="hidden" name="idProduct" value="<%=basket.get(i).getProduct().getSerialNumber() %>"/>
                <input type='submit' value='Удалить'/>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%--<h2 align="right">Итого к оплате: <%= String.format("%.2f", basket.getTotalCostOfBasket()) %>--%>
</h2>
<h2 align="right">
    <form action='/makeOrder.jsp'>
        <input type="hidden" name="operationInBasket" value="pay">
        <input type='submit' value='Оформить заказ'/>
    </form>
</h2>
<p><a href="/shop">В магазин</a></p>
<p><a href="/mainPage.jsp">На главную страницу</a></p>
</body>
</html>