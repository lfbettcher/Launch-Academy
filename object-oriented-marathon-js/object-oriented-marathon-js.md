For this lesson, you will build a Zoo, complete with many different animals and employees.

## Objectives

- Build objects in JavaScript
- Understand object composition
- Practice test-driven development (TDD)

## Getting Started

Run the following:

```no-highlight
et get object-oriented-marathon-js
cd object-oriented-marathon-js
yarn install
yarn run test src/__tests__/01_Gorilla.test.js
```

Let the tests guide your development.

You'll see something like

```no-highlight
  ● Test suite failed to run

    Cannot find module '../Gorilla' from '01_Gorilla.test.js'

    > 1 | import Gorilla from "../Gorilla.js"
        | ^
      2 |
      3 | describe("A Gorilla", () => {
      4 |   let gorilla

      at Resolver.resolveModule (node_modules/jest-resolve/build/index.js:229:17)
      at Object.<anonymous> (src/__tests__/01_Gorilla.test.js:1:1)

Test Suites: 1 failed, 1 total
Tests:       0 total
Snapshots:   0 total
Time:        1.551s
Ran all test suites matching /src\/__tests__\/01_Gorilla.test.js/i.
error Command failed with exit code 1.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.)
```

The important bit of this message is `Cannot find module './Gorilla.js' from 'testHelper.js`, which tells us that JavaScript is looking for, but can't find, `Gorilla.js`. Create this file and rerun the test.

```
FAIL  src/__tests__/01_Gorilla.test.js
  A Gorilla
    ✕ has a name (4ms)
    ✕ speaks its tagline (1ms)
    ✕ makes sure you didn't hardcode the gorilla's name

  ● A Gorilla › has a name

    TypeError: Gorilla is not a constructor

       7 |   beforeEach(() => {
       8 |     gorillaName = "Bob"
    >  9 |     gorilla = new Gorilla(gorillaName)
         |               ^
      10 |   })
      11 |
      12 |   it("has a name", () => {

      at Object.beforeEach (src/__tests__/01_Gorilla.test.js:9:15)
```

As you can see we now have a different error.

## Test-Driven Development

Next, follow the [Red-Green-Refactor](http://blog.cleancoder.com/uncle-bob/2014/12/17/TheCyclesOfTDD.html) cycle, letting the tests guide you. In other words:

### Step 1: Red

Run the tests and note the failures.

```no-highlight
yarn run test
// failing test
```

### Step 2: Green

Write some code to make the test pass.

```no-highlight
yarn run test
// passing test
```

When you receive no failing tests for the Gorilla, move on to the Fox.

```
yarn run test src/__tests__/02_Fox.test.js
```

Repeat this process with each test file in succession.

When you finish the 06_Zoo.test.js file go ahead and run

```
yarn run test
```

this will run your full test suite and make sure that you haven't broken anything along the way. It's always good practice to rerun your full test suite before you submit your work.

### Step 3: Refactor

Evaluate and improve what you have written. Refactor your code by extracting duplication into functions. Look for cleaner ways to achieve the goal of each function.

Rerun your test suite after every refactor to ensure that your changes don't break your application.

#### Hints

- If you want to isolate a single test while you're working on the related function(s) you can add `.only` to the test and it will only run that single test. i.e

```
import Fox from "../Fox.js"

describe("A Fox", () => {
  let fox
  let foxName

  beforeEach(() => {
    foxName = "Kit"
    fox = new Fox(foxName)
  })

  it.only("has a name", () => {
    expect(fox.name).toEqual(foxName)
  })

  it("says what foxes say", () => {
    expect(fox.speak()).toEqual("Ring-ding-ding-ding-dingeringeding!")
  })

  it("makes sure you haven't hardcoded the name", () => {
    newFox = new Fox("Robin Hood")
    expect(newFox.name).toEqual("Robin Hood")
  })
})
```

will result in

```
yarn run v1.3.2
 ./node_modules/.bin/jest src/__tests__/02_Fox.test.js
 FAIL  src/__tests__/02_Fox.test.js
  ● Test suite failed to run

    Cannot find module '../Fox' from '02_Fox.test.js'

    > 1 | import Fox from "../Fox.js"
        | ^
      2 |
      3 | describe("A Fox", () => {
      4 |   let fox

      at Resolver.resolveModule (node_modules/jest-resolve/build/index.js:229:17)
      at Object.<anonymous> (src/__tests__/02_Fox.test.js:1:1)

Test Suites: 1 failed, 1 total
Tests:       0 total
Snapshots:   0 total
Time:        0.82s
Ran all test suites matching /src\/__tests__\/02_Fox.test.js/i.
```
