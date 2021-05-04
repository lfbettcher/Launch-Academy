## Learning Goals

- Discuss how Spring implements the MVC design pattern
- Demonstrate how Spring controllers interact with Model objects and views (with either JSP or Java templates)

## Getting Started

```no-highlight
et get java-spring-controllers
cd java-spring-controllers
idea .
```

## Introduction

The Spring framework provides a simple implementation of the Model-View-Controller (MVC) architecture. Traditionally, the MVC pattern is defined as:

- **Model**: Specifies the logical structure of data
- **View**: The user interface (UI), or that which the user will view.
- **Controller**: Controls interaction between the Model and View. In a web development context, the controller handles HTTP requests.

The MVC pattern has the benefit of keeping the program structure separated along clearly defined logical layers. This is also known as _"Separation Of Concerns"_.

## Spring Implementation of MVC

In order to create our first endpoint, we must first explore Spring's interpretation of the Model, View, Controller (MVC) design pattern.

### Models

We'll use Spring Data like we've used JPA Entities to manage our database interaction.

### UI Models

So far, we know of models purely as managers of our business logic. Primarily, we've used JPA entities to house information we want to store.

In Spring, there's another layer of models. Instances of this type are known as **UI Models**. Effectively, these models are responsible for sharing data between our controller and view.

In our Servlets and JSP interactions, we used `setAttribute` and `getAttribute` to accomplish the same goal. In Spring, this responsibility is centralized in the `ui.Model`. It's confusing, isn't it? While we're used to a Model being a Java Bean or a JPA Entity, this model is effectively a bag / data structure that allows us to exchange information between the controller and the view. Later in this article, we will compare the two approaches. It's worth noting here that Models and UI Models have very little in common.

### Views

We're going to use ThymeLeaf to manage our view layer. Though the syntax is a bit different from JSTL or even Handlebars, the design premise is the same. Given a `ui.Model`, we use ThymeLeaf to present the user with HTML that delivers the user experience.

### Controllers

Similar to how our servlets were used, a Spring controller's primary responsibility is to handle and respond to HTTP requests.

Spring, and in particular, Spring Controllers, makes excessive use of annotations. We will denote our Spring controller classes with `@Controller`. We'll then define methods that will respond to specific URL patterns and HTTP methods.

## Our First Controller Endpoint

Let's create a basic controller and view. In Spring, we're going to get more disciplined about managing packages. Let's create a package `com.launchacademy.webGreeter.controllers`. Inside there, we can create our `GreetingsController` class.

```java
@Controller
@RequestMapping("/greetings")
public class GreetingsController {
}
```

We use the `@Controller` annotation to indicate to Spring that we want to use this class to handle HTTP requests. Because we are also using Spring Boot, this controller will be automatically wired up to respond to HTTP requests starting with `/greetings`. This means that we no longer need to list out routes like `/greetings/new`.

Let's define our first endpoint within the `GreetingsController` class:

```java
public class GreetingsController {

  @GetMapping("/default")
  public String getDefaultGreeting(Model model) {
    model.addAttribute("greeting", "Hello from Spring");
    return "greetings/show";
  }
}
```

First, we annotate the method with a `GetMapping`. This annotation indicates that this method will respond to a GET request with the provided path. Because the controller starts with `/greetings` based on the request mapping, the full path for this endpoint will be `/greetings/default`.

Secondly, notice that the method takes an argument. This is the `ui.Model` instance we discussed above. We use this model to exchange data between our controller and the view. We never have to define this instance. It is supplied to the controller method as its first argument.

Finally, we return the path to the view template that we want to render.

Let's take a moment to compare this to what we've done with Java Servlets. If we were going to implement a similar endpoint via a Servlet, we would write a class like the following:

```java
@WebServlet(urlPatterns = {"/default"})
public class GreetingsController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.setAttribute("greeting", "Hello from Servlet");

    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/show.jsp");
    dispatcher.forward(req, resp);
  }
}
```

Instead of setting an attribute on the `Request` object, Spring supplies a `ui.Model` instance as an argument to the controller method. This `ui.Model` is referenced both in the controller and the view, and is used as a separate object that manages the data shared between the two. Notice also that in our Spring controller, we don't have to instantiate a `RequestDispatcher` - we can just return a path to the intended view template. Other than those items, the premise is very much the same when comparing our Servlet implementation to our Java Spring implementation.

### A Simple View

Let's define our view as `src/main/resources/templates/greetings/show.html`:

```html
<!DOCTYPE html>
<html>
  <head>
    <title>Spring Controllers Example</title>
  </head>
  <body>
    <p th:text="${greeting}">Replaced by Thymeleaf</p>
  </body>
</html>
```

We'll explore thymeleaf in a subsequent article, but for now, just understand that the value of the `th:text` attribute is going to replace what's inside the `<p>` element. Here, we're specifying the `greeting` that was set in our `ui.Model` by the controller.

### Testing It Out

We can start our server by running `./mvnw spring-boot:run`. When we navigate to `http://localhost:8080/greetings/default`, we should see "Hello from Spring".

## Query Strings

Let's make our greetings a bit more interesting with some dynamic behavior. Like we saw with our Servlet implementations, we can use the query string as part of our HTTP request to provide parameters to the server. Let's add a new endpoint that will support a `?name=<name>` in the path.

```Java
@GetMapping("/by-query-string")
public String getQueryStringGreeting(@RequestParam String name, Model model) {
  model.addAttribute("greeting", "Hello from Spring, " + name);
  return "greetings/show";
}
```

To see the output, be sure to rebuild your project in Intellij. Remember, as part of Spring Devtools, your server will reload your classes after a rebuild. After we rebuild and navigate to `http://localhost:8080/greetings/by-query-string?name=Susan`, we should see "Hello from Spring, Susan".

Notice that we annotate an argument and indicate that we're looking for the request parameter. We can take that parameter and use it like any other string.

## Dynamic Paths

Querystrings are great, but Spring provides us with an additional enhancement. We'll define a new method in our `GreetingsController`, underneath our query string implementation.

```java
@GetMapping("/by-name/{name}")
public String getCustomizedGreeting(@PathVariable String name, Model model) {
  model.addAttribute("greeting", "Hello from Spring, " + name);
  return "greetings/show";
}
```

When we rebuild and navigate to `http://localhost:8080/greetings/by-name/Jon` the output will be "Hello from Spring, Jon". You can change this last part of the URL to a first name of your choosing, and that name will be reflected in the HTML.

### Using ModelAndView

Remember that Spring likes to provide us with options. Depending on how we want our controllers to operate, we can also choose to return a `ModelAndView` object, which essentially just returns the pair of our model and view in one object rather than simply returning the view. Let's see what it looks like to make use of a `ModelAndView` instance to accomplish the same tasks as above in our controller.

```java
@GetMapping("/default")
public ModelAndView getDefaultGreeting() {
  ModelAndView modelAndView = new ModelAndView("greetings/show");
  modelAndView.addObject("greeting", "Hello from Spring");
  return modelAndView;
}
```

Here, we construct a `ModelAndView` instance with the path to our view. We then add the `greetingContext` to that instance and return it.

Conventionally, we'll lean towards the original implementation. We'll shy away from using the `ModelAndView` approach, and we'll generally write endpoints that return a `String` pointing to the view template instead.

## In Summary

Spring MVC provides an easy to use backend framework for implementing web based services. URL paths are used to access controller endpoints, and parameters & models are easily incorporated and mapped to relevant views.

[camel-case-conventions]: https://techterms.com/definition/camelcase
