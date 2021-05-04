<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rolls" value="${requestScope.rolls}" />
<c:set var="message" value="${requestScope.message}" />

<h1>You rolled <c:forEach items="${rolls}" var="roll">
  <c:out value="${roll}" />
</c:forEach> for a total of <c:out value="${requestScope.sum}" />
</h1>

<c:if test="${not empty requestScope.guess}">
  <p>
    <c:choose>
      <c:when test="${requestScope.correct}">
        <strong><c:out value="${message}" /></strong>
      </c:when>
      <c:otherwise>
        <c:out value="${message}" />
      </c:otherwise>
    </c:choose>
  </p>
</c:if>
