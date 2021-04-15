# Java with JSON and Jackson

JavaScript Object Notation (JSON) is the format of choice for data on the web. When we connect to web-based Application Programming Interfaces (APIs), we're often communicating with JSON.

So, to be an effective software developer, we must know how to both read and write JSON. We'll explore that together in this article.

## Learning Goals

- Read a JSON file using Java and the Jackson Parser
- Write a JSON file using Java and the Jackson Parser

## Getting Started

```no-highlight
et get java-with-json
cd java-with-json
idea .
```

## Jackson Parser - Reading JSON

It's important to note that Java core does not contain objects and methods to support our effort of parsing JSON. So, most Java developers use a third party implementation for its JSON support. We will use a [package called `Jackson`][jackson-repo].

### Add Jackson to Your Project

Begin by adding framework support to your project. To do so right-click your project root folder and select `Add Framework Support`. In the resulting dialog box select Maven and click ok. This will generate a `pom.xml` (ensure you click "Enable Auto Import" when the dialog box asks. If you don't, you can always go to the Maven tool bar and import from there.). Add the following dependencies between `<version>1.0-SNAPSHOT</version>` and `<properties>` as shown below (This is what the finished version of your `pom.xml` should look like).

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>groupId</groupId>
    <artifactId>java-with-json</artifactId>
    <version>1.0-SNAPSHOT</version>


    <dependencies>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.10.3</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.10.3</version>
        </dependency>
    </dependencies>

    <properties>
      <java.version>11</java.version>
      <maven.compiler.source>${java.version}</maven.compiler.source>
      <maven.compiler.target>${java.version}</maven.compiler.target>
    </properties>

</project>

```

After Maven initializes you will need to move `TwitterReader.java` into the `src/main/java` folder for the program to work.

### Working with the Jackson Library

Jackson has two ways of approaching JSON. It can use either a "parser" or an "object map". We will dig into both options.

In this first section, you can read through the code without running it anywhere. In the second, we will provide you with runnable code.

The Jackson _parser_ is created by a **factory** object which is provided by Jackson. To create the parser, create the factory, then call the createParser() method.

```Java
File jsonFile = new File("input.json");
JsonFactory factory = new JsonFactory();
JsonParser parser = factory.createParser(jsonFile);
```

This will create the _parser_ and associate a file and parse it into **tokens**, or pieces. It will consider each "part" of our JSON as a token: the opening `{`, each individual _key_ and _value_, and the closing `}`. You can think of the parser like a cursor. It processes the file sequentially starting with the `{` that starts our JSON. When we call `nextToken()` we advance the cursor to the next item of interest. For example, our second token would be the "id" attribute, and our third token would be the id of `1125687077`. Like a cursor, our parser keeps track of where we currently are in the file, and doesn't move on until we tell it to by calling `nextToken()` again.

Let's start by simply learning to navigate around our JSON. First, take a look at our `input.json` file. It has the following JSON:

```json
{
  "id": 1125687077,
  "text": "@stroughtonsmith You need to add a \"Favorites\" tab to TC/iPhone. Like what TwitterFon did. I can't WAIT for your Twitter App!! :) Any ETA?",
  "fromUserId": 855523,
  "toUserId": 815309,
  "languageCode": "en"
}
```

Then, in our `TwitterReader` `main` function, we can add the below (remember to add necessary imports!):

```Java
File jsonFile = new File("input.json");
JsonFactory factory = new JsonFactory();
JsonParser parser = factory.createParser(jsonFile);

//skip past the beginning `{`
parser.nextToken();

while(parser.nextToken() != JsonToken.END_OBJECT) {
  System.out.println(parser.getCurrentName());
}
```

Here, we've started a `while` loop that says "as long as the next token isn't the end of the JSON, print out the name of the token you're focused on". As mentioned, our second token will be the `"id"` property. So the first time we get inside of the `while` loop, we'll be looking at "id".

We use the `getCurrentName()` method provided to us by the parser. This method asks our parser to hand us the _property_ (or _key_) related to the token we're looking at.

If we run the code above, we'll see that each property is outputted twice. That's because the property itself is a token, and its respective value is another.

So, let's refine our approach a bit further to make sure we print the property and then its value:

```Java
File jsonFile = new File("input.json");
JsonFactory factory = new JsonFactory();
JsonParser parser = factory.createParser(jsonFile);

// skip past the beginning `{`
parser.nextToken();

// loop until we encounter the terminating `}`
while(parser.nextToken() !=  JsonToken.END_OBJECT) {
  // get the name of the property
  String field = parser.getCurrentName();

  // advance to the next "token", which is the value
  JsonToken value = parser.nextToken();

  // output the combination of the field and the value's text
  System.out.println(field + ":" + parser.getText());
}
```

Above, we use the `getCurrentName()` method to get the property name. Then, we advance to the related _value_ by calling `parser.nextToken()`, and call `parser.getText()` to get the **text** of the value (rather than its related name, as we were doing before).

It's worth noting that if we're focused on a key already (`"id"`), `getCurrentName()` and `getText()` will return the same thing, since `getText()` would simply say "give me the text of the exact token I'm looking at". But if we're focused on a value (`1125687077`), `getCurrentName()` will give us "id" (the related property), whereas `getText()` will give us the text of the value itself.

As we iterate through each token, we can start to translate our JSON file into a hashmap, by assigning the token to your object. We can update our code to do this as shown below.

```Java
File jsonFile = new File("input.json");
JsonFactory factory = new JsonFactory();
JsonParser parser = factory.createParser(jsonFile);
Map<String,Object> myHashmap = new HashMap<String, Object>();

// skip past the beginning `{`
parser.nextToken();

// loop until we encounter the terminating `}`
while(parser.nextToken() !=  JsonToken.END_OBJECT) {
  // get the name of the property
  String field = parser.getCurrentName();

  // advance to the next "token", which is the value
  JsonToken value = parser.nextToken();
  // store the value text
  String valueText = parser.getText();

  // add to our hashmap
  myHashmap.put(field, valueText);
}
```

Slowly but surely, we're turning our JSON file into an iterable Java data structure.

So far, our code is great, so long as our value is always a string. Even in our simple example, though, we have three numbers. Thankfully, Jackson has a tool to help us.

```java
File jsonFile = new File("input.json");
JsonFactory factory = new JsonFactory();
JsonParser parser = factory.createParser(jsonFile);

// skip past the beginning `{`
parser.nextToken();

// loop until we encounter the terminating `}`
while(parser.nextToken() !=  JsonToken.END_OBJECT) {
  String field = parser.getCurrentName();

  // advance to the next "token", which is the value
  JsonToken value = parser.nextToken();

  // output the combination of the field and the value
  if(value.isNumeric()) {
    System.out.println(field + " (number): "  + parser.getLongValue());
  }
  else {
    System.out.println(field + ":" + parser.getText());
  }
}
```

We can use `getLongValue()` to access the numeric value. A list of all available methods can be found [here][jackson-docs].

## Jackson Object Mapper

The solution discussed above is a fairly painful way of getting the JSON into a workable Java data structure. Thankfully, Jackson provides an easier way. In order to make it work, we will need to load the [DataMapper Jar][datamapper-jar] and the [Annotations Jar][annotations-jar] into our codebase the same way we did for Jackson Core.

Once installed and configured, we can use an [ObjectMapper][objectmapper]. You can pass in a _Map_ or a Plain Old Java Object (_POJO_). The _readValue()_ and _writeValue()_ will pull the properties out of the object and populate the map.

```Java
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TwitterReader {

  public static void main(String[] args) throws IOException {
    try {
      //read in the file
      byte[] mapData = Files.readAllBytes(Paths.get("input.json"));

      //instantiate the `Map` that will hold the keys and values
      Map myMap = new HashMap<String, String>();

      //instantiate the object mapper with helpful indentation
      ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

      //populate the HashMap with the JSON contents
      myMap = objectMapper.readValue(mapData, HashMap.class);

      //output the map
      System.out.println("Map is: " + myMap);
    } catch (IOException io ) {
      io.printStackTrace();
    }
  }
}
```

Rather than have to iterate manually through all tokens like we did with our parser, we do a bit of setup (connecting to our file and telling the `ObjectMapper` how we want to format), and then the `ObjectMapper` does a lot of the heavy-lifting for us in terms of reading our JSON file and putting it into `Map` form. When we call `objectMapper.readValue()`, we hand it two arguments: the JSON data we want it to map, and the object type that we want it to map to (here, a HashMap object).

### ObjectMapper "enable output format"

As shown above, you can call _enable(SerializationFeature.INDENT_OUTPUT)_ to enable the "pretty print" option for objectMapper. _SerializationFeature.INDENT_OUTPUT_ is a constant used to indicate the indentation that you see with pretty printing. This will make any resulting JSON we write easy on the eyes.

## Dealing with Arrays of JSON

Often, JSON payloads are in the form of an `Array`. So, if we wanted to read multiple tweets for example, we could build an `ArrayList` of `HashMap` instances:

```java
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterReader {

  public static void main(String[] args) throws IOException {
    try {
      //read in the file
      byte[] mapData = Files.readAllBytes(Paths.get("tweets.json"));

      //instantiate the `Map` that will hold the keys and values
      List<HashMap<String, String>> tweets = new ArrayList<HashMap<String, String>>();

      //instantiate the object mapper with helpful indentation
      ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

      //populate the HashMap with the JSON contents
      tweets = objectMapper.readValue(mapData, ArrayList.class);

      //output the map
      System.out.println("List is: " + tweets);
    } catch (IOException io ) {
      io.printStackTrace();
    }
  }
}
```

The concept is the same. What we map to the `objectMapper` is different. In this case we map to an `ArrayList` that has `HashMap` elements.

## Jackson Generator - Creating JSON

Previously, we covered how we can use Jackson to _read_ JSON files and turn them into a Java data structure -- but what about _writing_ JSON? Jackson has a class called [JsonGenerator][json-generator] for this purpose. It is used to create JSON. When you instantiate the generator, you supply a `File`, `OutputStream`, or `Writer` (in this case, we are using a file). As we start writing, we add the _Start Object_, then go through the properties. At the end, we close the generator. Before we get started, import `JSONGenerator`, `JSONEncoder` and `JSONFactory` via the Jackson package. You will also need the `io.File` package.

```Java
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import java.io.File;
```

Add the following code inside your `main` method below the `try...catch` block from above.

### Generator `useDefaultPrettyPrinter()`

You can include a call to _useDefaultPrettyPrinter()_ to format the output with indentation. It is helpful for debugging or looking at the output.

```Java
  JsonFactory jsonF = new JsonFactory();
  JsonGenerator jg = jsonF.createGenerator(new File("result.json"), JsonEncoding.UTF8);

  jg.useDefaultPrettyPrinter();
  jg.writeStartObject();
  jg.writeNumberField("id", 1125687997);
  jg.writeStringField("text", "@stroughtonsmith You need to add a \"Favorites\" tab to TC/iPhone. Like what TwitterFon did. I can't WAIT for your Twitter App!! :) Any ETA?");
  jg.writeNumberField("fromUserId", 855523);
  jg.writeNumberField("toUserId", 815309);
  jg.writeStringField("langugeCode", "en");
  jg.writeEndObject();
  jg.close();
```

You'll notice that we're using a number of different methods here to write to our JSON, including `writeNumberField()` and `writeStringField()`. Each of these expect a property and a value to add to our JSON.

Now when you run `TwitterReader` you will see a file called `result.json` appear in your project. Open it to verify that the information was stored correctly.

### Summary

While Java doesn't come with built in JSON functionality, `Jackson` gives us all the tools we need.

[annotations-jar]: https://mvnrepository.com/artifact/org.jetbrains/annotations/16.0.1
[datamapper-jar]: https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-mapper-asl
[jackson-docs]: https://fasterxml.github.io/jackson-core/javadoc/2.7/com/fasterxml/jackson/core/JsonParser.html
[jackson-repo]: https://github.com/FasterXML/jackson-core
[json-generator]: https://fasterxml.github.io/jackson-core/javadoc/2.8/com/fasterxml/jackson/core/JsonGenerator.html?is-external=true
[objectmapper]: https://fasterxml.github.io/jackson-databind/javadoc/2.9/com/fasterxml/jackson/databind/ObjectMapper.html
