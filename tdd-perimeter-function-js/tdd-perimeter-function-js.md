Use your JavaScript & Jest skills to test drive your solution to a real-universe problem.

## Getting Started

From your `challenges` folder in the terminal, type the following commands:

```no-highlight
et get tdd-perimeter-function-js
cd tdd-perimeter-function-js
yarn install
code .
```

> your work will go in `main.js`

In the browser, open up the JavaScript console. You can refresh the page in order to see your changes.

### Instructions

Write a JavaScript function to calculate the perimeter of a rectangle by letting your tests be your guide.

Run `yarn run test` and take a look at the first test failure. You should see

```no-highlight
yarn run v1.22.4

jest
 FAIL  src/__tests__/main.test.js
  ● Test suite failed to run

    ReferenceError: perimeter is not defined

      1 | // your code, here
      2 |
    > 3 | export default perimeter        |                  ^
      4 |

      at Object.perimeter (src/main.js:3:18)
      at Object.<anonymous> (src/__tests__/main.test.js:1:1)

Test Suites: 1 failed, 1 total
Tests:       0 total
Snapshots:   0 total
Time:        1.758s
Ran all test suites.
error Command failed with exit code 1.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.
```
We're getting a `ReferenceError`, because `perimeter` has not been declared and we are trying to export it.  This error is occurring before the test suite can even run.

As you come across errors like this its always a good idea to just do the bare minimum to get it passing and then run `yarn test` again to check.  It's important not to try and solve too much at the same time.  Often times this can lead to other errors which become harder to debug, because so much has changed since the last error.  Take your time, do the bare minimum, make sure it passes and move onto the next failing test.

In order to move past this error all we need to do is declare a `perimeter` variable in the `main.js` file. We aren't concerned about making it a working function just yet.

```JavaScript
// your code, here
let perimeter

module.exports = perimeter
```

Run `yarn test` again and take a look at the first test failure. You should see

```no-highlight
yarn run v1.22.4

jest
 FAIL  src/__tests__/main.test.js
  perimeter function
    ✕ defines perimeter (5ms)
    ✕ allows only one argument to be 0
    ✕ allows does not allow both arguments to be 0 (1ms)
    ✕ takes in two arguments and returns a positive number

  ● perimeter function › defines perimeter

    expect(received).toBeDefined()

    Received: undefined

      3 | describe("perimeter function", () => {
      4 |   it("defines perimeter", () => {
    > 5 |     expect(perimeter).toBeDefined()
        |                       ^
      6 |   })
      7 |
      8 |   it("allows only one argument to be 0", () => {

      at Object.toBeDefined (src/__tests__/main.test.js:5:23)
  ...
Test Suites: 1 failed, 1 total
Tests:       4 failed, 4 total
Snapshots:   0 total
Time:        2.226s
```

You can look in the `tests` folder to open your `main.test.js` file to take a closer look at the failing test. The first test is looking to confirm the existence of a `perimeter` function.  While you had declared a perimeter variable earlier, we'll need it to be a function, and not only that it will need to be able to receive two arguments and return the correct perimeter of the rectangle used in the test case.

In `main.js`, write your function. Your goal is to satisfy the test suite and see an output like this:

```no-highlight
yarn run v1.12.3
jest
 PASS  tests/main.test.js
  ✓ it defines perimeter (5ms)
  ✓ it does not allow both arguments to be 0 (1ms)
  ✓ it takes in two arguments and returns a positive number (1ms)

Test Suites: 1 passed, 1 total
Tests:       3 passed, 3 total
Snapshots:   0 total
Time:        2.223s
Ran all test suites.
✨  Done in 4.17s.
```

Your perimeter function may require some conditional statements, so pay attention to what your tests are expecting from you!

Hints: In order for the function to `.beDefined` it will need a return value that is NOT undefined.

Use a `console.log` to invoke your perimeter function & see your function's returns.

### Sample Output

```no-highlight
perimeter(3, 5)  // returns 16
```
