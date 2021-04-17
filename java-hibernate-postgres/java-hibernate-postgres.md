You know **PostgreSQL** as a data source and database (_DB_). **Java** has many ways to connect to databases using **Java Database Connectivity** (_JDBC_). While we could use JDBC directly to issue SQL commands programmatically, there is a better way! When we first discussed SQL, we alluded to a design pattern known as **O**bject **R**elational **M**apping (ORM). ORMs allow us to take a more object-oriented approach to our database manipulation. In this article, we'll dive into the world of ORMs with Java's **Hibernate**. Let's set up Hibernate and connect to PostgreSQL.

### Learning Goals

- Configure Hibernate dependencies
- Create a Hibernate Entity
- Write data with PostgreSQL

### Getting Started

```no-highlight
et get java-hibernate-postgres
cd java-hibernate-postgres
idea .
```

## Creating Our Database

### Establish the Database

Remember the `createdb` command? We're going to use it to create a database about books. We'll use this database for the remainder of the article.

```no-highlight
createdb books
```

***Note: If you already have a books db you will need to run `dropdb books` in order to create the db for this article***.

### Setting up our Schema

We've predefined a schema for you with an `authors` table. Run the commands below from the article root:

```no-highlight
psql books
# \i src/main/resources/db/schema.sql
# \q
```

## What is an Object Relational Mapping?

When we were working with Node and the `pg` npm package, we saw how cumbersome it was to work with SQL in a programming context. Thankfully, developers have established a better pattern for working with a database in an object-oriented context. With a library like Hibernate, we can use plain old Java objects (POJO's) to represent a record in a database table.

Effectively, we use an Object Relational Mapping to correlate a Java class to a database table. Through a bit of configuration, we can use a Java class to perform CRUD operations on the database table.

What's more, robust libraries like Hibernate have conveniences for validating our data and for associating related records.

In fact, you already learned some of these principles by using Class methods like `findAll()`, `save()`, or `findById`. We had to build them manually in Express, but the Hibernate ORM provides a lot of those for us!

Let's take a closer look at how to configure and use Hibernate. Please feel free to code along, but we will also provide the final code established here in our next article.

## Set Up Hibernate

As with most frameworks, you will need to get the JAR files for Hibernate. The easiest way is to use Maven and download the dependencies. Let's add the dependency to our `pom.xml`.

```XML
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-core</artifactId>
  <version>5.4.2.Final</version>
</dependency>
```

You will also need to get the PostgreSQL JDBC Driver.

```XML
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <version>42.2.5</version>
</dependency>
```

Put these into your Maven `pom.xml` file. You will need to run Maven's update by navigating to the Maven drawer and hitting the reload button.

You can also use the shortcuts to load the changes: Ctrl+Shift+O for Windows and Linux, or Shift+Cmd+I for Mac.

### persistence.xml

In your `src/main/resources` directory, we're going to create a directory called `META-INF` . Inside there, we'll define a `persistence.xml` file. Write the following contents to your `src/main/resources/META-INF/persistence.xml` file.

```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.2"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
  <!-- Define persistence unit -->
  <persistence-unit name="com.launchacademy.books">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <properties>
      <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/books" />
      <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver" />
      <property name="javax.persistence.jdbc.user" value="postgres" />
      <property name="javax.persistence.jdbc.password" value="password" />
    </properties>
  </persistence-unit>
</persistence>
```

Note the `<provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>` line. JPA (the Java Persistence API) is a standard for implementing Object Relational Mapping in Java. Hibernate is the specific ORM that we are using to implement that JPA standard.
We will frequently shorthand "JPA using Hibernate" to simply "Hibernate" or "JPA" in the articles for the week.

## Hibernate Entity Manager

There is an important object we need to instantiate to harness the power of the JPA. The object's class is `EntityManager`. This object is an abstraction of our database connection -- a class that we can use to connect to our database. We can instantiate the `EntityManager` by using an `EntityManagerFactory`. All of these objects are provided to us via the JPA.

```java
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.books");
    EntityManager em = emf.createEntityManager();

    try {
      // our database interactions can go here
    }
    finally {
      em.close();
      emf.close();
    }
  }
}
```

Creating an `EntityManager` is expensive, so we should do so sparingly, and make sure we don't unnecessarily re-instantiate the connection. Thankfully, when we get to Spring and Spring Boot, most of this will be handled for us automatically, without having to create `EntityManager`s manually.

We create a factory using the `persistence-unit` name we defined in our `persistence.xml`.

Notice, too, that we call `close()` on both the factory and the manager. This is just good sanitation and instructs Java that we're done with the database connection. The `try...finally` is a construct we haven't yet shown you. Java will _try_ to execute everything inside the `try` block. If an exception occurs executing any code within the `try` block, the `finally` block will execute. The difference from a `catch` block is that `finally` will ALSO execute even if no exception occurs. This way, if something goes wrong with our code, we still clean up after ourselves and close our connections.

Now we're ready to start working with the data in our next article.

### Why This Matters

ORMs like Hibernate exist in most modern programming languages. Using abstraction and object-oriented programming makes us, as developers, more efficient when working with a database.

Also, you will need to know how to connect to a database if you are going to be a Java developer. Hibernate is one of the most-used ORM packages out there, including inside of development frameworks like Spring.

### Resources
[JPA Docs](https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html)

[Hibernate JPA Docs](https://docs.jboss.org/hibernate/jpa/2.2/api/overview-summary.html)
