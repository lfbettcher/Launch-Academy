When it comes to our data, we want to deploy multiple layers of defense against garbage-in, garbage-out.
Thankfully with the JPA and ORM, validations are a first-order concern.
In this article, we use bean validations to protect the integrity of our data.

## Getting Started

```no-highlight
createdb contacts
et get java-validations-and-the-jpa
cd java-validations-and-the-jpa
idea .
```

In Intellij, be sure to run `flyway:migrate` once you've properly imported the project. This should result in the creation of a `contacts` table.

## Learning Goals

- Explain the importance of duplicating schema constraints
- Install the validation library
- Define bean validations
- Exercise validations

## Observing Schema Constraints

If we view our initial migration as well as our mapped `Contact` class, we'll see that a number of fields are desginated as `NOT NULL`. While this schema-based protection ensures that a value will be supplied, specifying these constraints at such a low level can be limiting.

Just like we reflect our columns via Java fields, we can similarly map our data expectations and requirements. So, when it comes to specifying formats, whether fields are required or not, as well as uniqueness constraints, we can leverage the JPA's bean validations. First, we'll need to do some configuration.

## Installing the Validation Library

We'll need to add the validation library as a dependency in our `pom.xml`.

```xml
<dependencies>
  <dependency>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
  </dependency>
  <dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.4.2.Final</version>
  </dependency>
  <dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>7.0.0.Final</version>
  </dependency>
  <dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.2.5</version>
  </dependency>
  <dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.1.Final</version>
  </dependency>
  <dependency>
    <groupId>javax.el</groupId>
    <artifactId>javax.el-api</artifactId>
    <version>3.0.1-b06</version>
  </dependency>
  <dependency>
    <groupId>org.glassfish</groupId>
    <artifactId>javax.el</artifactId>
    <version>3.0.1-b12</version>
  </dependency>
</dependencies>
```

## What Happens Without Validations

Let's start with an example that we know will fail in our `Main` class. Be sure to add any necessary imports as well!

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.contacts");
EntityManager em = emf.createEntityManager();


Contact contact = new Contact();
em.getTransaction().begin();
em.persist(contact);
em.getTransaction().commit();
```

If we leave the implementation as is and attempt to save a record, Hibernate will provide us a `org.hibernate.PropertyValueException: not-null property references a null or transient value : Contact.email`. These exceptions are helpful in protecting the integrity of our data, but they do not assist the user with details as to what went wrong with their data entry. We need something a little more user friendly.

## Specifying Our First Validation

Just like we saw with our initial foray into ORM, we need to mirror these concerns in an object-oriented context. The JPA provides us with utilities to validate our data. So, to match up our schema constraints, we first need to introduce an annotation from the dependencies we just installed.
Let's add an annotation to our `firstName` field in our `Contact` class.

```java
@NotNull(message = "can't be blank")
@Column(name="first_name", nullable = false)
private String firstName;
```

Here, we're introducing *application logic* to validate that when we want to persist a `Contact` instance, we are requiring the `firstName` field to be populated. This is better than relying on the schema, because we can use software to better manage the correction of improperly formatted data.

Let's exercise this validation in our `Main` class.

```java
Contact contact = new Contact();
ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
Validator validator = factory.getValidator();
Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
for (ConstraintViolation<Contact> violation : violations) {
  System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
}
```

Cool, right? Our annotations can be combined to essentially ensure properties of our objects fulfill our requirements. Let's define some additional annotations in our `Contact` class.

## Exploring Additional Validations

```java
@NotNull(message = "can't be blank")
@Column(name="first_name", nullable = false)
private String firstName;

@NotNull(message = "can't be blank")
@Column(name="last_name", nullable = false)
private String lastName;

@NotNull
@Email
@Column(name="email", nullable = false)
private String email;

@NotNull
@Length(min=10)
@Column(name="phone_number", nullable = false)
private String phoneNumber;
```

If we run our `main` again, we'll see the following output.

```no-highlight
lastName: can't be blank
firstName: can't be blank
phoneNumber: must not be null
email: must not be null
```

Ok, so let's address these validation errors in our main class, prior to calling validation.

```Java
Contact contact = new Contact();
contact.setLastName("Smith");
contact.setFirstName("Sally");
contact.setEmail("user");
contact.setPhoneNumber("6975309");
ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
Validator validator = factory.getValidator();
Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
for (ConstraintViolation<Contact> violation : violations) {
  System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
}
```

When running now, we should get the below feedback:

```no-highlight
email: must be a well-formed email address
phoneNumber: length must be between 10 and 2147483647
```

While we have improved the `contact` instances disposition, it still does not meet our standards. Thankfully, JPA validations provide us with more robust validations than what we see in our schema.

Because we specified an `@email` annotation, the JPA will require a properly formatted email. Because we specified the `@length` annotation, we require a phone number that has at least 10 characters in it.

So, if we adjust our data to conform to the standards, there won't be any violations.

```java
Contact contact = new Contact();
contact.setLastName("Smith");
contact.setFirstName("Sally");
contact.setEmail("user@example.com");
contact.setPhoneNumber("6176975309");
ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
Validator validator = factory.getValidator();
Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
if(violations.size() > 0) {
  for (ConstraintViolation<Contact> violation : violations) {
    System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
  }
}
else {
  System.out.println("Ready to persist");
  EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.contacts");
  EntityManager em = emf.createEntityManager();
  em.getTransaction().begin();
  em.persist(contact);
  em.getTransaction().commit();
}
```

For a full list of validations available to you as part of the JPA, you can refer to the [documentation][validation-docs].

## Why This Matters

Schema validations only take us so far. With an ORM like Hibernate, we can expand our protections against bad data.

[validation-docs]: https://javaee.github.io/javaee-spec/javadocs/javax/validation/constraints/package-summary.html
