<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<% String tagLine = "I am the King of the North"; %>

<!DOCTYPE html>
<html>
  <head>
    <title>JSTL Examples</title>
  </head>
  <body>
    <p><%= tagLine %></p>
    <p>Number of Supporters (not counting themselves): <%= (Integer)request.getAttribute("nightsWatchSupporters") - 1 %>

    <% if((Boolean)request.getAttribute("isSignedIn")) { %>
      <p>You are signed in as <%= request.getAttribute("userName") %></p>
    <% } %>

    <% String[] favoriteThings = (String[])request.getAttribute("favoriteThings"); %>
    <% if(favoriteThings.length > 0) { %>
    <h1>Favorite Things</h1>
    <ul>
      <% for(String thing : favoriteThings) { %>
        <li><%= thing %></li>
      <% } %>
    </ul>
    <% } %>
  </body>
</html>