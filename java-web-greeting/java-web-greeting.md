## Getting Started

```no-highlight
et get java-web-greeting
cd java-web-greeting
idea .
```

## Core User Stories

### Basic Greeting with Optional Last Name

```no-highlight
As a Java web application user
I want to be greeted
So that I feel welcome
```

Acceptance Criteria:

- Your application should greet the user with an HTML based "Hello".
- Modify your servlet and JSP to support a user's last name parameter in the query string.
- If both a `first_name` and `last_name` is supplied, output the user's full name
- If only the `last_name` is supplied, show the default greeting

### Specify Language

```no-highlight
As an international Java web application user
I want to be greeted in my native tongue
So that I can feel welcome
```

Acceptance Criteria:

- If I specify a query string parameter of `lang` with the value `fr`, the application should greet me with a `Bonjour`
- If I specify a query string parameter of `lang` with the value `es`, the application should greet me with a `Hola`
- If I specify a query string parameter of `lang` with the value `en`, the application should greet me with a `Hello`
- If I don't specify a `lang` querystring parameter, it should default to the behavior of specifying a `lang` value of `en`
