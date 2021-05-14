So far, we have been building our Spring applications under the assumption that our users will behave nicely. As we've learned in previous units, we must defensively handle for the unexpected.

One key area is in the defense again Garbage-in, Garbage-out. Remember that we can impose JPA validations to ensure the data that users supply meets our standards. In this lesson, we'll put these validations to work for our forms-based applications.

## Getting Started

```no-highlight
et get java-spring-validations
cd java-spring-validations
idea .
```

## Learning Goals

- Validate user input in a forms-based application

## Validation in a Forms-based Application

We have provided an implementation of our App Ideas application from your previous database lesson. Take a moment to familiarize yourself with controller, repository, and model. Let's first study the `@PostMapping` endpoint.

```java
@PostMapping
public String create(@ModelAttribute Idea idea, Model model) {
  ideaRepository.save(idea);
  return "redirect:/ideas/new";
}
```

Right now, this method assumes that what information the user provides will be valid. If we have any JPA validations, they will be ignored. While this will work 90% of the time, end users that do not enter a title will receive a 500 error because of the `NOT NULL` database constraints on the `name` field. Naturally, this is something we want to fix.

### Adding Validations to Our Entity

Let's start by adding a validation to our `Idea` class.

```java
@NotBlank
@Column(name="name", nullable=false)
private String name;
```

Recall that this JPA validation will ensure that our `name` field contains valid text. This particular validation will trim the user's input to ensure that something is entered. For example, if a user enters all spaces for the `name` field, this validation will result in a validation error.

### Updating Our Controller to Handle Validation

```java
@PostMapping
public String create(@ModelAttribute @Valid Idea idea, BindingResult bindingResult, Model model) {
  if(bindingResult.hasErrors()) {
    return "ideas/new";
  }
  else {
    ideaRepository.save(idea);
    return "redirect:/ideas/new";
  }
}
```

There are a few key changes to the makeup of this controller endpoint that we can observe:

- We use the `@Valid` annotation to indicate that we want to validate the `idea` instance prior to it being passed to this request handler. This will check the data against our JPA validations to ensure the user has supplied the appropriately formatting information.
- We use a second argument, the `BindingResult` to effectively store the results of that validation.
- We can then use that `BindingResult` to control flow. If validation fails and there are errors present, we re-render the form and provide the user with an opportunity to fix the issues with their input. If validation succeeds, we persist the idea in the database and redirect the user so that they can enter another `Idea`.

### Showing Errors on Our Form

In the case where validation fails, we re-render the form. It would be useful to inform the end user what the issues with their input are, so they can fix them and resubmit.

To do so, we can use `th:errors` in our form to display these errors.

```html
<!DOCTYPE html>
<html>
  <head>
    <title>Add a New Idea</title>
  </head>
  <body>
    <h1>Add a New Idea</h1>
    <form action="/ideas" method="POST" th:object="${idea}">
      <div>
        <label for="name">Name</label>
        <input type="text" id="name" th:field="*{name}" />
        <p th:errors="*{name}"></p>
      </div>
      <div>
        <label for="description">Description</label>
        <textarea id="description" th:field="*{description}"></textarea>
        <p th:errors="*{description}"></p>
      </div>
      <div><input type="submit" value="Add Idea" /></div>
    </form>
  </body>
</html>
```

When the form gets re-rendered in the event of validation failure, we'll see the relevant error message for the `name` if we leave it blank.

## Validation in an API-based Application

Let's imagine we have an API for our app ideas. We can similarly manage validations, but indicate that the client's API request does not meet our data validation requirements. Generally, we do this by responding with a 422 status code, which semantically means "Unprocessable Entity".

```java
@PostMapping("/api/v1/ideas")
public ResponseEntity create(@Valid @RequestBody Idea idea, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    }
    else {
      return new ResponseEntity<Idea>(ideaRepository.save(idea), HttpStatus.CREATED);
    }
  }
```

A `ResponseEntity` is what we use in Spring to represent the abstraction of an HTTP Response. In this case, we can specify a generic to indicate what type of object we're returning as part of the response body. In the first `ResponseEntity` (when validation fails), we return a list of validation errors, and indicate that we want to respond with the 422 status code. In the `else` branch, we respond with the serialized version of the newly created `Idea` and with a 201 status code, which signals to the HTTP client that the object was created successfully.
We'll cover this in more detail later in the week.

## Why This Matters

If you let garbage data into your system, you can expect garbage to come out. It can also cause unexpected behavior which can have significant impact on your applications.
