Creating inheritance relationships is a key aspect of Object Oriented Programming. It helps us to create a class hierarchy and to eliminate duplication, and it can help us to improve the metaphor of our programming.

Like anything in programming, with great power comes great responsibility. It is easy for inheritance to be an overused and abused mechanism, especially within the Java community.

We'll explore the applications of inheritance in this article.

## Learning Goals

- Create a parent and child class with a Java inheritance relationship
- Apply the "is a" test for inheritance

**Note**: Refer to our article in week 3 titled "JavaScript Prototypes and Inheritance" for a refresher on the "is a" test

## Getting Started

```no-highlight
et get java-inheritance
cd java-inheritance
idea .
```

## The Search for Taxonomy

It's that time for every developer's rite of passage. We're going to create our own version of Farmville.

First, create a new Maven project in IntelliJ.

Now, let's create some classes to represent some of the animals on the farm.

```java
// src/main/java/com.launchacademy.farmville.Pig.java
//For this first class you will need to create the `src`, `main`, and `java` directories as well as the file.

public class com.launchacademy.farmville.Pig {
  private boolean isAlive;
  private int ageInYears;

  public com.launchacademy.farmville.Pig(boolean isAlive, int ageInYears) {
    this.isAlive = isAlive;
    this.ageInYears = ageInYears;
  }

  public String says() {
    return "Oink";
  }
}
```

```java
// src/main/java/com.launchacademy.farmville.Horse.java
public class com.launchacademy.farmville.Horse {
  private boolean isAlive;
  private int ageInYears;

  public com.launchacademy.farmville.Horse(boolean isAlive, int ageInYears) {
    this.isAlive = isAlive;
    this.ageInYears = ageInYears;
  }

  public String says() {
    return "Neigh";
  }
}
```

We can see a tremendous amount of duplication in this case. As we continue modeling them, there's quite a bit that a com.launchacademy.farmville.Pig and a com.launchacademy.farmville.Horse will have in common. That's because the objects we're trying to represent here are already part of an established hierarchy. In fact, there's a whole science that studies how to [classify animals][zoology]!

Let's use inheritance to clean up some of the duplication. We'll introduce the com.launchacademy.farmville.Animal class, which will serve as the base for both our `com.launchacademy.farmville.Pig` and `com.launchacademy.farmville.Horse` classes. Here, we'll implement the similarities.

```java
// src/main/java/com.launchacademy.farmville.Animal.java
public class com.launchacademy.farmville.Animal {
  private boolean isAlive;
  private int ageInYears;

  public com.launchacademy.farmville.Animal(boolean isAlive, int ageInYears) {
    this.isAlive = isAlive;
    this.ageInYears = ageInYears;
  }
}
```

With this class defined, we can now centralize the logic for managing whether the animal is alive and how old they are.

```java
import com.launchacademy.farmville.Animal;// src/main/java/com.launchacademy.farmville.Horse.java

public class com.launchacademy.farmville.Horse extends Animal {

  public Horse(boolean isAlive, int ageInYears) {
    super(isAlive, ageInYears);
  }

  public String says() {
    return "Neigh";
  }
}
```

To define an inheritance relationship in Java, we use the `extends` keyword to define the fact that our `com.launchacademy.farmville.Horse` class now inherits functionality from `com.launchacademy.farmville.Animal`. In the `com.launchacademy.farmville.Horse` constructor, we now call `super` to reference the `com.launchacademy.farmville.Animal` constructor. We can basically mirror this for the `com.launchacademy.farmville.Pig` class, too.

```java
import com.launchacademy.farmville.Animal;// src/main/java/com.launchacademy.farmville.Pig.java
public class com.launchacademy.farmville.Pig extends Animal {
  public com.launchacademy.farmville.Pig(boolean isAlive, int ageInYears) {
    super(isAlive, ageInYears);
  }

  public String says() {
    return "Oink";
  }
}
```

Now, both the `com.launchacademy.farmville.Pig` and `com.launchacademy.farmville.Horse` class share the same parent. We can define shared behaviors in `com.launchacademy.farmville.Animal`. Let's define some new functionality in our `com.launchacademy.farmville.Animal` class.

```java
// src/main/java/com.launchacademy.farmville.Animal.java
public class com.launchacademy.farmville.Animal {
  private boolean isAlive;
  private int ageInYears;
  private boolean isSleeping;

  public com.launchacademy.farmville.Animal(boolean isAlive, int ageInYears) {
    this.isAlive = isAlive;
    this.ageInYears = ageInYears;
    this.isSleeping = false;
  }

  public void goToSleep() {
    this.isSleeping = true;
  }

  public void wakeUp() {
    this.isSleeping = false;
  }
}
```

Now, any `com.launchacademy.farmville.Pig` or `com.launchacademy.farmville.Horse` instance will be able to call `goToSleep`. Let's play with these classes by defining a `static main` in `com.launchacademy.farmville.Animal`.

```java
import com.launchacademy.farmville.Horse;import com.launchacademy.farmville.Pig;// src/main/java/com.launchacademy.farmville.Animal.java
public class com.launchacademy.farmville.Animal {
  private boolean isAlive;
  private int ageInYears;
  private boolean isSleeping;

  public com.launchacademy.farmville.Animal(boolean isAlive, int ageInYears) {
    this.isAlive = isAlive;
    this.ageInYears = ageInYears;
    this.isSleeping = false;
  }

  public void goToSleep() {
    this.isSleeping = true;
  }

  public void wakeUp() {
    this.isSleeping = false;
  }

  public static void main(String[] args) {
    Horse blackBeauty = new Horse(true, 6);
    Pig babe = new Pig(true, 4);

    System.out.println(babe.says());

    babe.goToSleep();
    blackBeauty.goToSleep();

    blackBeauty.wakeUp();
  }
}
```

Here, we're creating a `com.launchacademy.farmville.Horse` instance and a `com.launchacademy.farmville.Pig` instance. Both animals went to sleep, but `blackBeauty` woke up. Therefore, `babe`'s `isSleeping` state will be set to true, while `blackBeauty`'s `isSleeping` state will be set to false.

### Handling for Non-Compliant Children

The beauty to this approach is that we do not lose anything in terms of flexibility when developing our child classes. If there's functionality that we want to add to a specific child, we can do so without issue.

```java
import com.launchacademy.farmville.Animal;// src/main/java/com.launchacademy.farmville.Cow.java
public class com.launchacademy.farmville.Cow extends Animal {
  public static final int DEFAULT_MILKING_PERCENTAGE = 5;
  private int milkLevelPercentage;

  public com.launchacademy.farmville.Cow(boolean isAlive, int ageInYears, int milkLevelPercentage) {
    super(isAlive, ageInYears);
    this.milkLevelPercentage = milkLevelPercentage;
  }

  public void milk() {
    this.milk(DEFAULT_MILKING_PERCENTAGE);
  }

  public void milk(int percentageToMilk) {
    if(this.milkLevelPercentage - percentageToMilk > 0) {
      this.milkLevelPercentage -= percentageToMilk;
    }
    else {
      this.milkLevelPercentage = 0;
    }
  }
}
```

Here, we've created a new child class of `com.launchacademy.farmville.Animal`, `com.launchacademy.farmville.Cow`. We've added this whole new concept of being able to milk the cow. This functionality is specific to the cow, so the `milk` method will be unavailable to `com.launchacademy.farmville.Horse` and `com.launchacademy.farmville.Pig`.

### Multiple Levels of Inheritance

Java supports multiple levels of inheritance. If we're adhering to strict zoological definitions, `com.launchacademy.farmville.Horse`, `com.launchacademy.farmville.Pig`, and `com.launchacademy.farmville.Cow` are all mammals, so what if we extracted that functionality to a `com.launchacademy.farmville.Mammal` class?

```java
import com.launchacademy.farmville.Animal;// src/main/java/com.launchacademy.farmville.Mammal.java
public class com.launchacademy.farmville.Mammal extends Animal {
  public static final int DEFAULT_MILKING_PERCENTAGE = 5;
  private int milkLevelPercentage;

  public com.launchacademy.farmville.Mammal(boolean isAlive, int ageInYears, int milkLevelPercentage) {
    super(isAlive, ageInYears);
    this.milkLevelPercentage = milkLevelPercentage;
  }

  public void milk() {
    this.milk(DEFAULT_MILKING_PERCENTAGE);
  }

  public void milk(int percentageToMilk) {
    if(this.milkLevelPercentage - percentageToMilk > 0) {
      this.milkLevelPercentage -= percentageToMilk;
    }
    else {
      this.milkLevelPercentage = 0;
    }
  }
}
```

This way our `com.launchacademy.farmville.Cow` class would be greatly simplified. *Note that we will want to make the same changes to the constructor of our `com.launchacademy.farmville.Horse` and `com.launchacademy.farmville.Pig` classes as well, since they are also extending `com.launchacademy.farmville.Mammal` and will now need access to the `milkLevelPercentage`.*

```java
import com.launchacademy.farmville.Mammal;// src/main/java/com.launchacademy.farmville.Cow.java
public class com.launchacademy.farmville.Cow extends Mammal {
  public com.launchacademy.farmville.Cow(boolean isAlive, int ageInYears, int milkLevelPercentage) {
    super(isAlive, ageInYears, milkLevelPercentage);
  }
}
```

There are a few conclusions we can derive from this implementation.

- `com.launchacademy.farmville.Horse`, `com.launchacademy.farmville.Pig`, and `com.launchacademy.farmville.Cow` all would inherit from this `com.launchacademy.farmville.Mammal` class. Because `com.launchacademy.farmville.Mammal` inherits from `com.launchacademy.farmville.Animal`, all `com.launchacademy.farmville.Horse`, `com.launchacademy.farmville.Pig`, and `com.launchacademy.farmville.Cow` instances would receive the functionalities of both `com.launchacademy.farmville.Mammal` and `com.launchacademy.farmville.Animal`. Because of this, we call Java's interpretation of inheritance **transitive** - every child of `com.launchacademy.farmville.Mammal` is also a child of `com.launchacademy.farmville.Animal`, and therefore gains behavior from both parent classes.
- If we wanted to build a new class `Penguin`, `Penguin` is not a mammal, so we would have that class inherit directly from `com.launchacademy.farmville.Animal`.

## Use Inheritance Sparingly

As we've cautioned previously, inheritance is often overused and not always representative of a "is a" relationship. Be confident of your use case when implementing inheritance! In later lessons, we'll offer up a potential alternative with Interfaces.

## In Summary

Java supports inheritance with the `extends` keyword. To leverage the powers of parent classes, we can use the `super` call to call a method's correlating parent. Java supports multiple levels of inheritance. Though Java's implementation of class inheritance is quite powerful, we should use caution when considering it to solving a design problem.

[zoology]: https://en.wikipedia.org/wiki/Zoology
