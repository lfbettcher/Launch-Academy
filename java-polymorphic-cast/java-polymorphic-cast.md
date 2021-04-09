You've been tasked with designing the credit reel system for Launch's hot new movie: "Launchers in Space".

## Getting Started

```no-highlight
et get java-polymorphic-cast
cd java-polymorphic-cast
idea .
```

## Building Our Classes

### Create a CastMember Class

- The `CastMember` class should have `firstName`, `lastName`, and `role` attributes that are private to the instance
- Implement a method `getCreditLine()` so that the code below results in the correlating output

```java
CastMember castMember = new CastMember("Maxwell", "Jenkins", "Will Robinson");
System.out.println(castMember.getCreditLine());
```

```no-highlight
Will Robinson - Maxwell Jenkins
```

### Create a TeamMember Class

- Refactor `CastMember` to inherit from a base class `TeamMember`
- The `TeamMember` should manage `firstName` and `lastName`

### Create a Producer Class

- Create a `Producer` class that inherits from `TeamMember`
- Implement a method `getCreditLine()` so that the code below results in the correlating output

```java
Producer producer = new Producer("Neil Marshall");
System.out.println(producer.getCreditLine());
```

```no-highlight
Producer - Neil Marshall
```

## Building our Credits

We have supplied a `CreditReelRunner` class for you to complete. Add appropriate `CastMember` instances and `Producer` instances so that the program outputs the following

```no-highlight
Maureen Robinson - Molly Parker
John Robinson - Toby Stephens
Will Robinson - Maxwell Jenkins
Producer - Neil Marshall
Producer - Zack Estrin
```
