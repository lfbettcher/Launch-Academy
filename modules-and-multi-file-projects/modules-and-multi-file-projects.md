Let's learn how to subdivide our applications into multiple files using ESM modules.

### Learning Goals

- Understand what a module is and why they're useful
- Learn how to split up your program into multiple files using ESM modules
- Learn how to create a factory module for instantiating similar objects

### Getting Started

```no-highlight
et get modules-and-multi-file-projects
cd modules-and-multi-file-projects
code .
```

### Why This Matters

So far, we've written all our JavaScript code in a single file, but we want a more scalable solution that also helps us organize our code logically.

## What are Modules?

A dictionary definition of the word "module" is "a standardized, often interchangeable component of a system or construction that is designed for easy assembly or flexible use".[^1] In software engineering, a module is a section of code that we've extracted out from the rest of our code. A module contains the code needed for a specific functionality; to actually use that code, we import the module into other code files. Don't worry -- we'll be working through an example to clarify this point!

First, a note about module systems in JavaScript. There are two main ways to create a JavaScript module: CommonJS and ESM (**ES**2015 **m**odules). These two systems have some differences in execution that we don't need to concern ourselves with now. We'll be using ESM modules throughout this course for consistency and compatibility with popular frontend JavaScript frameworks. We have nonetheless included notes on the CommonJS syntax in this article to clarify code snippets you may encounter elsewhere.

ESM modules are supported in the most recent versions of Node.js; however, we need to add a `package.json` file that includes `"type": "module"` in our project directory (this has been provided for you). We'll introduce the `package.json` when we discuss package managers in an upcoming article!

If you're interested in learning more about the difference between CommonJS and ESM modules, check out [this blog post][javascript-module-systems-showdown]! Note, however, that ESM and browser/Node.js support for ESM is a rapidly changing topic, and some details in any online resource may be out of date. _In general, always look at publication date when reading about cutting-edge tech!_ Older resources can still be helpful for basic learning, but be sure to take the finer points with a grain of salt.

## Writing Modules

Open up the `main.js` file provided in this directory and add the following:

```javascript
// main.js
let catchphrase = 'Pop POP!'
let sayPopPop = () => {
  console.log(catchphrase)
}

sayPopPop()
sayPopPop()
```

Note: we've provided the file name in a comment at the top of the code block for clarity as you're working through the assignment; it is not a best practice to include the name of your file as the first line.

Now let's run this file in the terminal:

```no-highlight
 node main.js
 // Pop POP!
// Pop POP!
```

This program is pretty great as it stands. However, as our [_Community_][community-tv-wiki] catchphrase program grows, this one file will eventually contain too much code, and we will most likely run into _namespace collisions_, in which we unknowingly use the same variable name for different things. For example, the `catchphrase` variable acts as a private variable, meaning that it is only used by the `sayPopPop` function. We'd run into problems if we tried to implement a new catchphrase function `sayNewCatchphrase` -- it would be sensible to also name our _new_ catchphrase string `catchphrase`, but changing this variable would change the expected return value of the `sayPopPop` function as well. To solve this problem we can use modules and split the file into two files: `main.js` and `sayPopPop.js`.

Let's create that `sayPopPop.js` file by running `touch sayPopPop.js` in the terminal and adding the following to it:

```javascript
// sayPopPop.js
let catchphrase = 'Pop POP!'
let sayPopPop = () => {
  console.log(catchphrase)
}

export default sayPopPop
```

The `export default` statement is telling Node what part of this file we want to make available elsewhere; in this case, it's the `sayPopPop` function.

Now let's update our `main.js` file to pull in our new module:

```javascript
// main.js
import sayPopPop from './sayPopPop.js'

sayPopPop()
sayPopPop()
```

We're using an `import` statement to assign whatever is exported by `./sayPopPop.js` to the specified variable name, `sayPopPop`. Since `sayPopPop.js` is in the same folder as `main.js`, we prepend `./` before the name of the file. If we run `node main.js`, we get the same result as before.

If you got a `SyntaxError: Unexpected identifier` error highlighting `sayPopPop`, double-check that you're using Node 14.0.0 by running `nvm current`; if some other version number is displayed, use `nvm use 14.0.0` to switch to the correct version!

> Note: If we were using CommonJS, we would use `module.exports = sayPopPop` instead of `export default sayPopPop` and `let sayPopPop = require('./sayPopPop')` instead of `import sayPopPop from './sayPopPop.js'`. You won't need to use this syntax here, but it may be something you run into out in the wild in your code development!

For kicks, let's also add a `sayMyNameIsAlex` function using the same pattern we've just learned. This function should print `"My name is Alex!"` to the console.

Give it a try!

**Hint!**

```javascript
// sayMyNameIsAlex.js
let catchphrase = 'My name is Alex!'
let sayMyNameIsAlex = () => {
  console.log(catchphrase)
}

export default sayMyNameIsAlex
```

```javascript
// main.js

import sayPopPop from './sayPopPop.js'
import sayMyNameIsAlex from './sayMyNameIsAlex.js'

sayPopPop()
sayPopPop()

sayMyNameIsAlex()
sayMyNameIsAlex()
```

This refactoring has two major benefits:

- Our code is now much more readable for other developers who might end up working with it. If I'm debugging something in `sayMyNameIsAlex.js`, I don't have to wade through the clutter of the `sayPopPop` implementation. If I need to know, at a basic level, what `main.js` is doing, I don't have to dive into the implementation details of _either_ function, since they're abstracted into separate files and we've done such a great job naming our functions.
- We no longer have to worry about namespace collisions -- `myNameIsAlex` and `sayPopPop` can each have variables called `catchphrase` because each `catchphrase` is _encapsulated_ in a different module and cannot be accessed outside of that module.

Regarding the second point above, we can even declare `catchphrase` in `main.js` without affecting either module:

```javascript
// main.js

import sayPopPop from './sayPopPop.js'
import sayMyNameIsAlex from './sayMyNameIsAlex.js'
let catchphrase = 'This will not interfere with the output'

sayPopPop()
sayPopPop()

sayMyNameIsAlex()
sayMyNameIsAlex()
```

Running this code does not change our output.

## Using Modules as Factories

Our previous examples used modules to encapsulate very simple functions that just outputted result to the terminal. Let's take a look at another example, in which we use a module as a _factory_. A factory is a easy way to create a bunch of related things -- in our case, a bunch of objects with the same properties.

Let's say we want to create objects for various students at Greendale Community College. We could write

```javascript
let students = [
  {
    firstName: 'Annie',
    lastName: 'Edison',
    favoriteClass: 'Spanish 101',
    greet() {
      console.log(
        `${this.firstName} ${this.lastName} loves ${this.favoriteClass}`
      )
    }
  },
  {
    firstName: 'Jeff',
    lastName: 'Winger',
    favoriteClass: 'Intro to Pottery',
    greet() {
      console.log(
        `${this.firstName} ${this.lastName} loves ${this.favoriteClass}`
      )
    }
  }
]

students.forEach(student => {
  student.greet()
})
```

This is already a little tedious, but it would be even more so if we wanted to include many more students. Moreover, it would be easy to make a typo -- if we used `first` instead of `firstName` for one student, we wouldn't catch that error until our code broke. There must be a better way!

Let's create a function, `createNewStudent` that acts as a _factory_ by producing objects of the same structure both with different property values.

```javascript
let createNewStudent = (firstName, lastName, favoriteClass) => {
  let student = {
    firstName,
    lastName,
    favoriteClass,
    greet() {
      console.log(
        `${this.firstName} ${this.lastName} loves ${this.favoriteClass}`
      )
    }
  }

  return student
}
```

Note that we're using property value shorthand to set the values of `firstName`, `lastName`, and `favoriteClass` in `student` -- if this feels unfamiliar, take a few minutes to review the curriculum on object destructuring.

We could put this function in `main.js`, but our finely honed developer instincts tell us that it would be better to move this into its own module, where we put any code related to creating new students. Let's do that now:

```javascript
// createNewStudent.js

let createNewStudent = (firstName, lastName, favoriteClass) => {
  let student = {
    firstName,
    lastName,
    favoriteClass,
    greet() {
      console.log(
        `${this.firstName} ${this.lastName} loves ${this.favoriteClass}`
      )
    }
  }

  return student
}

export default createNewStudent
```

Now, let's add to our `main.js` file:

```javascript
// main.js

import createNewStudent from './createNewStudent.js'

let annie = createNewStudent('Annie', 'Edison', 'Spanish 101')
let jeff = createNewStudent('Jeff', 'Winger', 'Intro to Pottery')

annie.greet()
jeff.greet()
```

Lookin' good! Our factory has made it much easier to create new objects, and putting that factory in a module kept our `main.js` nice and clean!

### Factories Within Factories

One last point: so far, we've imported our modules into `main.js`, but there's no reason why we can't import them into other files -- for example, we could have

```javascript
// createNewBook.js

let createNewBook = (title, author) => {
  let book = {
    title,
    author
  }

  return book
}

export default createNewBook
```

and also

```javascript
// createNewStudent.js

import createNewBook from './createNewBook.js'

let createNewStudent = (firstName, lastName, favoriteClass) => {
  let student = {
    firstName,
    lastName,
    favoriteClass,
    book: createNewBook('my book title', 'my book author'),
    greet() {
      console.log(
        `${this.firstName} ${this.lastName} loves ${this.favoriteClass}`
      )
    }
  }

  return student
}

export default createNewStudent
```

In the example above, we're using our book factory to create a book for every student created by our student factory (and, for some reason, giving every book the same name and author!). If we updated our code accordingly, we could run

```javascript
console.log(annie.book)
// { title: 'my book title', author: 'my book author' }
```

and see that we did indeed create a book object and assign it to the `book` property of our student object.

### In Summary

In this article, we talked briefly about what a module is and the different specifications for modules in JavaScript: CommonJS and ESM.

We then learned how to use ESM modules to split our code into several files. This has many advantages including reducing the of amount of code per file, hiding the implementation details of an interface, and avoiding namespace collisions.

Finally, we looked at an example of how we can use modules to create factories for our objects, saving us time and headache!

[javascript-module-systems-showdown]: https://auth0.com/blog/javascript-module-systems-showdown/
[community-tv-wiki]: "https://en.wikipedia.org/wiki/Community_(TV_series)"

[^1]: module. (n.d.) American Heritage® Dictionary of the English Language, Fifth Edition. (2011). Retrieved April 30 2020 from https://www.thefreedictionary.com/module
