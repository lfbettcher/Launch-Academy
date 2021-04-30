<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Add a hacker</h1>
<form action="/orders" method="post">
  <div>
    <label for="username">Username</label>
    <input type="text" id="username" name="username" value="" />
  </div>
  <div>
    <label for="itemName">Item Name</label>
    <input type="text" id="itemName" name="itemName" value="" />
  </div>
  <div>
    <label for="itemQuantity">Quantity</label>
    <input type="number" id="itemQuantity" name="itemQuantity" value="" />
  </div>
  <div>
    <label for="glutenFree">Gluten Free</label>
    <input type="radio" id="glutenFree" name="glutenFree" value="true" />
    <label for="gluten">Inactive</label>
    <input type="radio" id="gluten" name="glutenFree" value="false" />
  </div>
  <div>
    <label for="imageUrl">Item Name</label>
    <input type="text" id="imageUrl" name="imageUrl" value="" />
  </div>
  <input type="submit" value="Add Order" />
</form>
