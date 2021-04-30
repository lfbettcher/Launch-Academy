<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>List of Hackers</h1>
<ul>
  <c:forEach items="${requestScope.profiles}" var="hacker">
    <li><c:out value="${hacker.firstName} ${hacker.lastName} ${hacker.emailAddress}" /></li>
  </c:forEach>
</ul>