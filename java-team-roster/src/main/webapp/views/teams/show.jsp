<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="team" value="${requestScope.team}" scope="request" />
<c:set var="players" value="${requestScope.players}" scope="request" />

<html>
  <head>
    <title>Team Roster</title>
  </head>
  <body>
    <h1>Team: <c:out value="${team.teamName}" /></h1>
    <ul>
      <c:forEach items="${players}" var="player">
        <li><c:out value="${player.position}: ${player.name}" /></li>
      </c:forEach>
    </ul>
  </body>
</html>
