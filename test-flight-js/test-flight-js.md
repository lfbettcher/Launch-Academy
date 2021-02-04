### Learning Goals

- Get familiar with writing and organizing Jest tests
- Build a working program using Test-Driven Development

### Getting Set Up

```no-highlight
et get test-flight-js
cd test-flight-js
yarn install
code .
```

### Instructions

Test-drive the implementation of an `Airplane` class in the `Airplane.js` file.

Be sure to rely heavily on the **[Jest Docs](https://jestjs.io/docs/en/api)** for this assignment.

#### Part One: Create an Airplane

To create an airplane you need to specify the **type**, [**wing loading**](http://en.wikipedia.org/wiki/Wing_loading) (`In aerodynamics, wing loading is the total weight of an aircraft divided by the area of its wing`), and **horsepower**:

```JavaScript
const newAirplane = new Airplane("Cesna", 10, 150)
```

Write tests in the `src/__tests__/Airplane.test.js` file to ensure that you can access each of these attributes after creating an airplane object. To run your test suite, enter `yarn run test`. To run the test suite and hit a debugger run `yarn run test:debug` and open your debugger inspection console with Chrome.


For example, you may have a test in the `describe initialization` block that reads back the `type` after creating the object:

```JavaScript
const newAirplane = new Airplane("Cesna", 10, 150)

test('returns the type of plane', () => {
  expect(newAirplane.type).toBe("Cesna")
})
```

To run the test suite use the `yarn test` command within the project directory:

Now, in true TDD fashion, update your `Airplane.js` file to properly initialize your object and make your tests pass.

#### Part Two: Make the Airplane do things

Once you have passing tests for the three attributes, write tests for the following methods that an instance of an Airplane can do. Once you run the tests and they fail, implement the methods needed to pass the tests.

- The `start` method should turn on the engine if it is not already running. (You may need to add a new property to the constructor!) It should return whether the engine was just started or if it has already been running:

```JavaScript
newAirplane.start
//=> "airplane started"

// OR if the plane was already started

newAirplane.start
//=> "airplane already started"
```

- The `takeoff` method should change the state of the plane to flying. The plane can only take off if the engine is already running. It should return whether the plane has taken off or not:

```JavaScript
newAirplane.takeoff
//=> "airplane not started, please start"

// OR if the plane was already started

newAirplane.start
newAirplane.takeoff
//=> "airplane launched"
```

- The `land` method should change the state of the plane to landed. The plane can only land if it is currently flying. It should return whether the plane successfully landed or not (or whether it needs to be started):

```JavaScript
newAirplane.land
//=> "airplane not started, please start"

// OR if the plane has been started but hasn't taken off

newAirplane.start
newAirplane.land
//=> "airplane already on the ground"

// OR if the plane is already in the air

newAirplane.start
newAirplane.takeoff
newAirplane.land
//=> "airplane landed"
```

### Part Three: Refactoring Tests

At this point your test file should be getting fairly large and probably contains duplicate steps -- like initializing an airplane instance -- to setup each test. Remove some of the duplication by using the [`beforeEach` syntax](https://jestjs.io/docs/en/setup-teardown#repeating-setup-for-many-tests) to "lazily" initialize the airplane when it is needed.

#### Using `beforeEach`

```JavaScript
describe('initializing a new student', () => {
//applies to all tests in describe block
  beforeEach(() => {
    newStudent = new Student("Geoffrey", "Drake")
  })

  test('created student is a Student class instance', () => {
    expect(newStudent).toBeInstanceOf(Student);
  })

  test('has a first_name attribute', () => {
    expect(newStudent.first_name).toBe("Geoffrey")
  })

  test('has a last_name attribute', () => {
    expect(newStudent.last_name).toBe("Drake")
  })
})
```

So what is this actually doing? `beforeEach` allows us to define a variable outside of our examples, and then reuse that variable within our test examples. Notice how the `beforeEach` block sits outside of all the `test` blocks. This allows all the `test` blocks to access the `newStudent` instance that has been created. Now we've defined `newStudent` in one place, and if we need make changes to `newStudent`, we only have to change one line of code.

`beforeEach` lazily loads your variable. That means that, in any given example, the variable doesn't get defined until it is accessed for the first time. If you don't call `newStudent` at all in an example, it won't be defined for that example.

### Extra Challenge: Keep track of the Airplane's fuel status

Add a `fuel` internal attribute to your airplane that is set at initialization. Write tests for scenarios where the plane does not have enough fuel to complete the above actions.

Each of the `start`, `takeoff`, and `land` methods should use up some amount of fuel every time they are called. If any of the methods cannot be completed due to a lack of fuel, they should return a different status message. If necessary, add an additional method for checking the fuel level of the plane to make writing the tests easier.
