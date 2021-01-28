Let's get some practice working with a multi-file project. Launch has decided that in addition to space travel, we want to focus on something a little closer to home. Help us get our airline off the ground!

## Getting Started

```no-highlight
et get launch-air-part-1
cd launch-air-part-1
code .
```

Now that we know how to work with Node, we're going to continue to run our JavaScript code using the `node` command, here with `node main.js`.

## Instructions

### Part 1 - Building an Airplane

In order to have a successful airline, we're going to need some airplanes! Let's set up some airplane objects in order to start building our fleet. Start by adding a new file in this project directory, `createNewAirplane.js`. Inside it, define a new function called `createNewAirplane` which takes in a `make`, `model`, and `capacity` for the airplane, and returns an object with those values as properties with the same name (see if you can remember any shorthands in order to do this!)

`export` your function at the bottom of your file, and `import` it into your `main.js` file. Now, add some lines in `main.js` to create some objects, like this:

```javascript
let magic777 = createNewAirplane('Magic', '777', 150)
```

### Part 2 - Adding Flights

Now that we have some airplanes in our fleet, we can start planning flights! Add a new file, `createNewFlight.js`, with a `createNewFlight` function inside it. This function should take in a `date`, `takeoffTime`, `landingTime`, `origin`, and `destination` as arguments, and return an object with properties with the same names.

Again, remember to `export` and `import` your function! Then, in `main.js`, add some flights to our repertoire, modeled after the below:

```javascript
let maidenFlight = createNewFlight(
  'September 2',
  '10AM',
  '1PM',
  'Boston',
  'San Diego'
)
```

### Part 3 - Assigning Flights

Now that we have our fleet of planes and a schedule of flights we want to complete, we can start allocating our planes to cover our existing schedule.

Let's go back to our `createNewAirplane` function and add a method to the object that's being returned. Add a new property into this object called `flights` which starts as an empty array. Then, add a method to this object called `scheduleFlight` which takes in a `flight` object as an argument, and adds it into the `flights` array for a given plane.

Finally, go back into `main.js` and schedule some flights with your test data! This should look like the below:

```javascript
magic777.scheduleFlight(maidenFlight)
```

Add a `debugger` or some `console.log`s to make sure everything is functioning and that your `flights` array is being updated properly.

Time for take off!
