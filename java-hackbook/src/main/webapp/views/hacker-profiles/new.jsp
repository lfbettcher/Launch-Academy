<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

  <head>
    <title>Title</title>
  </head>

  <body>
    <h1>Add a hacker</h1>
    <form action="/hacker-profiles" method="post">
      <div>
        <label for="firstName">First Name</label>
        <input type="text" name="firstName" value="" />
      </div>
      <div>
        <label for="lastName">Last Name</label>
        <input type="text" name="lastName" value="" />
      </div>
      <div>
        <label for="emailAddress">Email Address</label>
        <input type="email" name="emailAddress" value="" />
      </div>
      <div>
        <label for="favoriteLanguage">Language</label>
        <select name="favoriteLanguage">
          <option value="Java">Java</option>
          <option value="Python">Python</option>
          <option value="C#">C#</option>
        </select>
      </div>
      <div>
        <label for="activeStatus">Active</label>
        <input type="radio" name="status" id="activeStatus" value="activeStatus" />
        <label for="inactiveStatus">Inactive</label>
        <input type="radio" name="status" id="inactiveStatus" value="inactiveStatus" />
      </div>
      <input type="submit" value="Add Hacker" />
    </form>
  </body>

</html>
