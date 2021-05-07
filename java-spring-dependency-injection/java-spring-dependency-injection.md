Now that we have some context, we'll explore dependency injection and the Inversion of Control (IoC) Container in Spring.

## Getting Started

```no-highlight
et get java-spring-dependency-injection
cd java-spring-dependency-injection
idea .
```

## Learning Goals

- Justify the need for dependency injection
- Leverage the `@Autowired` annotation on a setter
- Leverage the `@Autowired` annotation on a constructor
- Discuss the drawbacks of setting `@Autowired` on a private field

## Laying Out Our Problem

We are building an ecommerce store, and there are often multiple ways we'd like to express our billing addresses.

There's the one line:

```no-highlight
77 Summer Street, 7th Floor, Boston, MA, 02110
```

...and there's the multiple lines that the post office likes:

```no-highlight
77 Summer Street
7th Floor
Boston, MA 02110
```

Both of these examples require the same data (via our supplied `BillingAddress` class), but the display is very different. Let's take a look at the supplied class.

```java
package com.launchacademy.diexample.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillingAddress {
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String postalCode;
}
```

Here, we've used lombok to define getters and setters for our `BillingAddress` class. We also rely on lombok to define our default constructor.

Now, why can't we implement something like this below?

```java
public String toFormattedString(Boolean multipleLines) {
  if(multipleLines) {
    String[] strings = {address1, address2, city + ", " + state + " " + postalCode};
    return String.join("\n", strings);
  }
  else {
    String[] strings = {address1, address2, city, state, postalCode};
    return String.join(",", strings);
  }
}
```

While this works computationally, this isn't the best design. Mainly, it violates the Single Responsibility Principle. We want each class in our system to do one thing well. Here, our `BillingAddress` class has taken on multiple responsibilities. Instead of just managing billing address information, it is now concerned with managing how we display it. Given that there are variants in how we format the address, there's opportunity for a better design here.

Let's create a separate class called `SingleLineAddressFormatter`.

```java
package com.launchacademy.diexample.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class SingleLineAddressFormatter {
  public String formatAddress(BillingAddress address) {
    String[] strings = {
      address.getAddress1(),
      address.getAddress2(),
      address.getCity(),
      address.getState(),
      address.getPostalCode()
    };
    return String.join(",", strings);
  }
}
```

This class now has one job: outputting addresses a single line string.

Let's make use of this in a `CommandLineRunner`. We'll put this in the `initializers` namespace, and we'll name it `CompanyAddressNotice`.

```java
package com.launchacademy.diexample.initializers;

import com.launchacademy.diexample.models.BillingAddress;
import com.launchacademy.diexample.models.SingleLineAddressFormatter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CompanyAddressNotice implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    BillingAddress companyAddress = new BillingAddress();
    companyAddress.setAddress1("77 Summer Street");
    companyAddress.setAddress2("7th Floor");
    companyAddress.setCity("Boston");
    companyAddress.setState("MA");
    companyAddress.setPostalCode("02110");

    SingleLineAddressFormatter formatter = new SingleLineAddressFormatter();

    System.out.println("The Company Address is: \n" + formatter.formatAddress(companyAddress));
  }
}
```

With this in place, when we run `spring-boot:run`, the final lines of output should display:

```no-highlight
The Company Address is:
77 Summer Street,7th Floor,Boston,MA,02110
```

This is great, but thankfully, Spring provides us with an alternative. As we developed it above, we have to instantiate our `formatter`. Dependency injection is a design pattern we can use so that our callers do not need to know the construction details for the instances of objects it is composed of. This cleans up our interfaces and can simplify our code.

With Spring's implementation of dependency injection, we do not have to call the constructor of a Spring component. Classes annotated with `@Service`, `@Repository`, `@Controller`, or `@Component` are considered Spring components. These classes will be registered and managed by Spring's Inversion of Control (IoC) container.

## Our Autowiring Options

To improve our implementation, our first option is to **autowire** the formatter.

```java
package com.launchacademy.diexample.initializers;

import com.launchacademy.diexample.models.BillingAddress;
import com.launchacademy.diexample.models.SingleLineAddressFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CompanyAddressNotice implements CommandLineRunner {
  private SingleLineAddressFormatter formatter;

  @Autowired
  public CompanyAddressNotice(SingleLineAddressFormatter formatter) {
    this.formatter = formatter;
  }

  @Override
  public void run(String... args) throws Exception {
    BillingAddress companyAddress = new BillingAddress();
    companyAddress.setAddress1("77 Summer Street");
    companyAddress.setAddress2("7th Floor");
    companyAddress.setCity("Boston");
    companyAddress.setState("MA");
    companyAddress.setPostalCode("02110");

    System.out.println("The Company Address is: \n" + formatter.formatAddress(companyAddress));
  }
}
```

Here, we used an Autowired Constructor to instantiate our `formatter`. Notice that we did not have to construct it in our `run` command. Instead, it's passed in to the constructor here via the `Autowired` annotation. On startup, the IoC container will instantiate and pass in an instance of `SingleLineAddressFormatter`.

An alternate option to autowiring our constructor is to autowire a setter. This gives us a functionally equivalent result.

```java
package com.launchacademy.diexample.initializers;

import com.launchacademy.diexample.models.BillingAddress;
import com.launchacademy.diexample.models.SingleLineAddressFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CompanyAddressNotice implements CommandLineRunner {
  private SingleLineAddressFormatter formatter;

  @Autowired
  public void setFormatter(SingleLineAddressFormatter formatter) {
    this.formatter = formatter;
  }

  @Override
  public void run(String... args) throws Exception {
    BillingAddress companyAddress = new BillingAddress();
    companyAddress.setAddress1("77 Summer Street");
    companyAddress.setAddress2("7th Floor");
    companyAddress.setCity("Boston");
    companyAddress.setState("MA");
    companyAddress.setPostalCode("02110");

    System.out.println("The Company Address is: \n" + formatter.formatAddress(companyAddress));
  }
}
```

This is the preferred method of autowiring if we are only dealing with one or a few components that we wish to autowire.

It's worth noting that there is a third, but less than ideal, autowiring strategy. We can autowire the field.

```java
@Autowired
private SingleLineAddressFormatter formatter;
```

Although it is the most convenient way, there are many drawbacks to this approach. Most importantly, it heavily couples your code to Spring's implementation of dependency injection, whereas the original two do not infer implementation details. Autowiring via private field should be avoided where possible.

In all autowiring scenarios, that's still quite a bit of code to achieve the same, functionally equivalent result. But, as we know happens with software, requirements often change. The product manager instead wants to use the Multi-Line variant to display the company's address on application startup. Let's build in an assumption that we may want to change up the formatter from time to time.

Let's first define a central interface to relate our `SingleLine` and `MultipleLine` address formatters.

```java
package com.launchacademy.diexample.models;

public interface AddressFormatter {
  public String formatAddress(BillingAddress address);
}
```

We can then update our `SingleLineAddressFormatter` to implement this interface.

```java
package com.launchacademy.diexample.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class SingleLineAddressFormatter implements AddressFormatter {
  @Override
  public String formatAddress(BillingAddress address) {
    String[] strings = {
      address.getAddress1(),
      address.getAddress2(),
      address.getCity(),
      address.getState(),
      address.getPostalCode()
    };
    return String.join(",", strings);
  }
}
```

We can then write our `MultipleLineAddressFormatter`.

```java
package com.launchacademy.diexample.models;

import org.springframework.stereotype.Component;

@Component
public class MultipleLineAddressFormatter implements AddressFormatter {

  @Override
  public String formatAddress(BillingAddress address) {
    String[] strings = {
      address.getAddress1(),
      address.getAddress2(),
      address.getCity() + ", " + address.getState() + " " + address.getPostalCode()};
    return String.join("\n", strings);
  }
}
```

So now, the application has two options - a single line formatter and a multiple line formatter. Let's update our `CommandLineRunner` to autowire by interface to better generalize our approach.

```java
package com.launchacademy.diexample.initializers;

import com.launchacademy.diexample.models.AddressFormatter;
import com.launchacademy.diexample.models.BillingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CompanyAddressNotice implements CommandLineRunner {
  private AddressFormatter formatter;

  @Autowired
  public void setFormatter(AddressFormatter formatter) {
    this.formatter = formatter;
  }

  @Override
  public void run(String... args) throws Exception {
    BillingAddress companyAddress = new BillingAddress();
    companyAddress.setAddress1("77 Summer Street");
    companyAddress.setAddress2("7th Floor");
    companyAddress.setCity("Boston");
    companyAddress.setState("MA");
    companyAddress.setPostalCode("02110");

    System.out.println("The Company Address is: \n" + formatter.formatAddress(companyAddress));
  }
}
```

When we do this, Spring gets confused when we attempt to run the application.

```no-highlight
***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of method setFormatter in com.launchacademy.diexample.initializers.CompanyAddressNotice required a single bean, but 2 were found:
	- multipleLineAddressFormatter: defined ...
	- singleLineAddressFormatter: defined ...

Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
```

While the options are great, we need to instruct Spring which one to use. We can annotate our `MultipleLineAddressFormatter` class as `@Primary` so that it takes precedence.

```java
@Component
@Primary
public class MultipleLineAddressFormatter implements AddressFormatter {

  @Override
  public String formatAddress(BillingAddress address) {
    String[] strings = {
        address.getAddress1(),
        address.getAddress2(),
        address.getCity() + ", " + address.getState() + " " + address.getPostalCode()};
    return String.join("\n", strings);
  }
}
```

Once that's in place, we can see the output below:

```no-highlight
The Company Address is:
77 Summer Street
7th Floor
Boston, MA 02110
```

## Why This Matters

The IoC Container is a central aspect of the Spring framework. Being able to use it to your own benefit will make you a more capable user of the framework.

## In Summary

Use autowiring to provide flexibility and to decouple your client code from the construction of their dependencies.
