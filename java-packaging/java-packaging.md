Thus far, we've been working with fairly simplistic Java applications. As we get more sophisticated, the need to get more organized with our Java increases in importance.

We can use Java **packaging** to better organize our code.

## Learning Goals

- Justify the need for Java packaging
- Create a Java package in Intellij
- Create subpackages

## Getting Started

```no-highlight
et get java-packaging
cd java-packaging
idea .
```

## The Global Namespace

By placing our classes in `src/main/java` and not using a `package` statement at the top of our files, we indicate that these classes are in the global namespace. As we'll discuss in this article, this has numerous drawbacks, but the most important problem for us is JSP does not allow us to make use of classes declared in the global namespace. So, we must find a better way to organize our programs. That's where Java packages come into play.

We like to think of packages like filing cabinets. As programmers, we tend to like things as organized as possible. With our Java programs, we can place our classes in **packages** so that we can better keep things organized. We can bundle related items together so that it's easier to remember where our classes and interfaces.

## What's New is Old

We are already making use of packages in our Java program. When we use the `import` statement, we're borrowing a class from another package.

```java
import java.util.List;
```

This statement at the top of our Java classes indicates that we want to "borrow" the `List` interface that is located in the `java.util` filing cabinet. If we look in the `java.util` package, we can find all kinds of core classes and interfaces that we've made ample use of thus far. `Collection`, `ArrayList`, and `Scanner` can all be found here.

Without the `import` statement, we could write our code like the example below:

```java
java.util.List myList = new java.util.ArrayList<String>();
java.util.Scanner scanner = new java.util.Scanner(System.in);
myList.add(scanner.getNextLine());
```

This is difficult to read, and requires quite a bit of typing. By using an `import` statement, we avoid having to repeat ourselves with the `java.util` prefix.

We've also used a class in another namespace:

```java
import java.io.IOException;
```

`IOException` and `ArrayList` are in the same filing cabinet, but they're in different drawers. Each `.` represents a subpackage, again allowing us to further relate classes and interfaces. Everything under the `java` package comes with the JDK and helps us to write meaningful programs. We can learn all about the foundational API's as well as how they're organized via the [Java documentation][java-package-docs].

Most Jars and third-party libraries will define packages as well, helping us to further organize our programs.

Here are some example import statements from other JARs we've made use of:

```java
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
```

`javax` indicates that these libraries are "Java E**x**tensions. They are not part of core Java. Otherwise, they would be in the `java` namespace. These extensions allow us to work with the JPA and to create Servlets.

In the future, we'll use a package called Jackson to work with JSON objects. When we get there, we'll import the necessary classes as so:

```java
import com.fasterxml.jackson.databind.ObjectMapper;
```

This package adheres to the way we name our `groupId` in our maven projects. We use a domain name to help reduce the possibility of duplicates. You can then notice that we use `jackson` and `databind` to further qualify where filing cabinet the `ObjectMapper` class is in. Most libraries found on the MVN registry will adhere to this convention, and we will too, moving forward.

## Why Packaging is Necessary

### Preventing Collisions

Let's say we were building a system that manages our Campus Tours. Prospective visitors can visit our website and book a tour. They can also check in with our front desk to let a tour guide know they're here. Both `Visitor` abstractions have two very different meanings.

```java
Visitor visitor = new Visitor();
visitor.trackReferrer();
visitor.sendSyllabus();
```

```java
Visitor visitor = new Visitor();
visitor.checkIn();
visitor.greet();
```

While we refer to both objects as `Visitor` instances, they serve two very different purposes. Thus we need to namespace them. It would make sense for us to place these different classes in different namespaces. IE, we could place them in the following package hierarchies.

```java
import com.launchacademy.site.Visitor;
```

```java
import com.launchacademy.campustours.Visitor;
```

### Keeping it Neat and Tidy

Using packaging just helps us to better organize our programs. We can organize our packages by module / feature while avoiding having to put every class in one giant directory.

### Conventions in Packaging

```java
import com.launchacademy.startupideas.models.Idea
```

We use a backwards domain name that we own to reduce the possibility of collisions. We then use all lowercase package names to compartmentalize our classes and interfaces. Here, we're saying that the `Idea` class belongs to the `startupideas` app, as part of its `models` sub-package.

### Defining Our Own Package in Intellij

We can right click on the `src/main/java` folder and select New > Package. We can then enter the new package name, adhering to the conventions above. We've already created a package for you in the provided code. For our example, we entered `com.launchacademy.greeting`.

### Creating a Class Inside Our Package

Once the package is created, we can right click on the resulting folder, and create a new class like we're accustomed to. We've created a class called `Greeting`. If you open it up, you'll notice something different right away at the top of the file:

```java
package com.launchacademy.greeter;
```

We've provided a full class inside of the package for you. Here's what our entire, newly created class looks like for example purposes:

```java
package com.launchacademy.greeter;

public class Greeting {
  private String name;

  public Greeting(String name) {
    this.name = name;
  }

  public String sayHi() {
    return "Hi " + name;
  }
}
```

The `package` keyword tells the Java compiler how to organize this class. Conventionally, we also store the class in `src/main/java/com/launchacademy/greeter/Greeting.java`.

### Moving Classes Into a Package

We've provided a `ProgramRunner` class that `import`s our newly created class.

```java
import com.launchacademy.greeter.Greeting;

public class ProgramRunner {

  public static void main(String[] args) {
    Greeting greeting = new Greeting("Jon");
    System.out.println(greeting.sayHi());
  }
}
```

Note the `import` statement. It looks just like we were using a class in the JAnsi library or some other third party library. That's because we've placed the `Greeting` class within our own custom package.

If we decide that we want to move this class into the package, we can click and drag the class into the desired folder in Intellij. Intellij will do the rest of the work for us once we click the "Refactor" button. _For this example, ensure that "Search for comments and strings" as well as "Search for Text Occurrences" are unchecked_

## In Summary

We use packages to prevent collisions, better organize our programs, and to protect data. We must use packages if we want to leverage our classes in Java Server Pages (JSP), which we will learn about in subsequent lessons. By using Intellij, filesystem conventions, and the `package` keyword, placing our classes and interfaces into need groups helps us to manage complexity as our software grows.

[java-package-docs]:https://docs.oracle.com/en/java/javase/11/docs/api/java.base/module-summary.html
