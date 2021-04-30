<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Hello from JSP</title>
  </head>
  <body>
    <form action="/greetings" method="post">
      <div>
        <label for="firstName">First Name</label>
        <input type="text" name="firstName" value="" />
      </div>
      <div>
        <label for="lastName">Last Name </label>
        <input type="text" name="lastName"value="" />
      </div>
      <div>
        <label for="language">Language</label>
        <select name="language">
          <option value=""></option>
          <option value="en">English</option>
          <option value="fr">French</option>
          <option value="es">Spanish</option>
        </select>
      </div>
       <input type="submit" value="Greet" />
    </form>
  </body>
</html>