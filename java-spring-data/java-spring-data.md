Let's incorporate our knowledge of the Java Persistence API into our Spring applications.

Follow along as we build an application to keep track of all the software we want to build.

## Getting Started

```no-highlight
createdb app_ideas_development
et get java-spring-data
cd java-spring-data
idea .
```

## Learning Goals

- Set up an application for PostgreSQL and Flyway
- Create a repository

## Configuring Spring Data

In order to access a PostgreSQL database with our Spring applications, we first need to set up our dependencies and connection string.

### pom.xml

Let's add the following dependencies to our `pom.xml`:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-core</artifactId>
</dependency>
```

First, we need to leverage the power of Spring Boot to automatically configure the Java Persistence API for us.

As we needed in our servlet-based applications, we need the posgresql client library to connect to our database. We'll also use flyway to manage our schema versioning.

### application.properties

We can use `src/main/resources/application.properties` to define a series of configuration parameters for our application. For the purposes of Spring data, we need to inform our application about the database we plan to interact with.

```no-highlight
spring.datasource.url = jdbc:postgresql://localhost:5432/app_ideas_development
spring.datasource.username = postgres
spring.datasource.password = password

flyway.url = jdbc:postgresql://localhost:5432/app_ideas_development
flyway.user = postgres
flyway.password = password

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults = false

spring.jpa.hibernate.ddl-auto = validate
```

The first block is for Spring's implementation of the JPA. By default, when properly configured, Spring will automatically migrate your database when you run your application.

If you want to run migrations manually, we recommend you add the second block of configuration. It is duplicative, but it gives us the ability to run our migrations with the following command:

```no-highlight
./mvnw flyway:migrate -Dflyway.configFile=src/main/resources/application.properties
```

This runs migrations after reading in the supplied `properties` file.

The third block of configuration informs Spring Data how to construct its SQL statements so that they are pertinent to PostgreSQL. We also supply a configuration to avoid a temporary issue with Hibernate and Spring compatibility that will otherwise cause issues with class reloading.

Lastly, we want to ensure that our schema and our JPA entities match up. We can set `ddl-auto` to `validate` to ensure they are in sync.

## Our First Entity In Spring

### Creating our Schema

Recall that we place migrations in the `src/main/resources/db/migration` directory. Let's create our first migration named `V1__create_ideas.sql`:

```no-highlight
CREATE TABLE ideas (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT
);
```

If we invoke `spring-boot:run` now, Spring Boot will ensure we are running the latest version of the schema. Run the task, and then verify that migrations have run.

```no-highlight
psql app_ideas_development
#= \d
```

You should see the `ideas` table listed.

### Creating the Entity

Now, we're ready to create our JPA entity to start connecting our model to our database table. Let's create a new namespace `models` and create a new class inside of it named `Idea`.

```java
package com.launchacademy.javaspringdata.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "ideas")
public class Idea {
  @Id
  @SequenceGenerator(name="idea_generator",
      sequenceName="ideas_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE,
      generator="idea_generator")
  @Column(name="id", nullable=false, unique=true)
  private Integer id;

  @NotEmpty
  @Column(name="name", nullable=false)
  private String name;

  @Column(name="description")
  private String description;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
```

As you can see, this is your standard, run-of-the-mill JPA entity.

### Creating the Repository

In our prior applications, we manually created our Data Access Object (DAO) class - the class we would use to query our database table. Thankfully, with Spring data, some of the work we did manually with Tomcat is done for us via an abstraction called `Repository`. Interestingly, we define our `Repository` as an interface. Let's place our `Repository` interface in a new package called `repositories`.

```java
package com.launchacademy.javaspringdata.repositories;

import com.launchacademy.javaspringdata.models.Idea;
import org.springframework.data.repository.CrudRepository;

public interface IdeaRepository extends CrudRepository<Idea, Integer> {
}
```

This interface will automatically use the `Idea` entity to make a few assumptions about how we can retrieve and manipulate data. Notice that the interface extends `CrudRepository` with  generic. The first defines what entity we're manipulating, and the second defines the data type we use to uniquely identify each entity.

Instances of this get a series of [useful methods][crud-repository] when it comes to working with our data. Below we'll discuss a few of the important ones.

#### count()

`count()` will efficiently provide you with the amount of records in the table.

#### findAll()

`findAll()` will return a list of all entities persisted.

#### findById(<Id> id)

`findById` takes a single id, the unique identifier of the record you are looking for.

#### save(Entity entity)

`save` takes a single argument, the JPA instance you want to persist.

By using the repository, you don't have to manage the `entityManager` or the `entityManagerFactory`. That is all done for you via Spring Data.

### Building Additional Query Methods

There's a bit of magic to Spring repositories. We can add additional methods to our interface, and Spring Data will manage the rest. For example, let's say we wanted to find an app idea with a given name, like:

```java
ideaRepository.findByTitle("Dog Twitter");
```

Through the power of convention over configuration, we can simply add that method to the repository interface `IdeaRepository`.

```java
public Idea findByName(String name);
```

This will return the first result. We can also fetch the list of ideas with the same name.

```java
public List<Idea> findAllByName(String name);
```

### Building Custom Queries

Let's say we wanted to find all app ideas where their names start with "Dog".

```java
ideaRepository.whereNameStartsWith("Dog")
```

```java
@Query("SELECT i from Idea i where i.name like :namePrefix%")
public Idea whereNameStartsWith(@Param("namePrefix") String namePrefix);
```

Through the `@Query` annotation, we can use the familiar JPQL syntax and supply any parameters using the `@Param` annotation. This provides us with the maximum amount of flexibility.

Spring, however, tries to supply us with a [number of phrases][repo-phrases] we can include in our method names to limit our need of the `@Query` annotation. If we know these keywords well, we can define such a method using the established conventions.

```java
public List<Idea> findByNameStartingWith(String namePrefix);
```

If we wanted to find all app ideas where the name has "Dog" in the name, we could instead use the following method name:

```java
public List<Idea> findByNameContaining(String nameSegment);
```

Notice that in these last two methods, the argument name is irrelevant. The name of the method itself is what matters.

When it comes to querying data, repositories are quite powerful. With some experimentation and playing with method names, retrieving relevant data becomes as simple as adding a specially named method to the interface.


## Why This Matters

We now have full exposure to the power of Spring MVC. Equipped with Spring Data, we can create meaningful applications that persist data.

[crud-repository]:https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
[repo-phrases]:https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
