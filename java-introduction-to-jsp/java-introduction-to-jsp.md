Java Server Pages provide us with the first glimpse of using Java in a web-based context.

Through using Tomcat, a new filesystem structure, and some new syntax in our HTML, we'll build our first Java Server Pages.

## Learning Goals

- Configure a Java Server Pages project
- Execute Java Server Pages
- Integrate a JSP Expression
- Integrate a JSP Scriptlet
- Integrate a JSP Declaration
- Use the `@page` directive

## Getting Started

```no-highlight
et get java-introduction-to-jsp
cd java-introduction-to-jsp
idea .
```

## Getting Set Up

We're going to need multiple pieces to get a Java web app up and running. Configuration aside, the main two tools we'll be using for this are:
- Tomcat, to act as our server
- Java Server Pages (JSPs), our HTML template files

Let's follow the below instructions to get these, and all related configuration, set up in our application and get our web app running!

We need to make some adjustments in our `pom.xml`.
First, we need to tell Maven that we want to build a `war` file. A WAR is a special kind of JAR. It is a Web Application Archive. While a JAR typically contains Java class files, and perhaps some configuration, a WAR file will include Java Server Pages, HTML, CSS, and other web-oriented files.

So let's change the `packaging` in our `pom.xml`. We usually place this directive directly under our `<version>` directive.

```xml
<packaging>war</packaging>
```

### Configure Dependencies

We need to configure our dependencies on Java Servlets.

```xml
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>javax.servlet-api</artifactId>
  <version>4.0.1</version>
  <scope>provided</scope>
</dependency>
```

### Install the Tomcat Plugin

We also need to configure the WAR plugin and the  Tomcat Plugin to facilitate copying our WAR file to a Tomcat container.

```xml
<build>
  <finalName>intro-to-jsp</finalName>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>2.3</version>
    </plugin>
    <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <configuration>
            <port>8080</port>
            <path>/</path>
        </configuration>
    </plugin>
  </plugins>
</build>
```

Note: In here it shows `tomcat7-maven-plugin` even though we are using Tomcat 9. This plugin has been update to work with higher versions of Tomcat, and should function just fine as long as we have Tomcat 9 installed correctly on our computer.

### The Whole pom.xml

Here's what your `pom.xml` should look like:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.launchacademy</groupId>
    <artifactId>java-introduction-to-jsp</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>
    <properties>
        <java.version>11</java.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
      <finalName>intro-to-jsp</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.3</version>
            </plugin>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <port>8080</port>
                    <path>/</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```


### Folder Structure

Next, we're going to organize a `web.xml` configuration.
This file is known as the "deployment descriptor".
It is used to instruct Tomcat and application servers on how servlets map to URLs.
We'll talk about servlets in a subsequent lesson.
Fow now, we'll set up our deployment description as simply as possible to build our first JSP.

Inside your `src/main` directory, create a `webapp` directory. This is where your web related files will live. Inside there, create, a `WEB-INF` directory.
You'll then need to create a `web.xml` file inside that newly added directory with the contents below.

So, below should be the contents of the file located at `src/main/webapp/WEB-INF/web.xml`.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://java.sun.com/xml/ns/javaee"
  xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
  id="WebApp_ID" version="3.0">

  <display-name>introduction-to-jsp</display-name>
  <servlet>
    <servlet-name>Intro</servlet-name>
    <jsp-file>/index.jsp</jsp-file>
  </servlet>
</web-app>
```

Next, we're going to create a simple Java Server Page (JSP). Let's create `src/main/webapp/index.jsp`.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello from JSP</title>
  </head>
  <body>
    <p>The date is <%= (new java.util.Date()).toLocaleString() %></p>
  </body>
</html>
```

## Deploying and Running Our Application

Deploying with Tomcat is both complicated and somewhat unreliable. Basically, our build process is going to build and package our WAR, and subsequently deploy it to Tomcat. This process requires some patience and careful watching of the logs.

To run our application for the first time, we have the option to run `tomcat7:run` from plugins, inside Intellij in the Maven Projects window/drawer. However, this ties us to the IntelliJ terminal, whereas we're used to running servers in our own local terminal in other languages!

**We've added a tool called [MavenWrapper][maven-wrapper] to your Tomcat assignments** so that you can run these tasks from your typical terminal (iTerm or Git Bash) instead. You can run Tomcat in your terminal using the command `./mvnw tomcat7:run`. You can also run any other scripts you find in your Maven Projects drawer by using the same format!

If all goes well, you should see output like the below:

```no-highlight
Jun 21, 2019 5:32:15 PM org.apache.coyote.AbstractProtocol init
INFO: Initializing ProtocolHandler ["http-bio-8080"]
Jun 21, 2019 5:32:15 PM org.apache.catalina.core.StandardService startInternal
INFO: Starting service Tomcat
Jun 21, 2019 5:32:15 PM org.apache.catalina.core.StandardEngine startInternal
INFO: Starting Servlet Engine: Apache Tomcat/7.0.47
Jun 21, 2019 5:32:19 PM org.apache.coyote.AbstractProtocol start
INFO: Starting ProtocolHandler ["http-bio-8080"]
```

Chances are, things will not go well at some point with deploying your servlets. Tomcat has a reputation for being pretty finicky, so it will require a great deal of patience. Here are a few debugging steps:

- Ensure you don't already have a running instance of Tomcat - look for open tabs in Intellij
- Ensure your `web.xml` is located in the proper place (`src/main/webapp/WEB-INF/web.xml`) and references the proper files
- Ensure all path references in your `web.xml` are absolute paths
- Ensure your `pom.xml` specifies an absolute `path` and a `port`
- Run `tomcat7:shutdown`
- Run `clean` from the `Lifecycle` list
- Run `tomcat7:run`

If all goes well and you're able to successfully launch, you should be able to access `http://localhost:8080` and see that the page will display the current date and time. If you run into an error saying that the port is already in use, see the **On Zombies** section below.

## JSP Expressions

Alright, so let's break things down a bit.

```jsp
<p>The date is <%= (new java.util.Date()).toLocaleString() %></p>
```

In our simplest JSP example, we're using the JSP expression above. An expression is indicated by the `<%= %>` tags. These infer that you will be outputting the return value or result of the Java expression inside. If the return value is not a `String`, JSP will automatically call `.toString()` on the object to get a `String` representation of the object.

## JSP Scriptlets

We can change this implementation a bit. To do so, we can introduce a JSP Scriptlet (like a baby script inside our template file!).

```jsp
<%
  java.util.Date currentDate = new java.util.Date();
  String formattedDate = currentDate.toLocaleString();
%>
<p>The date is <%= formattedDate %></p>
```

It's arguable which one is more readable or expressive, but we can use a Scriptlet (indicated by `<% %>` tags omitting the equal sign), to include some artibtrary Java in our page. When you use a scriptlet, the results will not be outputted as HTML source. Essentially, whether or not you include the `=` determines if the evaluated Java code will actually be _visible_ on the page or not.

We can even use flow control and looping inside a scriptlet:

```jsp
<%
  java.util.Date currentDate = new java.util.Date();
  String formattedDate = currentDate.toLocaleString();
  for(int i = 0; i < 3; i++) {
%>
  <p>The date is <%= formattedDate %></p>
<% } %>
```

This will output our paragraph tag 3 times.

## JSP Declarations

We can also use a JSP declaration indicated by `<%! %>` tags (the exclamation qualifies it as a declaration). We use these primarily for declaring methods.

```jsp
<%! String getFormattedDate() {
  java.util.Date currentDate = new java.util.Date();
  return currentDate.toLocaleString();
} %>
<p>The date is <%= getFormattedDate() %></p>
```

## JSP Directives

At the top of our JSP, we saw something slightly different.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
```

This is known as the page **directive**. Directives provide guidance to the container about how to handle the processing of the JSP. Let's use another directive to clean up our code a bit.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Hello from JSP</title>
  </head>
  <body>
    <%! String getFormattedDate() {
      Date currentDate = new Date();
      return currentDate.toLocaleString();
    } %>
    <p>The date is <%= getFormattedDate() %></p>
  </body>
</html>
```

Here the second `@page` directive functions like an `import` statement in our `java` files. We can use it to shorten our references to classes in other packages.

## Why This Matters

Using JSP allows us to provide HTTP responses constructed via Java code. It's worth noting that JSP is vestige of an older time - it's quite similar to classic PHP and ASP where we integrate too much business logic with presentational logic. But a lot of codebases use it, and it's important to know. As we know from prior templating languages, we typically prefer to keep our business logic OUT of our templates.

## Building Up our Toolbelt

Like everything here at Launch, we want to teach you underlying technologies before showing you the larger frameworks that make these activities easier.

Combined with servlets, we can achieve a view-controller paradigm like we achieved with our Express server and Handlebars templates. This is where we're headed shortly.

We'll see a few different templating languages in Java - first, the two primary ones that will be used outside of Spring. Secondly, when we start to study Spring, we will use a more complex templating engine (with richer features) called Thymeleaf. They'll all have similarities, and it's important to learn more about Java templating and the MVC paradigm before diving headfirst into Spring.

## A Note on Zombies

What the heck do zombies have to do with JSPs? Well, when using Tomcat (or any server really) if you don't intentionally shut down your server then it will cause errors when you try to start another one.
If you encounter an issue where it tells you the port is already in use try `brew services stop tomcat` (for Mac) or follow [this article](https://medium.com/@faiz.krm/how-to-kill-tomcat-service-running-on-any-port-windows-6756b3b3e2b5) for a Windows machine.

## In Summary

Java Server Pages allow us to execute Java to construct HTML web pages. Tomcat is how we host servlets that can process HTTP requests and serve HTTP responses.

Equipped with entities, scriptlets, declarations, and directives, there's much we can accomplish given all we've learned of Java to date.

[maven-wrapper]: https://github.com/takari/maven-wrapper
