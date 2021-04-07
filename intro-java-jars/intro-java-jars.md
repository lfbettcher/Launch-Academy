## Learning Goals

- Consider the benefits of using `jar` files
- Build a `jar` archive
- Execute a `jar`

## Getting Started

```no-highlight
et get intro-java-jars
cd intro-java-jars
idea .
```

## Introduction

Java uses a collection of objects to create applications. Rather than sending individual class files which would be a mess for deployment and/or delivery, the creators of Java decided to create a collection of classes in a file archive called a JAR. This makes it easy for us as Java developers to distribute libraries of code.

JAR is short for **J**ava **AR**chive. Basically, a JAR is a zip file of all of our class files, making our applications easier to distribute.

## Working with a JAR

Let's build a simple JAR file together with the code we've provided alongside this article. We've defined a simple `Main` class that outputs `"Hello from a JAR file"`. Run this program and verify that it works correctly for you.

```no-highlight
javac src/HelloWorld.java
java -cp src HelloWorld
```

## Creating Your First JAR file

Now that we've established a simple, working library, let's build a JAR file with it.

```bash
jar -cf HelloWorld.jar -C src .
```

The `-cf` command, short for `--create --file` indicates that you want to create a file. The `-C` option allows us to specify what directory we want to create the archive of (here, the `src` directory, from inside the current, `.`, directory).

### To view the contents of a JAR file

```bash
jar tf HelloWorld.jar
```

The `-tf` (or `tf`) or `--list --file` command asks for a table-list in the command-line showing a list of all the files within the JAR.

You'll notice that when we list the contents of this file, there are some new additions. `META-INF/MANIFEST.MF` is what the Java JAR will use to contain metadata about the JAR file. This will become important as we begin to work with our JAR files.

### To extract the contents of a JAR file

```Bash
jar xf HelloWorld.jar
```

The `-xf` or `--extract --file` command extracts the JAR file listed after, and all files will be copied to the current directory.

Note:

> This command will overwrite all files in the current directory with the same filenames as the files within the JAR, so make sure to double-check before running this command.

## Building a Runnable JAR with a Manifest

So we've learned how to package a JAR file that contains our `HelloWorld` class, but we can't really do anything with it yet. Thankfully, Java provides us with a way to easily run our code from a JAR file.

```no-highlight
rm HelloWorld.jar
jar -cfe HelloWorld.jar HelloWorld -C src .
java -jar HelloWorld.jar
```

If all goes well, you should see:

```no-highlight
Hello from a JAR file
```

Let's break down our `jar` command a bit.

- We use `rm` to remove the previously created `HelloWorld.jar` (if this doesn't work, for whatever reason, you can also manually remove this file via your IDE or Finder/FileExplorer).
- Next, we use the `-cfe` command to create our new `HelloWorld.jar`. The `e` option defines our **Entry Point** - this tells the JAR what class to run when we execute our JAR, and uses the second argument, `HelloWorld`, as that entrypoint.
- The `-C` option command builds the archive so that every file that is in `src` is included in the root of the archive.
- Lastly, we use `java -jar` to run our jar file.

### Taking a Look at the Manifest

Let's take a look at the manifest file (`META-INF/MANIFEST.MF`) that was generated when we specified our JAR entry point.

```no-highlight
jar xf HelloWorld.jar  // running this again to ensure we have the updates from our `cfe` above`.
cat META-INF/MANIFEST.MF

Manifest-Version: 1.0
Created-By: 11.0.3 (Oracle Corporation)
Main-Class: HelloWorld
```

As we mentioned above, Java packages our JAR with some metadata. In our example, Java built our JAR with a manifest version of `1.0`. We can also see what version of Java built the JAR for us. Lastly, and most importantly, since we specified a `-e` option, the manifest defines what class serves as the Main-Class. So, when we run `java -jar`, the JRE knows what class to execute.

## Why This Matters

JARs make it easy for us to distribute our Java Libraries. As we start to build more complex Java programs, we will often use JAR files created by other developers. In our own travels as Java engineers, it's likely that we will also package JAR files for other developers to use, too!
