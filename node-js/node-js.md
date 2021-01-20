### Learning Goals

- Understand what Node.js is
- Install Node.js
- Learn how to run code and debug in Node.js

### Getting Started

```no-highlight
et get node-js
cd node-js
code .
```

## What is Node.js?

[Node.js (usually just called Node)][node-js] is _a JavaScript runtime environment that provides us with a means of developing backend (server-side) Web applications in JavaScript_. Let's break that idea down into its parts:

- A _runtime environment_ is a bunch of code whose primary purpose is to run other code. In this case, we're saying that Node.js is a piece of software that lets us run JavaScript code.
- We'll learn more about terms like "backend" and "server-side" in the future, but for now, think of "frontend" code as code that is run by the browser and "backend" code as code that is run in your terminal instead.

So, in simpler terms, Node.js is software that lets us run JavaScript code without requiring a browser.

### Installing `nvm` and Node

**Check to see if you already have nvm and Node installed.** You can check on both OSX and Windows by running:

```sh
nvm current
```

You should see `v14.8.0` (or higher) appear in response. If not, navigate to either of the below articles to get set up!
- [Installing Node for OSX](https://learn.launchacademy.com/lessons/installing-node-osx)
- [Installing Node for Windows](https://learn.launchacademy.com/lessons/installing-node-windows)

## Running Code

Up to this point, you've always run your JavaScript code in your browser -- either by typing directly into the dev tools console or by opening an html file with a `<script>` that points to a JavaScript file, then observing the output in the dev tools console. In this section, we'll learn how to use Node instead!

### Using the Node REPL

Sometimes, we just want a place where we can quickly type out some code and run it on the fly. Previously, you may have opened up your browser's dev tools and typed JavaScript into the console, seeing it execute every time you press `enter`.

Node offers us a `REPL` (read–eval–print loop, pronounced _REE-puhl_) -- think of this as a real-time sandbox for your code. To enter the REPL, simply type `node` in the command line and press `enter`. You should see a prompt, `>`, indicating that the REPL is waiting for you to type in some code (similar to how we can type code into our browser console and hit `enter`).

Try typing the following:

```no-highlight
const myString = 'hi there'
// undefined
```

We can retrieve that value, as long as we haven't yet quit out of the REPL:

```no-highlight
myString
// 'hi there'
```

Try running some basic arithmetic or `console.log`s!

To quit out of the REPL, type `.exit` or press `control`+`c` twice.

### Running Files

The REPL is handy, but we're going to spend 99% of our time using Node to run files, not stray snippets of code. The directory you're currently in contains a `main.js` file with a `console.log()` statement in it -- let's run that code using Node, rather than the browser!

```no-highlight
node main.js
// Hello, world!
```

And that's it!

**Moving forward, unless we're specifically using JavaScript to modify HTML, we'll use Node to run JavaScript code instead on an `index.html` file.**

### Debugging

Node also provides us with a way to hit debuggers within our code, just as we've done in the past using our browser. In fact, our debugging process will involve our browser!

You've been provided with `buggyCode.js`, which contains some basic code with a debugger in the middle.

We need to tell Node that we want to _inspect_ our code, breaking (pausing) at the beginning of the file and also at any breakpoints (`debugger`s). In your terminal, run the below and see this output:

```no-highlight
node --inspect-brk buggyCode.js
// Debugger listening on ws://127.0.0.1:9229/469b3685-752c-4238-978f-07c61ddbea37
// For help, see: https://nodejs.org/en/docs/inspector
```

It looks like our code tried to run, but then....froze! Our code paused to tell us that it is listening for debuggers, but we need to be able to tell it to go ahead and run.

One way we can listen for debuggers is provided to us by Chrome. In your browser, navigate to `chrome://inspect`. You'll see a link to `Open Dedicated DevTools for Node` -- click it, and you'll see our old friend, the Chrome Dev Tools, pop up. In it, you may notice that our code is paused, prior to running, on the first line of our program: `const myName = 'Julia Oppenheimer'`. It is waiting for us to hit "play" before continuing to run.

When we hit play, our program will execute, and pause once again when it hits our `debugger` on line 4! We can now use the dev tools to take a look at the values of variables and generally do everything you're used to being able to do with Chrome dev tools.

Once you've finished exploring your code, type `control`+`c` in your terminal to return to the command prompt.

Your browser isn't the only way to debug your code; you can also configure VSCode's debugging tools to listen for Node debuggers. However, this is a bit more complicated, so we'll stick with Chrome Dev Tools for now.

> Note: Our code paused at the top of the entire program because we used the flag `--inspect-brk`, which asks our program to pause at the top so that we can confirm our inspector's debugger is attached properly before running our code. It is worth mentioning that you can always change this flag to `--inspect` (without the `-brk`), and it will still hit `debugger`s, but will no longer pause at the top of the program! We at Launch prefer `--inspect-brk` because it allows us to **first** make sure our inspector is _working_, and **then** make sure we're hitting the `debugger`s that we actually put in our code.

### In Summary

In this article, we've explored Node.js: what it is and how to use it. You should now have Node.js installed on your system and be able to use it to run and debug JavaScript code.

[node-js]: https://nodejs.org/en/
