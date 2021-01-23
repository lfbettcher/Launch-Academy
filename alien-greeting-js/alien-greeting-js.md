The ship's crew leader has asked you to write a program that automatically greets aliens when they meet them on adventures.

## Getting Started

```no-highlight
et get alien-greeting-js
cd alien-greeting-js
open index.html
code .
```

### Instructions Part 1

Write a function `greet` that takes an alien's `name` as a single, required argument. It should output a greeting for the supplied name.

Sample Output:

```no-highlight
Hi ZIM!
```

### Instructions Part 2

Modify your method so that it uses a random greeting among the following options below.

- `Hi [name]!`
- `Yo [name]!`
- `Hey [name]!`
- `Howdy [name]!`

Sample output:

```no-highlight
Hey ZIM!
```

### Instructions Part 3

Modify your method so that it takes an optional, second argument, the user's language. If the second argument is not specified, the method should default to the English greeting.

The method should support:

- English: "Hi [name]!"
- Spanish: "Hola [name]!"
- Italian: "Ciao [name]!"
- Irken: "DOOM de doom [name]!"
