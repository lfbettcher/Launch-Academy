<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>List of Orders</h1>
<ul>
  <c:forEach items="${requestScope.orders}" var="order">
    <li><c:out value="${order.username} ${order.itemName} ${order.itemQuantity}" /></li>
  </c:forEach>
</ul>