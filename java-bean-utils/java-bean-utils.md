BeanUtils is a library provided by Apache commons. It includes utility classes that can be used for populating beans. Ahead, we will be learning about the BeanUtils library and how it can be used to populate Java Beans.

## Learning Goals  

- Install and configure BeanUtils via Maven  
- Define a simple bean and set a property of an instance using setSimpleProperty  
- Define a Bean class and populate an instance using BeanUtils  
- Preview HTTP POST and Describe how that data can be reflected as a HashMap  

## Getting Started With BeanUtils

In order to use BeanUtils, you need to add the following Maven dependency:

```xml
    <dependency>
     <groupId>commons-beanutils</groupId>
     <artifactId>commons-beanutils</artifactId>
     <version>1.9.3</version>
    </dependency>
```
This will add the JAR files related to the BeanUtils library to your project.

## Using BeanUtils

As mentioned earlier, the BeanUtils library has utility classes that can be used for populating beans. Let us now learn these classes can be used.

### Defining a Bean

Let us first start by defining a Bean.
```java
import java.io.Serializable;

public class Book implements Serializable {
	private String name;
	private String author;
    public Book() {

	}
	  // getters and setters
    }
```
This code specifies a `Book` bean. It has fields corresponding to `name` and  `author`. It also has getters and setters corresponding to the fields which are left out for the sake of brevity.

### Setting an individual property

The BeanUtils library has a class called **PropertyUtils**. It has static methods that can be used to set individual bean properties.
The following code demonstrates how this method can be used:
```java
Book book = new Book();
PropertyUtils.setSimpleProperty(book, "name", "Head First Java");
```

This code invokes the `PropertyUtils.setSimpleProperty` method. This method updates the specified property (`name` ) of the specified bean (`book`) with the specified value (**"Head First Java"**). So once this code is executed, the `book` object will have its `name` field set to `"Head First Java"`

### Populating the whole bean

The BeanUtils library also has a class called `BeanUtils`.  It can be used to populate all the properties in a bean using key-value pairs defined in a `java.util.Map`.

Suppose we have a `Map` as follows:
```java
Map<String,String> dataMap = new HashMap<>();
dataMap.put("name", "Head First Java");
dataMap.put("author", "Kathy Sierra");
```
This code defines a `Map` called `dataMap` and adds some key-value pairs to it.  Note that the keys specified (**name** and **author**) correspond to the properties in the **Book** class.

We can now populate a `Book` bean using this `dataMap` as follows:

```java
Book book = new Book();
BeanUtils.populate(book, dataMap);
```

This code invokes the `BeanUtils.populate` method with `book`  and `dataMap`.  The `BeanUtils.populate` method matches each key in `dataMap` to the property name in the `book` bean and populates it with the corresponding value from `dataMap`. Thus, when this code is executed, the value **"Head First Java"** will be assigned to the `name` field of `book`, and the value **"Kathy Sierra"** will be assigned to the `author` field of `book`.

## HTTP POST Review

We will soon see how to obtain data from an input form via an HTTP POST request. Such form data typically consists of parameter names and their values. Thus, this data can be represented as key-value pairs in a Map.
For example, suppose we have a form that allows a user to enter book information like its **name** and **author**. Suppose the user enters the value **"book1"** corresponding to name and **"author1"** corresponding to author).
This can be represented as a `Map` as follows: `{name:book1,author:author1}`

The BeanUtils class can then be used to populate an underlying bean based on the form data in this Map. We will see an example of this further on.

## Why This Matters

As is clear from what we've seen above, the main benefit of the BeanUtils library is that it avoids having to hardcode bean properties.  
The `PropertyUtils` class (which can be used to set an individual property of a bean), allows externalizing property names into a configuration file. Thus, we no longer need to compile the code each time a property name changes.
The `BeanUtils` class goes a step further. By allowing populating all the properties of a bean using a Map, it reduces the number of lines of code and makes the code compact. Secondly, the code does not need to be modified when bean properties are added/removed, or renamed.


## In Summary

To summarize, BeanUtils is a simple library that can be used to populate a bean. In order to use it, its Maven dependency needs to be added to the project POM file. It provides the PropertyUtils and BeanUtils classes which can then be used to populate beans as required.
