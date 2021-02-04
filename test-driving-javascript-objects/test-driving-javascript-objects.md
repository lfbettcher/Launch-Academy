Like testing our functions, we can use automated tests to assist us with the creation of our classes. In this article, we'll walk through writing unit tests for JavaScript classes and instances.

## Learning Goals

- Create a JavaScript class with a corresponding unit test
- Construct a failing test and then implement the feature to make it pass
- Improve code through refactoring once there is a passing test suite
- Reduce test duplication with `beforeEach`
- Constrain test execution with Jest's `it.only`

## Get Started

```no-highlight
et get test-driving-javascript-objects
cd test-driving-javascript-objects
yarn install
```

## Considering Behavior First

Let's imagine we're building a management system for a function hall. We must first consider the objects at play in such a system.

- **Room**: for the purposes of our application, the room itself has characteristics like daily price, and maximum occupancy
- **Party**: when a party is booked and the room is reserved, we must keep track of the number of confirmed attendees, and the contact information of the individual reserving the room.

Let's consider the first user story for our system:

```no-highlight
As a function hall provider
I want to initialize a room
So that it can be rented by others
```

Acceptance Criteria:

- The room must have a name and a maximum occupancy
- The room starts as being unreserved

### Test Driving the Room's Construction

Before we even define our `Room` class, let's first write a test that outlines all of the properties and behaviors we need.

Let's create our `__tests__` directory in `src`. Inside, we'll define a `Room.test.js` file where we'll start to brainstorm on the way we want our `Room` class to work.

```javascript
describe("A room", () => {
  it("has a name", () => {})

  it("has a maximum occupancy", () => {})

  it("is initially unreserved", () => {})
})
```

So, we've basically listed out all of the characterstics and behaviors of our `Room` before we've written any code. We can consider this list of `it` statements like a checklist - in order for the `Room` class to function well for us, it needs to fulfill these **behaviors**.

Let's start by commenting out the behaviors below the first so that we can focus our efforts.

```javascript
describe("A room", () => {
  it("has a name", () => {})

  // it("has a maximum occupancy", () => {

  // })

  // it("is initially unreserved", () => {

  // })
})
```

Following the TDD workflow, we'll implement our ability to essentially create a room and apply a name to it. We'll first write a failing test, and then we'll write implementation code that will make the test pass. Formally, with Test Driven Development, we're supposed to write the test **first**. This is tricky to do at first, so don't worry too much about adhering to the workflow. We'll demonstrate it here to help you get a feel for it.

### Writing Our Failing Test - RED

```javascript
describe("A room", () => {
  it("has a name", () => {
    const roomName = "The Fairmont"
    const room = new Room(roomName)
    expect(room.name).toEqual(roomName)
  })
})
```

With this first behavior, we're basically writing how we want the `Room` class to function. We're saying that we'll construct `Room` objects with a single argument: the name of the room. We then expect that argument to be stored as a `name` property.

Let's run the test with a `yarn run test` command.

```no-highlight
 FAIL  src/__tests__/Room.test.js
  A room
    ✕ has a name (3ms)

  ● A room › has a name

    ReferenceError: Room is not defined

      2 |   it("has a name", () => {
      3 |     const name = "The Fairmont"
    > 4 |     const room = new Room(name)
        |                  ^
      5 |     expect(room.name).toEqual(name)
      6 |   })
      7 |

      at Object.it (src/__tests__/Room.test.js:4:18)

Test Suites: 1 failed, 1 total
Tests:       1 failed, 1 total
Snapshots:   0 total
Time:        2.029s
Ran all test suites.
error Command failed with exit code 1.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.
```

The test fails, which is what we would expect. We haven't yet built the `Room` class or required it in our tests. Instead of assuming that's the next step that we should take, we rely on the test itself to provide that guidance. The key to the output above is the `ReferenceError: Room is not defined`. It tells us what to do next. So, let's define the `Room` class and properly require it.

In `src/Room.js`, we'll define the class.

```javascript
class Room {}

export default Room
```

We'll then need to require that class in our automated test.

In `src/__tests__/Room.test.js`, be sure to add the line below to the top of the file.

```javascript
import Room from "../Room.js"
```

Once we've completed those tasks, we can run our test again. Our tests will still fail, but we should receive a new error message that guides us in what to do next.

```no-highlight
 FAIL  src/__tests__/Room.test.js
  A room
    ✕ has a name (6ms)

  ● A room › has a name

    expect(received).toEqual(expected)

    Expected: "The Fairmont"
    Received: undefined

       5 |     const name = "The Fairmont"
       6 |     const room = new Room(name)
    >  7 |     expect(room.name).toEqual(name)
         |                       ^
       8 |   })
       9 |
      10 |   // it("has a maximum occupancy", () => {

      at Object.toEqual (src/__tests__/Room.test.js:7:23)

Test Suites: 1 failed, 1 total
Tests:       1 failed, 1 total
Snapshots:   0 total
Time:        1.523s
Ran all test suites.
error Command failed with exit code 1.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.
```

This time, instead of an error, we see that our expectation did not match up with what actually happens when we run the code. We expected `room.name` to retain the value `"Fairmont"`, but instead we received `undefined`. So, we must update our **constructor** to support the handling of this argument.

### Getting to Green

Based on the last test failure, we'll update `src/Room.js`:

```javascript
class Room {
  constructor(name) {
    this.name = name
  }
}

export default Room
```

We've successfully defined our constructor that takes one argument, and we've assigned that argument to the `name` property of the object. When we run our test, we should expect that it will pass.

Let's run `yarn run test` one more time.

```no-highlight
 PASS  src/__tests__/Room.test.js
  A room
    ✓ has a name (4ms)

Test Suites: 1 passed, 1 total
Tests:       1 passed, 1 total
Snapshots:   0 total
Time:        1.875s
Ran all test suites.
✨  Done in 3.47s.
```

At this point, we'd look at our code, both in our automated test and in our `Room` class, and determine if any of it can be improved. This is the 3rd and final process of the workflow, to round out the Red, Green, Refactor sequence we're practicing.

### On to the Next Behavior

Once we've followed that flow for the first behavior, we can simply move on to the next one.

Right now, our test should resemble the code below:

```javascript
import Room from "../Room.js"

describe("A room", () => {
  it("has a name", () => {
    const name = "The Fairmont"
    const room = new Room(name)
    expect(room.name).toEqual(name)
  })

  // it("has a maximum occupancy", () => {

  // })

  // it("is initially unreserved", () => {

  // })
})
```

We'll uncomment the second behavior, and work through the Red, Green, Refactor process, again. First, we'll write a failing test.

```javascript
it("has a maximum occupancy", () => {
  const name = "The Fairmont"
  const maxOccupancy = 100
  const room = new Room(name, maxOccupancy)
  expect(room.maxOccupancy).toEqual(maxOccupancy)
})
```

When we run `yarn run test`, we can see the test fail, fulfilling the RED step in Red, Green, Refactor.

```no-highlight
 FAIL  src/__tests__/Room.test.js
  A room
    ✓ has a name (4ms)
    ✕ has a maximum occupancy (3ms)

  ● A room › has a maximum occupancy

    expect(received).toEqual(expected)

    Expected: 100
    Received: undefined

      12 |     const maxOccupancy = 100
      13 |     const room = new Room(name, maxOccupancy)
    > 14 |     expect(room.maxOccupancy).toEqual(maxOccupancy)
         |                               ^
      15 |   })
      16 |
      17 |   // it("is initially unreserved", () => {

      at Object.toEqual (src/__tests__/Room.test.js:14:31)

Test Suites: 1 failed, 1 total
Tests:       1 failed, 1 passed, 2 total
Snapshots:   0 total
Time:        1.702s
Ran all test suites.
```

So, the `maxOccupancy` value we're supplying in the constructor is not getting retained as state in the `room` instance. In order to accomplish that, we have to modify our constructor to support the second argument.

```javascript
class Room {
  constructor(name, maxOccupancy) {
    this.name = name
    this.maxOccupancy = maxOccupancy
  }
}

export default Room
```

Once that's in place we can run our tests again. This time, the test passes!

```no-highlight
 PASS  src/__tests__/Room.test.js
  A room
    ✓ has a name (3ms)
    ✓ has a maximum occupancy

Test Suites: 1 passed, 1 total
Tests:       2 passed, 2 total
Snapshots:   0 total
Time:        1.296s
Ran all test suites.
✨  Done in 2.25s.
```

Great! We didn't have to do as much work with this second test, because much of the mechanics were already in place. At this point in the process, we would consider if any improvements to the code can be made. There is a bit of duplication in our tests. So, let's clean that up.

### Refactoring with a `beforeEach`

```javascript
import Room from "../Room.js"

describe("A room", () => {
  let room
  const name = "The Fairmont"
  const maxOccupancy = 100

  beforeEach(() => {
    room = new Room(name, maxOccupancy)
  })

  it("has a name", () => {
    expect(room.name).toEqual(name)
  })

  it("has a maximum occupancy", () => {
    expect(room.maxOccupancy).toEqual(maxOccupancy)
  })

  // it("is initially unreserved", () => {

  // })
})
```

We've moved variables that we were repeatedly assigning to be inside the `describe` block. That way, we only have to define them in one place, and they can be shared amongst all of the behaviors.

Secondly, we created a `beforeEach` statement. This code will execute before every `it` statement, allowing us to do set up for our tests right before we start setting expectations.

So, we've eliminated some duplication, and it makes writing our third and final test a whole lot easier.

### Our Third and Final Behavior

We'll start the process all over again for a third and final time. First, let's write our failing test.

```javascript
it("is initially unreserved", () => {
  expect(room.isReserved()).toEqual(false)
})
```

Thanks to our `beforeEach`, our `room` instance is already created for us. So, we just need to ensure that when a new `Room` object is instantiated, that it starts off as unreserved.

Let's watch our test fail. Running `yarn run test` will yield the following output:

```no-highlight
 FAIL  src/__tests__/Room.test.js
  A room
    ✓ has a name (4ms)
    ✓ has a maximum occupancy (1ms)
    ✕ is initially unreserved (1ms)

  ● A room › is initially unreserved

    TypeError: room.isReserved is not a function

      19 |
      20 |   it("is initially unreserved", () => {
    > 21 |     expect(room.isReserved()).toEqual(false)
         |                 ^
      22 |   })
      23 | })

      at Object.isReserved (src/__tests__/Room.test.js:21:17)

Test Suites: 1 failed, 1 total
Tests:       1 failed, 2 passed, 3 total
Snapshots:   0 total
Time:        1.978s
Ran all test suites.
error Command failed with exit code 1.
```

Sometimes, we may want to run a single behavior to isolate what's happening with our test suite. Let's change our last `it` to `it.only`. This will tell jest that we only want to execute the behavior `"is initially unreserved"`

```javascript
it.only("is initially unreserved", () => {
```

When we run our tests again, we'll see that Jest skipped the other two tests.

```no-highlight
 FAIL  src/__tests__/Room.test.js
  A room
    ✕ is initially unreserved (2ms)
    ○ skipped has a name
    ○ skipped has a maximum occupancy

  ● A room › is initially unreserved

    TypeError: room.isReserved is not a function

      19 |
      20 |   it.only("is initially unreserved", () => {
    > 21 |     expect(room.isReserved()).toEqual(false)
         |                 ^
      22 |   })
      23 | })

      at Object.isReserved (src/__tests__/Room.test.js:21:17)

Test Suites: 1 failed, 1 total
Tests:       1 failed, 2 skipped, 3 total
Snapshots:   0 total
Time:        1.388s
Ran all test suites.
error Command failed with exit code 1.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.
```

Ok, now let's get our test to pass. We could implement support for this like so:

```javascript
class Room {
  constructor(name, maxOccupancy) {
    this.name = name
    this.maxOccupancy = maxOccupancy
    this.reserved = false
  }

  isReserved() {
    return this.reserved
  }
}

export default Room
```

Our test passes! Let's be sure to change our `it.only` back to an `it` so that all examples are run.

## Why This Matters

Automated tests help us to ensure our code works the way in which we expect. When designing our objects, it can often benefit our approach by writing failing tests first. That way, we have to consider how the objects will be used before we implement them. Also, as our objects evolve and requirements change, we have confidence in how our code works because automated tests exist.

## In Summary

Try to write your tests first and follow the practice of Test Driven Development. It will feel slower at first, and it takes a while to get used to, but getting skilled with the technique can greatly expedite and improve your development. The sequence of Red, Green, Refactor can help you to remember how to stay disciplined with the workflow. `beforeEach` and `it.only` are helpful Jest tools that we can use to build more robust tests.
