While playing with query strings helps to illustrate the dynamic nature of Java web applications, production software in practice use web forms. Ahead, we will learn to take in parameters via a form and a post request.

## Learning Goals

- Build a form with JSP
- Bind a form with a Java bean
- Process input from a web form

## Getting Started

```sh
et get java-forms
cd java-forms
idea .
```

## Building Our Form

Continuing onward with our greeting work, we're going to build a form that prompts the user for a first name, last name, and language. In order to do so, we have defined a servlet that will render our greeting form.

We've discussed in some detail how to create the controller and the view. In this lesson, we've also created a very basic model. This model is a POJO with no special constructor. We can see this model in the `src/main/java/com.launchacademy.greetings/Greeting.java` file:

```java
public class Greeting {
  private String firstName;
  private String lastName;
  private String language;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }
}
```

It's a fairly straightforward POJO that has getters and setters for our three attributes.

We have some business logic to introduce in terms of how the `Greeting` should be formulated. Let's introduce some of that logic inside of our `Greeting` class.

```java
  //set defaults
  public Greeting() {
    this.language = "en";
    this.firstName = "";
    this.lastName = "";
  }

  public String getSalutation() {
    if(language.equals("en")) {
      return "Hello";
    }
    else if(language.equals("fr")) {
      return "Bonjour";
    }
    else if(language.equals("es")) {
      return "Hola";
    }
    else {
      return "Hello";
    }
  }

  @Override
  public String toString() {
    String salutation = getSalutation();
    if(!lastName.isBlank() && !firstName.isBlank()) {
      return salutation + ", " + firstName + " " + lastName;
    }
    else if(!firstName.isBlank()) {
      return salutation + ", " + firstName;
    }
    else {
      return salutation;
    }
  }
```

For the first time, we're seeing the benefit of using a model. Here, we're first setting some defaults for the model with a zero-argument constructor. Next, we're defining a `toString()` method that considers the different variants of our greetings based on our requirements. Putting this logic inside the model makes it easily unit-testable, and it separates our business rules from handling web traffic or presenting the user with content. For example, if we added another language or if we wanted to change the default language, we could do so with minimal disruption to our controller and view. You'll see how this comes into play when it all comes together at the end of this article.

For now, let's take a look at our existing servlet and its `doGet` method that will serve the form to the end user. We can see this code in `src/main/java/com.launchacademy.greetings/GreetingsController.java`:

```java
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/greetings/new", "/greetings"})
public class GreetingsController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/new.jsp");
    dispatcher.forward(req, resp);
  }
  ...
}
```

This is a pretty straightforward servlet that basically serves up the Java Server Page. Make note of the url pattern `/greetings/new` as it adheres to the RESTful conventions we've established for presenting a user with a form to create a new object.

Let's now check out our form in the referenced JSP. This file is already located at `src/main/webapp/views/greetings/new.jsp`.

```jsp
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
        <label for="lastName">Last Name</label>
        <input type="text" name="lastName" value="" />
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
```

Notice that the **names** of our `input` and `select` elements match up with the fields on our `Greeting` model. This is important, and will come in handy as we build out our solution a bit further. You might have also noticed we didn't use any JSTL here; that's because we only need HTML to make the form. If you wanted to work with objects in conjunction with your form you need to add JSTL to the mix.

## Handling the POST

Now, let's check out the existing override of the `doPost` method of our Servlet.

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
  if(req.getServletPath().equals("/greetings")) {
    PrintWriter writer = resp.getWriter();
    writer.println("POSTED");
  }
  else {
    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
  }
}
```

In order for this to work, we have added an additional request `urlPattern` to our `@WebServlet` annotation.

```java
@WebServlet(urlPatterns = {"/greetings/new", "/greetings"})
```

Notice that we specifically look for a particular path. If a user performs a POST to a different URL, we will respond with a 404 as indicated by the `resp.sendError(HttpServletResponse.SC_NOT_FOUND);`

Rebuild your project and verify that your form results in the a web page that says "POSTED". If that's working successfully, we can actually begin our work of processing the POST request.

**Update** your `doPost` to have the following logic:

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
  if(req.getServletPath().equals("/greetings")) {
    Greeting greeting = new Greeting();
    greeting.setFirstName(req.getParameter("firstName"));
    greeting.setLastName(req.getParameter("lastName"));
    greeting.setLanguage(req.getParameter("language"));
    req.setAttribute("greeting", greeting);

    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/show.jsp");
    dispatcher.forward(req, resp);
  }
  else {
    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
  }
}
```

This routine sets a populated `greeting` to our `src/main/webapp/views/show.jsp` Java Server Page.
Notice how all of this work dramatically simplifies our view logic.

In our `show.jsp`, we'll find the following logic:

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Hello from JSP</title>
  </head>
  <body>
    <p><c:out value="${requestScope.greeting}"/></p>
    <p><a href="/greetings/new">Get a New Greeting</a></p>
  </body>
</html>
```

`requestScope.greeting` retrieves the `greeting` value we set in our servlet. Since we've overridden that in our `Greeting` model, we get sensible output!

This is the power of MVC. The controller assembles the necessary information by interacting with the model, and then it supplies that information for the view to present.

## Refactoring our Controller

This pattern is so common that there is an even better way to construct our `doPost`.

If you recall, we had earlier learned about the **BeanUtils** library and how it can be used to populate a bean using a Map.

The `HttpServletRequest` interface has a `getParameterMap` method. This returns a `java.util.Map` corresponding to the request parameters and their values. So basically each key-value pair in the Map corresponds to a parameter name and its value. This Map can then be used to populate a bean via **BeanUtils**.

Thus, we can modify the `doPost` method in our servlet once more, as follows.

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
  if(req.getServletPath().equals("/greetings")) {
    Greeting greeting = new Greeting();
    try {
      BeanUtils.populate(greeting, req.getParameterMap());
    }
    catch(IllegalAccessException ex) {
      //do some logging
    }
    catch(InvocationTargetException ex) {
      //do some logging
    }
    req.setAttribute("greeting", greeting);

    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/show.jsp");
    dispatcher.forward(req, resp);
  }
  else {
    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
  }
}
```

This code first creates a `greeting` object. It then invokes the `BeanUtils.populate` method using `greeting` and the parameter map from the `request`.

The  `BeanUtils.populate` method matches each key in the request map with the name of each property in the `greeting` bean and invokes the corresponding setter method.  Thus, this results in the `greeting` bean properties getting populated using the values in the map.   

Note that for the `BeanUtils.populate`method to work, the parameter names in the request Map need to match the property names in the bean. Since we've properly named our form inputs `firstName`, `lastName`, and `language` which match the property names in the `Greeting` bean, this code works as expected.

This approach not only makes the code compact, but it does away with the need to hardcode the setting of fields in our controller.

*Note:* you may have to completely restart Tomcat for this change to take effect. Generally, whenever you add a new dependency, it's a good idea to run `tomcat7:shutdown` and then `tomcat7:run` to ensure everything loads properly.

## Why This Matters

We're starting to see the organizational benefits of the Model View Controller (MVC) paradigm. When it comes to processing user input, we can leverage this separation of concerns to make our programs more coherent and easier to maintain.

## In Summary

- **Models** are responsible for managing the business logic of our application. We should relegate as much data manipulation and computation to these classes as possible.
- **Controllers** are responsible for receiving the HTTP Request and determining what to do with it. In order to do its work, controllers almost always have to serve as the intermediary between models and views.
- **Views** are responsible for presenting the user with relevant information. In a web-based context, this almost always means presenting the user with an HTML web page.

The MVC pattern transcends Java and pervades the whole web development industry. We like the MVC pattern because it helps us to organize our applications.

In Java, so far, we implement MVC with POJO's or JPA entities as the models, Java Server Pages (JSP) as the views, and Java Servlets as the controllers. Through intelligently applying Java Beans, we can take data from form submissions and set relevant values in our models.
