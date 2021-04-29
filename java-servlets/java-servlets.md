Java servlets allow us to serve HTTP requests with responses. Below, we will dive into different ways we can use our servlets to best organize our code, from views to business logic.

## Learning Goals

- Create a Java servlet
- Integrate Java servlets with Java Server Pages

## Getting Started

```no-highlight
et get java-servlets
cd java-servlets
idea .
```

## Creating Our First Servlet

In the previous article, we talked about why servlets are important tools to provide a _controller_ for our HTTP request/response cycle in our application, and saw a sample servlet file. Here, let's work our way up, starting with the configuration and an incredibly simple webpage, and then learning how to better organize our code for more complex and dynamic pages.

 Let's create our first servlet in `src/main/java/MyFirstServlet.java`:

```java
// MyFirstServlet.java
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class MyFirstServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter writer = resp.getWriter();
    writer.println("<h1>Hello from a servlet</h1>");
  }
}
```

Here we define a class that extends `HttpServlet`, and includes an overridden `doGet` method inside, to handle for our GET request. As a reminder, a servlet considers abstractions of the request and the response as the first and second argument respectively. We type these arguments using the imported `HttpServletRequest` and `HttpServletResponse`. Our `@WebServlet` annotation allows us to provide our path via a `urlPattern`, telling our application that we're writing a route for a GET request to the `/hello` path, specifically.

`resp.getWriter` effectively gives us the ability to provide output to the Response stream. Just like we use `System.out` to output to the terminal, we can use `writer` to output to our HTTP response. It's not the most optimal approach, but it allows us to get some text on the page very quickly. Here, we write some invalid HTML, just a single `<h1>` tag, to the response, but it drives home the concept. In practice, we'd want to write a full, valid HTML document with a doctype and a well-formed `<html>` element. Doing so directly in a Java Servlet is cumbersome and not best practice, so we'll discuss a better approach (using JSPs!) later in this article. But, first, we've got a bit more of configuration to do.

Go ahead and run `tomcat7:run` and visit `localhost:8080/hello` to see if the header loads into your page! Remember that you can use the Maven Wrapper to run this command with `./mvnw tomcat7:run`.

## Reloading

If we make changes to our servlet, we have to compile, shutdown and re-run Tomcat. This really slows down the development process, so we need a more effective solution.

First, we need to add a configuration item to our `pom.xml`. Let's update our `tomcat` maven plugin configuration to include the `<contextReloadable>` option, so our app can reload automatically.

```xml
<plugin>
  <groupId>org.apache.tomcat.maven</groupId>
  <artifactId>tomcat7-maven-plugin</artifactId>
  <version>2.2</version>
  <configuration>
    <port>8080</port>
    <path>/</path>
    <contextReloadable>true</contextReloadable>
  </configuration>
</plugin>
```

Next, we have to make some adjustments to our IntelliJ project. Navigate to your Preferences / Settings. On Mac, It's in IntelliJ Idea -> Preferences. On Windows, it is File -> Settings. In the Compiler menu under "Build, Execution, and Deployment", there's a checkbox to "Build Project Automatically". Check that and click OK to save your settings.

We'll then need to run `tomcat7:shutdown` and `tomcat7:run` again. After that, instead of stopping and starting our Tomcat server, we can just rebuild our project by going to Build -> Build Project, or clicking the hammer icon in the top right of IntelliJ, or using the relevant hotkey. From here on out, as we save and build our servlets, IntelliJ will rebuild our project and Tomcat will reload the classes. Note that there is a bit of a delay, but it is better than having to do all of those steps ourselves as we're in the flow of development.

**Note**: We find the build key mapping to be awkward and unusable. You can re-map to a key mapping of your choosing in Preferences / Settings -> Keymap -> Main Menu -> Build. We set it to cmd+shift+b.

## Working with Query Strings

Recall that in the context of our GET requests, we can append parameters to the end of our URL using a query string (with a `?` in our path). Let's add support for a parameter to customize our greeting.

```java
// MyFirstServlet.java
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class MyFirstServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter writer = resp.getWriter();
    String firstName = req.getParameter("first_name");
    if(firstName != null && firstName != "") {
      writer.println("<h1>Hello from a servlet, " + firstName + "</h1>");
    }
    else {
      writer.println("<h1>Hello from a servlet</h1>");
    }
  }
}
```

With this example, we have two possible outcomes. If I navigate to `http://localhost:8080/hello`, I get the standard "Hello from a servlet". If I navigate to `http://localhost:8080/hello?first_name=Elizabeth`, my greeting is customized to include the name. The `getRequestParameter` method is provided as a part of the `HttpServletRequest` object, and can also be used to extract values from our POST bodies as well.

## Redirection

Let's imagine that we want to redirect users from our root path to our established `/hello` servlet. To issue a 302 redirect, we'll have to create another servlet to accomodate the `/` endpoint. Let's create a new class and call it `RootDispatcher`.

```java
// RootDispatcher.java
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class RootDispatcher extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.sendRedirect("/hello");
  }
}
```

This servlet has a `urlPattern` of "/", so it will load at our root path. We use `HttpServletResponse`'s built-in `sendRedirect` method to redirect the user.

Note that adding a new servlet is **not** something that contextReloadable will help with. Instead, we have to run `tomcat7:shutdown` and `tomcat7:run` to restart our server.

Once we've restarted, navigating to `/` will redirect us to `/hello`.

## Writing a Complete HTML Document

When we first introduced servlets earlier in this article, we wrote some contrived, and invalid, HTML markup using `PrintWriter`.
Let's see what it would take to write a complete response, still using `PrintWriter`.

```java
// MyFirstServlet.java
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class MyFirstServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter writer = resp.getWriter();
    String firstName = req.getParameter("first_name");

    resp.setContentType("text/html");
    writer.println("<!DOCTYPE HTML>");
    writer.println("<html>");
    writer.println("  <head>");
    writer.println("    <title>First Servlet</title>");
    writer.println("  </head>");
    writer.println("  <body>");

    if(firstName != null && firstName != "") {
      writer.println("<h1>Hello from a servlet, " + firstName);
    }
    else {
      writer.println("<h1>Hello from a servlet</h1>");
    }

    writer.println("  </body>");
    writer.println("</html>");
  }
}
```

Hopefully, given what you've learned with Launch to date, the code above evokes a "YUCK" response. Writing HTML so verbosely inside our Java class has many limitations. First, it's hard to read and maintain. Secondly, and most importantly, it confuses responsibilities between business logic and presentation. Why is our servlet responsible for the HTML generation here? We already learned a better tool for that!

As we might expect, this is where our views come in, in the form of our JSPs.

## Using JSPs in our Servlets

Let's work to combine a servlet with a Java Server Page. Let's first modify `MyFirstServlet` to use a `RequestDispatcher` to reference a view page, replacing our `PrintWriter` entirely:

```java
// MyFirstServlet.java
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet(urlPatterns = "/hello")
public class MyFirstServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String greeting = "Hello from a servlet backed JSP";
    String firstName = req.getParameter("first_name");
    if(firstName != null && firstName != "") {
      greeting += ", " + firstName;
    }

    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/show.jsp");
    req.setAttribute("greeting", greeting);
    dispatcher.forward(req, resp);
  }
}
```

We've refactored our code to conditionally set up a variable `greeting`, rather than conditionally write to our `PrintWriter` object. Once we've composed our `greeting` string, we're loading up the Java Server Page we want to use (`/views/greetings/show.jsp`) with the `RequestDispatcher` instance. It's important to note that this view will not automatically have access to our `greeting` variable, however! We need to send that data to our JSP file so that it has access, using `setAttribute` to hand the variable down to our view. In this way, we're able to keep business logic in our controller (in this specific case, the servlet), and then send the necessary data to the view (the Java Server Page), so that the JSP can present the right information to the user.

With our controller in place, we can now construct our Java Server Page. Create the JSP as `src/main/webapp/views/greetings/show.jsp` and insert the below code:

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>JSP backed by a servlet</title>
  </head>
  <body>
    <p><%= request.getAttribute("greeting") %></p>
  </body>
</html>
```

Ah, isn't that so much cleaner? We have the controller handling the processing of the `first_name` querystring parameter, and the JSP handling the resulting HTML. We can access the attribute we set in our servlet using `getAttribute`. Everything neat and tidy.

If we navigate to `/hello`, we should see the standard greeting, and if we navigate to `/hello?first_name=Jon`, we should see "Hello from a servlet backed JSP, Jon".

## Why This Matters

We now know how to use servlets to organize our different routes, and what we want our user to be able to view at different paths. Using servlets as our controllers and JSPs as our views allows us to separate our concerns, keeping business logic in the controller and presentational logic in the view.

## In Summary

Servlets are Java classes that provide developers with a mechanism to serve HTTP requests with responses. Writing HTML inside a servlet, however, is poor practice. Servlets and JSP's can be combined to better separate concerns, and to serve as a building block for a Model View Controller (MVC) design paradigm.
