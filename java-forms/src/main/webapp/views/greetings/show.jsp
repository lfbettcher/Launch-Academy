<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello from JSP</title>
  </head>
  <body>
    <p><c:out value="${requestScope.greeting}" /></p>
    <p><a href="/greetings/new">Get a New Greeting</a></p>
  </body>
</html>