Sometimes, there won't be a 1-to-1 correlation between what we persist in our database tables and what we expose via our API. In order to perform some translation, we need a Data Transfer Object (DTO). In Java, one tool that can assist us with this is a third party library called [MapStruct][mapstruct]

## Getting Started

```no-highlight
et get java-serialization-with-mapstruct
cd java-serialization-with-mapstruct
idea .
```

We're going to continue on with our `consumelater` project from `java-spring-rest-controller` for saving URLs to review later. We have supplied an implemented version of the `RestController` from the previous article.

## Learning Goals

- Discuss the business imperative for a Data Transfer Object (DTO)
- Implement a mapping that renames a value
- Implement a mapping that hides a value
- Implement a service layer for data transformation
- Update a controller endpoint to use this new service layer

## The Need for a Data Transfer Object (DTO)

Eventually, we will want to support multiple users for our `consumelater` application. In relational database modeling, it would make sense to add a `user_id` column to our `urls` table to relate a url to its owner. From a security standpoint, however, we may not want to expose that value to the outside world via our REST API. If we're not careful, doing so may allow users to enact an [impersonation attack][impersonation-attack]!

So how do we hide this field in our JSON payloads? Additionally, what if we wanted to rename our `mediaType` to `contentType`?

One option would be to implement a `toJSON()` method in our `Url` model, something like the below:

```java
public String toJSON() {
  Map<String, String> keyValuePairs = new HashMap<String, String>();
  keyValuePairs.put("\"id\"", this.getId());
  keyValuePairs.put("\"url\"", this.getUrl());
  keyValuePairs.put("\"description\"", this.getDescription());
  keyValuePairs.put("\"contentType\"", this.getMediaType())
  List<String> properties = new ArrayList<String>();
  for(String key : keyValuePairs.keySet()) {
    properties.add(key + ": '" + keyValuePairs.get(key) + "'");
  }
  return "{" + String.join(",", properties) + "}";
}
```

Wow, where to start with the problems with this implementation! First, it relies on String concatenation to manage this translation. Secondly, it assumes that we want all properties to be represented as a String, when our `id` should be represented as an Integer. This also assumes that all properties are primitives. If we add another field, we have to remember to update this method. Lastly, and most importantly, this implementation violates the Single Responsibility Principle. Our `Url` Entity now has two responsibilities, maintaining the information about URLs we want to review later, and now, it is responsible for translating that information to a JSON based format.

These are common problems. So common, in fact, that there are patterns and tools engineered to help us with these types of scenarios where we want to format our data to a JSON. One established pattern is to create a Data Transfer Object, or DTO for short.

## The Data Transfer Object (DTO)

Like our Data Access Objects, which allow us to _access_ the data in our database, our Data Transfer Objects help us to _transfer_ this data from raw data to data that's nicely formatted for our user or front-end to use. We'll use this DTO class to effectively translate our Entity into a format we want to use for translating to a JSON object. Technically, we call this translation process *serialization*.

Let's create a `UrlDto` class in a `dtos` namespace.

```java
package com.launchacademy.consumelater.dto;

import com.launchacademy.consumelater.models.Url;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UrlDto {
  private Integer id;
  private String url;
  private String description;
  private String contentType;

  public static UrlDto fromUrl(Url url) {
    UrlDto dto = new UrlDto();
    dto.setId(url.getId());
    dto.setUrl(url.getUrl());
    dto.setDescription(url.getDescription());
    dto.setContentType(url.getMediaType());
    return dto;
  }
}
```

In this class, we define the fields we want to translate to JSON. We also define a static method that serves as an alternate constructor. Taking a `Url` model as an argument, we use it to create a new instance of the data transfer object.

At this point, we can think of our structure as so:

![Image of diagram with Database, Entity, and DTO](https://horizon-production.s3.amazonaws.com/images/article/java-serialization-with-mapstruct/serialization_diagram_1.jpg)

While this is an improvement, this DTO class has two responsibilities. One responsibility is to hold all of the information we want to "transfer" via JSON, and the other is to translate from a `Url` model to a `UrlDto` data transfer object. It almost feels as if we need a middle-man here: an object to turn a `Url` into a `UrlDto`.

So, we need a lightweight way to translate from one format to another. We will do so using a `Mapper` class. One popular library for managing this in our Java applications is a tool called [MapStruct][mapstruct]. Equipped with this library, we can extract a class that takes on that second responsibility of mapping fields from one class to another.

## Install and Configure MapStruct

### Maven

In order to have MapStruct do its magic, we have some some work to do in our `pom.xml`. First, we must define what version of MapStruct we want to use. At the time of this writing, we'll use `1.3.0.Final`. Let's add that to our `properties`. We also need to make some Lombok-related updates so that the two libraries will play nicely together.

```xml
<properties>
  <java.version>11</java.version>
  <org.mapstruct.version>1.3.0.Final</org.mapstruct.version>
  <org.projectlombok.version>1.18.8</org.projectlombok.version>
</properties>
```

We then need to add the library as a dependency.

```xml
<dependency>
  <groupId>org.mapstruct</groupId>
  <artifactId>mapstruct</artifactId>
  <version>${org.mapstruct.version}</version>
</dependency>
```

Lastly, we need to make a modification to how we compile the project, so let's add some configuration to the compilation plugin inside the `<plugins>` section. This will tell maven to compile with both Mapstruct and Lombok in mind.

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.5.1</version>
  <configuration>
    <source>${java.version}</source>
    <target>${java.version}</target>
    <annotationProcessorPaths>
      <path>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct-processor</artifactId>
        <version>${org.mapstruct.version}</version>
      </path>
      <path>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${org.projectlombok.version}</version>
      </path>
    </annotationProcessorPaths>
  </configuration>
</plugin>
```

### Intellij

Like we had to do with Lombok, we also need to install an Intellij plugin for MapStruct.

Go to `IntelliJIDEA -> preferences` on Mac or `File -> Settings` on PC. Select `plugins` and make sure `Marketplace` is highlighted at the top of the window. In the search bar type in `MapStruct` and click the `Install` button. If you are on an older version of IntelliJ you will need to switch over to the `MarketPlace` tab and search for `MapStruct` to install it.

## Create a Mapper

Let's create a new package called `mappers`. In that package, let's define a new interface called `UrlMapper`.

```java
package com.launchacademy.consumelater.mappers;

import com.launchacademy.consumelater.dto.UrlDto;
import com.launchacademy.consumelater.models.Url;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface UrlMapper {
  @Mapping(source="mediaType", target="contentType")
  UrlDto urlToUrlDto(Url url);
}
```

Here, the `@Mapper` annotation can be used to perform translations from our source to our target. We pass in some arguments to the annotation to inform Mapstruct how we'll use dependency injection to load this Mapper instance. We also define a method stub in the interface using a `@Mapping` annotation. In this case, we use it to translate the `mediaType` field to a `contentType` field. Secondly, because no `userId` column is defined in our `UrlDto`, it will be completely ignored and therefore not available in the JSON representation of the object.

This gives us the best of both worlds. We get a singly responsible interface for translating our object, and it is flexible enough so that it can grow with our `Url` object as it changes.

At this point, we can think of our object structure as shown below:
![Image of diagram with Database, Entity, Mapper, and DTO](https://horizon-production.s3.amazonaws.com/images/article/java-serialization-with-mapstruct/serialization_diagram_2.jpg)

### Dealing with Lists

What if we're dealing with a collection of `Url` instances that we want to map? Mapstruct anticipates this. Let's add another method to our mapper interface. We can put it directly below our method that returns a `UrlDto` instance.

```java
List<UrlDto> urlsToUrlDtos(List<Url> urls);
```

Because we're already defined a method that returns a `UrlDto` translation off of a `Url` instance, this new method will *automatically* use our mappings there in translating the collections.

## Managing a GET Request

Ok! So now we're ready to modify our controller to use this new Mapper and DTO mechanism. But there's a problem: the controller is currently interacting directly with the Repository. We need an intermediary **Service** to read the data in from the Repository and translate that data into the DTO instances. This service will first reach out to our Repository to get all of our `Url` objects, and then use our mapper to turn them into `UrlDto` objects. This final product will have a structure as shown below:

![Image of diagram with Controller, Service, Repository, Entity, Database, and Mapper and DTO](https://horizon-production.s3.amazonaws.com/images/article/java-serialization-with-mapstruct/serialization_diagram_3.jpg)

## Building out our Service for Serialization

Time for our final class to get this all running. This one will be placed in a new `services` namespace.

```java
package com.launchacademy.consumelater.services;

import com.launchacademy.consumelater.dto.UrlDto;
import com.launchacademy.consumelater.mappers.UrlMapper;
import com.launchacademy.consumelater.models.Url;
import com.launchacademy.consumelater.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
  private final UrlRepository urlRepository;
  private final UrlMapper urlMapper;

  @Autowired
  public UrlService(UrlRepository urlRepository, UrlMapper urlMapper) {
    this.urlRepository = urlRepository;
    this.urlMapper = urlMapper;
  }

  public Page<UrlDto> findAll(Pageable pageable) {
    Page<Url> page = urlRepository.findAll(pageable);
    return new PageImpl<UrlDto>(urlMapper.urlsToUrlDtos(page.getContent()), pageable, page.getTotalElements());
  }
}
```

We're basically aiming for this service to stand in front of the `Repository` class so that we can perform the necessary translations. First, we can see that we're using dependency injection to manage the instantiation of a `UrlRepository` and a `UrlMapper`. Thanks to the way we set up our `@Mapper` annotation, we can use `@Autowired` to construct our instance of the `UrlMapper`.

We then implement a method that returns a page of `UserDto` instances. To do that, we have to first retrieve a page from the repository. We then use that result to create a transformed copy of the page using our DTO translation. `PageImpl` is provided by the Spring framework, and it allows us to create a new `Page` instance. We construct it with three arguments:

1. The collection that represents the "page" of data
2. The `pageable` options that get passed into the controller
3. The total number of elements in the result set

## Putting it All Together

We're almost to the home stretch. Let's take this new Service abstraction and update our listing/index and our show pages.

### Injecting Our Service Object

Now that we have this intermediate layer of abstraction in our newly created `@Service`, we'll want to include that in the controller. Replace your fields and constructor with what's below:

```java
private final UrlRepository urlRepo;
private final UrlService urlService;

@Autowired
public UrlsController(UrlRepository urlRepo, UrlService urlService) {
  this.urlRepo = urlRepo;
  this.urlService = urlService;
}
```

### The Index Endpoint

Finally, we're ready to start updating our endpoint to use this new `Service`. We can update the Index endpoint like so:

```java
@GetMapping("/api/v1/urls")
public Page<UrlDto> getList(Pageable pageable) {
  return urlService.findAll(pageable);
}
```

## Why This Matters

It's certainly tedious, but it's possible. With MapStruct and some intelligent architecture, we can distinguish our data concerns from our JSON API.

[mapstruct]:http://mapstruct.org/
[impersonation-attack]:https://link.springer.com/referenceworkentry/10.1007%2F0-387-23483-7_196
