In this article, we will introduce you to the Java Compiler.

## Learning Goals

- Execute the `javac` Java Compiler command
- Compile a small test application with Java
- Run a simple Java program with the `java` executable

## Getting Started

```no-highlight
et get intro-java-javac
cd intro-java-javac
```

## Compiling with JavaC

### Introduction

Java has two design intents. Java is designed to be **fast**, and it is designed to be **portable**. To accomplish both goals, the creators of Java decided to have it compiled and then interpreted.

We compile Java code to make it fast. The compilation process turns our code (text) into a more machine-friendly format: byte code.

The Java compiler command to make this all happen is 'javac'. It has a simple format.

`javac <options> <source files>`

The available options are:

- `-classpath` Specify where to find user class files and annotation processors
- `-help` display the help for this command
- `-verbose` Output informative messages about what the compiler is doing
- `-version` Version of the `javac` compiler

Don't worry too much about the `classpath` option - we'll discuss this in further detail later.

We write our code inside `*.java` files. That's where all of your Java source code will live. Like our JavaScript classes, we use upper camel case to name our Java files.

### Your first Class file

Here is an example of a simple class. It has been included for you as part of this article.

Main.java

```Java
public class Main {
  public static void main(String[] args) {
    String message = "Hello World";
    System.out.println(message);
  }
}
```

To compile this class, simply open up your terminal or console window, change to the directory of the `Main.java` file and type `javac Main.java`.

We get the `javac` command as part of the Java Development Kit, otherwise known as the `JDK`. The JDK provides us with what we need to effectively write and distribute Java applications.

### Compilation and Interpretation

Our compilation command will produce a file named `Main.class`. This is your class file that you can run with the `java` command. The `java` command will take the bytecode we compiled with the `javac` command, and execute it with the Java Virtual Machine (JVM). Behind the scenes, it will load your class file(s) and initiate the JVM, which will then **interpret** and execute the bytecode there.

## Execution with the Java command

If you type `java --help`, you will get the list of all the options.

The `-classpath` option is what we'll use most frequently. It tells the Java Runtime Environment (JRE) where in the filesystem to search for \*.class files. You can alternatively use the option `-cp` for short and to save some typing.

In a terminal or command window, type `java -version`. This will show what version of this command. Specifically, you should see something like:

```no-highlight
java version "11.0.3" 2019-04-16 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.3+12-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.3+12-LTS, mixed mode)
```

### Executing a class file

Open a terminal or command window, navigate to where you've been working in the filesystem, and type `java -cp . Main` (short for `java -classpath . Main`). You will see:

```no-highlight
Hello World
```

Potentially followed by:

```no-highlight
Process finished with exit code 0
```

For the `-cp` option we specify the current working directory (`.`). We then supply `Main` as an argument - this is the `class` file we want to run that presumably has a `public static main` to execute.

The `java` command is provided by the Java Runtime Environment, otherwise known as the **JRE**. This software includes everything we need to effectively run Java.

## Summary

Java promises to offer both performance and portability. To deliver on both promises, Java is both _compiled_ and _interpreted_. We compile Java source code (`*.java` files) into Java bytecode (`*.class` files) using the `javac` command. We execute Java bytecode with the `java` command.

`javac` comes as part of the Java Development Kit, otherwise known as the JDK. `java` comes as part of the Java Runtime Environment, otherwise known as the JRE. The JDK is a software package that allows us to do our work as developers that use Java. The JRE is what users require to run Java bytecode.
