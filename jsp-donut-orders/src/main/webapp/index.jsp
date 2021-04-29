<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Donut Orders</title>
  </head>
  <body>
    <%
      Map<String, Integer> donutOrders = new HashMap<>();
      donutOrders.put("Jennifer", 10);
      donutOrders.put("Alex", 1);
      donutOrders.put("Carly", 3);

      Map<String, String> favoriteDonut = new HashMap<>();
      favoriteDonut.put("Jennifer", "Type One");
      favoriteDonut.put("Alex", "Type Two");
      favoriteDonut.put("Carly", "Type Three");
    %>
    <h2>Donut Orders</h2>
    <p>The date is <%= new Date().toLocaleString() %>
    </p>
    <ul>
      <%
        List<String> badList = new ArrayList<>();
        int totalDonuts = 0;
        for (Map.Entry<String, Integer> order : donutOrders.entrySet()) {
          if (order.getValue() <= 5) {
            totalDonuts += order.getValue();
      %>
      <li><%= order.getKey() %>: <%= order.getValue() %> Donuts (Favorite flavor: <%= favoriteDonut
          .get(order.getKey())%>)
      </li>
      <% } else { %> <%--/if--%>
      <li><%= order.getKey() %> is trying to game the donut system (Favorite
        flavor: <%= favoriteDonut.get(order.getKey())%>)
      </li>
      <% } %> <%--/else--%>
    <% } %> <%--/for--%>
    </ul>
    <p>Total number of donuts: <%= totalDonuts %>
    </p>
  </body>
</html>