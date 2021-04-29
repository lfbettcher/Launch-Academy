<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="tagLine" value="I am King of the North" />
<!DOCTYPE html>
<html>
  <head>
    <title>JSTL Examples</title>
  </head>
  <body>

    <c:set var="tagLine" value="I am King of the North" />
    <p><c:out value="${tagLine}" /></p>

    <p>Number of Supporters(not counting themselves): <c:out value="${requestScope.nightsWatchSupporters - 1}" /></p>

    <c:if test = "${requestScope.isSignedIn}">
      <p>You are signed in as <c:out value="${requestScope.userName}" /></p>
    </c:if>

    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <c:if test="${fn:length(requestScope.favoriteThings) > 0}">
      <h1>Favorite Things</h1>
      <ul>
      <c:forEach items="${requestScope.favoriteThings}" var="thing">
        <li><c:out value="${thing}"/></li>
      </c:forEach>
    </c:if>
  </body>
</html>