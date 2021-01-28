It's finally time for you to get out and explore space! Since you're so new, the crew won't lend you a ship - we've got to work together and build one that will get us there!

## Getting Started

```no-highlight
et get js-objects-marathon
cd js-objects-marathon
code .
```

**You'll be doing your work in Node in the terminal.**

We've provided a `shipyard.js` file that we're going to use to build our spaceship, our rockets, our crew, and blast off.

### Step 0 - Create a launchpad

We need a launchpad from which we can launch our spaceship!

Create a function called `launchpad`. Inside of the function write a short message announcing the initiation of preflight procedures - any string of text that you want!

```JavaScript
//shipyard.js

let launchpad = () => {
  console.log('Initiating preflight procedures');
}
```

Then call `launchpad` at the end of the file.

```JavaScript
//shipyard.js

let launchpad = () => {
  console.log('Initiating preflight procedures');
}

launchpad()
```

In your terminal, you should see your preflight message similar to the one below!

```
Initiating preflight procedures!
```

After each step, we should run our program by running `node shipyard.js` to make sure our code works, and is printing what we expect.

### Step 1 - Build a spaceship!

Let's start building our space vessel!

Create a file called `createNewSpaceship.js`. Define a factory function `createNewSpaceship` which takes a `name` argument and assigns it to a `name` attribute. Don't forget to export your module! Import the file to your `shipyard.js`.

What is an appropriate name for this spaceship? Create a new Spaceship object with the name of your choice, and assign it the variable `ourShip`.

Let's add another preflight message that announces the name of our spaceship in our `launchpad` function.

Don't forget, we'll need to add an argument to the `launchpad` function, so we can pass in `ourShip`. Like so:

```JavaScript
//shipyard.js

let launchpad = (ship) => {
  // your code
}
```

Now when we run our code, we should see something like:

```
Initiating preflight procedures!
Welcome aboard the Waverider!
```

### Step 2 - Round up your crew!

We're going to work on getting our crew on the spaceship - but first we need to round them up!

In `shipyard.js`, first create an array named `crewNames`, and fill it with the name of all your friends!

Create a JavaScript file called `createNewCrewMember.js`. Make a factory function of the same name which takes a `name` argument and assigns it to a `name` property. Also make a `trained` property that defaults to false. Don't forget to export your new module and import it into `shipyard.js`!

Rerun your program. The output should be **exactly** the same as before, but we should still run it because we've added a bunch of code. If there are any syntax errors or we forgot to import or export our modules, we'll get an error and we can fix it now before moving on to the next step.

### Step 3 - Train your crew!

Before we can load the crew on the spaceship, we have to first train the crew! In `shipyard.js`, we're going to create a function called `trainCrew` that takes in an array of strings (the names of each crew member) and returns an array of JavaScript objects (a crew member object via the createNewCrewMember function). The `trainCrew` function should also change their `trained` attribute to true.

Return an array of trained crew member objects by using both the `crewNames` array and the `trainCrew` function.

We should run our code to make sure we don't have broken anything. If we don't have any errors, the output should still be exactly the same as before.

### Step 4 - Board the spaceship!

Time to get our crew onboard the spaceship!

Let's add a `crew` property to the object created by our `createNewSpaceship` function, with a default value of an empty array.

Next, let's create a `loadCrew` method in our `createNewSpaceship` factory object.

This method should take in an array of crew member objects and one by one it should add them to the spaceship's `crew` array.

### Step 5 - Announce the crew!

Update the `loadCrew` function to output a message announcing the name of the crew member that has boarded the spaceship as it iterates through. **Make sure you use object destructuring to obtain the name of the crew member before announcing their name.**

Let's make loading the crew part of the `launchpad` function. We're going to add another argument to `launchpad` to accept the `crewMembers` array (the array of trained crew members from step 3!).

Finally, let's call the spaceship's `loadCrew` method with the `crewMembers` array in the `launchpad` function.

Once again rerun your code, and you should see something along the lines of:

```
Initiating preflight procedures!
Welcome aboard the Waverider!
Aubrey is now aboard the Waverider.
Dorothy is now aboard the Waverider.
Tina is now aboard the Waverider.
Lynn is now aboard the Waverider.
Laura is now aboard the Waverider.
Jacquie is now aboard the Waverider.
```

### Step 6 - Pick a random captain!

The crew decided to take turns playing the role of captain of the spaceship. Every time we ask the spaceship who is the captain, we're going to get a random crew member!

Start by defining the `captain` method in the `spaceship` object. This method will randomly pick a member from the spaceship's `crew` array. But, how do we implement randomness in JavaScript? Take a look at the following code for some inspiration!

```no-highlight
const number = 3;
const randomNumber = Math.floor(Math.random() * number);
console.log(randomNumber);
// Outputs either 0, 1, or 2 to the browser's console.
```

Once the `captain` method has been implemented, let's add another preflight message to the the launchpad, so that we can announce the captain of this particular launch.

Be sure to check how the code looks by running it, then give our first captain a high-five.

### Step 7 - Building your spaceship's rockets!

Earth's gravity well is no small task to overcome. We're going to need some sturdy rockets to take us to the stars. Make a file called `createNewRocket.js` where we will export a module containing an object literal representing the rocket we will use to propel our spaceship!

Define a factory function to create a `rocket` object (which we will later mount to our spaceship for propulsion), and assign the object two attributes:

1.  `fuel` which will default to `0`.
2.  `addFuel` which will be a function that accepts an integer as an argument, adds it to the rocket's fuel, and prints out the new fuel amount.

Finally, create a rocket object within `shipyard.js` and assign it to a variable `ourFirstRocket`.

### Step 8 - Define a rocket's fire function!

Now, we're going to write some code to fire the engines! We want to make sure there is fuel in the tank, and if there is, we want to print that the rocket's fired and remove some fuel.

Our `fire` function needs to:

- Check and see if there is fuel in the `fuel` property.
- If there is, subtract 1 from the `fuel` property, print that the engines have been fired, print the rocket's current fuel count, and return true.
- If there is not, print that the engines failed to fire and return false.

We're almost there!

### Step 9 - Mount your rocket!

To start off, let's add a property to the spaceship object in our `createNewSpaceship` function called `propulsion` which is set initially to `null`.

We're going to write some cool methods on the spaceship object to attach our rocket.

Add a method to the spaceship object called `mountPropulsion`, this is going to be a method that takes in a rocket object and assigns it to the spaceship's `propulsion` property.

It should also print out that the propulsion mounted!

Finish by passing in `ourFirstRocket` to the `launchpad` function. In the `launchpad` function, mount the rocket to our ship!

### Step 10 - Blast off!

We've got all of our pieces together. Now we've got to blastoff!

Let's add a `takeoff` method on the `spaceship` object that:

- `fire`s the propulsion drives.
- If the engines successfully fire, print a really convincing takeoff noise.
- If the engines fail to fire, print that takeoff was unsuccessful.

Once you are done, call the `takeoff` method in the launchpad function and take off!!

Oh snap, did you forget to fuel up your rocket??? If you need to add fuel to your rocket, add that to your launchpad function!

### Bonus challenge: add a countdown!

### Step 11 - Add a countdown sequence!

Congrats! Our ship works. But...I feel like it's missing something...

...Of course! We need a countdown!

Let's create a `countdown` function on your spaceship object whose first argument is an integer from which we will start the countdown.

- If the function receives `0` as an argument, it should print out that we're blasting off.
- If the number is not `0`, then the function should print out the number and _recursively_ call itself with the next number in the countdown.

Now, just call the function in `launchpad`, and give it a run!

### Step 12 - Slow down the countdown sequence!

The countdown is working, but it's going too fast. It would be great if there was a second delay between each number being printed out.

There's a special function in JavaScript, [setTimeout()](https://developer.mozilla.org/en-US/docs/Web/API/WindowTimers/setTimeout) that executes code snippet after a specified delay.

Update your `countdown` function to use `setTimeout()` in order to have a one second delay between printing out the numbers.

### Step 13 - The Final Countdown

Congrats, everyone! It looks like a successful... wait, why did the rockets fire before the countdown was finished?

JavaScript executes its code in a very particular way. It goes line by line, scheduling the execution of each line of code. However, some functions - like `setTimeout` - are _asynchronous_, and JavaScript schedules them for the future. This means that `takeoff()` is firing before the countdown finishes.

How do we fix this? Well, we could use a popular pattern in JavaScript called the `callback` pattern to register a `callback` for the asynchronous code to call at the appropriate time. Callbacks are usually functions, but one could also pass an object and call a function on that object. Here's an example of a callback function:

```no-highlight
let hello = () => {
  console.log("Outputted last");
}

let asynchronous_code = (fn) =>{
  setTimeout(() => {
    console.log("Outputted first");
    fn();
  }, 1000);
}

asynchronous_code(hello);
```

Let's edit the countdown function to accept something we can run as a callback, and then update the launchpad accordingly.

Run your program, and get ready for takeoff, Launchers!
