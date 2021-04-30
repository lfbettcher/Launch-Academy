<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>List of Dogs</h1>
<ul>
  <c:forEach items="${requestScope.dogs}" var="dog">
    <li>
      <c:out value="${dog.firstName} ${dog.lastName}" /><br />
      <img src="${dog.photoUrl}" alt="dog" />
    </li>
  </c:forEach>
</ul>