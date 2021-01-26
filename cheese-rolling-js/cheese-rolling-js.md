We've decided to bring [cheese-rolling][cheese-rolling] to Boston! Let's make an app to simulate a cheese-rolling competition on Bunker Hill.

### Getting Started

```no-highlight
et get cheese-rolling-js
cd cheese-rolling-js
code .
open index.html
```

Your code should be added to `main.js`. Don't forget to use debuggers and `console.log`s to help you!

## Blessed are the CheeseRollers!

We're going to modify the format of the original cheese-rolling competition; instead of having competitors run down a hill chasing a 7-9 lb wheel of cheese, we're going to give each of our competitors a wheel of cheese to roll up Bunker Hill.

This amazing demonstration of endurance is further complicated by the fact that some of the competitors are hungry and may give in to eating the cheese, which will disqualify them.

### Step One: Get Familiar with the Code

Let's start by reviewing the code already provided:

- a `generateRandomRolltime` function that will return a random integer in between 10 and 60 
- a `rawData` array of objects containing cheese and contestant data

Cheese deliciousness is rated using the International Cheese Deliciousness Scale, in which 1 is least scrumptious and 10 is most scrumptious.

Hunger is rated using the Metric Hunger Scale, in which 1 is not at all hungry and 10 is ravenous.

### Step Two: Create Contestants

Ultimately, we want to be working with objects representing contestants, which can then have helpful properties and methods that describe or act on each contestant. `rawData` isn't really organized the way we want it -- it's just a hodgepodge of cheese and contestant data mixed together.

So, to start, let's create a function `registerContestants` which returns an array of contestant objects by mapping over `rawDataArray`. That return value array should look like this:

```javascript
[
  {
    name: 'John C',
    hunger: 8,
    cheese: {
      name: 'Red Leicester',
      deliciousness: 9
    }
  },
  //... continue for all other contestants
]
```

In `main.js`, you can then set the array to a `contestants` variable as so:

```javascript
let contestants = registerContestants()
```

### Step Three: Contestant Properties

This is a great start, but we need some additional properties and methods. Modify the code you wrote above to add these properties:

Each contestant should have the following attributes:

- a `disqualified` boolean set to `false`
- a `results` array, initially empty, to store our contestant's results

Each object in `contestants` should now have this structure:

```javascript
{
  name: 'John C',
  hunger: 8,
  cheese: {
    name: 'Red Leicester',
    deliciousness: 9
  },
  disqualified: false,
  results: []
}
```

### Step Three: Contestant Methods

We've set up our contestants' _state_, but now we want to add behavior. We need methods to

- determine whether a contestant has eaten their cheese already, thereby disqualifying them
- simulate a cheese-rolling

#### `checkForCheeseEating`

Create a method, `checkForCheeseEating`, that does the following:

- if the average of the contestant's hunger and the cheese's deliciousness is greater than or equal to `7`, then the contestant succumbs to the cheese. Set `isDisqualified` to `true`.
- otherwise, do nothing!

Remember that an average is calculated by adding the two numbers together, and then dividing by the total count of numbers (here, dividing by 2).

#### `rollDatCheese`

Create a method to simulate one round of rolling the cheese up the hill.

- if a contestant hasn't been disqualified, randomly generate the time it takes the contestant to roll the cheese up the hill using the provided `generateRandomRolltime`. Push this value into the contestant's `results` array.
- otherwise, do nothing!

### Step Four: Roll Dat Cheese!

We're ready to roll! Iterate through your contestants and have them each roll three times using the `rollDatCheese` method. Use a debugger or the console to check that the final results look reasonable.

### Step Five: Report the Results

All cheese-rollers deserve celebration.

1. Create a new method, `reportIndividualResults`, to report the contestant's name, type of cheese, whether they were disqualified, and each of their cheese rolling results (in an appropriately formal and celebratory tone, using words of your choosing). An example output for one contestant might look like:

```no-highlight
Contestant John Doe entered this competition with 
a lovely Sharp Cheddar cheese. By meeting the criteria, 
they were able to compete with resultings scores of 43, 11, 39.
```

2. Now, iterate through your contestants and use the `reportIndividualResults` method to ultimately print to the console each contestant's results.

[cheese-rolling]: https://en.wikipedia.org/wiki/Cooper's_Hill_Cheese-Rolling_and_Wake
