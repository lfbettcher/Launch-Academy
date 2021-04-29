<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <title>JSP backed by a servlet</title>
  </head>
  <body>
    <p><%= request.getAttribute("greeting") %></p>
    <p><c:out value="${requestScope.greeting}" /></p>
  </body>
</html>