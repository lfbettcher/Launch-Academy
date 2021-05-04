Where we used Java Server Pages in the first version of our "stack", we will use Thymeleaf to construct our views in Spring applications. Let's explore it by comparing and contrasting it with JSP.

## Getting Started

```no-highlight
et get java-views-with-thymeleaf
cd java-views-with-thymeleaf
idea .
```

## Learning Goals

- Compare and contrast Thymeleaf with JSP
- Discuss established filesystem conventions
- Provide view context from the controller
- Use `th:text` to render a value on a web page
- Assign attributes using Thymeleaf
- Iterate through a collection with `th:each`

For this project, we're going to build an application that keeps track of business mileage for work-related travel.

## Building Our Controller

Let's build out a basic controller that will eventually list out the trips in our system. While we would normally connect our application to a database, we're focusing on views, so we'll hardcode our entities to keep the focus on the subject on this article.

Let's define our `Trip` POJO in a `models` package.

```java
package com.launchacademy.thymeleafViews.models;

import java.util.Date;

public class Trip {
  private String description;
  private Double milesTraveled;
  private Date traveledOn;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getMilesTraveled() {
    return milesTraveled;
  }

  public void setMilesTraveled(Double milesTraveled) {
    this.milesTraveled = milesTraveled;
  }

  public Date getTraveledOn() {
    return traveledOn;
  }

  public void setTraveledOn(Date traveledOn) {
    this.traveledOn = traveledOn;
  }
}
```

Let's create a `controllers` package and define a `TripsController`:

```java
package com.launchacademy.thymeleafViews.controllers;

import com.launchacademy.thymeleafViews.models.Trip;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trips")
public class TripsController {
  @GetMapping
  public String findAll(Model model) {
    Trip trip = new Trip();
    trip.setDescription("Lunch meeting in Boston");
    trip.setMilesTraveled(20.1);
    trip.setTraveledOn(new Date());

    model.addAttribute(trip);
    return "trips/index";
  }
}
```

Here, we're just dealing with a single instance of a `Trip`. We add it to context via the `ui.Model`.

Let's write our view that presents this single `trip` instance in `src/main/resources/templates/trips/index.html`

```html
<!DOCTYPE html>
<html>
  <head>
    <title>Thymeleaf</title>
  </head>
  <body>
    <h1>View Template Code Here</h1>

    <div class="trip">
      <h2 th:text="${trip.description}"></h2>
      <dl>
        <dt>Miles Traveled</dt>
        <dd th:text="${trip.milesTraveled}"></dd>

        <dt>Traveled On</dt>
        <dd th:text="${trip.traveledOn}"></dd>
      </dl>
    </div>
  </body>
</html>
```

We use `th:text` to effectively assign what we want to appear inside the HTML element. Note also that we're making use of a `dl` or [definition list][mdn-dl] in this example. Semantically, applying this element is best suited for when we are listing out terms and definitions. In this case, the term is the field name, and the definition is the field value. Using a `<dl>` in this way is common practice when we're listing the attributes of a single record in our database tables.

***Hint: You might get an error that `attribute th:text is not allowed here`. If so, ignore it.***

### More Syntactic Sugar

With Thymeleaf, we can set context as we get deeper into our data.

```html
<!DOCTYPE html>
<html>
  <head>
    <title>Thymeleaf</title>
  </head>
  <body>
    <h1>View Template Code Here</h1>

    <div class="trip" th:object="${trip}">
      <h2 th:text="*{description}"></h2>
      <dl>
        <dt>Miles Traveled</dt>
        <dd th:text="*{milesTraveled}"></dd>

        <dt>Traveled On</dt>
        <dd th:text="*{traveledOn}"></dd>
      </dl>
    </div>
  </body>
</html>
```

We set the `th:object` in `div.trip` so that every element nested underneath can take advantage of that context. So, inside the `div` element, we can use a `*{}` instead of a `${}`. This is known as a **selection expression**. This provides us with flexibility and modularity when writing out the HTML that describes our objects. This saves us some typing and also helps with the maintainability of our view. We can change the `trip` variable name without having to update it in multiple locations within the view.

## Conditional Rendering

We can use the `th:if` to evaluate whether markup should be rendered. For example, if our description is optional, we wouldn't want to render an empty `<h2>` tag.

```html
<h2 th:if="*{not #strings.isEmpty(description)}" th:text="*{description}"></h2>
```

There's a few things happening here.

- We're using the `th:if` attribute to conditionally render the `<h2>` element. If we modified our controller to `trip.setDescription("");` and rebuilt, the `<h2>` element would not be displayed.
- We use the `strings` [expression utility object][expression-utility-objects] to get a boolean as to whether the `description` is null or emty string.
- We wrap the whole `th:if` expression in a selection to indicate we waht to use the `th:object` context of the parent `div`.

## Attributes

Let's add a visual cue for high mile trips vs. low mile trips.

First, we'll place a `style` tag in the `<head>`

```html
  <head>
    <title>Thymeleaf</title>
    <style>
      .trip .high-miles {
        color: orange;
      }

      .trip .low-miles {
        color: green;
      }
    </style>
  </head>
```

Then we can conditionally apply a css class to our element:

```html
<dd th:class="*{milesTraveled ge 10}? 'high-miles' : 'low-miles'" th:text="*{milesTraveled}"></dd>
```

Again, we're using our selection expression to ask whether the miles traveled exceeds or equals 10. If it's less, we'll see the miles reflected in green text. If the mileage is more, we'll see it in orange text.

With Thymeaf and boolean expressions, we can use:

- `gt` for `>`
- `ge` for `>=`
- `lt` for `<`
- `le` for `<=`
- `eq` for `==`
- `ne` for `!=`

We can set other attribute values the same way. For further explanation of this topic, refer to [Thymeleaf's attribute documentation][thymeleaf-attr-docs]

## Collections

Let's modify our controller so that we have a list of `Trip` instances.

```java
package com.launchacademy.thymeleafViews.controllers;

import com.launchacademy.thymeleafViews.models.Trip;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trips")
public class TripsController {
  @GetMapping
  public String findAll(Model model) {
    Trip trip = new Trip();
    trip.setDescription("Lunch meeting in Boston");
    trip.setMilesTraveled(20.1);
    trip.setTraveledOn(new Date());

    Trip otherTrip = new Trip();
    otherTrip.setDescription("Dinner meeting in Quincy");
    otherTrip.setMilesTraveled(5.8);
    otherTrip.setTraveledOn(new Date());

    List trips = new ArrayList<Trip>();
    trips.add(trip);
    trips.add(otherTrip);

    model.addAttribute("trips", trips);
    return "trips/index";
  }

}
```

We can then modify our view template to make use of a `th:each` attribute.

```html
<!DOCTYPE html>
<html>
  <head>
    <title>Thymeleaf</title>
    <style>
      .trip .high-miles {
        color: orange;
      }

      .trip .low-miles {
        color: green;
      }
    </style>
  </head>
  <body>
    <h1>View Template Code Here</h1>

    <div class="trips" th:each="trip : ${trips}">
      <div class="trip" th:object="${trip}">
        <h2 th:if="*{not #strings.isEmpty(description)}" th:text="*{description}"></h2>
        <dl>
          <dt>Miles Traveled</dt>
          <dd th:class="*{milesTraveled gt 10}? 'high-miles' : 'low-miles'" th:text="*{milesTraveled}"></dd>

          <dt>Traveled On</dt>
          <dd th:text="*{traveledOn}"></dd>
        </dl>
      </div>
    </div>

  </body>
</html>
```

We've added a parent `div` element with the `th:each` attribute. With Thymeleaf, we can iterate over a collection with an iteration expression like this. Here, we're looping through all of the trips and assigning each trip to the variable `trip` for every time we loop around the race track.

## Layouts

With Thymeleaf, we can also share snippets of code. We commonly use this to centralize HTML headers, navigation, and footers of our web pages in order to obtain a consistent look and feel.

Let's define a new folder `src/main/resources/templates/fragments`. Inside there, let's create a `layout.html`.

```html
<!DOCTYPE html>
<html th:fragment="layout (template)">
<head>
  <title>Thymeleaf</title>
  <style>
        .trip .high-miles {
          color: orange;
        }
        .trip .low-miles {
          color: green;
        }
      </style>
</head>

<body>
<th:block th:include="${template}"/>
</body>

</html>
```

We've effectively taken everything that is not specifically geared towards our trips template and extracted it into a global layout. We use the `th:fragment` directive to tell the rest of Thymeleaf's system that we are creating a reusable fragment called `layout`. The fragment that we are defining here takes a single argument, the markup we want to inject into the fragment.

Then, inside the body we use `<th:block th:include>` to bring that template in.

So, with this in place, we can now simplify our `trips/index.html`.

```html
<html xmlns:th="https://www.thymeleaf.org"
th:replace="~{fragments/layout :: layout (~{::body})}">

<body>
<h1>View Template Code Here</h1>

<div class="trips" th:each="trip : ${trips}">
  <div class="trip" th:object="${trip}">
    <h2 th:if="*{not #strings.isEmpty(description)}" th:text="*{description}"></h2>
    <dl>
      <dt>Miles Traveled</dt>
      <dd th:class="*{milesTraveled gt 10}? 'high-miles' : 'low-miles'" th:text="*{milesTraveled}"></dd>

      <dt>Traveled On</dt>
      <dd th:text="*{traveledOn}"></dd>
    </dl>
  </div>
</div>

</body>
</html>
```

Here, we use `th:replace` to say that we're going to load the `fragments/layout.html` file and we're going to assign the body of this template to the layout's argument `layout`. This allows us to effectively inject our `index.html` template into the layout. Using this approach, all of our individual view templates can use a consistent look and feel for navigation and other items.

### Why This Matters

Thymeleaf is a very capable templating solution that provides for a lot cleaner syntax when presenting our markup to end users.
It integrates really well with Spring, so we'll be using it extensively moving forward.

[expression-utility-objects]:https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#appendix-b-expression-utility-objects
[thymeleaf-attr-docs]:https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values
[mdn-dl]:https://developer.mozilla.org/en-US/docs/Web/HTML/Element/dl