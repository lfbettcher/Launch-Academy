## Learning Goals

- Define a Java class
- Code and run our first Java program
- Define static and local variables
- Explore the concept of access scope

## Getting Started

```no-highlight
et get java-fundamentals
cd java-fundamentals
idea .
```

## Introduction

Let's get down to exploring code in Java. For this article, simply follow along and study the concepts introduced. We will show you how to run Java in a subsequent lesson. The key thing to know about Java is that it is an _Object Oriented_ language. As you may know, this means that every line of code needs to be part of a class. A class is the abstraction used to bind data (like properties) and actions (like methods) together.

To be more specific, sometimes other languages such as JavaScript or Python allow us to write code in the **global scope** (outside of a class definition), but in Java all of our code will always be part of some class that we define.

## Classes vs. Objects

What is the difference between classes and instances? Think about the problem like this, a class is the template or blueprint for the object. Each time we create an instance, we construct it from the blueprint. Each instance shares the same blueprint.

## Classes

`class` is the keyword used to define a class. For example:

```Java
public class ThisIsAWidget {}
```

This is the simplest class definition we can make. The pieces of this statement are:

- **class** - define a class
- **public** - access scope of the class
- **ThisIsAWidget** - name of the class _Notice that I am using UpperCamelCase for the name._
- **{** - start of code block
- **}** - end of code block

We'll talk about access scope when we explore objects in greater detail. For now, all of your classes should be defined as `public`.

## The Main Method

There is one last standard method a class can use: `main`, or `public static void main(String[] args)`. It is a special method that the interpreter will look for to determine how to run the program. It is also the entry point to our application, so if we want the code in our class to run at all, we will need our `main` method defined. Let's break down the elements of this method.

- **public** - everyone can access this method
- **static** - this makes it a class level method, shared by all object instances of this class.
- **void** - there is no return value
- **main** - name of the method

```Java
public class ThisIsAWidget {
  public static void main(String[] args ) {
    System.out.println("Hello from my Java ThisIsAWidget class");
  }
}
```

Let's test this code by running it from our command line. There is a file `ThisIsAWidget.java` in the current directory. Place the above ThisIsAWidget class code in the file. Run `javac ThisIsAWidget.java`. If you recheck your directory, you should now see _ThisIsAWidget.class_. Run `java -cp . ThisIsAWidget` and you should see output similar to below:

```no-highlight
Hello from my Java ThisIsAWidget class
```

**We'll discuss what those commands do in a later article! **

## Primitive Types

What makes Java a little odd in the Object Oriented community is that not all data types are classes. We call these types primitive types. They represent the most basic types of data we can work with as Java engineers. While understanding these will be important eventually, for now, we'll just touch on them. Here is a list of all of Java's primitive types:

- **int**
  - range: -2,147,483,648 to 2,147,483,647
  - description: an integer datatype used to store numbers without decimals
- **short**
  - range: -32,768 to 32,767
  - description: can also hold integer values, but has a more limited range. Unless we are concerned about memory or disk space, we will often use `int` instead.
- **long**
  - range: 9x(10^18)
  - description: can also hold integer values, but has a MUCH wider range than `int`. Used for helping us work with exceptionally large numbers, we will still likely defer to using `int` instead.
- **double**
  - IEEE 754 floating point double-precision
  - description: used for storing floating point numbers (those that have decimals), and usually a Java standard for storing floating point numbers
- **float**
  - IEEE 754 floating point single-precision
  - description: floats are similar to `double` in that they hold a floating point number, but have less precision & range. Most of the time Java will default to using `double` instead.
- **boolean** - boolean values
  - range: _true_ or _false_
  - description: booleans can only either be `true` or `false` and are used to determining the truthiness or falsiness of something. We will often use booleans when doing comparisons to help with our code logic.
- **char**
  - description: char is able to hold characters and is often defined with single quotes (see example below). A collection of characters will often be stored in Java's `String` class, to help us store words or even sentences.

The syntax to declare any of these primitives includes specifying the type of primitive, then our variable name, then the value, as so:

```java
int someNumber = 10;
double decimalNumber = 110.55;
char myLittleChar = 'b';
```

There are more primitive types than this, but we won't have cause to use them for a long while. If you are interested you can [learn more in Java's documentation](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html).

## Variables

Variables are the way to store data in your application. In Java classes, there are two levels of variables, _class_ and _method_ levels. The first part of creating a variable is the **variable declaration**, where we declare the type of the data we want to store followed by the name we will use to refer to the variable. We also need to assign a value to this variable with the assignment operation `=`. This is outlined below:

```no-highlight
variableType variableName = variableValue;
```

Or more specifically:

```java
int newInteger = 8;
```

## Class Variables

Class variables are defined across the scope of an entire class, meaning that there's only one copy of that variable that is shared with all instances of that class. If changes are made to that variable, all other instances will see the effect of the changes. We define these variables with the `static` keyword.

```java
public class Car {
  private static boolean Drivable = true;
}
```

### Method (or Local) Variables

Inside of each code block, you can create variables. When you create a method, you are also creating a code block. As such, you can define variables that are available only inside that method.

```Java
public class ThisIsAWidget {
  public static void main(String[] args ) {
    String greeting = "Hello from my Java ThisIsAWidget class";
    System.out.println(greeting);
  }
}
```

Method level variables are scoped to each individual method call. The are declared and instantiated when the method is called, and then removed when the method execution finishes.

In this case, the `greeting` variable will only be available inside the `main` function of the class. We say that this is locally scoped. We name local variables using lower [camel case conventions][camel-case-conventions].

### String Variables

The `char` primitive can only hold one character at a time. As such, in order to create a word, we need to be able to define a collection of `char` variables all at once. We could do this, and even store them in an array. But Java gives us the `String` class to easily allow us to create a sequence of `char`s all at once. Note that `String` isn't a primitive. It's a class.

```java
String greeting = "Hello world!";
```

One final distinction: Single quotes are used for the `char` primitive e.g. `char myLittleChar = 'b'`, while double quotes are used for strings e.g. `String newDogName = "Spot";`

### Constant Variables

Instead of hard-coding our string, and as an alternative to a local variable, let's do some refactoring.

```java
public class ThisIsAWidget {
  private static final String WIDGET_GREETING = "Hello from my Java ThisIsAWidget class";

  public static void main(String[] args ) {
    System.out.println(WIDGET_GREETING);
  }
}
```

In the code above, we changed our `main` function slightly. We moved the string we wanted to output to a `static final` string. In this case, `static` means that it does not change from instance to instance, and final means that the value cannot be reassigned. `final` effectively makes the value read-only.

When creating constants, make them all uppercase with the words separated by an underscore ('\_'). This helps distinguish them from the other variable types. By making them **static**, they become "class" level meaning that they are accessible across all objects of this class. Making them **final** makes it so they can only be assigned a value once. `final` variables are similar to `const` variables in JavaScript.

## In Summary

Java is extremely object oriented. Even the function we use to run our program must exist inside of a class. In defining a `public static void main`, we can explore many aspects of the Java programming language. While we cover the basics, we'll work exclusively in such a function.

[camel-case-conventions]: https://techterms.com/definition/camelcase
