We've learned that the most common problem in programming is...us! It's easy to make mistakes when coding, and to inappropriately assume that something is working when it's not. Let's use the computer as a tool to ensure code works the way we expect.

In this article, we'll introduce the concept of automated testing, and a popular JavaScript testing framework, `jest`.

## Learning Goals

- Study the syntax of a jest test
- Practice the test-driven development sequence of red, green, refactor
- Consider the benefits of writing automated tests prior to writing implementation

## Following Along

To follow along with this article, be sure to run the following:

```no-highlight
et get function-testing-tdd-and-javascript
cd function-testing-tdd-and-javascript
yarn install
```

## Code to Ensure Our Code Works

Wat? As you may have experienced, it's easy for us as programmers to be confident in code that is otherwise flawed. With the right tools, we can use computing power to validate our understanding of how our code works.

Going back to our factory metaphor, most functions assume that certain inputs (arguments) will yield a certain output (a return value).

As we've worked through defining functions, we've mentally verified our **expectations** of what our code should do.

For example, let's go way back to our `greet` function.

```javascript
const greet = (name) => {
  return `Hi, ${name}`
}
```

Mentally, we imagine that if we call `greet('Sam')`, then the return value should in fact be `Hi, Sam`.

Doing this exercise in our minds really helps, especially for our more simple functions. However, as our programs grow in complexity, we need a better way. Let's use code to ensure that when we call the `greet` function defined above with a single argument `"Sam"`, that it does what we expect.

In order to do that, we need an automated test framework. For NodeJS, we currently prefer Jest. Let's get it running.

## Install Jest

First, we'll install the `jest` test framework as a development dependency.

```no-highlight
yarn add jest --dev
```

[Jest][jest] is an emerging test framework popularized by the developers of ReactJS. It's fast, well-featured, and easy to get up and running.

We will also need to add `babel-jest`, `@babel/core`, and `@babel/preset-env`:

```no-highlight
yarn add --dev babel-jest @babel/core @babel/preset-env
```

[`babel-jest`][babel-jest] and the related packages allow us to automatically compile our JavaScript code so that Jest can understand our ESM `import`/`export` statements. We'll learn more about Babel in future lessons. Babel requires some configuration, so let's add that file as well. Create a file called `babel.config.json` in your project folder, and input the below code:

```js
{
  "presets": [
    [
      "@babel/preset-env",
      {
        "targets": {
          "node": "current"
        }
      }
    ]
  ]
}
```

In our `package.json`, we'll also want to add two utility scripts to make running our automated tests really easy. Modify the `package.json` to include a `scripts` property so that it looks like this:

```json
{
  "name": "function-testing-tdd-and-javascript",
  "version": "1.0.0",
  "main": "index.js",
  "license": "MIT",
  "type": "module",
  "scripts": {
    "test": "./node_modules/.bin/jest",
    "test:debug": "node --inspect-brk ./node_modules/.bin/jest --runInBand"
  },
  "devDependencies": {
    "@babel/core": "^7.10.4",
    "@babel/preset-env": "^7.10.4",
    "babel-jest": "^26.1.0",
    "eslint": "^5.16.0",
    "eslint-config-airbnb": "^17.1.0",
    "eslint-plugin-import": "^2.17.1",
    "eslint-plugin-jest": "^22.4.1",
    "eslint-plugin-jsx-a11y": "^6.2.1",
    "eslint-plugin-prettier": "^3.0.1",
    "eslint-plugin-react": "^7.12.4",
    "jest": "^26.0.1",
    "prettier": "^1.17.0"
  }
}
```

Adding this `scripts` directive gives us the ability to run the following to execute our Jest tests:

```no-highlight
yarn run test
```

It also allows us to run the below in order to hit debuggers while running tests:

```no-highligh
yarn run test:debug
```

As we write our automated tests, these commands will execute our tests.

## Writing Our First Tests

Let's create a new directory in the `src` directory.

```no-highlight
mkdir src/__tests__
```

This directory will be where we put our tests for the `greet` function.

It's time to create our test file. Our first test file with be `src/__tests__/greet.test.js`. When it comes to automated testing with jest, all of our tests should be located inside a `__tests__` directory, and their filenames should end with `.test.js`. This is a default convention defined by the test framework.

```no-highlight
touch src/__tests__/greet.test.js
```

Open this newly created file in your editor, and ensure it has the following contents:

```javascript
import greet from "../greet.js"

describe("greet function", () => {
  it("says Hi to Scott", () => {
    expect(greet("Scott")).toEqual("Hi, Scott")
  })
})
```

Run `yarn run test`. If you've followed the steps above correctly, you should see something like:

```no-highlight
yarn run v1.13.0
$ ./node_modules/.bin/jest
 PASS  src/__tests__/greet.test.js
  greet function
    ✓ says Hi to Scott (4ms)

Test Suites: 1 passed, 1 total
Tests:       1 passed, 1 total
Snapshots:   0 total
Time:        2.346s
Ran all test suites.
✨  Done in 3.90s.
```

We just wrote code that basically runs code, and ensures it works the way in which we expect. Let's break it down.

### Loading Our greet function

```javascript
import greet from "../greet.js"
```

Because of where we are in the filesystem, we can load our `greet` function so that we can call it.

```javascript
describe("says Hi to Scott", () => {})
```

The `describe` function call defines the functionality we're testing. Notice that the `describe` function takes two arguments - a string and a function.

The first argument is purely something for the developer of the test. It just documents the functionality we're testing. What we put in this argument is entirely arbitrary, and up to us as the developer. The second argument is where we'll basically ensure our function works the way in which we expect.

```javascript
it("says Hi to Scott", () => {})
```

This `it` function defines one way or scenario in how we wish the `greet` function to behave. Like `describe`, the first argument is arbitrary, and up to us as the developer of the test to define. Basically, we're looking to document what we expect the function to do given certain circumstances. In this case, we're saying that if we intend to greet `"Scott"`, that it will say `"Hi"` to `"Scott"`.

The second argument, however, is more concrete and important. It's here we're we'll **assert** how we expect the function to behave.

```javascript
expect(greet("Scott")).toEqual("Hi, Scott")
```

When we run our automated tests via `yarn run test`, jest will execute this code and basically ensure that the return value of `greet("Scott")` will be `"Hi, Scott"`. It's our way of writing code that ensures the `greet` function works the way in which we expect when we call it with the single argument `"Scott"`.

Let's modify our function in `src/greet.js` to be a bit more formal.

```javascript
const greet = (name) => {
  return `Hello, ${name}`
}

export default greet
```

If we run our test again, we see a test failure! Basically, our automated tests are saying that we _expect_ our function to use the word `Hi`, but our formality adjustment to `Hello` violated our expectation defined in the test.

```no-highlight
yarn run v1.13.0
$ ./node_modules/.bin/jest
 FAIL  src/__tests__/greet.test.js
  greet function
    ✕ says Hi to Scott (7ms)

  ● greet function › says Hi to Scott

    expect(received).toEqual(expected)

    Difference:

    - Expected
    + Received

    - Hi, Scott
    + Hello, Scott

      3 | describe("greet function", () => {
      4 |   it("says Hi to Scott", () => {
    > 5 |     expect(greet("Scott")).toEqual("Hi, Scott")
        |                            ^
      6 |   })
      7 | })

      at Object.toEqual (src/__tests__/greet.test.js:5:28)

Test Suites: 1 failed, 1 total
Tests:       1 failed, 1 total
Snapshots:   0 total
Time:        2.224s
Ran all test suites.
error Command failed with exit code 1.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.
```

At this point, we have two options. We can edit our test to adopt the more formal salutation, or we can update our implementation to use the `"Hi"` greeting. No matter which option we choose, the key point here is that we have code that will ensure the way in which we've built the `greet` function works as we intended. Let's change our expectation in `src/__tests__/greet.test.js` to reflect our more formal implementation:

```javascript
import greet from "../greet.js"

describe("greet function", () => {
  it("says Hello to Scott", () => {
    expect(greet("Scott")).toEqual("Hello, Scott")
  })
})
```

When we run `yarn run test` again, the test passes! Our test and the way in which we've written the `greet` function match up. As we change the program, our tests will ensure we didn't inadvertenly break our expectations about how `greet` should work. As we write applications that are more complicated, having this confidence will allow us to move faster. If we've inadvertently broken something, we'll know when we run `yarn run test`.

### Writing Our Tests First

Having confidence that our code works is great, but we can actually write tests to _drive_ the implementation of our functions. We call this process **T**est **D**riven **D**evelopment (TDD). Through this process, we'll write a failing test that will require us to write a new function, and the test will guide the implementation of the functionality we're trying to achieve.

Let's illustrate this with a very simple example. Let's imagine that we want to write a function that doubles the provided number.

Instead of rushing to create the function, let's write our test first. Let's create a new file `src/__tests__/double.test.js` with the following contents:

```javascript
describe("doubling a number", () => {
  it("doubles 2 resulting in 4", () => {
    expect(double(2)).toEqual(4)
  })
})
```

When we run `yarn run test`, we'll see the following output:

```no-highlight
yarn run v1.13.0
$ ./node_modules/.bin/jest
 FAIL  src/__tests__/double.test.js
  ● doubling a number › doubles 2 resulting in 4

    ReferenceError: double is not defined

      1 | describe("doubling a number", () => {
      2 |   it("doubles 2 resulting in 4", () => {
    > 3 |     expect(double(2)).toEqual(4)
        |     ^
      4 |   })
      5 | })

      at Object.expect (src/__tests__/double.test.js:3:5)

 PASS  src/__tests__/greet.test.js

Test Suites: 1 failed, 1 passed, 2 total
Tests:       1 failed, 1 passed, 2 total
Snapshots:   0 total
Time:        1.904s
Ran all test suites.
error Command failed with exit code 1.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.
```

Although jest gives us a very intimidating `FAIL` and a lot of red, this is all part of the red, green, refactor process that is Test Driven Development! We've written an **expectation** of how we want a function to behave. The test is telling us that we first need to define the `double` function. This makes sense, since we haven't yet written it. The beauty of following the TDD process is that our tests will tell us what to do next.

So, let's define our `double` function, by creating the appropriate file and requiring it in our tests. Let's create the `double` fucntion in `src/double.js`

```javascript
const double = (num) => {}

export default double
```

We also need to require this function in our test. Here's what `src/__tests__/double.test.js` should look like:

```javascript
import double from "../double.js"

describe("doubling a number", () => {
  it("doubles 2 resulting in 4", () => {
    expect(double(2)).toEqual(4)
  })
})
```

```no-highlight
yarn run v1.13.0
$ ./node_modules/.bin/jest
 FAIL  src/__tests__/double.test.js
  ● doubling a number › doubles 2 resulting in 4

    expect(received).toEqual(expected)

    Expected: 4
    Received: undefined

      2 | describe("doubling a number", () => {
      3 |   it("doubles 2 resulting in 4", () => {
    > 4 |     expect(double(2)).toEqual(4)
        |                       ^
      5 |   })
      6 | })

      at Object.toEqual (src/__tests__/double.test.js:4:23)

 PASS  src/__tests__/greet.test.js

Test Suites: 1 failed, 1 passed, 2 total
Tests:       1 failed, 1 passed, 2 total
Snapshots:   0 total
Time:        2.085s
Ran all test suites.
error Command failed with exit code 1.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.
```

Interesting! Though our test is still failing, we received some new feedback. Where previously we hadn't yet defined the `double` function, here it's telling us that when we call `double`, we're not getting the return value we expect. Again, this is all part of the TDD process. Now, we can implement the function. Let's update `src/double.js` to reflect what we want `double` to do.

```javascript
const double = (num) => {
  return num * 2
}

export default double
```

If we run `yarn run test` again, we'll see that the test passes. The function is now working the way in which we expect. We've gone from _red_ to _green_, in that we've built a failing test and subsequently built out the function implementation that made it _green_. Writing the test first allows us to think about the design of our function before we implement it.

We've previously discussed how functions can be a black box. We don't necessarily care how this `double` function does it, but we expect that when we pass `2` as its only argument, we should get `4` back. Likewise, if we pass `5` in, we should get `10`. For good measure, and to ensure the function works we expect for multiple use cases let's add that additional behavior.

Let's modify our test file to consider this.

```javascript
import double from "../double.js"

describe("doubling a number", () => {
  it("doubles 2 resulting in 4", () => {
    expect(double(2)).toEqual(4)
  })

  it("doubles 5 resulting in 10", () => {
    expect(double(5)).toEqual(10)
  })
})
```

Since we've implemented our function properly, this use case should succeed. Running `yarn run test` will confirm that assumption.

```no-highlight
yarn run v1.13.0
$ ./node_modules/.bin/jest
 PASS  src/__tests__/greet.test.js
 PASS  src/__tests__/double.test.js

Test Suites: 2 passed, 2 total
Tests:       3 passed, 3 total
Snapshots:   0 total
Time:        2.718s
Ran all test suites.
✨  Done in 4.06s.
```

Aw yeah! Ok, so the last step of TDD is to consider how we can rewrite or improve our implementation. Instead of multiplication, we could use addition. Let's modify our function in `src/double.js` to use addition instead of multiplying by 2.

```javascript
const double = (num) => {
  return num + num
}

export default double
```

Though the way in which we've calculated a `double` has changed, the result is still the same. When we run `yarn run test`, our established tests still pass. With the power of automated tests, we can change the details of our implementation and still ensure our functions work the way in which we expect.

## Why This Matters

Writing automated tests gives us confidence. We can use computing power to basically validate our assumptions about how our code works.

Writing our tests first forces us to think about how the code will be used before attempting to write any code. This can often yield better design and clearer code.

[babel-jest]: https://www.npmjs.com/package/babel-jest
[jest]: https://jestjs.io/
