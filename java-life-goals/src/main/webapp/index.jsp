<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Hello from JSP</title>
  </head>
  <body>
    <h2>Life Goals</h2>
    <% List<String> goals = new ArrayList<>(Arrays.asList("goal 1", "goal 2")); %>
    <ul>
      <% for (String goal : goals) { %>
        <li><%= goal %></li>
      <% } %>
    </ul>
  </body>
</html>