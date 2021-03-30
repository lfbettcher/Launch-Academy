We've learned that we can package and distribute our code using Java Jar files. Recall that they are simply a zipped up distribution of our `class` files. In this article, we'll explore finding and incorporating Jars that are freely accessible to us as part of Java's open source ecosystem.

## Learning Goals

- Download a JAR
- Incorporate a third party JAR into your application
- Package Your Code into a JAR Artifact with Intellij

## Getting Started

```no-highlight
et get java-using-third-party-jars
cd java-using-third-party-jars
idea .
```

Let's imagine that we want to add some color to our command line programs. Just like apps we download on our phone, there is open source software available for us to download. Theses libraries exist to solve a common problem, like colorizing our terminal output.

## Downloading Third Party Code

When it comes to Java, the best places to look for open source code is the [MVN Repository][mvn-repo]. This website maintains an index of popular repositories for open source Java libraries.

### Finding Jars

The MVN repository's user experience is not great, including its search mechanism. So, we usually search for Java repositories on GitHub, or use the repository's browsing features to find a popular library to fulfill a specific need.

It just so happens that there is a popular library, [JAnsi][jansi-mvn], that will allow us to colorize our terminal output. In Java, we refer to a published library like this as an artifact. If we look at the artifact's page, we can see how the library is licensed as well as a wealth of information about releases.

If we click on the latest version (at the time of this writing, it is [1.18][jansi-version]), there is a _Files_ row in the first table of information about the release. Next to it is a link to download the JAR. Download the JAR and disregard any threatening warnings from your browser about downloading a potentially harmful file. We're programmers, we know what we're doing...kind of.

### A Note About Maven

MVN is an abbreviated form of the word Maven. Maven is a critical part of the Java ecosystem. Maven, while originally constructed to help us manage the building and distribution of our Java programs, has become the standard for dependency and project management. The Maven Repositories are where open source software providers publish their code for us to use.

While this application only has one dependency, jAnsi, most software out there in the wild will have multiple dependencies. Later, we'll define a `pom.xml` file and manage our dependencies through Maven. For now, though, let's just stick to downloading the JAR for simplicity.

## Using Third Party Code

### Locating Your Jar File

Generally, if we're using third party libraries without a `pom.xml`, we should package our dependencies with our codebase. This is known as "vendoring" our libraries. Let's move our downloaded Jar to a vendor directory in our project. Note that the path of your downloaded Jar file may be different depending on your operating system and configuration.

```no-highlight
mkdir vendor
mv ~/Downloads/jansi-1.18.jar vendor
```

Once we've moved our Jar into the project root, we can now tell Intellij that we want to use it. So, in the IDE, we can navigate to File -> Project Structure -> Libraries. We can then click the `+` -> Java and point to our newly located Java Jar. Click "Open" and you'll notice that we can now browse the classes defined in the Java JAR through the project window.

### Using JAnsi

Like all great open source software, Jansi has a wonderful [README][jansi-readme] that gives us guidelines on how to utilize it. Let's add some color to our simple `Welcome` class.

```java
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.*;

public class Welcome {
  public static void main(String[] args) {
    AnsiConsole.systemInstall();

    System.out.println(ansi().fg(GREEN).a("Welcome to the ship."));
    System.out.println(ansi().fg(RED).a("RED ALERT"));
  }
}
```

We can now run this in Intellij by clicking the green "Play" button on the top toolbar, or right-clicking the `Welcome.java` file in our file structure on the left-hand side and selecting "Run 'Welcome.main()". Note that running it this way uses Intellij's built-in "build and run" functionality which both compiles and runs the file.

When we DO run it, however, the results are anticlimactic. Intellij unfortunately strips out our coloring!

### Packaging a JAR of Our Own in Intellij

That's no fun! Let's build our own Jar so that we can easily run our program in the terminal. Intellij can help us with that.

1. Navigate to File -> Project Structure
2. Go to the Artifacts section
3. Click the `+` Sign
4. Select JAR -> From modules with dependencies
5. Select our `Welcome` class as the "main" class
6. Select "Copy to the output directory and link via manifest"
7. Click "Ok"

You can now build your JAR:

1. Navigate to Build -> Build Artifacts...
2. Select Build on the resulting dialog

Now you can navigate to your output directory. It should be something like `out/artifacts/java_using_third_party_jars_jar`.

You can execute:

```no-highlight
java -jar /vendor/jansi-1.17.1.jar
```

Live and in color!

## Why This Matters

We'll be using dependencies from the Maven registries quite often. It's important to know how to include Jar files into your application.

## In Summary

Open source Java libraries are available to use to solve common problems. The MVN Repository is a nice, centralized place to both discover and download these libraries. We can add downloaded Jar files to our project and make use of them from within Intellij. We can also configure our projects in Intellij to result in the building of a Jar file.

[mvn-repo]: https://mvnrepository.com/
[jansi-mvn]: https://mvnrepository.com/artifact/org.fusesource.jansi/jansi
[jansi-version]: https://mvnrepository.com/artifact/org.fusesource.jansi/jansi/1.18
[jansi-readme]: https://github.com/fusesource/jansi
