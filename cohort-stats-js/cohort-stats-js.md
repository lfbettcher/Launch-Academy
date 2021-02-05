## Instructions

You have been tasked with creating an Object-Oriented system to keep track of the happenings at a certain educational institution not unlike Launch Academy. A few engineers have already provided tests for the features needed for the system. However, they need your help satisfying these tests so that they can track students and their students system check submissions for each of their cohorts. Based on a product planning session, you have determined these are the JavaScript classes you will need:

- `Student`
- `SystemCheckSubmission`
- `SystemCheck`
- `Cohort`

### Getting Set Up

Run the following commands from your terminal:

```
et get cohort-stats-js
cd cohort-stats-js
yarn install
yarn run test
```

Note: we recommend running one test at a time:

```
yarn run test src/__tests__/Cohort.test.js
```

Let the tests guide your development.

## Meeting Expectations Requirements

Make the following tests pass by adding code to the `src` folder:

1. `src/__tests__/Student.test.js`
2. `src/__tests__/SystemCheckSubmission.test.js`
3. `src/__tests__/SystemCheck.test.js`
4. `src/__tests__/Cohort.test.js`

We also recommend satisfying each test suite in this order.

By the end, all but the `exceedsExpectations` tests should be passing.

### Tips

- Take a moment to familiarize yourself with the codebase. Look at each provided file, especially your test files, to get an idea of what is expected of you.
- Jest will tell you what is breaking. Use the following workflow:
  - Run the test suite.
  - Read the error message.
  - If you're confused by the error check the actual test file to see how it's set up.
  - Do the simplest implementation, without "hard coding" values, that corrects the error.
  - Repeat.
- Once all but the skipped tests are passing, please submit your code by running `et submit`.
- No tests should be failing
- **DO NOT** start the requirements for exceeding expectations before you have completed the requirements necessary for meeting expectations. To reiterate, **`et submit` once you have finished meets, before moving on to exceeds.**

## Exceeding Expectations Requirements

- Make the following tests pass by adding code to the `src` folder:
  1. `src/__tests__/exceedsExpectationsSystemCheckSubmission.test.js`
  2. `src/__tests__/exceedsExpectationsSystemCheck.test.js`
  3. `src/__tests__/exceedsExpectationsCohort.test.js`
- You will need to remove the `.skip` at the top of each test with exceeds
- These need to pass with implementation that does not have "hard coded data". Once these tests pass, you should see zero failing tests, and all tests should be passing when you run `yarn run test`.

In addition, create the following classes: `Article`, `Challenge`, and `Video`.

- Each new classes should have a `name` and a `body`
- The `Article` and `Video` classes have a `#submittable` method that returns `false`.
- The `Challenge` and `SystemCheck` classes are submittable. Ensure that calling `#submittable` on these objects returns `true`.
- In addition to a `name` and `body`, a `Video` class is also instantiated with a `url`
- **Write Jest tests for these functionalities.**

**Note**: Make sure to refactor the `SystemCheck` class and Jest test so that your objects have the appropriate functionality.
