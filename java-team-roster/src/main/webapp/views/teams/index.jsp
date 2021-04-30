<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>Team Roster</title>
  </head>
  <body>
    <h1>Teams</h1>
    <ul>
      <c:forEach items="${requestScope.teams}" var="team" varStatus="array">
        <li><a href="/team?teamIndex=${array.index}"><c:out value="${team}" /></a></li>
      </c:forEach>
    </ul>
  </body>
</html>
