It is time. We've learned about Hibernate and the JPA. We've learned about Java Server Pages and Servlets. Let's put them all together to make a full-on, data-persisting web application built with the MVC design pattern.

## Learning Goals

- Initialize a database in the context of a web application
- Write a Data Access Object (DAO)

## Getting Started

```no-highlight
createdb java_bookmarks_development
et get java-database-applications
cd java-database-applications
idea .
```

Worlds are colliding. Servlet controllers and JSP views, meet the JPA and entities. Let's build an app that keeps track of our favorite places on the internet.

## Let's Build Out Our Form

### Servlet

We'll need to implement a `doGet` to render the form. Let's create a `BookmarksController` class inside of our `com.launchacademy.bookmarks` package.

```java
package com.launchacademy.bookmarks;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/bookmarks/new"})
public class BookmarksController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if(req.getServletPath().equals("/bookmarks/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/bookmarks/new.jsp");
      dispatcher.forward(req, resp);
    }
  }
}
```

We're effectively passing the `/bookmarks/new` to a JSP. Let's build out the JSP at `src/main/webapp/views/bookmarks/new.jsp`.

### JSP

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Add a New Bookmark</title>
  </head>
  <body>
    <form action="/bookmarks" method="post">
      <div>
        <label for="title">Title</label>
        <input type="text" id="title" name="title" value="" />
      </div>
      <div>
        <label for="url">URL</label>
        <input type="text" id="url" name="url" value="" />
      </div>
      <div>
        <label for="description">Description</label>
        <textarea id="description" name="description"></textarea>
      </div>
       <input type="submit" value="Create Bookmark" />
    </form>
  </body>
</html>
```

Thus far, we've been working with some simple Java beans to do something meaningful with our form input. Let's connect this form to the database with the JPA so that we can save our bookmarks.

## Getting Ready for the JPA

Let's add our JPA and Hibernate dependencies.

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.4.2.Final</version>
</dependency>
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-validator</artifactId>
  <version>5.4.3.Final</version>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.2.5</version>
</dependency>
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.0.Final</version>
</dependency>
<dependency>
    <groupId>javax.el</groupId>
    <artifactId>javax.el-api</artifactId>
    <version>2.2.5</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.javassist</groupId>
    <artifactId>javassist</artifactId>
    <version>3.23.1-GA</version>
</dependency>
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>javax.el</artifactId>
    <version>2.2.6</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>commons-beanutils</groupId>
    <artifactId>commons-beanutils</artifactId>
    <version>1.9.3</version>
</dependency>
```

We'll also need to add Flyway into the mix for schema migrations.

```xml
<plugin>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-maven-plugin</artifactId>
  <version>5.2.4</version>
  <configuration>
    <url>jdbc:postgresql://localhost:5432/java_bookmarks_development</url>
    <user>postgres</user>
    <password>password</password>
  </configuration>
</plugin>
```

So, here's what your entire `pom.xml` should look like at this point.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>groupId</groupId>
    <artifactId>java-database-applications</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>
    <properties>
        <java.version>11</java.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>javax.servlet.jsp-api</artifactId>
            <version>2.3.3</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.4.2.Final</version>
        </dependency>
      <dependency>
          <groupId>org.hibernate</groupId>
          <artifactId>hibernate-validator</artifactId>
          <version>5.4.3.Final</version>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.5</version>
        </dependency>
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <version>2.0.0.Final</version>
        </dependency>
        <dependency>
            <groupId>javax.el</groupId>
            <artifactId>javax.el-api</artifactId>
            <version>2.2.5</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.javassist</groupId>
            <artifactId>javassist</artifactId>
            <version>3.23.1-GA</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.web</groupId>
            <artifactId>javax.el</artifactId>
            <version>2.2.6</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>commons-beanutils</groupId>
            <artifactId>commons-beanutils</artifactId>
            <version>1.9.3</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.3</version>
            </plugin>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <port>8080</port>
                    <path>/</path>
                    <contextReloadable>true</contextReloadable>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.flywaydb</groupId>
                <artifactId>flyway-maven-plugin</artifactId>
                <version>5.2.4</version>
                <configuration>
                    <url>jdbc:postgresql://localhost:5432/java_bookmarks_development</url>
                    <user>postgres</user>
                    <password>password</password>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

We'll also need to set up our `persistence.xml` - we can set that up in `src/main/resources/META-INF/persistence.xml`:

```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.2"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
  <!-- Define persistence unit -->
  <persistence-unit name="com.launchacademy.javaDatabaseApplications">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <properties>
      <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/java_bookmarks_development" />
      <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver" />
      <property name="javax.persistence.jdbc.user" value="postgres" />
      <property name="javax.persistence.jdbc.password" value="password" />
    </properties>
  </persistence-unit>
</persistence>
```

### Creating Our Schema

To support the form we're building, we're going to need to build a table to persist the data. So let's create `src/main/resources/db/migration/V1__create_bookmarks.sql`

```sql
CREATE TABLE bookmarks (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  url VARCHAR(255) NOT NULL,
  description TEXT
);
```

Run `flyway:migrate` to bring your schema up to date.

### Creating Our Entity

Alright, let's create our entity like we know what we're doing.

![I have no idea what I'm doing][doing-meme]

```java
package com.launchacademy.bookmarks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name="bookmarks")
public class Bookmark {
  @Id
  @SequenceGenerator(name="bookmark_generator", sequenceName="bookmarks_id_seq", allocationSize = 1)
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bookmark_generator")
  @Column(name="id", nullable=false, unique=true)
  private Long id;

  @NotEmpty
  @Column(name="title", nullable=false)
  private String title;

  @NotEmpty
  @URL
  @Column(name="url", nullable=false)
  private String url;

  @Column(name="description")
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
```

## Writing Our `doPost`

We're ready to start saving us some bookmarks off the trusty ol' web! Let's build out our `doPost` in `BookmarksController`.

First, we need to add `/bookmarks` to our UrlPatterns annotation attribute.

```java
@WebServlet(urlPatterns = {"/bookmarks/new", "/bookmarks"})
public class BookmarksController extends HttpServlet {
```

Now, let's define that `doPost` method.

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

  if(req.getServletPath().equals("/bookmarks")) {
    Bookmark bookmark = new Bookmark();
    try {
      BeanUtils.populate(bookmark, req.getParameterMap());
    }
    catch(IllegalAccessException ex) {
      //do some logging
    }
    catch(InvocationTargetException ex) {
      //do some logging
    }
    //TODO: save the bookmark

    resp.sendRedirect("/bookmarks/new");
  }
  else {
    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
  }
}
```

Whoops! We forgot that while we can populate the `Bookmark` entity, we don't have an `EntityManager` instance to create a new `Bookmark` object.

## Initializing our Entity Manager Factory

We've learned that creating and managing both the `EntityManagerFactory` and `EntityManager` should be completed thoughtfully. Generally, we follow the rule that there should be one, application-wide `EntityManagerFactory` and that there should be one `EntityManager` instance per request context. `EntityManager`'s are expensive to create, but they are also not thread-safe. In the context of a web app, this is a fancy way of saying they won't reliably handle multiple users at the same time.

So, to create our `EntityManagerFactory`, we're going to place it in a `ServletContext`. By definition a single `ServletContext` exists for every web application installed on a container. It's a good place to stick instances that we want to use globally within our application.

In order to set this up, we're going to create a special kind of class, an implementer of the `ServletContextListener` to add this context in without creating a lot of code headache. Let's create a class called `EMFListener`.

```java
package com.launchacademy.bookmarks;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EMFListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.javaDatabaseApplications");
    event.getServletContext().setAttribute("emf", emf);
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    EntityManagerFactory emf = (EntityManagerFactory)event.getServletContext().getAttribute("emf");
    emf.close();
  }
}
```

We're effectively adding and managing a global variable in the `ServletContext`. By using `getServletContext()` we can later access the `EntityManagerFactory` to do its work.

Like the Model View Controller design pattern, the Listener design pattern is used extensively in Java programming. The subject is worthy of an article in and of itself, but listeners provide us with a way to unobtrusively "listen" for events that occur in an application and attach code to them.

In this case, we define a `ServletContextListener` with two methods. One for when the application is loaded, and another when it is unloaded or destroyed. Because we annotate it with `@WebListener`, it will automatically connect with the servlet context of our application.

*Note*: Hopefully you're following along. Now would be a good time to restart your Tomcat server to ensure this variable is set in our `ServletContext`.

## Initializing Our `EntityManager`

Now that we have the `EntityManagerFactory` in context, we can retrieve it in our `doPost`.

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

  if(req.getServletPath().equals("/bookmarks")) {
    Bookmark bookmark = new Bookmark();
    try {
      BeanUtils.populate(bookmark, req.getParameterMap());
    }
    catch(IllegalAccessException ex) {
      //do some logging
    }
    catch(InvocationTargetException ex) {
      //do some logging
    }

    EntityManagerFactory emf = getEmf();
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(bookmark);
      em.getTransaction().commit();
    }
    finally {
      em.close();
    }

    resp.sendRedirect("/bookmarks/new");
  }
  else {
    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
  }
}

private EntityManagerFactory getEmf() {
  return (EntityManagerFactory)this.getServletContext().getAttribute("emf");
}
```

Notice here that we have created a `private` method `getEmf()` - this allows the process of retrieving the `EntityManagerFactory` to be available to all methods within the servlet.

Also note that we are creating and closing our `EntityManager` instance in the controller. As mentioned above, we want want to only have one `EntityManager` for the entire HTTP request/response life cycle.

Once in place, we can verify via the database that a record has been added after submitting the form.

*Note*: If a bookmark isn't being added try using a full url like `https://www.google.com/`

```sql
psql java_bookmarks_development
SELECT * FROM bookmarks;
```

## Data Access Objects

Recall that we want to isolate our business logic to models, rather than putting it directly in our controller. There's a lot to writing records to our database. We'd want to run validation on our object, for example.

Again, to isolate logic and to make our code more testable, we generally create another level of models. Let's extract a Data Access Object to isolate the concern. A Data Access Object is a model that is specifically responsible for accessing and updating data in our database.

We will call it `BookmarkService`:

```java
package com.launchacademy.bookmarks;

import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.TypedQuery;

public class BookmarkService {
  private EntityManager em;

  public BookmarkService(EntityManager em) {
    this.em = em;
  }

  public boolean save(Bookmark bookmark) {
    try {
      em.getTransaction().begin();
      em.persist(bookmark);
      em.getTransaction().commit();
      return true;
    }
    catch(Exception exc) {
      //an error occurred with the INSERT so return false
      em.getTransaction().rollback();
      return false;
    }
  }
}
```

As we build out our database interactions with the `bookmarks` table, we would add them here. Whether we're querying or manipulating data, that should be considered business logic that we eventually want to put under test. Therefore, it belongs in a class isolated away from the HTTP request/response context.

We can then update our `doPost` to make use of this new service object.

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

  if(req.getServletPath().equals("/bookmarks")) {
    Bookmark bookmark = new Bookmark();
    try {
      BeanUtils.populate(bookmark, req.getParameterMap());
    }
    catch(IllegalAccessException ex) {
      //do some logging
    }
    catch(InvocationTargetException ex) {
      //do some logging
    }

    EntityManagerFactory emf = getEmf();
    EntityManager em = emf.createEntityManager();

    BookmarkService service = new BookmarkService(em);
    if(!service.save(bookmark)) {
      //do some error logging, re-render the form, etc
    }
    resp.sendRedirect("/bookmarks/new");
    em.close();
  }
  else {
    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
  }
}
```

## Displaying Results

If we want to display our list of bookmarks, we can query the database with JPQL. But, where should that query exist? We should place it in the Data Access Object to isolate querying and data manipulation concerns. We can extend our `BookmarkService` with an additional method:

```java
public List<Bookmark> findAll() {
  TypedQuery<Bookmark> query = em.createQuery("SELECT b FROM Bookmark b", Bookmark.class);
  return query.getResultList();
}
```

If we wanted to put our listing at the root `/` path, we must add another `urlPattern` and handle for that path.

```java
@WebServlet(urlPatterns = {"/bookmarks/new", "/bookmarks", "/"})
public class BookmarksController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if(req.getServletPath().equals("/bookmarks/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/bookmarks/new.jsp");
      dispatcher.forward(req, resp);
    }
    else if(req.getServletPath().equals("/")) {
      EntityManager em = getEmf().createEntityManager();
      try {
        BookmarkService bookmarkService = new BookmarkService(em);
        req.setAttribute("bookmarks", bookmarkService.findAll());
      }
      finally {
        em.close();
      }

      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/bookmarks/index.jsp");
      dispatcher.forward(req, resp);
    }
  }
}
```

In the above snippet, we issue the query via the service object and set the results as an attribute in order to pass it down to the Java Server Page.

Then you can create a correlating JSP to list out your bookmarks:

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="com.launchacademy.bookmarks.Bookmark" %>
<%@ page import="java.util.ArrayList" %>
<c:set var="bookmarks" value="${requestScope.bookmarks}" />

<div>
  <ul>
  <c:forEach items="${bookmarks}" var="bookmark">
      <li><a href=<c:out value="${bookmark.url}" />> <c:out value="${bookmark.title}"/></a></li>
  </c:forEach>
  </ul>
</div>
```

## In Summary

We can use a combination of Hibernate, the JPA, Java Server Pages, and Java Servlets to build out our own MVC framework.

[doing-meme]:https://i.kym-cdn.com/photos/images/newsfeed/000/234/765/b7e.jpg
