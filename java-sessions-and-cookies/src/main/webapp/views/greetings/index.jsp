<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.launchacademy.greetings.Greeting" %>
<c:set var="greeting" value="${requestScope.cookieGreeting}" scope="request" />

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello from JSP</title>
  </head>
  <body>
    <c:choose>
      <c:when test = "${not empty cookieGreeting}" >
        <p>The greeting you previously supplied was <c:out value="${cookieGreeting}" />
      </c:when>
      <c:otherwise>
        <p>No greeting on file.</p>
        <p><a href="/greetings/new">Get a New Greeting</a></p>
      </c:otherwise>
    </c:choose>

  </body>
</html>