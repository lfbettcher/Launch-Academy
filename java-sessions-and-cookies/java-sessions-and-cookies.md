# Who wants a cookie

By itself, the HTTP protocol is said to be stateless. Let's say Johnny Cupcakes is using the same application as Little Bobby Tables located at http://example.com. HTTP being a stateless protocol means that from request to request, the `example.com` web server is not capable of discerning which request is coming from Johnny Cupcakes and which request is coming from Little Bobby Tables. The web server is effectively blind to uniquely identifying its end users.

Recognizing this as a shortcoming of the technology, browsers and web servers added **cookies**. Cookies are a simple and elegant solution to this problem, and we'll explore them in this article.

## Learning Goals

- Justify how cookies bring statefulness to the stateless HTTP protocol
- Set a cookie with expiration
- Store and retrieve data in a session

## Getting Started

```no-highlight
et get java-sessions-and-cookies
cd java-sessions-and-cookies
idea .
```

## What's a Cookie?

As we alluded to above, HTTP is a stateless protocol. Each HTTP request-response cycle is treated independently. Our web server does not need to remember anything about past requests. It does not have to maintain any **state**.

This aspect of HTTP simplifies request handling, but it doesn't always mirror how we interact with web sites. Often, we visit several pages on a site and expect that the server has some way to remember our interaction with the website. For example, if we visit one page to log into a site, we expect the next page to remember that we're authenticated. If we add items to a cart, we expect the subsequent pages to remember the items we've added so far.

We can think of a series of requests from a single user as being part of a single **session**. Within this session, we might include some information that we want to remember such as their username, what items are in their cart, display preferences, etc. When that user visits the next page on our app, we want to associate that request with their previous session so we can access these bits of information.

Given that each HTTP request and response is handled independently by the web server, how can we tie multiple requests from the same user together? A common solution to this problem is to send the session information _attached with the HTTP response_ and have the browser include it with the next request it sends. This information is attached by setting an **HTTP cookie**.

Cookies are included via HTTP headers in both the request and response. Consider a website that wants to track how often a user visits a page. The first request sent is a simple GET request without any additional information:

```no-highlight
GET / HTTP/1.1
Host: example.com
```

The server will generate a response containing the HTML body but it also might include a cookie that tracks the number of times the page has been visited:

```no-highlight
HTTP/1.1 200 OK
Content-Type: text/html
Content-Length: 178
Set-Cookie: visit_count=1

<!DOCTYPE html>
<html>
  <head><title>Welcome!</title></head>
  <body>
    <h1>Greetings!</h1>

    <p>This appears to be your first time visiting the page!</p>
  </body>
</html>
```

The `Set-Cookie: visit_count=1` header instructs the browser to save this key-value pair and associate it with the _example.com_ domain. The next request we send to that site will include the additional `Cookie` header:

```no-highlight
GET /about.html HTTP/1.1
Host: example.com
Cookie: visit_count=1
```

When the web server handles this request, it notices the cookie indicating the user has visited one time previously. It can use this information to generate a different page as well as incrementing the visit counter:

```no-highlight
HTTP/1.1 200 OK
Content-Type: text/html
Content-Length: 176
Set-Cookie: visit_count=2

<!DOCTYPE html>
<html>
  <head><title>Welcome!</title></head>
  <body>
    <h1>Welcome back!</h1>

    <p>You have visited this page 1 time(s) previously.</p>
  </body>
</html>
```

Cookies can store arbitrary key-value pairs but the HTTP standard specifies that browsers only need to support 4K worth of data. It's best to avoid storing large chunks of data to avoid hitting this limit and also to reduce the bandwidth required to pass those values back and forth over the network.

Unless otherwise specified, browsers will only store a cookie as long as the browser window is open. These are known as **session cookies**. It is also possible to include an expiration date so that browsers know to keep these cookies around until the specified date has been hit:

```no-highlight
Set-Cookie: visit_count=1; Expires=Sat, 5 Nov 1955 10:04:00 GMT
```

Because cookies are stored in the client's browser, it is possible for a user to delete a cookie at any time. This will lose any information that was previously set and the user will be treated as if it was the first time they were visiting the page on the next request. Because we can't guarantee the lifetime of information stored in cookies, only use them for storing temporary settings or preferences that can be easily re-created if needed. Anything that needs to exist longer term should be stored in a more permanent setting such as a database.

## Setting Cookies in Java Applications

In the context of our greeting application, the information is lost after the form submission. What if we wanted to use our warm welcome as a component of each webpage the user hits? Let's explore this and help our little application grow.

So, in order to retain the data, we'll need to store the resultant greeting in a cookie or session variable. If you had to guess, where would you place the logic that assigns this value? If you guessed the controller, you'd be right. Recall that the controller serves as the manager of HTTP and traffic, and cookies / session management are traffic concerns. Let's modify our supplied `doPost` in `GreetingsController` to store the data in a cookie (be sure to auto-import `Cookie`!).

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

    //new code
    Cookie greetingCookie = new Cookie("greeting", greeting.toString());
    greetingCookie.setMaxAge(60*60*24);
    resp.addCookie(greetingCookie);

    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/show.jsp");
    dispatcher.forward(req, resp);
  }
  else {
    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
  }
}
```

Notice that we're only storing the resultant `String` in the cookie. This is intentional, and required because cookies only deal with text. Generally, we should store only the information we require in a cookie given size limitations and otherwise good architectural practice.

Here, we've explicitly set the expiration of our cookie to 24 hours from when the form submission was processed. The `setMaxAge` takes an arguments of seconds. (`60` seconds in a minute * `60` minutes in an hour * `24` hours in a day gives us the total number of seconds in a 24-hour day).

Let's run our application with this new code, submit the form, and see how it affects our browser's state. After submitting, let's open Chrome Inspector, and click on the "Application" tab. You should see a "Cookies" option in the sidebar. If you click on that, and look at the cookies for `http://localhost:8080`, you'll see our newly established cookie.

## Reading Cookies in Our Application

Let's create a new page entirely independent of the form submission workflow. We need to add a new `urlPattern` to our servlet for the root path, and we need to handle for that new path in our `doGet` method.

```java
@WebServlet(urlPatterns = {"/greetings/new", "/greetings", "/"})
public class GreetingsController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    if(req.getServletPath().equals("/greetings/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/new.jsp");
      dispatcher.forward(req, resp);
    }
    else if(req.getServletPath().equals("/")) {
      String cookieGreeting = null;
      for(Cookie cookie : req.getCookies()) {
        if(cookie.getName().equals("greeting")) {
          cookieGreeting = cookie.getValue();
        }
      }
      req.setAttribute("cookieGreeting", cookieGreeting);
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/index.jsp");
      dispatcher.forward(req, resp);
    }
    else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  //...
}
```

Unfortunately, the mechanism that Java provides for retrieving cookie values isn't ideal. It provides an Array, so we have to iterate through all of them until we find the cookie we're looking for. Once we do, we retrieve the value of the cookie and prepare it for display on the `index.jsp` template. Here's what the template located at `src/main/webapp/views/greetings/index.jsp` should look like:

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.launchacademy.greetings.Greeting" %>
<c:set var="greeting" value="${requestScope.cookieGreeting}" scope="request" />

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Hello from JSP</title>
  </head>
  <body>
      <p>The greeting you previously supplied was <c:out value="${cookieGreeting}" />
    <p><a href="/greetings/new">Get a New Greeting</a></p>
  </body>
</html>
```

If we resubmit the form, and navigate back to `http://localhost:8080`, we'll see the new value reflected.

## Handling for a Non-Existent Cookie

It's important to remember that the end-user can remove the cookie from their machine at any time, so it's important to consider that use case. If we delete our cookie in Chrome Inspector, and refresh the root path again, we'll receive a `NullPointerException`. Not. Good.

Let's modify our JSP a bit to handle for this possibility. Notice that we use the `<c: choose>` pattern here. While most conditional logic should be handled in the controller, the `choose` pattern allows us to handle cases where we want to conditionally render something to our page. This is very similar to the `switch` statement.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.launchacademy.greetings.Greeting" %>
<c:set var="greeting" value="${requestScope.cookieGreeting}" scope="request" />

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Hello from JSP</title>
  </head>
  <body>
    <c:choose>
      <c:when test = "${not empty cookieGreeting}" >
        <p>The greeting you previously supplied was <c:out value="${cookieGreeting}" />
      </c:when>
      <c:otherwise>
        <p>No greeting on file.</p>
        <p><a href="/greetings/new">Get a New Greeting</a></p>
      </c:otherwise>
    </c:choose>

  </body>
</html>
```

Inside of the `choose` tags we see `<c:when>` and `<c:otherwise>`.

`when` allows us to test for a condition. We can use as many `when`s as we need to following the pattern

```jsp
<c:when test = (boolean) >
  <p>resulting output />
</c:when>
<c:when test = (someOtherBoolean) >
  <p>resulting output />
</c:when>
```

`otherwise` is the catch all if no tests are satisfied.

## Clearing Our Cookie

To round out our discussion, let's programmatically remove the cookie. How do we get rid of the cookie? We set its expiration to 0. Let's add a new endpoint and update our `doGet` one more time.

```java
@WebServlet(urlPatterns = {"/greetings/new", "/greetings", "/", "/greetings/forget"})
public class GreetingsController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/new.jsp");
//    dispatcher.forward(req, resp);

    if(req.getServletPath().equals("/greetings/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/new.jsp");
      dispatcher.forward(req, resp);
    }
    else if(req.getServletPath().equals("/")) {
      String cookieGreeting = null;
      for(Cookie cookie : req.getCookies()) {
        if(cookie.getName().equals("greeting")) {
          cookieGreeting = cookie.getValue();
        }
      }
      req.setAttribute("cookieGreeting", cookieGreeting);
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/index.jsp");
      dispatcher.forward(req, resp);
    }
    else if(req.getServletPath().equals("/greetings/forget")) {
      Cookie foundCookie = null;
      for(Cookie cookie : req.getCookies()) {
        if(cookie.getName().equals("greeting")) {
          foundCookie = cookie;
          break;
        }
      }

      if(foundCookie != null) {
        foundCookie.setMaxAge(0);
        foundCookie.setPath("/");
        resp.addCookie(foundCookie);
      }

      resp.sendRedirect("/");
    }
    else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  //...
}
```

When we navigate to `/greetings/forget` after rebuilding, the servlet will look for the existing cookie, and if found, set its age to 0, and effectively expire the cookie.

## Doing the Same With Sessions

Java provides an more intuitive object when we want to work with data that is only pertinent to a session. We can retrieve a session inside of a servlet with a `request.getSession()`. Note, that in using this approach, the information is lost when the user closes the browser (instead of being held onto until a predetermined expiration date) -- so we would only want to use Sessions if we're okay with that! The work, however, is much less complicated than implementing cookies, because we can use `setAttribute` and `getAttribute` methods. Thankfully, because we've isolated logic in a good MVC pattern, we only have to update the _servlet_ to use sessions instead of cookies. Here's a new version of the servlet that uses an HttpSession instead of a cookie.

```java
package com.launchacademy.greetings;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/greetings/new", "/greetings", "/", "/greetings/forget"})
public class GreetingsController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    if(req.getServletPath().equals("/greetings/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/new.jsp");
      dispatcher.forward(req, resp);
    }
    else if(req.getServletPath().equals("/")) {
      req.setAttribute("cookieGreeting", req.getSession().getAttribute("greeting"));
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/index.jsp");
      dispatcher.forward(req, resp);
    }
    else if(req.getServletPath().equals("/greetings/forget")) {
      HttpSession session = req.getSession();
      session.setAttribute("greeting", null);
      resp.sendRedirect("/");
    }
    else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(req.getServletPath().equals("/greetings")) {
      Greeting greeting = new Greeting();
      greeting.setFirstName(req.getParameter("firstName"));
      greeting.setLastName(req.getParameter("lastName"));
      greeting.setLanguage(req.getParameter("language"));
      req.setAttribute("greeting", greeting);

      //new code
      HttpSession session = req.getSession();
      session.setAttribute("greeting", greeting.toString());

      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/show.jsp");
      dispatcher.forward(req, resp);
    }
    else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }
}
```

## Why This Matters

Cookies and sessions help us to remember our users, and to provide a more seamless experience for them. This mechanism is powerful for handling functionalities like authentication, shopping carts, and search preferences.

## In Summary

Use cookies to store data you'd like for browsers to retain over the long-term. Work with a session for its ease of use and for temporary persistence. Cookies have established expiration dates. When the user closes their browsers, session values are lost.

Retain as little information as possible in cookies and sessions.
