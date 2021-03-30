## Getting Started

```bash
et get java-standard-io
cd java-standard-io
```

## Learning Goals

- Provide Java program output with standard out
- Handle user input and incorporate it into a Java application
- Integrate the `throws` keyword into our Java application
- Construct an `import` statement to easily access a class

## Introduction

Sometimes you want to send some text to the user or receive some information from them. Java has a quick mechanism to do this. Java has classes called **streams** to accept or output data.

## Write to Standard Out

It is easy to write a "standard out" statement since every application has it automatically open. It is an [OutputStream](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/OutputStream.html), one of the Java classes that sends data to the console or other data repository.

```Java
System.out.println("some text");
```

This will output "some text" to your console.

The [System](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/System.html) class is automatically created when the application starts, opening the input and output streams.

## Read from Standard In

It is as easy to read from standard in. Just as with the Standard Out, it is automatically opened when your application starts. It is an [InputStream](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/InputStream.html).

For example see the code below:

```Java
int byte = System.in.read();
```

This will read one character (UTF-8) from the console.

In your terminal run these commands:

```bash
touch ReadStdIn.java
idea .
```

Open `ReadStdIn.java` inside IntelliJ and add the following:

```Java
import java.io.IOException;

public class ReadStdIn {
  public static void main(String[] args) throws IOException {
    byte[] buffer = new byte[10];
    int offset = 0;
    System.in.read(buffer, offset, buffer.length);
    String userInput = new String(buffer);
    System.out.println("You said: " + userInput);
  }
}
```

The class above will read 10 bytes from the console (a `byte` is another smaller `primitive` that we use here to hold only 1 character), starting at position `0` (thanks to our offset). Inside the `main` method, we create an array called `buffer` to store a list of bytes. Here we set the space for 10 bytes for this array. This is now the maximum size of the array.

Run `javac` to compile the class, then use the `java` command to run it as shown below. Note that it's not going to _prompt_ you for input, but it will be listening for you to type input and press `Enter`:

```bash
javac ReadStdIn.java
java ReadStdIn
```

We use `System.in.read` to fill the `buffer` array with what the user enters into the console. Once you've supplied data, press enter. You will see that the first 10 characters of your input will be played back to you.

## Working with both streams

You can also combine both the `out` and `in` streams to prompt the user and provide feedback based on what they entered.

```java
import java.io.IOException;

public class ReadStdIn {
  public static void main(String[] args) throws IOException {
    System.out.println("Input a number between 0 - 9 ");
    int number = System.in.read();
    if (number < '0' || number > '9') {
      System.out.println("Uhm, number please?");
    } else {
      System.out.println((char)number);
    }
  }
}
```

## The `throws` Keyword

As developers, it's important to anticipate problems or errors that can arise in our programs. With Java, and other strongly typed languages, we indicate what errors we expect a given function may encounter with a `throws` statement. In this case, since we're reading from standard in, we're integrating with the operating system a bit. Things like permissions and available operating system resources come into play, resulting in the possibility of something going wrong. Using the `throws` statement in this way signals to developers working with our `main` method that they will have to handle for an `IOException`.

## Namespaces and the `import` Statement

Like most programming languages, Java supplies us with a predefined set of objects and methods. This way, we don't have to reinvent the wheel each time we write a new Java application. We can instead focus on building a car.

Java, in particular, provides us with _a lot_, so much so that it can be confusing how all of the objects and methods are organized. With these complex libraries, we often organize our classes into **namespaces**. Like a good filesystem, well-thought-out namespaces help us as humans understand how our code is organized.

In the code examples provided above, we use the `IOException` class, which is located in the `java.io` namespace. We could use the following code:

```java
public class ReadStdIn {
  public static void main(String[] args) throws java.io.IOException {
    System.out.println("Input a number between 0 - 9 ");
    int number = System.in.read();
    if (number < '0' || number > '9') {
      System.out.println("Uhm, number please?");
    } else {
      System.out.println((char)number);
    }
  }
}
```

The difference in this example is we explicitly pull the class from `java.io` in our `throws` statement. Using an import statement like `import java.io.IOException;` makes it so we don't have to add the namespace into our code. Think of it like creating a shortcut to access the class.

This also prevents _naming collisions_. If we wanted to, we could create an alternative class `IOException` that exists in another namespace. That class could do something entirely different, yet we could use both without causing a problem, because they are from different namespaces.

### Using a Scanner

Scanners are intended to make it easier to manage input from the user. The `Scanner` class is built into the `java.util` package, and simplifies the syntax we need to use in order to gather user input, especially if we're collecting multiple different inputs from the user. While we won't see _all_ that much benefit in this example since it's only taking in one input, we can rewrite our first example with the use of a `Scanner` instance.

```java
import java.io.IOException;
import java.util.Scanner;

public class ReadStdIn {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.next();
    System.out.println("You said: " + userInput);
  }
}
```

Note that the `next()` method used above will provide the next part of the user input, separated by spaces. There are many other options to take input, like `nextLine()` which will take in the full line of input.

### Catching our Errors

One way to handle for errors, especially when it comes to user input, is with a `try`/`catch` block. This can allow us to wrap any code we think _might_ error in a block that would handle for the error smoothly.

For example, in the example above, we're getting user input and simply printing it out. However, let's say we wanted to go back to asking for a number 0 - 9. The issue is, the user input comes through as a `String`. If we wanted to parse the number to turn it into an integer, we could update our code as follows:

```java
import java.io.IOException;
import java.util.Scanner;

public class ReadStdIn {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Input a number between 0 - 9");
    String userInput = scanner.next();
    int number = Integer.parseInt(userInput);
    if (number < 0 || number > 9) {
      System.out.println("Input a number between 0 and 9");
    } else {
      System.out.println(number);
    }
  }
}
```

This code will work fantastically, assuming the user types in a number -- any number, even if it's not in our range. However, what if the user types in `"abc"`? We'll get an error: `Exception in thread "main" java.lang.NumberFormatException: For input string: "abc"`. This is because our `Integer.parseInt()` call failed to parse "abc".

We can handle for this error by calling the `Integer.parseInt()` call inside of a `try`/`catch` block, as so:

```java
import java.io.IOException;
import java.util.Scanner;

public class ReadStdIn {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Input a number between 0 - 9 ");
    String userInput = scanner.next();
    try {
      int number = Integer.parseInt(userInput);
      if (number < 0 || number > 9) {
        System.out.println("Input a number between 0 and 9");
      } else {
        System.out.println(number);
      }
    } catch (NumberFormatException e) {
      System.out.println("Please enter a number, not text");
    }
  }
}
```

Thanks to our `try` block, the program will still behave the same way if a user inputs a number. But if it fails to parse the input, it will hop over to our `catch` block. Our `catch` is then responsible for outputting an error-specific message that tells the user to enter a number, not text!

`catch` expects an `NumberFormatException` argument, which we can name anything (here, `e`). This gives us access to the error it encountered, just in case we want to use it. Here, we write our own custom error message for the user instead. Notice that wwe want our `catch` to specifically look for a `NumberFormatException` rather than just a general `Exception`, to make sure we're only catching the error if it's due to non-numeric input.

## Why This Matters

All software revolves around taking information from the user and doing something meaningful with it. In these examples, we're building the basic foundations of what will become increasingly complex Java.

## In Summary

Use `System.out.println` to _output_ to the Command-line and use `System.in.read` to take in _input_ from the Command-line. When it comes to `System.in.read`, keep in mind that reading in input requires you to set an explicit length of expected input, and that the data comes in to your program as a `byte[]`. An easier way instead you could use a `Scanner` for user input.

Using the `throws` keyword, Java anticipates errors to signal to those using your code that something may go wrong. Reading from standard in, for example, may throw an `IOException`.

As Java developers, we use packages and namespaces to keep our code organized, and to prevent naming collisions.
