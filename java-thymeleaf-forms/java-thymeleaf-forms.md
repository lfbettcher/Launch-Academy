In this article, we'll explore how to connect forms to our Spring application.

## Getting Started

```no-highlight
et get java-thymeleaf-forms
cd java-thymeleaf-forms
idea .
```

## Learning Goals

- Build a form template using ThymeLeaf
- Process an HTTP Post using a Spring Bean

In this article, we'll continue on with our `Trip` domain.

## Building Our Form

### Setting Up Our Controller GET

Here, we have re-created our `TripsController` in a `controllers` namespace. *Note that we've added an additional subpackage of our `thymeleafforms` package here, annotated by adding `.controllers` at the end of our package name at the top of the file.* Sub-packages allow us to further organize our code. Take a look at the existing `TripsController` to see our `/new` `@GetMapping`:

```java
// src/main/java/com/launchacademy/thymeleafForms/controllers/TripsController.java

@GetMapping("/new")
public String getNewForm(@ModelAttribute Trip trip) {
  return "trips/new";
}
```

_You'll notice that we're using a new annotation here: `@ModelAttribute`._ This annotation allows us to skip the need to separately create our Model and add the attribute, as so:

```java
@GetMapping("/new")
public String getNewForm(Model model) {
  Trip trip = new Trip();
  model.addAttribute("trip", trip);
  return "trips/new";
}
```

If we're _only_ adding one attribute to our model, we can use `@ModelAttribute` as a shorthand. We'll talk even more about the powers of this annotation later in the article!

We have also implemented the view template with Thymeleaf at `src/main/resources/templates/trips/new.html`. 

```html
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org"
th:replace="~{fragments/layout :: layout (~{::body})}">

 <body>
  <h1>Add a New Trip</h1>

  <form action="#" th:action="@{/trips}" th:object="${trip}" method="POST">
    <div>
      <label th:for="description">Description</label>
      <input type="text" th:field="*{description}" />
    </div>

    <div>
      <label th:for="milesTraveled">Miles Traveled</label>
      <input type="number" th:field="*{milesTraveled}" />
    </div>

    <div>
      <label th:for="traveledOn">Date and Time of Travel</label>
      <input type="datetime-local" th:field="*{traveledOn}" />
    </div>

    <div>
      <input type="submit" value="Log Trip" />
    </div>
  </form>
 </body>
</html>
```

Here, we use the `th:object` expression to set the scope of the form. Then for each input, we use the `th:field` expression with a selection that correlates to the attribute we want to populate.

If we submit this form, as you might expect, the server will respond with a 404. We must define a `POST` handler to process the input. Let's **add an endpoint** to our `TripsController`.

```java
@PostMapping
public String createTrip(@ModelAttribute Trip trip) {
  return "trips/show";
}
```

The `@ModelAttribute` annotation does all the work for us in terms of populating our Trip object. So long as we correlate our field names between our form and our `Trip` object, it will automatically map the parameters. That newly constructed `Trip` object is added to our `ui.Model` behind the scenes, and we render a "show" view as can be found in `src/main/resources/trips/show.html`.

```html
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org"
      th:replace="~{fragments/layout :: layout (~{::body})}">

  <body>
    <h1>Submission Results</h1>
    <div class="trip" th:object="${trip}">
      <h2 th:if="*{not #strings.isEmpty(description)}" th:text="*{description}"></h2>
      <dl>
        <dt>Miles Traveled</dt>
        <dd th:class="*{milesTraveled gt 10}? 'high-miles' : 'low-miles'" th:text="*{milesTraveled}"></dd>

        <dt>Traveled On</dt>
        <dd th:text="*{traveledOn}"></dd>
      </dl>
    </div>
  </body>
</html>
```

There's just one problem: we have the following field in our form, but if we had not added additional handling in our model, the whole HTTP transaction would have blow up.

```html
<div>
  <label th:for="traveledOn">Date and Time of Travel</label>
  <input type="datetime-local" th:field="*{traveledOn}" />
</div>
```

Recall that HTTP is a text-based protocol, so our library would not know how to translate the string that comes from this parameter into a `Date`. To accomplish this, we have added an annotation to our `traveledOn` field in the `Trip` model. _Check it out in your provided Trip model!_

```java
@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
private Date traveledOn;
```

This will assist Spring in converting our traveledOn `Date` from a string. When we rebuild and submit the form, the date successfully persists.  Try entering the following sample data:

```
Description : My First Trip!
Miles Traveled: 50
Date and Time of Travel: 2013-09-29T10:46
```

As a secondary check, add this to the end of your `createTrip` post method to show that you have the correct output. We'll talk about persisting the data in the next article.

```java
System.out.println(trip);
```

## Why This Matters

Spring tries to encourage sound object oriented practices. Through the use of intelligently placed annotations, we can do a lot with a small amount of code. What's more, we can leave ourselves in a great position for future modularity and expansion of our codebase. Used well, Spring is a powerful way to rapidly build web applications.

While the concepts used may currently feel a bit magical, we want to expose you to real world use cases for things like Service objects and `@Autowired` so they are easier to understand when we discuss them in detail, which will happen in the next article!
