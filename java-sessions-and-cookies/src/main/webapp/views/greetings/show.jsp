<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="com.launchacademy.greetings.Greeting" %>
<c:set var="greeting" value="${requestScope.greeting}" scope="request" />

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Hello from JSP</title>
  </head>
  <body>
    <p> <c:out value="${greeting}"/> </p>
    <p><a href="/greetings/new">Get a New Greeting</a></p>
  </body>
</html>
