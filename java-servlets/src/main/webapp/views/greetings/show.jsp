<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>JSP backed by a servlet</title>
  </head>
  <body>
    <p><%= request.getAttribute("greeting") %></p>
  </body>
</html>