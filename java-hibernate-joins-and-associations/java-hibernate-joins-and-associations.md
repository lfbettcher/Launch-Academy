We have discussed how ORMs allow us to bridge the gap between our Java objects and our database tables. One of the most powerful aspects of working with a database is relational data modeling. So, how can we relate our mapped objects with one another? In this article, we'll explore how JPA associations allow us to use relationships in an object-oriented context.

## Learning Goals

- Define a one to many relationship
- Define a many to one relationship

## Getting Started

```no-highlight
createdb blog_development
et get java-hibernate-joins-and-associations
cd java-hibernate-joins-and-associations
idea .
```

## The Key to Lasting Relationships

Recall that in our exploration of relational databases, we can have one-to-one, one-to-many, and many to many relationships. Leveraging these relationships among tables allows us to eliminate duplication and to keep our data well-normalized. When the JPA was designed, the existence of database relationships was a first-order concern through the concept of _associations_. So, let's mirror a database relationship in our Java entities.

For this article, we'll discuss associations in the context of a blogging website. Consider the following ER diagram while we build this out:

![Entity-Relationship Diagram for articles and comments](https://s3.amazonaws.com/horizon-production/images/article/java-hibernate-joins-and-associations/erd-articles-comments.png)

## Creating our Table

Equipped with Flyway we can write a migration to enact the above ER diagram. Let's create a migration `V1__create_initial_tables.sql`.

```sql
CREATE TABLE articles (
  id SERIAL PRIMARY KEY,
  subject VARCHAR(255) NOT NULL,
  story TEXT NOT NULL
);

CREATE TABLE comments (
  id SERIAL PRIMARY KEY,
  article_id int NOT NULL REFERENCES articles(id),
  body TEXT
);
```

Run `flyway:migrate` to affect these schema changes.

## Creating Our POJO's

Using our knowledge of annotating entities, we can create our `Article` POJO.

```java
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
public class Article {
  @Id
  @SequenceGenerator(name="article_generator", sequenceName="articles_id_seq", allocationSize = 1)
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="article_generator")
  @Column(name="id", nullable=false, unique=true)
  private Long id;

  @Column(name="subject", nullable=false)
  private String subject;

  @Column(name="story", nullable=false)
  private String story;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getStory() {
    return story;
  }

  public void setStory(String story) {
    this.story = story;
  }
}
```

We can also similarly create our `Comment` POJO.

```java
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
  @Id
  @SequenceGenerator(name="comment_generator", sequenceName="comments_id_seq", allocationSize = 1)
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="comment_generator")
  @Column(name="id", nullable=false, unique=true)
  private Long id;

  @Column(name="article_id", nullable=false)
  private Long articleId;

  @Column(name="body")
  private String body;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getArticleId() {
    return articleId;
  }

  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
```

## Seeding Data

Now that our schema and POJO's are in place we can seed some data. Let's create a `Seeder` class that loads some data in for us.

```java
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Seeder {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.blog");
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Article article = new Article();
      article.setSubject("Testing Hibernate");
      article.setStory("It's quite an interesting story");

      Article otherArticle = new Article();
      otherArticle.setSubject("Working with the JPA");
      otherArticle.setStory("Just Persist Awesomeness");

      em.persist(article);
      em.persist(otherArticle);

      em.getTransaction().commit();

      em.getTransaction().begin();

      Comment comment = new Comment();
      comment.setArticleId((article.getId()));
      comment.setBody("I like Hibernate.");

      Comment otherComment = new Comment();
      otherComment.setArticleId(article.getId());
      otherComment.setBody("Yeah. Hibernate is cool.");

      em.persist(comment);
      em.persist(otherComment);

      em.getTransaction().commit();
    }
    finally {
      em.close();
      emf.close();
    }
  }
}
```

Using what we know about foreign keys, we were able to relate two articles, and two comments. The two comments are related to our article about Hibernate. In a later application, we may need to access that article and its relevant comments. Let's take a look at how we might accomplish this using what we know of the JPA so far in a new `Main` class.

```java
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.blog");
    EntityManager em = emf.createEntityManager();

    try {
      Article article = em.createQuery("SELECT a FROM Article a WHERE subject = 'Testing Hibernate'", Article.class).getSingleResult();
      String query = "SELECT c FROM Comment c WHERE article_id = '" + article.getId() + "'";
      List<Comment> comments = em.createQuery(query, Comment.class).getResultList();
      System.out.println(comments.get(0).getBody());
    }
    finally {
      em.close();
      emf.close();
    }
  }
}
```

While this is ok, from an object-oriented programming standpoint, this is less than ideal. There is no composition relationship among our `Author` and `Comment` instances. We can use JPA associations to improve our implementation here.

## Creating Our First Association

We need to update our `Comment` POJO first. We need to indicate via JPA annotations that our `articleId` is effectively readonly. Update the private field declaration so that it looks like what's provided below:

```java
@Column(name="article_id", nullable=false, insertable = false, updatable = false)
private Long articleId;
```

This makes it so we must set the article on the basis of the object and not on the foreign key.

**Note: The `article_id` column name from the table is snake_case where the Comment class has a lowerCamelCase variable name for `articleId`.**

Next, we'll define our association.

Add the below imports to the top of `Comment`:

```java
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
```

And the below code above our getters and setters:

```java
@ManyToOne
@JoinColumn(name="article_id", nullable=false)
private Article article;

public Article getArticle() {
  return article;
}

public void setArticle(Article newArticle) {
  article = newArticle;
}
```

There's a lot happening here. We're using the JPA annotation `ManyToOne` to define a relationship between our `Comment` and `Article` classes. We then instruct the JPA how to access the relationship through the `article_id` foreign key. This allows us to both get and set the `article`.

So here's what our new and revised `Comment` class should look like:

```java
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
  @Id
  @SequenceGenerator(name="comment_generator", sequenceName="comments_id_seq", allocationSize = 1)
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="comment_generator")
  @Column(name="id", nullable=false, unique=true)
  private Long id;

  @Column(name="article_id", nullable=false, insertable = false, updatable = false)
  private Long articleId;

  @Column(name="body")
  private String body;

  @ManyToOne
  @JoinColumn(name="article_id", nullable=false)
  private Article article;

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article newArticle) {
    article = newArticle;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getArticleId() {
    return articleId;
  }

  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
```

## The Other Side of the Association

Next, we can define the association in our `Article` class. Since we'll be defining an association in both classes, we call this association **bidirectional**. Unless we're restricting how relationships can be accessed or set, we generally recommend implementing bi-directional relationships in all cases.

Add the below imports to the top of `Article`:

```java
import java.util.List;
import java.util.ArrayList;
import javax.persistence.OneToMany;
```

And the below code into the class:

```java
@OneToMany(mappedBy = "article")
private List<Comment> comments = new ArrayList<Comment>();

public List<Comment> getComments() {
  return comments;
}
```

The `mappedby` argument refers to the `private` data of our `Comment` instances. In using the `mappedBy` argument, we're specifying that the `Comment` class is the _owner_ of the relationship. We can query on both sides, but assignment and updating should happen in our owning classes. In this case, we're referring to our `Comment` instances. (This is important -- because the foreign key lives in our `comments` table!)

Here's what your `Article` class should look like now:

```java
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
public class Article {
  @Id
  @SequenceGenerator(name="article_generator", sequenceName="articles_id_seq", allocationSize = 1)
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="article_generator")
  @Column(name="id", nullable=false, unique=true)
  private Long id;

  @Column(name="subject", nullable=false)
  private String subject;

  @Column(name="story", nullable=false)
  private String story;


  @OneToMany(mappedBy = "article")
  private List<Comment> comments = new ArrayList<Comment>();

  public List<Comment> getComments() {
    return comments;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getStory() {
    return story;
  }

  public void setStory(String story) {
    this.story = story;
  }
}
```

## Updating Our Query

Now, with the magic of JPA associations, we no longer need to manually write our queries to access related data. So, let's update our `Main` routine to leverage the power of associations.

```java
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.blog");
    EntityManager em = emf.createEntityManager();

    try {
      Article article = em.createQuery("SELECT a FROM Article a WHERE subject = 'Testing Hibernate'", Article.class).getSingleResult();
      //the old way
      // String query = "SELECT c FROM Comment c WHERE article_id = '" + article.getId() + "'";
      // List<Comment> comments = em.createQuery(query, Comment.class).getResultList();
      // System.out.println(comments.get(0).getBody());

      //the new way
      Comment firstComment = article.getComments().get(0);
      System.out.println(firstComment.getBody());
    }
    finally {
      em.close();
      emf.close();
    }
  }
}
```

## Updating Our Seeder

Our seeder can also be updated to reflect the use of associations. Instead of setting the foreign keys, we instead set the objects directly. Here is the relevant snippet:

```java
em.getTransaction().begin();

Comment comment = new Comment();
comment.setArticle(article);
comment.setBody("I like hibernate.");

Comment otherComment = new Comment();
otherComment.setArticle(article);
otherComment.setBody("Yeah. Hibernate is cool.");

em.persist(comment);
```

Because we have properly set up our associations inside the `Comment` class, Hibernate and the JPA will do the appropriate thing in terms of setting the relevant foreign keys.

Here's the entire, newly revised `Seeder` class.

```java
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Seeder {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.blog");
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Article article = new Article();
      article.setSubject("Testing Hibernate");
      article.setStory("It's quite an interesting story");

      Article otherArticle = new Article();
      otherArticle.setSubject("Working with the JPA");
      otherArticle.setStory("Just Persist Awesomeness");

      em.persist(article);
      em.persist(otherArticle);

      em.getTransaction().commit();

      em.getTransaction().begin();

      Comment comment = new Comment();
      comment.setArticle(article);
      comment.setBody("I like hibernate.");

      Comment otherComment = new Comment();
      otherComment.setArticle(article);
      otherComment.setBody("Yeah. Hibernate is cool.");

      em.persist(comment);
      em.persist(otherComment);

      em.getTransaction().commit();
    }
    finally {
      em.close();
      emf.close();
    }
  }
}
```

We can test this by running `dropdb blog_development && createdb blog_development` on the command line and then running `flyway:migrate` again. Once we have reset our schema, we can run the `Seeder` and verify that it is writing records to the `comments` table.

## Why This Matters

In an object-oriented context, the idea of foreign key relationships does not fit with the abstraction. Therefore, the JPA provides us with ways to define associations among entities. In using `ManyToOne` and `OneToMany` relationships, we can create a more intuitive and object-oriented way for us to work with related data.
