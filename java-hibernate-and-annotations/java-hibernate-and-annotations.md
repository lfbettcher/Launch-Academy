Rapid web application development requires less maintenance headache. Recognizing that XML mappings were frequently an area of human-attributable error, Java developers have created a way for us to use **annotations** as a mechanism for configuring our field mappings.

## Learning Goals

- Justify the need for an annotation based approach
- Study the benefits of the Java Persistence API (JPA)

## Getting Started

```no-highlight
et get java-hibernate-and-annotations
cd java-hibernate-and-annotations
idea .
```

In this article, we will continue to work with our `books` database. We have supplied a starting point and a `POM.xml` to accompany this article for your benefit.

## The Java Persistence API (JPA)

A long time ago and in a galaxy far, far away, Java engineers would have to write their own layers of logic for interacting with a database. As open source software became increasingly popular, a few ORM frameworks emerged, but they were all starkly different from one another. Deciding which framework or methodology to use was a very loaded decision, because the cost of engineering effort to switch would be significant.

In an effort to standardize the way we communicate with databases, the Java Persistence API (JPA) was created. It defines a common language for database abstraction layers in Java. After its release, many maintainers of these libraries modified their codebases to support the JPA. As of version 3.2, Hibernate led the way in this migration.

## Moving Towards the JPA Approach

### Annotations

We've used annotations before: specifically, when indicating an `@Override` method in our classes. JPA annotations allow us to implement similar code into our Author class, which will tell Hibernate how to connect with our database. Let's take a look at our newly annotated `Author` class (be sure to update the file!).

```java
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="authors")
public class Author {
  @Id
  @SequenceGenerator(name="author_generator", sequenceName="authors_id_seq", allocationSize = 1)
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="author_generator")
  @Column(name="id", nullable=false, unique=true)
  private long id;

  @Column(name="first_name", nullable=false, length=20)
  private String firstName;

  @Column(name="last_name", nullable=false, length=20)
  private String lastName;

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
```

Let's walk through each of the annotations here:

### @Entity

The JPA refers to a mapped class, mapping a class to a database table, as an _entity_. We designate the `Author` entity by annotating the class.

### @Table

We also need to instruct the JPA what table correlates with the class. The `@Table` annotation includes the table name as the `name` argument.

### @Id

This designates the name of the primary key column for our table/class.

### @Sequence and @SequenceGenerator

With PostgreSQL, we rely on the sequence to provide new values for our primary keys. These annotations inform Java how those primary keys are being assigned.

### @Column

The @Column annotation maps a Java field to a database column.

We can provide multiple arguments to this annotation to describe the nature of the column. In our `Author` class, we use `nullable=false` to indicate that a field is required for successful insertion or updating, and we use `length` to restrict the number of characters that can be persisted in the column.

We now have a class that understands exactly how to map to a table, represent data from that table, and insert and query records in that table. We can now use this Entity class elsewhere in our code!

## Creating our Records using this Entity

We have created our `EntityManager` and `EntityManagerFactory` inside of the `Main.java` class. These allow Hibernate to work with the database on our behalf. Lets update our main method with the following:

```java

EntityManagerFactory emf =
    Persistence.createEntityManagerFactory("com.launchacademy.books");
EntityManager em = emf.createEntityManager();

try {
  Author author = new Author();
  author.setFirstName("James");
  author.setLastName("SA Corey");
  em.getTransaction().begin();
  em.persist(author);
  em.getTransaction().commit();
}
finally {
  em.close();
  emf.close();
}
```

Inside of our `try` block we create a new `Author` object. We then use the setters from the `Author` class to set the first name and last name before beginning our transaction. Most modern databases allow us to write transactions. With a transaction, we can execute multiple SQL statements. But more importantly if something goes wrong, we can `rollback` the transaction to essentially undo any data manipulation we did since we started the transaction. In the JPA, this is a first-order concern. Whenever we are issuing writes to our database, we must work within a transaction.

* `getTransaction().begin;` gives us a new transaction to work with
* `persist(author);` saves the object we've passed in to memory
* `getTransaction().commit();` takes the saved object from memory and inserts it into the database

## Why This Matters

How we map our Java fields / properties to database columns is a critical aspect of ORM. Instead of having to work in cumbersome XML-based mapping files as Java developers of the past did, we can use annotations to ease maintainability. The same information can be expressed, but having it in one place makes things more convenient for us as Java developers.
