Rapid web application development requires less maintenance headache. Recognizing that XML mappings were frequently an area of human-attributable error, Java developers have created a way for us to use **annotations** as a mechanism for configuring our field mappings.

## Learning Goals

- Justify the need for an annotation based approach
- Replace an XML mapping with an annotation-based approach
- Study the benefits of the Java Persistence API (JPA)

## Getting Started

```no-highlight
et get java-hibernate-and-annotations
cd java-hibernate-and-annotations
idea .
```

In this article, we will continue to work with our `booksOnHibernate` database. We have supplied a starting point and a `POM.xml` to accompany this article for your benefit.

## The Drawbacks of Hibernate Mapping

In a prior article, we related a Plain Old Java Object to a database table with an `hbm` mapping file. As we alluded to in a prior article, having a separate file that manages the mapping between our database table and our POJO is prone to human error. It's easy to make a modification to the class and forget to adjust our mapping file.

Thankfully, using the power of the Java Persistence API and annotations, there is a better way.

## The Java Persistence API (JPA)

A long time ago and in a galaxy far away, Java engineers would have to write their own layers of logic for interacting with a database. As open source software became increasingly popular, a few ORM frameworks emerged, but they were all starkly different from one another. Deciding which framework or methodology to use was a very loaded decision, because the cost of engineering effort to switch would be significant.

In an effort to standardize the way we communicate with databases, the Java Persistence API (JPA) was created. It defines a common language for database abstraction layers in Java. After its release, many maintainers of these libraries modified their codebases to support JPA. As of version 3.2, Hibernate led the way in this migration.

In a previous article, we had you use the _Hibernate way_ of communicating with a database. This process involves the XML mapping files and quite a bit of configuration.

## Moving Towards the JPA Approach

So, let's lay the ground work to embrace the JPA and map our class to our table with annotations. First we'll need to delete our `Author.hbm.xml` file in our resources, and we'll also want to remove the `configuration.addResource("Author.hbm.xml");` in our `Main` class.

We will also have to add the following line above our call to `.configure`:

```java
configuration.addAnnotatedClass(Author.class);
```

### Moving Towards Annotations

We've used annotations before: specifically, when indicating an `@Override` method in our classes. JPA annotations allow us to implement similar code into our Author class, which will tell Hibernate how to connect with our database. We're basically going to take what we previously had in our XML file and coalesce it with our Java implementation. Let's take a look at our newly annotated `Author` class.

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
@Table(name="authors", uniqueConstraints = {@UniqueConstraint(columnNames="id")})
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

Let's walk through each of the additions here:

### @Entity

The JPA refers to a mapped class as an _entity_. We designate the `Author` entity by annotating the class.

### @Table

We also need to instruct the JPA what table correlates with the class. The `@table` annotation includes the table name as the `name` argument, and we include a uniqueness constraint to ensure that the the ID is unique as the table's primary key.

### @Id

This designates the primary key column for our table/class.

### @Sequence and @SequenceGenerator

With PostgreSQL, we rely on the sequence to provide new values for our primary keys. These annotations inform Java how those primary keys are being assigned.

### @Column

The @Column annotation maps a Java field to a database column.

We can provide multiple arguments to this annotation to describe the nature of the column. In our `Author` class, we use `nullable=false` to indicate that a field is required for successful insertion or updating, and we use `length` to restrict the number of characters that can be persisted in the column.

With all of these pieces, we're able to remove our XML-based `Author` configuration entirely.

## Why This Matters

How we map our Java fields / properties to database columns is a critical aspect of ORM. Instead of having to work in two files with the XML-based mapping approach, we can use annotations to ease maintainability. The same information can be expressed, but having it in one place makes things more convenient for us as Java developers.
