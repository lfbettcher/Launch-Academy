We have briefly touched on the concepts of abstraction and information hiding along with the `public`, `private`, and `protected` keywords. As a matter of Java practice, we do not expose state directly to the outside world. To get and set state, we must do so through instance methods defined in the class. This practice is know as **encapsulation**. Since it is such an important topic in Java, let's walk through a specific example to drive the point home.

## Learning Goals

- Define encapsulation
- Outline a practical use case for encapsulation and information hiding

## Getting Started

```no-highlight
et get java-encapsulation
cd java-encapsulation
idea .
```

## Our Banking Application

Let's imagine we're building a banking application, and we're heading up the main architecture of the system. We're currently designing our `BankAccount` class. Here's our initial implementation:

### Protecting Our Metaphor / Abstraction

```java
public class BankAccount {
  public float balance;

  BankAccount(float initialDeposit) {
    this.balance = initialDeposit;
  }
}
```

The team writing the ATM software to interact with this class writes code like this:

```java
BankAccount savings = new BankAccount(5000);
float authorizedWithdrawal = 50000;
savings.balance = savings.balance - authorizedWithdrawal;
```

This type of logic would put the bank out of business in a flash! Instead of manipulating the state of our `BankAccount` `balance` directly, we need **behavior** to ensure the bank account has the funds available for withdrawals. Our class needs to better represent the abstraction of a `BankAccount`. If the customer doesn't have enough cash in their account to support their requested withdrawal, the bank should not authorize the withdrawal.

**Encapsulation** is at the core of how we can architect our `BankAccount` class more effectively. With encapsulation, we can hide the balance field from outside callers and require the ATM team to use the methods we design. Put differently, we need to **encapsulate** the balance field so that it can't be arbitrarily set by outside callers.

```java
//BankAccount.java
public class BankAccount {
  private float balance;
  private static final float MINIMUM_BALANCE = 50;


  BankAccount(float initialDeposit) {
    this.balance = initialDeposit;
  }

  public boolean withdraw(float withdrawalAmount) {
    if (balance - withdrawalAmount > MINIMUM_BALANCE) {
      balance = balance - withdrawalAmount;
      System.out.println("\nAfter withdrawing " + withdrawalAmount + " your new balance is $" + balance);
      return true;
    } else {
      return false;
    }
  }
}
```

In your `Main` file update to reflect the following:

```java
public class Main {

  public static void main(String[] args) {
    BankAccount exampleAccount = new BankAccount(100);
    exampleAccount.withdraw(10);
  }
}

```

When you run Main.java after adding the code to `BankAccount.java` you should see an output like:

```no-highlight
/Library/Java/JavaVirtualMachines/jdk-11.0.8.jdk/Contents/Home/bin/java "-javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=64049:/Applications/IntelliJ IDEA CE.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /Users/arkhamsrazor/launch/java-encapsulation/target/classes Main
After withdrawing 10.0 your new balance is $90.0

Process finished with exit code 0
```

This is an improvement. We've made the balance private so that outside callers cannot access it. The only way we can currently affect change on the bank balance is through the withdrawal mutator method. This also allows us to modify the behavior on the basis of business requirements. For example, the bank may impose a minimum balance on their customers. Because we've practiced sound encapsulation, changing our withdrawal behavior is centralized in our `BankAccount` class.

### Encapsulation for Side Effects

This makes sense for setters, but encapsulation can be important for retrieving state as well. For example, the FDIC, the government agency that regulates banks, may have regulations on logging who checks bank balances.

If our `balance` is just a public field or attribute, we can't impose any kind of additional functionality other than directly accessing the balance value. With Java's interpretation of encapsulation, we must access our balance field's value through a **getter**.

```java
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankAccount {

  private static final float MINIMUM_BALANCE = 50;
  private float balance;

  BankAccount(float initialDeposit) {
    this.balance = initialDeposit;
  }

  public boolean withdraw(float withdrawalAmount) {
    if (balance - withdrawalAmount > MINIMUM_BALANCE) {
      balance = balance - withdrawalAmount;
      System.out.println("\nAfter withdrawing " + withdrawalAmount + " your new balance is $" + balance);
      return true;
    } else {
      return false;
    }
  }

  public float getBalance() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    System.out.println("\nBALANCE ACCESSED AT " + dateFormat.format(date));
    return balance;
  }
}
```

In order for side effects to take place when we retrieve a value from an instance, the value must be accessed via a method. If we access the data directly, we can't introduce side effects of accessing that data.

Updating our Main.java as follows will show us the current balance without having to run the withdrawal, then it will show the withdrawal, then finally it will show the amount after the withdrawal using the `getBalance` method.

```java
public class Main {

  public static void main(String[] args) {
    BankAccount exampleAccount = new BankAccount(100);
    System.out.println(exampleAccount.getBalance());
    exampleAccount.withdraw(10);
    System.out.println(exampleAccount.getBalance());
  }
}
```

This will give us something like the following output:

```no-highlight
/Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home/bin/java "-javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=64115:/Applications/IntelliJ IDEA CE.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /Users/arkhamsrazor/launch/java-encapsulation/target/classes Main

BALANCE ACCESSED AT 2020/08/20 16:22:33
100.0

 After withdrawing 10.0 your new balance is 90.0

BALANCE ACCESSED AT 2020/08/13 16:22:33
90.0

Process finished with exit code 0

```

## Why This Matters

**Encapsulation** is a core concept in object oriented programming. By properly combining state and behavior, we can build more flexible systems that better convey our intended abstraction.

## In Summary

Java has a strict interpretation of Object Oriented Programming. The Java perspective on encapsulation is that data must be accessed and mutated by methods. That way, your abstraction can be better protected, and you can impose functionality around accessing relevant data in your instances.
