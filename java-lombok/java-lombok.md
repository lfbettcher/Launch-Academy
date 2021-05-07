Creating boilerplate code over and over again becomes a tedious task. Luckily, there is a solution for this problem, called `Lombok`.

## Learning Goals

- Install Lombok and set up the IntelliJ plugin
- Use `@Getter` and `@Setter` to define readers and writers
- Use `@NoArgsConstructor` to define a bean's constructor
- Incorporate `@NonNull` to ensure no null values
- Use `@ToString` to generate the `toString()` method

## Install Lombok via Maven

**First of all, note that we will use different code blocks for examples here, but this is NOT a code-through article!**

To install `Lombok`, we must first add the Lombok dependency, under the `<dependencies>` block in our `pom.xml`.

```xml
<dependency>
  <groupId>org.projectLombok</groupId>
  <artifactId>Lombok</artifactId>
  <version>1.18.8</version>
  <scope>provided</scope>
</dependency>
```

And it is that easy! Once we have added the `Lombok` dependency, Maven will pull the `Lombok` library down into the project we have created.

## Install the Lombok Intellij plugin

One issue with `Lombok` is Intellij needs to understand the annotations we are going to use. This means we must also make use of a plugin for Intellij that understands what these annotations are.

Lucky for us the [Lombok Plugin][lombok-intellij-plugin] has become so popular that it is now installed by default with all IntelliJ versions > 2020.3.

If you want to view your plugins to confirm it is installed, go to `IntelliJIDEA -> preferences` on Mac or `File -> Settings` on PC. Select `plugins` and make sure `Installed` is highlighted at the top of the window. In the search bar type in `Lombok` and you will see that it is already installed for you! If you are on an older version of IntelliJ you will need to switch over to the `MarketPlace` tab and search for `Lombok` to install it.

The final step is to enable Intellij to read the annotations and process them within the current project.

Go back into your preferences/settings, and in the popup navigate to `Build, Execution, Deployment -> Compiler -> Annotation Processors`, and click the checkbox that says "Enable annotation processing". Click "apply" and "ok" at the bottom, and then we are finished. We can now start annotating with Lombok.

## Use of @Getter and @Setter to define readers and writers

Object oriented programming languages require us to create access methods to get, or set, private variables. Equipped with `Lombok`, this is no longer a manual process.

As shown below, a normal POJO (Plain Old Java Object) has numerous boilerplate methods to access the variable `ide`. We have to create both `getIde()` and `setIde()` to access our private variables.

```java
public class JetBrains {
  private String ide;

  public String getIde(){
    return ide;
  }

  public void setIde(String ide){
    this.ide = ide;
  }
}
```

This can become tedious every time we change or add a new variable. With `Lombok`, we can simplify this with the annotations `@Getter` and `@Setter`.

```java
@Getter
@Setter
public class JetBrains {
  private String ide;
}
```

Wow! This is much easier. It removes all the requirements of creating a `get` and a `set` function to access the private variable. It will add a getter and setter for each of our private variables automatically, so we would have access to `getIde()`, for example.

By adding `@Getter` and `@Setter`, we are telling `Lombok` that there is no need to create the boilerplate code, since it is always created in the same way every time. Instead, `Lombok` will allow us to use these methods and create them at build time.

Another way to use the `@Getter` and `@Setter` is at the individual variable level. For example:

```java
public class JetBrains {
  @Getter @Setter private String ide;
}
```

This gives more fine-grained control on each variable. Instead of generating the "getters" and "setters" for all attributes in the class, we can annotate the fields. This becomes extremely useful when we have variables which should be read or write only.

```java
public class JetBrains {
  @Getter @Setter private String ide;
  private Integer balance;

  public void setBalance(Integer balance){
    if(balance < 0){
      balance = 0;
    } else {
      this.balance = balance;
    }
  }
}
```

This gives us the ability to only use `Lombok` on the variables we want.

### Use of @NoArgsConstructor to define a bean's constructor

As we know, if we don't define any constructor in our Java classes, they will default to a no-argument constructor.

However, with `Lombok`, there are some additional functionalities built in for various constructor auto-creators. When we want to stick to a no-arg constructor, it's best to add a `@NoArgsConstructor` annotation so that `Lombok` knows what we're trying to do (and for documentation purposes). In different situations, we may use a `@RequiredArgsConstructor` or `@AllArgsConstructor`. `Lombok` has some really great documentation on our different options [here][lombok-args-docs], but we'll stick to a `@NoArgsConstructor` for our Beans.

```java
@NoArgsConstructor
public class JetBrains {
  private String ide;
}
```

### Use of @NonNull to ensure no null values

`Lombok` has even more tricks up its sleeves with the use of the `@NonNull` annotation. Time and time again, while we are writing code, we start to notice a pattern. We have null values being passed around, when they should contain actual values. This is where the annotation `@NonNull` shines. By using this annotation, we guarantee there will not be a null value present.

```java
public class JetBrains {
  private String ide;

  public void NonNullExample(String ide){
    if(ide == null){
      throw new NullPointerException("ide is null");
    }
    this.ide = ide;
  }
}
```

When we pass in the value `ide` above, we check to verify that `ide` is not null. The more we code, the more we start to notice the repetition. By using `@NonNull` we can save ourselves time and keystrokes.

```java
public class JetBrains {
  private String ide;

  public void NonNullExample(@NonNull String ide){
    this.ide = ide;
  }
}
```

Both examples above, are compiled into the same code when we run or build the program. But the bottom code does not require typing out an additional `if` statement. This not only save us keystrokes but also readability for the next person looking at our code.

### Use of @ToString to generate the toString method

When talking about `Lombok`, one of the most under-appreciated annotations is `@ToString`. The more we start to code object oriented projects, the more we start to realize that we need better logging. As our application increases with complexity, so too will our need for logging. The `@ToString` method keeps us from changing our `toString()` method anytime we add to our objects. Take this new object below (with another object defined inside), for example:

```java
public class ToStringExample {
  private String name;
  private Dog dog = new Dog("Clifford", "Big Red");
  private String[] tags;
  private int id;

  public ToStringExample(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public String toString() {
    return "ToStringExample(" + this.getName() + ", " + this.dog + ", " + Arrays.deepToString(this.tags) + ")";
  }

  public static class Dog {
    private String name;
    private String breed;

    public Dog(String name, String breed) {
      this.name = name;
      this.breed = breed;
    }

    @Override public String toString() {
      return "Dog(super=" + super.toString() + ", name=" + this.name + ", breed=" + this.breed + ")";
    }
  }
}
```

To add another variable to this class, we also have to edit our `toString()` method. On a massive scale, doing this over and over again, just slows us down and frustrates developers when they miss a space between commas. But, thanks to `Lombok`, we can automate this using the `@ToString` annotation. Here is an example after we have added `Lombok':

```java

@ToString(includeFieldNames = false)
public class ToStringExample {
  @Getter private String name;
  private Dog dog = new Dog("Clifford", "Big Red");
  private String[] tags;
  @ToString.Exclude private int id;

  public ToStringExample(String name) {
    this.name = name;
  }

  @ToString
  public static class Dog{
    private String name;
    private String breed;

    public Dog(String name, String breed) {
      this.name = name;
      this.breed = breed;
    }
  }
}
```

Pretty cool, right? One thing to notice in the POJO without `Lombok`, we specifically left out id from the `toString()` method. In `Lombok`, we use the annotation method `@ToString.Exclude`, and it will not be incorporated. The `@ToString` annotation creates a toString method in the following format, `ClassName(var1, var2, var3...)`.

Additionally, we're using `includeFieldNames = false` to not add field names into the `ToStringExample` string, but skipping it so the field names for `Dog` get added. Notice we also used @ToString on the embedded Dog class.  If we didn't, the `toString()` from the Object class would be used.  Lastly, the `tags` was not initialized in a constructor or setter, so we expect to get a null value.

If we were to output this `toString()` method now like:

```java
public static void main(String[] args) {
  System.out.println(new ToStringExample("Jason").toString());
}
```

This will end up outputting a string as shown below:

```sh
ToStringExample(Jason, ToStringExample.Dog(name=Clifford, breed=Big Red), null)
```

As you can see, the `private int id` field is not in the output, but the Dog string and field names of the dog are.

### In Summary

`Lombok` features allow us to simplify our life by creating better readability, saving time, and usability.

As we start to develop applications, saving keystrokes not only saves time, but also a lot of headache.

[lombok-args-docs]: https://projectlombok.org/features/constructor
[lombok-intellij-plugin]: https://github.com/mplushnikov/lombok-intellij-plugin
