<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Add a New Bookmark</title>
  </head>
  <body>
    <form action="/bookmarks" method="post">
      <div>
        <label for="title">Title</label>
        <input type="text" id="title" name="title" value="" />
      </div>
      <div>
        <label for="url">URL</label>
        <input type="text" id="url" name="url" value="" />
      </div>
      <div>
        <label for="description">Description</label>
        <textarea id="description" name="description"></textarea>
      </div>
       <input type="submit" value="Create Bookmark" />
    </form>
  </body>
</html>