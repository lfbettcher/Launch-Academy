# Get your Getters here!

Now that you've learned a little bit about Lombok, lets got through a simple exercise to put it into practice.

We're revisiting Knapford station to check in on Thomas and his friends.

## Getting Started

```no-highlight
et get java-lombok-exercise
cd java-lombok-exercise
idea .
```

## Get your getters out of the Trains

Currently our Train model is set up with the traditional method of implementing `getters` and `setters`

```java
package com.example.lombokexercise.models;

import org.springframework.stereotype.Component;

@Component
public class Train {
  private String name;
  private String color;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
```

Start up Spring Boot and visit `localhost:8080/trains`. You should see a list of each train and their colors.

Update the model to make use of the `@Getter`, `@Setter`, and `@NoArgsConstructor` annotations. Once complete rebuild your project and visit `localhost:8080/trains` again. If nothing has changed you've done your job correctly!

## Strings Attached

Let's also update our `Station` model to make use of Lombok's annotations. This time, however in addition to `getters` and `setters` we also want to make use of `@ToString`.
Let's add some logging to our site. Exclude the `name` from the `@ToString` annotation. In our controller let's add a `sout` to the `@GetMapping`.
You'll know you've succeeded if you see the following in your terminal (the run tab in IntelliJ).

```no-highlight
Station(location=Sodor)
```

While this example is contrived, logging application behavior can be an invaluable tool in troubleshooting your projects. Be careful to take out or obfuscate logging in a production application, otherwise unsavory types could use your logs against you!

## In Summary

Lombok provides us with a lot of great tools. `@Getter`, `@Setter`. `@NoArgsConstructor`, and `@ToSring` are just a few, but they are the ones we'll use most often.
Check out [Lombok's Documentation](https://github.com/rzwitserloot/lombok/wiki) to learn more about what Project Lombok can do for you!
