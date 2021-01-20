The JavaScript language is unique in that it was created specifically to be run in our web browser. In fact, you will often see unique aspects of JavaScript that reflect this browser based environment. The first of which is the many ways we can actually run JavaScript. JavaScript can be executed in your browser's console, from within an HTML script tag, as an asset provided alongside your HTML, or even in online REPLs. In order to start learning about the intricacies of the JavaScript language, we'll take a look at each way of executing JavaScript.

## Learning Goals

- Execute JavaScript code "on the client" (e.g.- in the browser).
- Serve up JavaScript code to the client via script tags or as an additional asset file.
- Execute JavaScript code in an online REPL program.
- [NodeJS][node-js] allows you to run JavaScript code from the command line using the Chrome V8 JavaScript engine.

### Running JavaScript in the Console

One of the easiest ways to run JavaScript is in the Developer Tools Console that comes with most web browsers such as Mozilla Firefox and Google Chrome.

To begin, open up a Google Chrome window. From here, you can type `cmd + option + j` to open your Developer Tools console. Alternatively, you can right click on the webpage, select "Inspect" from the menu, and select "Console" from the tabs that appear.

Once you have the console open, type in this example code:

```no-highlight
alert('Hello, Student.');
```

![Running code within the JavaScript console](https://s3.amazonaws.com/horizon-production/images/javascript-hello-student.png)

When you type `return`, you should see an alert pop up that says "Hello, Student." Congratulations! You just executed JavaScript code. The console provides an environment for us to run our JavaScript code, and will often be our playground for running plain JavaScript programs.

We can immediately see the power of the JavaScript language just from this one example: any one person that has access to a web browser can run JavaScript code. No other configuration is required. This is just one of the reasons why JavaScript is such a ubiquitous language.

### Running JavaScript Inline, in a Single HTML File

For very simple examples, like the `'Hello, Student.'` example above, you can also place the code in a single HTML file.

```no-highlight
et get running-javascript
cd running-javascript
touch index.html
code index.html
```

Now, copy and paste this code into that file:

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>JavaScript</title>
  </head>

  <body>
    <h1>Learn JavaScript!</h1>
    <p>This JavaScript code will cause an alert box to appear.</p>
    <script type="text/javascript">
      // write JavaScript within the 'script' tags
      alert('Hello, Student.')
    </script>
  </body>
</html>
```

The above syntax is mostly HTML. If you don't know any HTML, don't fret — we'll cover it in the near future. For now, just know that JavaScript can be run within an HTML file if surrounded by two `<script>` tags. We generally add this to the bottom of an HTML file's `<body>` tags.

Executing the example can be done by opening this particular `index.html` file with your browser. If you open your iTerm to this working directory, you should be able to type `open index.html` to have it open by your default browser. Alternatively, you can open the file via the browser interface with: "File > Open > then, navigate to `index.html`".

We can also make an edit to our existing code by refreshing the page. Change the line `alert('Hello, Student.');` to something like `alert('I can do JavaScript things.');`, or something else of your choosing. In this case, `alert` is a function built into JavaScript that allows us to send a message in the form of a popup box.

In order to see your new message, refresh the page. The best way to do so is by typing `Command + Shift + R` to hard refresh the page when in Google Chrome. This will clear our old code and run the code that you have directly from your JS file. A standard refresh can also be using with `Command + R`, but sometimes our old code will be used from our browser's caching system, so `Command + Shift + R` is the optimal route for ensuring we're always running the latest version of our program. You can also use `ctrl+shift+r` on a Windows machine.

The downside here is that all of our HTML and JS code is lumped together! There must be a better way to organize this.

### Running JavaScript in a Project Folder

Let's iterate on the example above by separating our concerns. In this case, we will separate our JavaScript code from our HTML.

Let's move any code that we have right now into its own folder. Create a folder within your `challenges` folder, named after the **project**. In this case, `js-alert-message`. Next, navigate to this folder by changing directories. Then use the terminal to run `touch index.html` and `touch main.js` to create two new files in the directory.

```no-highlight
challenges
└── js-alert-message
    ├── index.html
    └── main.js
```

HTML File:

```html
<!DOCTYPE html>
<!-- index.html -->
<html>
  <head>
    <meta charset="utf-8" />
    <title>JavaScript</title>
  </head>

  <body>
    <h1>Learn JavaScript!</h1>
    <p>My JavaScript code will cause an alert box to appear.</p>
    <script type="text/javascript" src="main.js"></script>
    <!-- load javascript -->
  </body>
</html>
```

JavaScript file:

```javascript
// main.js
alert('Hello, Student.')
```

While in the `js-alert-message` folder at your terminal, type `open index.html` to load up these files in your browser once more.

So what has changed? While nothing has changed functionally, we've tidied up a bit. All of our JavaScript code is kept in `main.js` now. We used the `open` terminal command to run HTML in our browser. The JavaScript code is loaded when our browser sees the line `<script type="text/javascript" src="main.js"></script>`. In this case, our browser knows to look for the relative file `main.js`, and then, load its JavaScript code in the same place as our HTML code. As a result, our HTML text is displayed, and if we have our browser console open, our message should be displayed as well.

Constructing code examples using a **project folder** structure is our preferred way of writing and running JavaScript code. As developers, we like to keep things neat and organized. Separating our HTML markup from our code is a great first step in that direciton.

Many of the lessons you retrieve with `et get` will be presented this way.

### Running JavaScript with an Online REPL

Websites such as [CodeSandbox][code-sandbox], [JSFiddle][js-fiddle] and [replit][repl.it] are great places to try out code examples within your browser. They also provide a great mechanism for sharing examples with others. You'll often encounter other developers using REPL websites to demonstrate JavaScript programs they wrote.

**REPL** stand for "Read, Eval, Print, Loop." REPL sites allow you to run coding languages right inside of your webpage. Many even allow you the ability to run Ruby, Python, etc. code, as you may have seen. Using these tools, we can run various programming languages without having to configure a local development environment on our machine.

The downside of these sites is that your code is stored remotely on their web application (and sometimes not at all).

We recommend keeping track of your own programs on your machine to better track your work.

### Running JavaScript using Node.js

We'll discuss running JavaScript code using Node in later lessons. For now, we will be primarily running our JavaScript code **in the browser**. It is important to note here, though, that the advent of NodeJS has transformed the way that developers use JavaScript, and it is often an essential tool for running larger JavaScript applications.

### Summary

In this lesson, we covered the various ways to run JavaScript code. Saving code examples in **project folders** and then separating our code in distinct and separate files is our preferred way of developing.

[rubber_duck]: https://en.wikipedia.org/wiki/Rubber_duck_debugging
[js-fiddle]: https://jsfiddle.net/
[repl.it]: https://repl.it/languages/javascript
[code-sandbox]: https://codesandbox.io/
[node-js]: https://nodejs.org/
