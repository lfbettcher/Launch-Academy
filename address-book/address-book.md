## Introduction

While you go through Launch, you're going to need some help from some well-known people in Computer Science! So, we're going to build a simple address book, so that you can look up email addresses for your list of contacts.

## Getting Started

```no-highlight
et get address-book
cd address-book
code .
```

## Instructions

```javascript
let addressBook = {
  "Mark Zuckerberg": "markymark@example.com",
  "Grace Hopper": "foundABug@example.com",
  "Alan Turing": "alanTheMachine@example.com",
  "Sister Mary Kenneth Keller": "theOGDoctor@example.com",
  "Ada Lovelace": "kindaCalledThisWholeComputerThing@example.com",
  "Bill Gates": "noBlueScreens@example.com"
}
```

- In `code.js`, we have supplied you with this super-secret list of emails for A-list tech celebrenerds.   
-Write a program that continuously asks (via `prompt`) for a name until the user supplies `exit` or cancels a prompt.   
For each name entered, output their correlating email address.   
- If the name is not in the address book, output that their name was not found, and prompt the user for another name.

### Non-core, additional practice

- Make the program so that it is not case sensitive when a name is entered. IE, if I enter, "mark zuckerberg", the program should still outputs "markymark@example.com"
- If I enter a name that is not found in the address book, instead of outputing that the name was not found, prompt the user to see if they would like to add the name to the address book. If they opt to do so, prompt them for the name's email address, and add it to `addressBook`.
  - Remember you can add a key/value pair by using the name of the object `addressBook[name] = emailToAdd`
