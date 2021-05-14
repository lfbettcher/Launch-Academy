APIs are a common feature of today's web applications. [Google](https://developers.google.com/api-client-library/), [Reddit](https://www.reddit.com/dev/api), [Twitter](https://dev.twitter.com/overview/documentation), and [Github](https://developer.github.com/v3/) all have APIs within their software that can be accessed to transfer information to and from external clients. Spring gives you the necessary annotations to create your own API. This lesson will go through the basic structure of a publicly available API that delivers a universal response known as JSON.

## Getting Started

```no-highlight
createdb consume_later_development
et get java-spring-rest-controller
cd java-spring-rest-controller
idea .
```

## Learning Goals

- Create a `@RestController` instance
- Create a GET endpoint to retrieve a list of entities (index)
- Create a GET endpoint to retrieve a single entity (show)
- Create a POST endpoint to support the creation of an entity (create)

## Getting a Lay of the Land

We have supplied you with a starting point for an application that is designed to help us keep track of URL's that we want to review at a later date. Poke around and observe what's happening in the `UrlRepository` and the `UrlSeeder`. This will also help to drive home the data model of the application.

## Building our REST Controller

So far, we've built Spring controllers that respond to traditional browser requests and form submissions. Recall that in our work with React, it is possible for us to issue `fetch` requests to remote application programming interfaces (API's). Traditionally, in most modern web applications, we like JavaScript Object Notation (JSON) as the preferred data format. You may come into contact with XML-based API's, for example, but most large API provdiers have moved on to JSON.

In this article, we're going to build a controller that responds to JSON API requests. Note that these could originate from the front-end of our application, or we could support third party callers and allow them to integrate with our service.

Thankfully, with Spring and Spring Boot, this is a snap. Let's create our `UrlsApiV1Controller` in a `controllers` namespace.

```java
package com.launchacademy.consumelater.controllers;

import com.launchacademy.consumelater.models.Url;
import com.launchacademy.consumelater.repositories.UrlRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlsApiV1Controller {
  private UrlRepository urlRepo;

  @Autowired
  public UrlsApiV1Controller(UrlRepository urlRepo) {
    this.urlRepo = urlRepo;
  }
}
```

The `@RestController` annotation is going to inform Spring that we're building a controller that responds to JSON based requests. Recall that HTTP clients pass a `Content-Type` in the headers of their requests. This controller will be built to respond to a `application/json` `Content-Type` request.

We have also taken advantage of the IoC Container to inject an instance of our `UrlRepository` class via an `@Autowired` constructor.

## Our Listing Endpoint

```java
@GetMapping
public Iterable<Url> getList() {
  return urlRepo.findAll();
}
```

### Returning a Proper Object

Once this endpoint is in place, rebuild your project and navigate to `http://localhost:8080/api/v1/urls` - you should see a JSON representation of the records we have in the `urls` table.

In our more traditional MVC applications, we have been returning a `String` instance, indicating a template to render or perhaps a redirection. In this case, we want to return a serialized version of our object(s), so we return either a `List` or a single instance of our `Url` class. In this particular example, we use the `urlRepo` repository instance to retrieve _all_ of the records in our `urls` table.

### Namespacing Our API Path

Especially when we're exposing our API to the public, it's very important to separate it from our normal application logic. For this reason, we usually place all of our API related endpoints in an `/api` root. Secondly, it is very likely that as our product progresses, we'll want to make changes to the API. To handle for this, many API developers will also explicitly version their API's as part of their URL's. That way, we don't inadvertently introduce breaking changes to folks that are using older versions of our API.

### The Listing Scalability Trap

Perhaps we become really backlogged, and we have thousands of URL's to review. This will result in a scalability problem at the server level. If the server has to retrieve and respond with thousands of records, it can cause significant memory and network performance issues.

Instead we should retrieve and respond with a subset of the records in our table. Notice that instead of extending `CrudRepository` in our Repository interface, we extended `PagingAndSortingRepository`. This is a special kind of `CrudRepository` in that it provides a mechanism for us to fetch a "page" of results at a time. Let's modify our endpoint slightly to take advantage of these features.

```java
@GetMapping
public Page<Url> getList(Pageable pageable) {
  return urlRepo.findAll(pageable);
}
```

If we navigate again to our API endpoint, the response will be slightly different. Our data will appear in a `content` node and we'll get details about how many elements and pages there are. This would allow a client to understand what they need to do in order to retrieve all of the results via multiple HTTP requests.

Through the power of the `pageable` argument we added to the endpoint, we can pass paging related items to the querystring to manipulate the paramters by which we retrieve the subset of results.

For example, if we navigate to `http://localhost:8080/api/v1/urls/?size=2`, we will only see a maximum of 2 results per page. We can fetch the second page by navigating to `http://localhost:8080/api/v1/urls/?size=2&page=1` (the `page` index is zero-based).

This is the preferred mechanism for listing our results. We **do not** want to serve the entire contents of our database table in a single HTTP request/response cycle.

## Our Show Endpoint

What if we know the Id of the entity we are trying to retrieve? We can fetch a single entity with an endpoint like this:

```java
@GetMapping("/{id}")
public Url getOne(@PathVariable Integer id) {
  return urlRepo.findById(id);
}
```

### Using findById

Here, we use the `findById` method of our repository instance along with a pattern in our URL to retrieve and respond with the desired URL object.

### Error Handling

There's just one problem with the above approach, however. We must anticipate that users of our API will attempt to retrieve records that do not exist in our system. As a result, Java will not even let us compile with this implementation. So we need to create a custom exception to handle this.

```java
@NoArgsConstructor
private class UrlNotFoundException extends RuntimeException {};

@ControllerAdvice
private class UrlNotFoundAdvice {
  @ResponseBody
  @ExceptionHandler(UrlNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String urlNotFoundHandler(UrlNotFoundException ex) {
    return ex.getMessage();
  }
}

@GetMapping("/{id}")
public Url getOne(@PathVariable Integer id) {
  return urlRepo.findById(id).orElseThrow(() -> new UrlNotFoundException());
}
```

Yeah. This is a lot of code, unfortunately. Let's walk through it.

- If a record is not found, we instruct the endpoint to throw a special exception using the `orElseThrow` method.
- We define the `UrlNotFoundException` internally within our controller. No outside callers can throw such an exception.
- Finally, we can create a `@ControllerAdvice` class that _advises_ the controller how to deal with the exception. In this case, we instruct the controller to respond with a 404 Not Found. Again, this class is defined privately so that no outside callers can collaborate with this class.

## Creating a URL

Lastly, we can define an endpoint that allows us to create URL records.

```java
@PostMapping
public Url create(@RequestBody Url url) {
  return urlRepo.save(url);
}
```

Once this is in place and we have rebuilt our project, we can issue the command below to issue a POST request with relevant data.

```bash
curl -X POST localhost:8080/api/v1/urls -H 'Content-type:application/json' -d '{"url": "https://launchacademy.com/blog/where-to-get-coding-help-online", "mediaType": "text"}'
```

If it succeeds, the `curl` command should result in the following output:

```no-highlight
{"id":4,"url":"https://launchacademy.com/blog/where-to-get-coding-help-online","description":null,"mediaType":"text"}
```

This means that the record was successfully inserted into the table, because we see an updated version of the payload with an assigned primary key.

If it fails, the `curl` command should result in the following output:
```no-highlight
{"timestamp":"2020-04-16T15:24:55.108+0000","status":404,"error":"Not Found","message":"No message available","path":"/api/v1/urls"}%
```

If you get this message, try killing your server, rebuilding, rerunning your server, and then running your `curl` again.

#### A Brief Note on Joins in API Endpoints

It is important to know that when we set up model joins using our `@OneToMany` and `@ManyToOne` annotations, our JSON API endpoints use these joins to show related data. However, if we have the join hooked up on both sides (both the `One` and the `Many` model), we can accidentally run into a _circular reference_ when we try to make an API endpoint. For example, if we have an `Author` who writes many `Books`, we could run into an issue where our list of authors then tries to show a list of all their books, but each book tries to show its author, and, well......you see how this becomes a problem. (If you don't see it yet, try it out!)

In this case, we can use Jackson's helpful `@JsonIgnoreProperties` annotation to block the circular reference from occurring. We add this to our join field, so that it knows to skip the circular reference if showing joins in a JSON API endpoint. This would look something like this (only relevant code shown here as a brief example):

```java
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
...
public class Book {
  ...

  @ManyToOne
  @JoinColumn(name = "author_id")
  @JsonIgnoreProperties("books")
  private Author author;
}
```

```java
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
...
public class Author {
  ...

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("author")
  private List<Book> books;
}
```

We're now telling our app, "if I ask you to render a JSON list of Books, show me their author, but don't nest the books under said author. And if I ask you to render an Author, show me the books written by them, but don't try to re-nest the author under each book."

Often, if we're getting more fancy with limiting attributes in JSON endpoints, we'll avoid using these annotations and instead build out more scalable Serializers using Data Transfer Objects (DTOs). We'll go into this further in the future, but for simple joins like this, `@JsonIgnoreProperties` works wonders.

## Why This Matters

This is the last building block we need to create full-stack applications. We can now create APIs that we can connect with our ReactJS applications.
