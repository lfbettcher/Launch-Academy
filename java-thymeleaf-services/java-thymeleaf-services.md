## Getting Started

Code along to this article by picking up where you left off with Java Thymeleaf Forms.

### Learning Goals

- Hold a collection of objects in session context
- Define a Spring Component
- Autowire a field

## Storing Our Trips in Session

While using a session for persistence is generally a bad idea, let's use it here simply to illustrate a few key aspects of the Spring framework.

We're going to store an `ArrayList` of trips in our session. To do so, we're going to create our first service object. In our `com.launchacademy.thymeleafForms` package, let's create a `services` package and create an interface called `TripService`:

```java
// src/main/java/com.launchacademy.thymeleafForms/services/TripService.java

package com.launchacademy.thymeleafForms.services;

import com.launchacademy.thymeleafForms.models.Trip;
import java.util.List;

public interface TripService {
  List<Trip> getList();
  void addToList(Trip trip);
}
```

Implementers of this service will provide us with a mechanism to return an entire list of trips, and they will allow us to append to the list. Let's implement this using a session component class. We will also place this in the `services` package, and we'll name it `TripSessionBasedService`.

```java
// src/main/java/com.launchacademy.thymeleafForms/services/TripSessionBasedService.java

package com.launchacademy.thymeleafForms.services;

import com.launchacademy.thymeleafForms.models.Trip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class TripSessionBasedService implements TripService {
  private List<Trip> trips;

  public TripSessionBasedService() {
    trips = new ArrayList<Trip>();
  }

  public List<Trip> getList() {
    return trips;
  }

  public void addToList(Trip trip) {
    trips.add(trip);
  }
}
```

Just like a Data Access Object, we use this Service to work with a collection of trips. Now, you may be asking yourself, why go to all of this trouble of first creating a TripService interface, and _then_ creating our TripSessionBasedService class?? The answer is in the flexibility Spring wants us to provide in our programs. While a session based persistence of our trips works as a proof of concept, we'll eventually want to write another class that implements persistence for a trips, ideally with a database. As long as these classes adhere to the provided interface, we can change them out pretty readily.

Notice, too, that this class has a zero argument constructor. This is especially important, because we want our services to be Spring beans. Recall that a class is a Java bean if it has a zero argument constructor, and if all of its fields are private. Spring beans are a special kind of Java bean, which we will explore in greater detail later.

Ok, here's where things get particularly magical, and it will require a bit of a trust fall for now. Let's `Autowire` our service to our controller via constructor injection.

Inside `TripsController`:

```java
// src/main/java/com.launchacademy.thymeleafForms/controllers/TripsController.java

...
public class TripsController {
  
  private TripService tripService;

  @Autowired
  public TripsController(TripService tripService) {
    this.tripService = tripService;
  }
  ...
```

The `@Autowired` annotation is provided by Spring core. When we add this field in such a way, Spring's Inversion of Control (IoC) container manages the instantiation of this object. We never have to call `new` to construct this `TripService` instance. This is all managed for us as part of the Spring framework.

Because we used the `@Session` annotation on the service implementation, the IoC container stores the list of trips specific to the browser session. We say that this component is scoped to the session.

Let's put this new service instance to work in our controller. We're going to rework our POST handler, and we're going to create a listing of our trips.

```java
// src/main/java/com.launchacademy.thymeleafForms/controllers/TripsController.java

@PostMapping
public String createTrip(@ModelAttribute Trip trip) {
  tripService.addToList(trip);
  return "redirect:/trips";
}
```

Notice, we are simply adding our new `trip` to the list via our `tripService`.

Instead of rendering the `trips/show` template like we had previously, we perform a redirect that will result in a GET to `/trips`. In Spring, we can introduce a 302 redirect by prefixing our returned string with `redirect:`.

Now that our redirect is in place, we can put together our listing endpoint and correlating template. Add the below mapping into your `TripsController`:

```java
// src/main/java/com.launchacademy.thymeleafForms/controllers/TripsController.java

@GetMapping
public String listTrips(Model model) {
  model.addAttribute("trips", tripService.getList());
  return "trips/index";
}
```

Because our `tripService` is already `@Autowired` and scoped to session, all we have to do is add it to the `ui.Model`. Now let's create the template `trips/index.html`.

```html
<!-- src/main/resources/templates/trips/index.html -->

<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org"
      th:replace="~{fragments/layout :: layout (~{::body})}">
  <body>
    <h1>Trip Log</h1>

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


    <p><a th:href="@{/trips/new}">&plus; Add a New Trip</a></p>
  </body>
</html>
```

Ok, let's rebuild and add a few trips. You will see those trips persisted on your listing page. Now, here's the cool part - open your application in another browser (eg. Firefox if you're using Chrome.). When you navigate to `http://localhost:8080/trips`, you'll start out with a blank list. That's because the IoC Container scopes the instance to our session, so each user will have a unique list of trips.

## Why This Matters

Spring tries to encourage sound object oriented practices. Through the use of intelligently placed annotations, we can do a lot with a small amount of code. What's more, we can leave ourselves in a great position for future modularity and expansion of our codebase. Used well, Spring is a powerful way to rapidly build web applications.

While the concepts used may currently feel a bit magical, we want to expose you to real world use cases for things like Service objects and `@Autowired` so they are easier to understand when we discuss them in detail.
