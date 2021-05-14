So far, we have learned how to create a Spring application that uses Thymeleaf views. In this article, we will learn how to connect our application to a database,  save form data into a database table, and populate our views with database data.

## Learning Goals

 - Connect a Spring application to a database
 -  Save form data into a database table and populate views with database data

## Getting Started

```
et get java-spring-data-service
cd java-spring-data-service
idea .
createdb trips_development
```

## Recap

Before we start, let us quickly recap what we have learned so far, in our previous Spring-based articles. We have so far built a ***trip***  application using Spring and Thymeleaf views. This application allows users to add trip information in a form and view all previously added trips. Our application uses a  session-based service that stores trip information into the session and retrieves it from the session. While that was useful for learning how services work, most real-world applications use databases. Now that we are familiar with Spring Data and how it can be used to access a database table from Spring, let us apply that here and replace our session-based service with a database-based service.

We have added the Maven dependencies and `application.properties` files for you, as laid out in the Java Spring Data article.

### Database Table

Now that our application is connected to a database, we can take a look at our migration, which creates a database table corresponding to a Trip. This has been provided:

```sql
-- src/main/resources/db/migration/V1__create_trips.sql

CREATE TABLE trips (
  id SERIAL PRIMARY KEY,
  description TEXT,
  miles_traveled float,
  traveled_on timestamp
);
```

Recall that running `spring-boot:run` will run our migrations for us.

## Code Modifications

### POJO Class

We had earlier defined a `Trip` bean that was used to display data to the Thymeleaf view.  Let us now make modifications to it to designate it as a JPA entity:

``` java
// src/main/java/com.launchacademy.thymeleafViews.models/Trip.java

@Entity
@Table(name = "trips")
public class Trip {
	
		@Id
	  @SequenceGenerator(name="trips_generator",
	      sequenceName="trips_id_seq", allocationSize = 1)
	  @GeneratedValue(strategy= GenerationType.SEQUENCE,
	      generator="trips_generator")
	  @Column(name="id", nullable=false, unique=true)
	  private Integer id;
	
	private String description;
	private Double milesTraveled;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date traveledOn;
   //getters and setters
}
```

So, now we have added the `@Entity` annotation and the `@Table` annotation to the class. We have also added a new `id` field which serves as a primary key column field. The getters and setters are left out in the code above for the sake of brevity.

### Creating a Repository

Next, we need to create a `TripRepository` interface as follows:

``` java
// src/main/java/com.launchacademy.thymeleafViews.repositories/TripRepository.java

public interface TripRepository extends CrudRepository<Trip, Integer>{

}
```

As explained earlier,  creating a repository interface like this automatically provides us with useful methods for saving/retrieving data to/from the database.

### Creating a database service

If you recall, we had earlier defined a `TripService` interface as follows:

```java
public interface TripService {

	List<Trip> getList();

	void addToList(Trip trip);
}
```

We had then implemented it with a `TripSessionBasedService` that stores data in a session and retrieves/returns it from the session.
Let us now create another service implementation called `TripDatabaseBasedService`  that uses a database instead of a session:

```java
// src/main/java/com.launchacademy.thymeleafViews.services/TripDatabaseBasedService.java

@Service
@Primary
public class TripDatabaseBasedService implements TripService{
	
	private TripRepository tripRepository;
	
	 @Autowired
	  public TripDatabaseBasedService(TripRepository tripRepository) {
	    this.tripRepository = tripRepository;
	  }

	public List<Trip> getList() {
		return (List<Trip>)tripRepository.findAll();
	}

	public void addToList(Trip trip) {
		tripRepository.save(trip);
	}
}
```

`TripDatabaseBasedService` implements `TripService`. The `@Service` annotation designates the class as a special type of Spring bean that holds the business logic.  The `@Primary` annotation designates this as the primary service.  Since we now have two classes (`TripSessionBasedService` and `TripDatabaseBasedService`) that implement the `TripService` interface, this annotation is required in order for Spring to be able to determine which implementation to use. Thus, if at any time we wish to go back to using the `TripSessionBasedService`, we simply need to designate this class with the  `@Primary` annotation. 

The `TripDatabaseBasedService`has a `tripRepository` field which is autowired via the constructor.  As explained earlier, when the `Autowired` annotation is specified like this, Spring manages creating the object. Thus, in this case, Spring automatically creates a `TripRepository` instance and passes it to the constructor. 

Finally, the `getList` method uses `tripRepository` to fetch data from the database, while the `addToList` method uses `tripRepository`to save Trip information into the database.

### Revisiting TripsController

Let us now revisit our `TripsController`:

``` java
public class TripsController {
  
  private TripService tripService;

  @Autowired
  public TripsController(TripService tripService) {
    this.tripService = tripService;
  }

 @PostMapping
 public String createTrip(@ModelAttribute Trip trip) {
  tripService.addToList(trip);
  return "redirect:/trips";
}

 @GetMapping
 public String listTrips(Model model) {
   model.addAttribute("trips",  tripService.getList());
   return "trips/index";
}
```
 
 If you recall, `TripService` is already autowired into our `TripsController`. Since we have designated `TripDatabaseBasedService` as our **primary** service, the spring container automatically injects this into the `TripsController`. Thus, when the `createTrip` method is invoked, this causes the `TripDatabaseBasedService#addToList` method to be invoked and the `Trip` data gets saved in the database. Similarly, when the `listTrips` method is invoked, the `TripDatabaseBasedService#getList` method is invoked which returns data from the database.

We are now beginning to see the real magic of Spring! By autowiring the service in the controller, it is easy to switch to a different service implementation with no impact on the rest of the code.

We can now go to <http://localhost:8080/trips> to see our app in action. When we fill out our form and see our page with our new trip, we can run the following in our terminal to see that this trip has been persisted to our database:

```
psql trips_development
> SELECT * FROM trips;
```


## Why This Matters

Most real-world programs use databases, and so it makes sense to populate our front-end views with database data and store data entered through a form into the database. Spring Data allows us to persist/retrieve data without having to write much code. 

## Summary

We started with a Spring application that uses a session-based service. We made changes to our POJO class so that it can also serve as a JPA entity. Finally, we replaced our session-based service with a database-based service. 
