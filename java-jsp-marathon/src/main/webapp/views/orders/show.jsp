<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="order" value="${requestScope.order}" scope="request" />

<p>
  <c:out value="Username: ${order.username}<br />Item Name: ${order.itemName} " escapeXml="false" />
  <c:if test="${order.glutenFree}">(GF)</c:if><br />
  <c:out value="Quantity: ${order.itemQuantity}" />
</p>
<img src="${order.imageUrl}" alt="order" />