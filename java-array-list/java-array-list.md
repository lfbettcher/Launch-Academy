## Learning Goals

- Introduce Java Arrays
- Identify commonalities among implementers of `Collection`
- Demonstrate the Use of Generics
- Experiment with the `ArrayList` class

## Getting Started

```no-highlight
et get java-array-list
cd java-array-list
idea .
```

## Introduction

In our cursory exploration of loops, we begin to see the need to maintain data in a set of some kind. Often, we want this set to be sorted in a specific order. In most programming languages, we use the construct of an Array to represent this idea of an ordered list. Java actually has numerous classes that already deal with the maintenance of the list. Let's take a look.

## What is an Array?

In Java, an Array is an ordered collection of values. You can create an array by adding a '[]' after the type name. If you would like to code along we have provided a Main.java which you can put the examples in. You may need to delete or comment out previous examples for newer ones to work.

For example:

```Java
int[] prices = {34, 52, 85, 77};
String[] productNames = {"Leather Briefcase", "Flannel Shirt", "Red Teapot"};
```

This will get us prices equal to list of four integers with values `34`, `52`, `85` and `77`. The productNames would equal a list of three Strings containing "Leather Briefcase", "Flannel Shirt", and "Red Teapot".

If we want to access the first price in the list we can access it at the zero-eth index. Like most programming languages, Java starts counting at zero.

```Java
//outputs the first price
System.out.println(prices[0]);

//outputs the third product name
System.out.println(productNames[2]);
```

If we'd like to see the full contents of our Array we can use `Arrays.toString();` as follows:

```java
System.out.println(Arrays.toString(productNames));
```

We can also construct Arrays with the `new` keyword.

```Java
public static void main(String[] args) {
  int[] prices = new int[4];
  prices[0] = 34;
  prices[1] = 52;
  prices[2] = 85;
  prices[3] = 77;
}
```

### Static Size and a Note on Initialization

In the example above, we explicitly set the length of our array to be of size 4. This solution is fairly rigid, in that we can't increase the size of our array dynamically.

```Java
public static void main(String[] args) {
  int[] prices = new int[4];
  prices[0] = 34;
  prices[1] = 52;
  prices[2] = 85;
  prices[3] = 77;
  prices[4] = 90;
}
```

The above code will throw an `ArrayIndexOutOfBoundsException` exception. We attempted to assign a value outside the established length of the array when we instantiated it.

Here's another weird thing about Java arrays that are instantiated with a static length.

```Java
public static void main(String[] args) {
  int[] prices = new int[4];
  prices[0] = 34;
  prices[1] = 52;
  prices[2] = 85;
  System.out.println(prices.length);
  System.out.println(prices[3]);
}
```

Even though we have not **assigned** a value at index `3`, Java will automatically initialize that `int` value to zero.

In Java, primitives have a default initialization.

- **int** - 0
- **bool** - false
- **double** - 0.0
- **char** - `\u0000` (unicode "NULL" character)

For all other types / reference values, Java will initialize the value to `null`. There is no concept of `undefined` in Java.

### Iterating Through an Array

```java
public static void main(String[] args) {
  int[] prices = new int[4];
  prices[0] = 34;
  prices[1] = 52;
  prices[2] = 85;

  for(int i = 0; i < prices.length; i++) {
    System.out.println(prices[i]);
  }
}
```

We can use a combination of a `for` loop and the array's `length` value to loop through an array. Notice that the last value, with the default initialization value, is output as `0`, and that four values are output.

The static length of our arrays above is fairly limiting for us as programmers. Thankfully, Java provides a more flexible construct: the `ArrayList`. Before we dive into the specifics of `ArrayList`, though, we have to explain two key concepts that will help us to better understand `ArrayList`.

## What are collections?

Back in Java 1.2, a series of classes called _collections_ were created. These are abstractions for dealing with a set of items. There is an interface called [Collection](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collection.html) that has methods like _add()_, _clear()_, _isEmpty()_, etc. We'll talk about interfaces later, but for now, know that the `ArrayList` class implements these methods as an `AbstractCollection`. Different implementers of `AbstractCollection` can be applied to suit performance needs. For now, we'll use `ArrayList` exclusively.

## What are generics?

Java provides a solution to some of its peskier issues tied to strict typing known as **generics**. Add this inside your main method and let's take a look at the approach:

```Java
ArrayList<String> list = new ArrayList<String>();
list.add("hello");
String s = list.get(0);  // notice no cast - we'll address that below
```

The `<String>` syntax in defining our `list` tells the Interpreter that we're creating a List that exclusively contains `String` instances. Through the use of generics, each item defined in the `ArrayList` is already defined as a `String` which eliminates the need for `casting` to make sure types match. In fact, with the generic defined, if we try the following, we won't even be able to compile:

```Java
list.add(0);
```

As a bonus, it also speeds up compilation.

## What if I don't want to use Generics

Java generics spare us the pain of having to `cast` our variables into a type.

```Java
ArrayList list = new ArrayList();
list.add("hello");
String s = (String) list.get(0); // cast list to String
```

As you can see, we had to cast the _list.get(0)_ to a `String` so that it would be an acceptable assignment to the variable `s`. Based on the way we declare that value, we explicitly say that `s` needs to be a `String`. The `get` method returns an `Object`. This results in some messy code and often unanticipated results.

This is why we greatly prefer generics.

## What is an ArrayList?

An [ArrayList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html) is an object that holds an array. As a part of `Collection`, it enhances arrays with some accessor and management functions. It is easy to instantiate the array by using the constructors:

- `ArrayList()` - creates an empty `ArrayList`
- `ArrayList(int size)` - creates an empty `ArrayList` with the initial capacity of _size_

For the following commands we will show examples using the following ArrayList

```java
ArrayList<String> exampleArray = new ArrayList<>;
exampleArray.add("Sisko");
exampleArray.add("Picard");
exampleArray.add("Janeway");

// exampleArray now equals ["Sisko", "Picard", "Janeway"]
System.out.println(Arrays.toString(exampleArray));
```

You can add to the ArrayList using the `add` command. When needed, it can even expand its array. We can also get and remove items from the ArrayList.

- `void add(int position, Object element)` - Inserts the specified element at the specified position in this list.
- `boolean add(Object element)` - Appends the specified element to the end of this list.
- `Object get(int index)` - Retrieves the element at the specified index
- `Object remove(int index)` - Removes the element at the specified position in this list.
- `boolean remove(Object o)` - Removes the first occurrence of the specified element from this list, if it is present.
- `void clear()` - Removes all of the elements from this list

To see these in action:

```java
exampleArray.add(1,"Kirk");
//exampleArray = ["Sisko", "Kirk" "Picard", "Janeway"]
System.out.println(Arrays.toString(exampleArray));

exampleArray.add("Shepard");
//exampleArray = ["Sisko", "Kirk" "Picard", "Janeway", Shepard]
System.out.println(Arrays.toString(exampleArray));

exampleArray.get(2);
//returns "Picard"
System.out.println(Arrays.toString(exampleArray));

exampleArray.remove(1);
//exampleArray = ["Sisko", "Picard", "Janeway", "Shepard"]
System.out.println(Arrays.toString(exampleArray));

exampleArray.remove("Shepard");
//exampleArray = ["Sisko", "Picard", "Janeway"]
System.out.println(Arrays.toString(exampleArray));

exampleArray.clear();
//exampleArray = []
System.out.println(Arrays.toString(exampleArray));
```

There are a couple useful but miscellaneous methods

- `boolean contains(Object o)` - Returns true if this list contains the specified element.
- `int indexOf(Object o)` - Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
- `boolean isEmpty()` - Returns true if this list contains no elements.
- `int lastIndexOf(Object o)` - Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
- `int size()` - Returns the number of elements in this list.
- `void trimToSize()` - Trims the capacity of this ArrayList instance to be the list's current size. This is useful to clear out any additional memory the ArrayList may be using, however an ArrayList will increase its capacity automatically if another item is added to it.

```java
//exampleArray = = ["Sisko", "Kirk" "Picard", "Janeway", "Shepard"]
exampleArray.contains("Kirk");
//returns true

exampleArray.contains("Warf");
// returns false

exampleArray.indexOf("Janeway");
//returns 3

exampleArray.indexOf("Pike");
//returns -1

exampleArray.size();
//returns 5

exampleArray.trimToSize();
//changes the max size of exampleArray to 5
```

### Iterating Through an Array List

Instead of using a standard `for` loop, we can iterate through an `ArrayList` with the following syntax:

```java
import java.util.ArrayList;

public class ForLoopExample {
  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<String>();
    names.add("Sam");
    names.add("Shawna");
    names.add("Brianna");

    for(String name : names) {
      System.out.println(name);
    }
  }
}
```

This will result in the following output:

```no-highlight
Sam
Shawna
Brianna
```

To break down the loop:
```java
for(String name : names) {
  System.out.println(name);
}
```

`for`: the method we are calling
`String name`: the variable declaration for each item we iterate over
`names`: the name of the ArrayList we are iterating over

If you want a mnemonic (memory trick) for remembering the order of variable vs ArrayList you can read it as "For each name in the names array". The fact that we are declaring a type for the `name` is also a good indicator.


## The Data Structures Discussion Will Continue

Java has a very rich object model when it comes to collections. While we'll stick to using `ArrayList` for now, we'll dive deeper into this area later in the course.
