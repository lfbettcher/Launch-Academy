Now that we know a little more about Hibernate, we should also study the Java Persistence API (JPA). The JPA provides more flexibility and has some important utilities we'll use when we start building Spring Boot applications. Note that for the purposes of learning one piece at a time, we're "breaking off" the JPA from Spring so that we can get familiar with some of the functionalities of the JPA, prior to diving into our Spring applications!

## Learning Goals

- Configure the JPA
- Retrieve an entity object using `find`
- Issue basic JPQL queries
- Persist an entity object with the JPA
- Update an entity object
- Delete an entity object
- Delete records by JPQL query

## Getting Started

```no-highlight
et get hibernate-queries
cd hibernate-queries
idea .
```

We're going back to the movies! For this walkthrough, we'll be using our existing Movies database from the `database-drills` exercise. If `psql movies` yields no existing database, run the following commands to re-create it:

***Note: Run these commands one at a time to ensure each finishes correctly.***

```sql
createdb movies
curl -o movie_database.sql.gz https://s3.amazonaws.com/launchacademy-downloads/movie_database.sql.gz
gunzip ./movie_database.sql.gz
psql movies
\i movie_database.sql
```

We have already supplied the relevant configurations to accompany this article. Take a moment to review the `Movie` class and observe how we used annotations to properly map our POJO to the existing `movies` table.

## Configuring the Java Persistence API (JPA)

The Java Persistence API (JPA) is intended to be a standardized mechanism for working with databases in Java. We have already used the JPA to annotate our first entity.

The JPA has additional capabilities, mainly in performing the CRUD operations we know SQL can do. Interestingly, the JPA defines its own special flavor of SQL called JPQL, and we can use that, along with a few new classes, to further bridge the divide between our objects and our database tables.

While we have configured Hibernate with our `hibernate.cfg.xml` file, we will need to supply the JPA with a similar file to start wielding its magic.

### persistence.xml

In your `src/main/resources` directory, we're going to place a `META-INF` directory. Inside there, we'll define a `persistence.xml` file. Write the following contents to your `src/main/resources/META-INF/persistence.xml` file.

```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.2"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
  <!-- Define persistence unit -->
  <persistence-unit name="com.launchacademy.movies">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <properties>
      <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/movies" />
      <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver" />
      <property name="javax.persistence.jdbc.user" value="postgres" />
      <property name="javax.persistence.jdbc.password" value="password" />
    </properties>
  </persistence-unit>
</persistence>
```

If we compare this file to our `hibernate.cfg.xml`, we'll notice that the file contains a bit of redundant information in it. Both configurations require us to provide database connection information. When we work with the Spring framework, this duplication will be eliminated, but if we want to use both Hibernate and the JPA to work with our data, we'll have to manage both files for now.

The other notable item here is what we name our `persistence-unit`. This will be important when we configure our `EntityManager`. Notice that we use a similar convention to how we name Java packages.

### Constructing an EntityManager

**Please note that this code is reliant on the movies database created in week 8. See the java-movie-trivia exercise for instructions on how to re-create it if needed.**

Similar to how we defined a `session` in Hibernate, there is an important object we need to instantiate to harness the power of the JPA. The object's class is `EntityManager`. Both of these objects are abstractions of our database connection. We can instantiate the `EntityManager` by using an `EntityManagerFactory`.

Let's place the following code in our `Main` `main` method as follows. (Replace the existing code.)

```java
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.movies");
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

Creating an `EntityManager` is expensive, so we should do so sparingly and in a way where we're don't unnecessarily re-instantiate the connection. Thankfully, when we get to Spring and Spring Boot, most of this will be handled for us.

We create a factory using the `persistence-unit` name we defined in our `persistence.xml`.

Notice, too, that we call `close()` on both the factory and the manager. This is just good sanitation and instructs Java that we're done with the database connection. The `try...finally` is a construct we haven't yet shown you. Java will _try_ to execute everything inside the `try` block. If an exception occurs executing any code within the `try` block, the `finally` block will execute. The difference from a `catch` block is that `finally` will ALSO execute even if no exception occurs. This way, if something goes wrong with our code, we still clean up after ourselves and close our connections.

Now we're ready to start working with the data.

## Finding Our Favorite Movie

We can find a movie if we know its primary key. Place the following code in between where the `EntityManager` is instantiated and where we close up our database connections (inside our previously established `try` block).

```java
Movie movie = em.find(Movie.class, 2L);
System.out.println(movie);
System.out.println(movie.getId());
System.out.println(movie.getTitle());
```

`find` takes the entity class we're looking for, and the primary key value we're searching for within that entity's table. Note that we use the `2L` value here because we want to supply a `Long` value.

## Retrieval Using JPQL

What if we don't know our primary key, or if we want to write a more general query?

First import the necessary packages

```java
import java.util.List;
import javax.persistence.TypedQuery;
```

and then:

```java
TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE title = 'Troll 2'", Movie.class);
List<Movie> movies = query.getResultList();
if(movies.size() > 0) {
  System.out.println(movies.get(0).getTitle());
}
else {
  System.out.println("NOT FOUND");
}
```

Here, we create our first JPQL query. JPQL is _like_ SQL but it has its own nuances. Notice that we did **not** use the table name in our `SELECT` keyword, but instead worked with our class name `Movie`. That's because JPQL is more Java than SQL. At runtime, the JPA will take our JPQL query and translate it to raw SQL. We alias `Movie` to `m` so that we can use it elsewhere in our JPQL statement.

When we invoke `getResultList()` we return a `List` object that contains relevant entities that fit the query. We can use this `List` like we have with our previous POJOs.

Alternatively, if we expect to only get one result, we can use `getSingleResult()` method.

```java
TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE title = 'Troll 2'", Movie.class);
Movie standalone = query.getSingleResult();
System.out.println(standalone.getTitle());
```

If we use `getSingleResult()`, we should ensure that the record exists. Otherwise, a `NoResultException` will get thrown. And if there is more than one result, a `NonUniqueResultException` will be thrown. In other words: we should **only** use `getSingleResult()` if we know that one and only one record matches. Otherwise, we should default to using `getResultList()`.

## Persisting an Entity

Let's create a new movie and save it to our database.

```java
Movie movie = new Movie();
movie.setTitle("Tequila Mockingbird");
movie.setGenreId(2L);
movie.setYear(2025);
em.getTransaction().begin();
em.persist(movie);
em.getTransaction().commit();

System.out.println(movie.getId());
```

Here, we basically populated our POJO with all of the relevant information we wanted to populate. Then, we use the JPA to generate an `INSERT` SQL statement for us. Finally, we commit these changes. Note that similar to `session.save()`, `persist()` is not actually committing the insert, but rather preparing it.

This is the power of ORM. We can work with the object, and let Hibernate and the JPA do the rest.

### Wait, what's a transaction?

Most modern databases allow us to write transactions. With a transaction, we can execute multiple SQL statements. If something goes wrong, we can `rollback` the transaction to essentially undo any data manipulation we did since we started the transaction. In the JPA, this is a first-order concern. Whenever we are issuing writes to our database, we must work within a transaction.

## Updating

### Updating the Entity

We forgot to give our blockbuster movie a rating!

```java
TypedQuery<Movie> retrieval = em.createQuery("SELECT m FROM Movie m WHERE title = 'Tequila Mockingbird'", Movie.class);
Movie bestEver = retrieval.getResultList().get(0);

em.getTransaction().begin();
bestEver.setRating(100);
em.getTransaction().commit();
```

If we modify a managed entity within a transaction and commit, the new information will be persisted to the database.

### Updating via JPQL

Alternately, we can choose to use JPQL to issue an UPDATE SQL command.

```Java
em.getTransaction().begin();
Query updateQuery = em.createQuery("UPDATE Movie SET rating = :newRating WHERE title = 'Tequila Mockingbird'");
updateQuery.setParameter("newRating", 400);
int updateCount = updateQuery.executeUpdate();
em.getTransaction().commit();
```

With JPQL, we can parameterize our queries by using `:varName` placeholders and subsequently calling `setParameter("varName", value)` for each parameter we mention in the query.

## Deleting

### Deleting the Entity

If we have a reference to the entity, we can remove it directly.

```java
TypedQuery<Movie> retrieval = em.createQuery("SELECT m FROM Movie m WHERE title = 'Tequila Mockingbird'", Movie.class);
Movie bestEver = retrieval.getResultList().get(0);

em.getTransaction().begin();
em.remove(bestEver);
em.getTransaction().commit();
```

### Deleting with JPQL

If we don't already have a reference to the object(s), we can issue `DELETE` commands similar to how we executed our `UPDATE` statement above.

```java
em.getTransaction().begin();
Query deleteQuery = em.createQuery("DELETE FROM Movie WHERE title = 'Tequila Mockingbird'");
int deleteCount = deleteQuery.executeUpdate();
em.getTransaction().commit();
```

## Resources

- [JPA Query](https://thoughts-on-java.org/jpql/)
- [JPA Delete](https://www.objectdb.com/java/jpa/persistence/delete)
- [JPA JPQL](https://www.objectdb.com/java/jpa/query)

## In Summary

When you want to filter your results and change the database, JPA has your back. A query can contain a JPQL statement, or call a method on the EntityManagement object. You can also use annotations to give default behaviors.
