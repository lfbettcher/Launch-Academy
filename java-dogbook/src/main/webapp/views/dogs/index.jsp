<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="breed" value="${requestScope.breed}" />
<c:set var="dogs" value="${requestScope.dogs}" />
<c:set var="sessionDog" value="${requestScope.sessionDog}" />

<c:choose>
  <c:when test="${not empty breed}">
    <h1><c:out value="All ${breed.name}s" /></h1>
  </c:when>
  <c:when test="${not empty sessionDog}">
    <h1>Find Friends for <c:out value="${sessionDog.firstName}" /></h1>
  </c:when>
  <c:otherwise>
    <h1>List of Dogs</h1>
  </c:otherwise>
</c:choose>
<ul>
  <c:forEach items="${requestScope.dogs}" var="dog">
    <li>
      <c:out value="${dog.firstName} ${dog.lastName}" /><br />
      <img src="${dog.photoUrl}" alt="dog" />
    </li>
  </c:forEach>
</ul>