## Getting Started

```no-highlight
et get java-spring-web-greeter
cd java-spring-web-greeter
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

- Your application should greet the user with an HTML based "Hello from Spring, Dr Strange" when navigating to `/greetings/by-query-string?firstName=Dr&lastName=Strange`.
- Modify your controller to support a user's first name and last name as parameters in the query string in the `/greetings/by-query-string` endpoint.
- If both a `firstName` and `lastName` is supplied, output the user's full name
- If only the `firstName` is supplied, output the "Hello from Spring, `<firstName>`"
- If only the `lastName` is supplied, show the default greeting with no name displayed.

### Specify Language

```no-highlight
As an international Java web application user
I want to be greeted in my native tongue
So that I can feel welcome
```

Acceptance Criteria:

- I can navigate to `/greetings/by-language/<lang>` and get greeted
- If I specify a path parameter of `lang` with the value `fr`, the application should greet me with a `Bonjour`
- If I specify a path parameter of `lang` with the value `es`, the application should greet me with a `Hola`
- If I specify a path parameter of `lang` with the value `en`, the application should greet me with a `Hello`
