<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Add a Dog</h1>
<form action="/dogs" method="post">
  <div>
    <label for="firstName">First Name</label>
    <input type="text" id="firstName" name="firstName" value="" required />
  </div>
  <div>
    <label for="lastName">Last Name</label>
    <input type="text" id="lastName" name="lastName" value="" required />
  </div>
  <div>
    <label for="photoUrl">Photo URL</label>
    <input type="url" id="photoUrl" name="photoUrl" value="" required />
  </div>
  <div>
    <label for="breed">Breed</label>
    <input type="text" id="breed" name="breed" value="" required />
  </div>
  <div>
    <label for="female">Female</label>
    <input type="radio" id="female" name="sex" value="female" required />
    <label for="male">Male</label>
    <input type="radio" id="male" name="sex" value="male" />
  </div>
  <input type="submit" value="Add Dog" />
</form>
