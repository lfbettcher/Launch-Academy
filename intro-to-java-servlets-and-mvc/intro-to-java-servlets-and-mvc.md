Java Server Pages provide us with a mechanism to incorporate Java into our web pages. They provide us with a way to control what is viewed by the user.
The underlying technology behind Java Server Pages is servlets.
Servlets are how HTTP requests are served in the Java ecosystem.

## Learning Goals

- Justify the benefits of the Model View Controller (MVC) design pattern
- Gain a base understanding of how servlets support REST architecture

## The Functionalities of JSPs

So far, we've worked with JSPs to create single-page web applications in Java. Our JSP determined what HTML would be rendered within our browser. The downside to the single-page application as we've been building it, however, is that the JSP file holds all of our logic! From declaring data structures to conditionally determining variables, our JSP file has held all of our custom functional code so far. And from what we know about the Separation of Concerns, this is not the way to do things! Our JSP files should be solely responsible for rendering things to the page - not declaring data sets, etc. We need a way to separate business logic from the presentation.

Additionally, as our apps grow, we will need ways to render multiple pages with different views at each path. We find that we need a way to handle for multiple "routes", so that our program can run different logic and load different pages at each path.

## The Emergence of an MVC Pattern

So far in Java, we've learned how to create Java entities, classes that can hold business logic and interact with our database. With JSPs, we learned how to create pages that render HTML, providing a user interface. And if we want a part of our application to handle our different routes, we'll need to bring Java servlets into the picture.

With Java, we can use a combination of a Java Server Page and a servlet to render our webpages. The Java Server Page will serve as our _view_, and will handle the presentation. The Servlet will serve as the _controller_, controlling what page shows up at what path, and it will supply data to the view for display. Later, we'll integrate JPA classes again, which will serve as _models_, and they will house the majority of business and database persistence logic.

This scheme is known as the Model View Controller (MVC) design pattern. As developers, we like to apply consistent and proven design approaches to address problems of the same nature. In a web-based context, the MVC design pattern is quite popular, because it allows us to separate the concerns of business, authorization/request handling, and presentation quite nicely. This pattern is at the core of many different frameworks across many different programming languages, and it will continue to serve us as we move forward in Java web development, including as we move into Spring. While we're getting our first peek at the MVC pattern now, we'll continue to dive deeper into this concept after some practice with implementing the pattern.

## How Servlets Work

While we wrote some examples of Java Server Pages in prior lessons, we haven't yet explored how they work. Java Server Pages leverage servlets. A **servlet** is a special kind of Java class which defines certain rules for how requests should be handled. Servlets are installed to a _servlet container_, like Tomcat. Effectively, Tomcat is programmed to delegate the processing of HTTP requests to the servlet.

Essentially, and especially within Tomcat, servlets are a more Java, class-oriented way of processing HTTP requests.

When we execute `tomcat7:run` or `tomcat7:deploy` tasks, we're deploying the servlets we're building to the Tomcat _container_.

It's worth noting that we could work directly in Tomcat's filesystem by adding our servlets into _Tomcat's_ existing file structure (rather than our own project file structure), but as web developers, it's best to make development and packaging of our code cohesive. Thankfully, with the `war` and `tomcat7` plugins, we can install our servlets in our _own_ file structure, and have them loaded into Tomcat seamlessly. The process may have its occasional snags, but it is the preferred development methodology when writing Java web applications.

Servlets are the foundational technology for doing web development with Java. If you use Java Server Pages (JSP), Java Server Faces (JSF), Struts, or Spring, you're using servlets.

## Servlets and RESTful Architecture

In our initial learning about HTTP and the request/response cycle, we learned about RESTful architecture, and the conventions typically adhered to when using the HTTP protocol. Specifically, we learned about the mapping of CRUD verbs to HTTP methods, as shown below:

| HTTP Verb | CRUD action|
| --------- | ---------- |
| POST      | Create     |
| GET       | Read       |
| PUT/PATCH | Update     |
| DELETE    | Delete     |

These verbs continue to be used in our Java web applications. With servlets, there is a pre-written class called an `HttpServlet`, which we can import and `extend` into our own custom servlets. `HttpServlet` gives us access to certain RESTful methods, such as `doGet` and `doPost`. We will `@Override` these methods in order to write our own routes and determine what logic will run based on the HTTP request.

## An Example Servlet

Let's create our first servlet in `src/main/java/MyFirstServlet.java`

```java
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet(urlPatterns = "/greetings")
public class MyFirstServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/index.jsp");
    dispatcher.forward(req, resp);
  }
}
```

Here we define a class that extends `HttpServlet`. This class implements methods that help us to service HTTP requests with HTTP Responses. We can override `doGet`, `doPost`, `doPut`, and `doDelete` to support all of our REST operations. In this example, we overrode `doGet`. Like our Express routes, a servlet considers abstractions of the request and the response as the first and second argument respectively. We type these arguments using the imported `HttpServletRequest` and `HttpServletResponse`.

We also use the `@WebServlet` annotation to define how this servlet maps to a url. This eliminates our need to include servlet mappings in our `web.xml` file. Our code is now set up such that a "GET" request to "/greetings" will start processing the Java code inside of this route.

Finally, inside of our route, we use an imported `RequestDispatcher` object to specify the specific JSP that we want to load (our view), and tell our Servlet to go ahead and send an HTTP response.


## Why This Matters

In the next article, we'll do a much deeper dive into how to configure our Java servlets, including some different options of how we can work with them. For now, we've begun to see how they fit into the broader organization of our application, and allow us to adhere to an MVC design pattern.

The Model View Controller (MVC) paradigm has taken the web development industry by storm. Both client side and server side frameworks across many programming languages implement this design pattern.

The combination of servlets and JSPs will allow us to write well-organized and designed web applications as an intermediary step before we jump into Spring.

## In Summary

Servlets are Java classes that provide developers with a mechanism to serve HTTP requests with responses. They allow us to control which views get loaded based on the incoming HTTP request's **verb** (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`) and **path**. Using JSPs servlets, we begin to implement a more thorough MVC design pattern into our Java web applications.
