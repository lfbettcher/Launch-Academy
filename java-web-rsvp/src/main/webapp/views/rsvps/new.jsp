<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

  <head>
    <title>RSVP</title>
  </head>

  <body>
    <h1>RSVP for Event</h1>
    <form action="/rsvps" method="post">
      <div>
        <label for="firstName">First Name</label>
        <input type="text" name="firstName" value="" required />
      </div>
      <div>
        <label for="lastName">Last Name</label>
        <input type="text" name="lastName" value="" required />
      </div>
      <div>
        <label for="emailAddress">Email Address</label>
        <input type="email" name="emailAddress" value="" required />
      </div>
      <div>
        <label for="phoneNumber">Phone Number</label>
        <input type="text" name="phoneNumber" value="" required />
      </div>
      <div>
        <label for="contactPhone">Phone</label>
        <input type="radio" name="contact" id="contactPhone" value="phone" />
        <label for="contactText">Text</label>
        <input type="radio" name="contact" id="contactText" value="text" />
      </div>
      <input type="submit" value="RSVP" />
    </form>
  </body>

</html>
