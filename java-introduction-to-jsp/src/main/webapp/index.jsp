<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Hello from JSP</title>
  </head>
  <body>
    <%! String getFormattedDate() {
      Date currentDate = new Date();
      return currentDate.toLocaleString();
    } %>
    <p>The date is <%= getFormattedDate() %></p>
  </body>
</html>