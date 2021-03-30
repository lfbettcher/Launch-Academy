You have been tasked with building the UI for Launch Academy's little library. In this article, we'll get you started with some building out the beginnings of the interface.

## Learning Goals

- Use a Java `Enum` to create interactive, command line menus
- Identify and isolate class responsibilities
- Store data in an `ArrayList`

## Getting Started

```no-highlight
et get java-interactive-menus
cd java-interactive-menus
idea .
```

## Understanding the Requirements

Launch Academy is all about sharing resources, and some folks love books. So, we're going to write a simple application to help us run a community-driven library.

The library interface we're building will provide users with an opportunity to contribute books, check out a book, and to return a book.

Since our program won't be web-based, we'll have to get a bit creative. We'll also want to ensure we're practicing sound, object oriented design principles in building out our menu.

There's one new construct that can help us, and that is the Java `Enum`.

## Enter the Enum

Let's diverge for a moment and imagine that we were building an interface for a coffee shop. We need a way to distinguish between Small, Medium, and Large sizes. We could represent these choices as static strings.

```java
public class CoffeeSelection {
  public static String SMALL_SIZE = "Small";
  public static String MEDIUM_SIZE = "Medium";
  public static String LARGE_SIZE = "Large";
}
```

### Relating Data Together

This is perfectly valid syntax, and helps us to advance our metaphor. We've also appropriately used static `String` values for these items. There's a problem with this, however. There's no explicit relationship between these items. We need a way to group these choices together. We can employ an enum to group these constants together.

```java
public class CoffeeSelection {
  public enum Size {
    SMALL,
    MEDIUM,
    LARGE;
  }
}
```

Now, we've appropriately represented the size options available to a customer. You may be asking, why is this important? Well, now, we can construct our `CoffeeSelection` with a guaranteed-to-be-valid size.

```java
public class CoffeeSelection {

  public enum Size {
    SMALL,
    MEDIUM,
    LARGE;
  }

  private Size size;

  public CoffeeSelection(Size size) {
    this.size = size;
  }

  public String toString() {
    return this.size.name() + " coffee"
  }
}
```

```java
CoffeeSelection selection = new CoffeeSelection(CoffeeSelection.Size.MEDIUM);
System.out.println(selection.toString());
```

Here, we've restricted of `CoffeeSelection` instances to be constructed in 1 of three ways. We cannot construct a `CoffeeSelection` without specifying a valid size.

We've also defined a `toString()` method that makes use of the selection. The `name()` method returns the string value of the enum.

### Abbreviating with Enum - Enums with Properties

There's a bit more we can do with the `Enum` construct. We can abbreviate our options using an `Enum`, and provide a more user-friendly representation of the size.

```java
public class CoffeeSelection {
  public static String SMALL_SIZE = "Small";
  public static String MEDIUM_SIZE = "Medium";
  public static String LARGE_SIZE = "Large";

  public enum Size {
    s(SMALL_SIZE),
    m(MEDIUM_SIZE),
    l(LARGE_SIZE);

    private String sizeName;

    Size(String sizeName) {
      this.sizeName = sizeName;
    }
  }

  private Size size;

  public CoffeeSelection(Size size) {
    this.size = size;
  }

  public String toString() {
    return size.sizeName + " coffee";
  }
}
```

```java
CoffeeSelection selection = new CoffeeSelection(CoffeeSelection.Size.m);
System.out.println(selection.toString());
```

Notice above how our `Enum` declaration has changed. We've used parens to indicate that each value has a property. In this case, we use the human-friendly strings, to add context to the values `s`, `m`, and `l`. We assign that value in the `Enum` constructor, and it is stored as a value associated with each size. We can then access that value from outside of the `Enum`.

### Enumerating Options with Enum

Now that these values are related to one another, we can loop through them to present a user with options. Let's define the following method in our `CoffeeSelection` class.

```java
public static String sizeOptionList() {
  String output = "";
  for(Size size : Size.values()) {
    output += size.name() + ". - " + size.sizeName + "\n";
  }
  return output;
}
```

In a `main` function we can output the list using this newly established `static` method.

```java
System.out.println(CoffeeSelection.sizeOptionList());
```

## Implementing Our Main Menu

Now that we've explored what we need with the `Enum` keyword, let's apply it to our Library UI. We're going to use an `Enum` to correlate a single key to a menu option for fast navigation. Let's create a new class `MainMenu`.

```java
public class MainMenu {
  public static final String ADD_BOOK_TEXT = "Contribute a book";
  public static final String CHECKOUT_TEXT = "Check out a book";
  public static final String RETURN_BOOK_TEXT = "Return a book";
  public static final String QUIT_TEXT = "Quit";

  public enum MenuOption {
    a(ADD_BOOK_TEXT),
    c(CHECKOUT_TEXT),
    r(RETURN_BOOK_TEXT),
    q(QUIT_TEXT);

    private String optionText;

    MenuOption(String optionText) {
      this.optionText = optionText;
    }

    public String toString() {
      return this.name() + ". " + this.optionText;
    }
  }
}
```

Here, we've built an object oriented representation of our library's `MainMenu`. We provide `static final` representations of the menu options, and we group them together via an `Enum`. The key/character `a` is related to "Contribute a Book" as is the `c` character with "Check out a book". We've created a `toString()` method within the `MenuOption` enum to make it easy to output the list, as you'll see in the code example below.

```java
public class MainMenu {
  public static final String ADD_BOOK_TEXT = "Contribute a book";
  public static final String CHECKOUT_TEXT = "Check out a book";
  public static final String RETURN_BOOK_TEXT = "Return a book";
  public static final String QUIT_TEXT = "Quit";

  public enum MenuOption {
    a(ADD_BOOK_TEXT),
    c(CHECKOUT_TEXT),
    r(RETURN_BOOK_TEXT),
    q(QUIT_TEXT);

    private String optionText;

    MenuOption(String optionText) {
      this.optionText = optionText;
    }

    public String toString() {
      return this.name() + ". " + this.optionText;
    }
  }

  @Override
  public String toString() {
    String output = "";
    for(MenuOption option : MenuOption.values()) {
      output += option.toString() + "\n";
    }
    return output;
  }
}
```

In our newly established `toString` method, we're iterating through each option, and providing a user-friendly line of output for each option. We use the `\n` newline character to ensure that each menu option gets it out line of output.

To see this in action, let's put this method to work in the supplied `LaunchLibraryRunner` class. Note that you'll have to copy the above `MainMenu` class into a corresponding Java file as well.

```java
public class LaunchLibraryRunner {
  public static void main(String[] args) {
    MainMenu menu = new MainMenu();
    System.out.println(menu.toString());
  }
}
```

Running this will produce the following output:

```no-highlight
a. Contribute a book
c. Check out a book
r. Return a book
q. Quit
```

Great! Now, let's allow the user to select one of these options. Where does that responsibility belong? Arguably, in the `MainMenu` class. We will want to handle the selection the user makes there, since its single responsibility is to represent the concept of actions the user can take with our little Launch library. Let's add the method below to the `MainMenu` class.

```java
public void promptUntilQuit() {
  System.out.println(this.toString());
  MenuOption input = null;
  Scanner scanner = new Scanner(System.in);
  do{
    System.out.print("> ");
    input = MenuOption.valueOf(scanner.next());
  } while(input != MenuOption.q);
  System.out.println("Thanks! Come to the library again.");
  scanner.close();
}
```

This method presents the user with a list of menu options, and asks them to select an option. The program will continue to run until they select `q`. Let's modify our `LibraryLaunchRunner` to make use of this new method.

**Hint: Make sure you import your scanner.**

```java
public class LaunchLibraryRunner {
  public static void main(String[] args) {
    MainMenu menu = new MainMenu();
    menu.promptUntilQuit();
  }
}
```

See how clean that is!?!? We've got a clear representation of the `MainMenu` object and what it needs to do in the context of our running program. When you run this, you should be presented with the list of options, and prompted to make a selection:

```no-highlight
a. Contribute a book
c. Check out a book
r. Return a book
q. Quit

>
```

If you type `q`, the program will terminate. What happens if you enter something invalid, however?

```no-highlight
a. Contribute a book
c. Check out a book
r. Return a book
q. Quit

> d

Exception in thread "main" java.lang.IllegalArgumentException: No enum constant MainMenu.MenuOption.d
  at java.base/java.lang.Enum.valueOf(Enum.java:240)
  at MainMenu$MenuOption.valueOf(MainMenu.java:10)
  at MainMenu.promptUntilQuit(MainMenu.java:43)
  at LaunchLibraryRunner.main(LaunchLibraryRunner.java:4)
```

## Handling for Bad User Input

`Enum`, by its nature, is very restrictive. So, when we attempted to access the `Enum` option `d`, it does not exist. So, Java got mad at us, and threw a `java.lang.IllegalArgumentException`. It's important that as smart programmers, we design for the fact that a user could make an errant selection. So, let's modify our `promptUntilQuit` method.

```java
public void promptUntilQuit() {
  System.out.println(this.toString());
  MenuOption input = null;
  Scanner scanner = new Scanner(System.in);
  do{
    System.out.print("> ");
    try {
      input = MenuOption.valueOf(scanner.next());
    } catch(IllegalArgumentException error) {
      System.out.println("Please make a valid selection!");
    }
  } while(input != MenuOption.q);
  System.out.println("Thanks! Come to the library again.");
  scanner.close();
}
```

Here, we introduce a `try...catch` block to consider the possibility of a user making a bad selection. Remember, that because Java is statically typed, we must anticipate the type of error the program will encounter. In this case, we're handling specifically for an `IllegalArgumentException`, so we specify that in the `catch`. It's important to target the error you're anticipating explicitly, so that other unanticipated errors will result in desired behavior: crashing the application. If we run this again, and try to select an invalid option `d`, we receive the following output:

```no-highlight
a. Contribute a book
c. Check out a book
r. Return a book
q. Quit

> d
Please make a valid selection!
>
```

## Handling Menu Selections

Ok, so now how do we handle the deeper components of the UI, you ask? Well, we can write a simple conditional to manage each option.

```java
public void promptUntilQuit() {
  System.out.println(this.toString());
  MenuOption input = null;
  Scanner scanner = new Scanner(System.in);
  do{
    System.out.print("> ");
    try {
      input = MenuOption.valueOf(scanner.next());
    } catch(IllegalArgumentException error) {
      System.out.println("Please make a valid selection!");
    }

    if(input == MenuOption.a) {
      //allow the user to add a book to the library
    }
    else if(input == MenuOption.c) {
      //allow the user to check out a book from the library
    }
    else if(input == MenuOption.r) {
      //allow the user to return a book to the library
    }

  } while(input != MenuOption.q);
  System.out.println("Thanks! Come to the library again.");
  scanner.close();
}
```

Generally, within each condition, we'd delegate to another class or instance method to manage the user interaction with each option.

## Why This Matters

There's certainly many different ways we could present users with a command-line oriented menu, and this isn't necessarily the simplest. It does, however, convey the benefit of sound object-oriented design. Through using an `Enum`, we relate the options together, and we're restrictive around the actions that the user can take. It also makes it easy to add additional menu options. A little design upfront can help us to write more flexible software.

## In Summary

Use an `Enum` to be restrictive when we need a user to interact with a list of options. Take an object-oriented approach to allow for extensibility and flexibility.
