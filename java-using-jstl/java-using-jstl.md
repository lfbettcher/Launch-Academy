So far, we've been writing Java in our JSP. For complex pages, the readability declines as we continue to mix Java programming with our HTML. That's where the Java Standard Tagging Library (JSTL) comes in. It provides us with an HTML-like syntax for dynamic web pages.

## Learning Goals

- Correlate variable assignment with its JSTL equivalent
- Correlate getting request context with its JSTL equivalent
- Correlate looping and flow control with JSTL equivalents

## Getting Started

```no-highlight
et get java-using-jstl
cd java-using-jstl
idea .
```

## Examples

As you're reading you can see examples of the code by starting Tomcat and visiting
[JSTL](http://localhost:8080/example?useJSTL=true) for the JSTL version or [here](http://localhost:8080/example) for the basic example. What do you notice when you observe them?

## Using JSTL

Like most of our work in Java, we need to effectively import our tagging library. This requires the addition of a dependency in our `pom.xml`.

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
```

We must also inform each JSP that we want to use the Java Standard Tagging Library with the following tag. Usually this is one of the first lines at the top of the file.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

The prefix attribute `c` is what we'll use in all of our JSTL declarations. We can assign an arbitrary value, but `c` is the conventional setting. (This is one of the very few times that single letter variables are okay.)

### Assigning Variables

In Java/JSP, we're used to assigning values like this:

```jsp
<% String tagLine = "I am the King of the North" %>
```

We can do this in JSTL via the `<c:set>` tag.

```jsp
<c:set var="tagLine" value="I am King of the North" />
```
### Outputting Values

We've demonstrated how to output variables using "show cones".

```jsp
<p><%= tagLine %></p>
```

There is also a JSTL equivalent for this.

```jsp
<c:out value="${tagLine}" />
```

Like with handlebars in Node, everything inside the curly braces will be evaluated as JSP Expression Language (EL).

### Request Context

We've been retrieving values set in the servlet like the example below:

```jsp
<%= request.getAttribute("greeting") %>
```

This is especially problematic and prone to bugs if we have to type coerce.

```jsp
<p>Number of Supporters (not counting themselves): <%= (int)request.getAttribute("nightsWatchSupporters") - 1 %>
```

Thankfully, there's a better JSTL equivalent.

```jsp
<p>Number of Supporters(not counting themselves): <c:out value=${requestScope.nightsWatchSupporters - 1} /></p>
```

We can use the `requestScope` keyword to access any of the variables set in the servlet. Also, the Expression Language takes care of type coercion for us.

### Conditionals

When it comes to conditionals and loops, this is where the syntax we've been showing you gets particularly messy.

```jsp
<% if((Boolean)request.getAttribute("isSignedIn")) { %>
  <p>You are signed in as <%= request.getAttribute("userName") %></p>
<% } %>
```

First, we have to cast the `request` variable to a boolean for it to work inside the conditional. Additionally, the curly braces really make it difficult to follow the flow of this simple conditional.

Again, there's a better JSTL equivalent that provides a readability improvement.

```jsp
<c:if test = "${requestScope.isSignedIn}">
    <p>You are signed in as <c:out value="${requestScope.userName}" /></p>
</c:if>
```

### Loops

Readability is also adversely affected in the context of loops.

```jsp
<% String[] favoriteThings = (String[])request.getAttribute("favoriteThings"); %>
<% if(favoriteThings.length > 0) { %>
  <h1>Favorite Things</h1>
  <ul>
    <% for(String thing : favoriteThings) { %>
    <li><%= thing %></li>
    <% } %>
  </ul>
<% } %>
```

This can be dramatically improved with JSTL:

```jsp
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${fn:length(requestScope.favoriteThings) > 0}">
  <h1>Favorite Things</h1>
  <ul>
  <c:forEach items="${requestScope.favoriteThings}" var="thing">
  <li><c:out value="${thing}"/></li>
  </c:forEach>
</c:if>
```

In order to check the length of our array, we have to use a new tagging library with a prefix of `fn`. We can then use the `<c:forEach>` tag to loop through the array.

## Why This Matters

JSTL improves the readability of our view logic. JSTL is generally favored when compared to inline Java.
