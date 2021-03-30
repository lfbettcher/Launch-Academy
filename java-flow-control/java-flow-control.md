# Java Flow Control

A major part of writing good software is having that software make decisions for you via pre-determined logic. Java has several ways of changing and managing the flow of logic in your application.

## Lesson Goals

- Use boolean variables
- Experiment with conditional statements
- Use an if-else statements
- Experiment with ternary statements
- Implement a **switch** statement

As with other languages, Java has **boolean** values. A boolean variable can only be a `true` or `false` value.

## Getting Started

```no-highlight
et get java-flow-control
cd java-flow-control
```

## Relational Operators

We can ask questions about booleans with **boolean expressions**. There are multiple relational operators.

Boolean expressions generally follow the format:

```java
variable == "expected result"
```

In the boolean expression above, we're checking to see if the the variable named `variable` is equal to the String `"expected result"`. The variable `variable` is on the left side, there's an operator, and then there's a right side value to make our comparison. In this case, the operator we're using determines whether the value on the left side and the value on the right side are equal.

There are a number of boolean operators we can use to ask questions that result in a `true` or `false` value. Here are a few:

| Symbols| Meaning | Description |
| ------ | ------------- | ----- |
| **==** | binary equals | The value is the same between two variables |
| **!=** | not equals | The values are not the same |
| **>** | greater than | The value on the left is larger than the right |
| **<** | less than | The value on the left is smaller than the right |
| **>=** | greater than or equal to | The value on the left is the same size or larger to the right |
| **<=** | lesser than or equal to | The value on the left is the same size or smaller than the right |

## Logical Operators

Sometimes, we want to ask multiple questions with our boolean expressions. Thankfully, you can perform more than one boolean comparison in a single expression. There are multiple logical operators.

For example:

```java
variable != "unexpected result" && variable != ""
```

This asks the question: _does my variable not equal 'unexpected result' and is it not blank?_ If the answer is yes to both questions, the result of this boolean expression is `true`. If one of these comparisons returns `false`, the result of the entire boolean expression is then `false`.

There are few ways that we can combine boolean expressions:

| Symbols| Meaning | Description |
| ------ | ------------- | ----- |
| **&&** | logical AND | If both sub-expressions are true then the "logical AND operator" evaluates to true |
| **\|\|** | logical OR | The logical OR operator is only evaluated as true when one of its sub-expressions evaluates `true`. If either or both expressions evaluate to true, then the result is true

## Negating Boolean Expressions

In the context of boolean expressions, we refer to **!** as logical NOT.

```java
!false // returns true
!true // returns false
```

Logical NOT is a _Unary Operator_, meaning it operates on a single boolean expression. It effectively reverses the boolean value.

## Conditional Statements

You can take the logical operators and create a statement that returns either _true_ or _false_ (what we call a boolean value).

```Java
boolean bool1 = (a && b);
boolean bool2 = (c || d);
```

- `bool1` is true if `a` **and** `b` are true
- `bool2` is true if either `c` **or** `d` is true

## `if` statement

To test to see if a value is true, use the **if** statement. The syntax of the _if_ statement is straightforward - `if (conditional statement) {statements...}`.

```Java
boolean value1 = true;
boolean value2 = false;
if (value1) {
  System.out.println("It is true");
}
```

If we change the variable under test to `value2` above, the `"It is true"` output will never been seen. That's because the conditional statement evaluated to false, and so the interpreter knows not to execute our `System.out.println`.

For the first time in our Java code, we see our program making decisions for us. But, what happens if there's work I want my program to do if `value1` is false?

## `if`...`else` statement

That's where the `else` keyword comes into play. There are times you need to do both sides of the test.

This is programmatically awkward:

```Java
if (true){
  // do something
}
if (!true){
  // do something else
}
```

So, we can use this:

```Java
if (true) {
  // do something
} else {
  // do something else
}
```

It's important to note something about conditionals. We can get into some trouble here:

```Java
if (true) {
  // executed all the time
}
else {
  System.out.println("We could have an error here that would never get found")
}
```

In the example above, our `else` branch will **never** get executed. This is often where bugs can crop up. If one side of the conditional is rare or unlikely to occur, often bugs can hide in these obscure areas of the code.

Additionally, if you want to check if something exists in the conditional then you must use `if(yourVariable.exists())`. This is different from other languages where you can simply check with `if(yourVariable)`.

## `if`...`else if`...`else` statement

There is one last case for _if_. We can provide additional boolean expressions to evaluate as we work through the flow.

```Java
if (condition)
else if (another conditional)
else // if both expressions return false
```

For example, we can look at the following method. Don't worry too much about how we're analyzing the input and output yet -- we'll go into that further in future articles!

```Java
import java.io.IOException;

public class FlowDemo {

  public static void main(String[] args) throws IOException {
    byte[] buffer = new byte[1];
    int offset = 0;
    System.out.println("Enter a letter grade.");
    System.in.read(buffer, offset, buffer.length);
    char userInput = (char)buffer[0];

    System.out.println("You said: " + userInput);

    if (userInput == 'a') {
      System.out.println("The student scored a 90 or better");
    } else if (userInput == 'b') {
      System.out.println("The student scored somewhere between an 80 and an 89");
    } else if (userInput == 'c'){
      System.out.println("The student scored somewhere between an 70 and an 79");
    } else if (userInput == 'd') {
      System.out.println("The student scored somewhere between an 60 and an 69");
    } else if (userInput == 'f') {
      System.out.println("The student scored lower than a 60");
    } else {
      System.out.println(userInput + " is not a valid letter grade");
    }
  }
}
```

This code is a handy guide for teachers to understand the grade ranges based on a student's provided letter grade. We check to see whether the student received a valid letter grade (a-f). If not, the last branch of logic alerts the user that they did not provide a valid letter grade.

You can add as many _else if_ cases as needed.

## Short-circuiting the `if` blocks

It's worth mentioning That _&&_ (AND) short circuits when the left value of the operator is _false_.

```Java
String test = null;
if (test != null && test.length() > 0) {
  ...
}
```

If _test_ is _null_, the interpreter does not need to check the right side of the conditional. Therefore, it will not check `test.length()`.

The same is true for _||_ (OR), but if the left value is _true_.

```Java
private static final SPR = "SPR";
String test = "A"
if ( test.contains("A") || test.contains("B") || test.contains("C")) {
  ...
}
```

The second and third boolean expression will not get evaluated because the interpreter immediately found a `true` result with the first expression.

This can be really helpful when writing our code's logic flow, because we can order our statements so that any "pre-requisite" evaluations happen further to the left. For example, in the first example above, we don't want to call `test.length()` unless `test != null`, because if `test == null`, `test.length()` would throw an error! So we can order our evaluations such that we can avoid any errors being thrown.

## Ternary Operator

Sometimes we have a very small if...else test case. In that case, we can use the ternary operator. The ternary operator has three parts:

- the test condition - this will show up before the `?`
- the code to execute if the test condition is true - this will be between the `?` and the `:`
- the code to execute if the test condition is false - this will be after the `:`

```no-highlight
conditional ? <what to do if true> : <what to do if false>;
```

Here's an example of the ternary statement in action:

```Java
public void myLittleTest(String test) {
  String output = (test != null && test.length() > 0) ? test : "No valid string available";
  System.out.println(output);
}
```

Here, we are conditionally setting the `output` string variable to either `test` or to an error message, based on our conditional check from before.

## switch statement

If you have a variable you are trying to match with a series of values, the **switch** statement may be a good alternate option to the `if...else` statement. It essentially makes an `if...else` statement that is always comparing against one specific variable or value.

The syntax of _switch_ is shown below:

```Java
  switch(variable) {
    case 1:
      statement1;
      break;
    case 2:
      statement2;
      break;
    ...
    case n:
      statementN;
      break;
    default:
      default defaultStatement;
  }
```

We could refactor our grade checker to use a `case` statement like so:

```java
import java.io.IOException;

public class CaseDemo {

  public static void main(String[] args) throws IOException {
    byte[] buffer = new byte[1];
    int offset = 0;
    System.out.println("Enter a letter grade.");
    System.in.read(buffer, offset, buffer.length);
    char userInput = (char)buffer[0];

    System.out.println("You said: " + userInput);

    switch(userInput) {
      case 'a':
        System.out.println("The student scored a 90 or better");
        break;
      case 'b':
        System.out.println("The student scored somewhere between an 80 and an 89");
        break;
      case 'c':
        System.out.println("The student scored somewhere between an 70 and an 79");
        break;
      case 'd':
        System.out.println("The student scored somewhere between an 60 and an 69");
        break;
      case 'f':
        System.out.println("The student scored lower than a 60");
        break;
      default:
        System.out.println(userInput + " is not a valid letter grade");
      }
  }
}
```

This is a lot of syntax! For most of our purposes, we recommend shying away from using the `switch` statement and sticking with traditional `if` `else` statements.

## In Summary

Our programs can make decisions thanks to boolean expressions and conditionals. Boolean expressions evaluate to either `true` or `false`. We can use these boolean expressions in conditional statements. In Java, we can use `if...else`, ternary operations, and the `switch` statement to control flow.
