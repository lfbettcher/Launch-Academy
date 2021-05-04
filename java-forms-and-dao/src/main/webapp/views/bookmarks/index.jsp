<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="com.launchacademy.bookmarks.Bookmark" %>
<%@ page import="java.util.ArrayList" %>
<c:set var="bookmarks" value="${requestScope.bookmarks}" />

<div>
  <ul>
  <c:forEach items="${bookmarks}" var="bookmark">
      <li><a href=<c:out value="${bookmark.url}" />> <c:out value="${bookmark.title}"/></a></li>
  </c:forEach>
  </ul>
</div>