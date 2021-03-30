You know **PostgreSQL** as a data source and database (_DB_). **Java** has many ways to connect to databases using **Java Database Connectivity** (_JDBC_). While we could use JDBC directly to issue SQL commands programmatically, there is a better way! When we first discussed SQL, we alluded to a design pattern known as **O**bject **R**elational **M**apping (ORM). ORM's allow us to take a more object-oriented approach to our database manipulation. In this article, we'll dive into the world of ORM's with Java's **Hibernate**. Let's set up Hibernate and connect to PostgreSQL.

## Learning Goals

- Configure Hibernate dependencies
- Create a Hibernate Entity
- Write data with PostgreSQL

Follow along as we introduce you to configuring Hibernate.

## Getting Started

```no-highlight
et get hibernate-with-postgres
cd hibernate-with-postgres
idea .
```

## What is an Object Relational Mapping

When we were working with Node and the `pg` npm package, we saw how cumbersome it was to work with SQL in a programming context. Thankfully, developers have established a better pattern for working with a database in an object-oriented context. With a library like Hibernate, we can use plain old Java objects (POJO's) to represent a record in a database table.

Effectively, we use an Object Relational Mapping to correlate a Java class to a database table. Through a bit of configuration, we can use a Java class to perform CRUD operations on the database table.

What's more, robust libraries like Hibernate have conveniences for validating our data and for associating related records.

Let's take a closer look at how to configure and use Hibernate.

## Creating Our Database

### Establish the database

Remember the `createdb` command? We're going to use it to create a database about books. We'll use this database for the remainder of the article.

```no-highlight
createdb booksOnHibernate
```

### Setting up our schema

We've predefined a schema for you with an `authors` table. Run the commands below from the article root:

```no-highlight
psql booksOnHibernate
# \i src/main/resources/schema.sql
# \q
```

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

Put these into your Maven `pom.xml` file. If you've enabled auto-import, these dependencies will be installed for you.

### Configure Hibernate

You will need to setup a configuration file for Hibernate. In Maven projects, we like to place our configuration files and other assets in a _resources_ directory to keep them isolated from actual Java code. Place the following in `src/main/resources/hibernate.cfg.xml`

```xml
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
    "-//Hibernate/Hibernate Configuration DTD//EN"
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
  <session-factory>
    <property name="connection.url">
      jdbc:postgresql://localhost:5432/booksOnHibernate
    </property>
    <property name="connection.driver_class">
      org.postgresql.Driver
    </property>
    <property name="connection.username">
      postgres
    </property>
    <property name="connection.password">
      password
    </property>
    <property name="hibernate.current_session_context_class">
      thread
    </property>

    <mapping class="Quote"/>
  </session-factory>
</hibernate-configuration>
```

When you start up Hibernate, you will need to load the configuration file, hook into the service registry, and get the session factory. Let's update our class `Main` as follows:

```Java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main {
  public static void main(String[] args) {
    // load the hibernate configure file
    Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    configuration.addResource("Author.hbm.xml");

    // Create the Hibernate service Registry
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
      applySettings(configuration.
      getProperties()).
      build();

    // instantiate the session factory
    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    Session session = sessionFactory.getCurrentSession();
  }
}
```

Here, we create a Hibernate `Configuration` and attach it to our configuration file and an Author Hibernate mapping file (which we will create below). Then, we set up a ServiceRegistry using that configuration, and use it to make a SessionFactory, so that we can connect to our database. This will bind Hibernate to PostgreSQL using the postgres driver.

## Creating our Plain Old Java Object (POJO)

We've provided an Author class as shown below.

```java
public class Author {
  private long id;
  private String firstName;
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

Here, we're defining both getters and setters for all fields. So, how do we connect this with a database table?

## Hibernate Entity **Plain Old Java Object** (_POJO_)

Hibernate needs to know how the tables are formatted to map Java fields to database values. The old-fashioned way is to use a mapping file that describes the table using Column statements that gives the name, type, size, and 'nullablity'.

We **could** (and will, in this example, just to practice it) write another XML file to map our Java class to a database table. Taking this approach for the `Author` class, we need to add a new file called `Author.hbm.xml` in the resources directory.

This file should look like the below:

```xml
<?xml version = "1.0" encoding = "utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC
  "-//Hibernate/Hibernate Mapping DTD//EN"
  "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
  <class name="Author" table="authors">
    <meta attribute="class-description">
      Author information
    </meta>
    <id name="id" type="long" column="id">
      <generator class="sequence">
        <param name="optimizer">none</param>
        <param name="increment_size">1</param>
        <param name="sequence_name">authors_id_seq</param>
      </generator>
    </id>
    <property name ="firstName" column="first_name" type = "string" />
    <property name ="lastName" column="last_name" type = "string" />
  </class>
</hibernate-mapping>
```

A file like this is prone to human error and inconsistency between our `Author` class and this mapping. Thankfully, there's a better way with annotations, which we will explore in a subequent reading.

Let's talk about the `generator` as part of this XML. Hibernate and the Java Persistence API tries to manage the way in which we set our primary keys. Using a generator of `class` `sequence` like we've done above relies on the native functionality of the database to set our primary keys. Note that in order for this to work, we must have a correlating sequence defined in our schema. We've included the `authors_id_seq` sequence in the supplied `schema.sql` file.

Phew! Now that we have a schema, configuration, driver, map and POJO, we're ready to start manipulating data.

### Write data to PostgreSQL

There are a few API calls we make when accessing the table. The first thing we need to get is a `Session` object from the `SessionFactory`. Add the following to your `main` method in the `Main` class in order to write an object to the database:

```Java
session.beginTransaction();
Author author = new Author();
author.setFirstName("RL");
author.setLastName("Stine");
session.save(author);
session.getTransaction().commit();
sessionFactory.close();
```

Looking at this code, you can see a couple of things are going on. The first is we are beginning a transaction with `beginTransaction()`. Think of a transaction as a group of actions you perform on a table. Next we create a new `Author`, then use the setters to give them a name. When you first see session.save you might think that your work is done... but no! `session.save()` saves your data to the cache and then `session.getTransaction().commit()` will take your cached data and persist it to the database; this will also end your transaction. While this might seem redundant now, it will make sense when we talk about rolling back transactions later. When you are done with the database, call `close()` on the session factory -- otherwise Java will leave it open.

Verify that your transaction worked by connecting to your database via the commandline and querying your Authors table.

```sql
  $ psql booksOnHibernate
  SELECT * FROM authors;
```

if you see RL Stein you've successfully written to your database!

Your finished `Main` class should look like this

```java
public class Main {

  public static void main(String[] args) {
    // load the hibernate configure file
    Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    configuration.addResource("Author.hbm.xml");

    // Create the Hibernate service Registry
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
      applySettings(configuration.
      getProperties()).
      build();

    // instantiate the session factory
    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    Session session = sessionFactory.getCurrentSession();

    session.beginTransaction();
    Author author = new Author();
    author.setFirstName("RL");
    author.setLastName("Stine");
    session.save(author);
    session.getTransaction().commit();
    sessionFactory.close();
  }
}
```

## Why This Matters

ORMs like Hibernate exist in most modern programming languages. Using abstraction and object-oriented programming makes us more efficient when working with a database.

Also, you will need to know how to connect to a database if you are going to be a Java developer. Hibernate is one of the most-used ORM packages out there, including development frameworks like Spring.

## Resources

- [Hibernate Documentation](https://hibernate.org/orm/documentation/5.4/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/11/index.html)

## In Summary

We learned how to open a connection to a database with Hibernate and write data into it. Also, we got a good look at the configuration of Hibernate and some of the additional material as far as using Hibernate.
