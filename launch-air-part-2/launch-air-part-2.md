Now that we know how to build JavaScript classes, let's refactor our original LaunchAir airline and build on it a bit!

## Getting Started

```no-highlight
et get launch-air-part-2
cd launch-air-part-2
code .
```

We'll run our code using the `node main.js` command.

## Instructions

### Part One - Updating to Classes

In Part 1, we built our airplane and flight objects, but we didn't yet have `class` syntax under our belt!

Refactor your existing code to use JavaScript classes. This means that you should have the below set up to be in parity with "Part 1"'s functionality:

`Airplane` class (in a new `Airplane.js`):

- Takes in a `make`, `model`, and `capacity` to the constructor, and sets them as instance variables. We can expect `make` and `model` to be strings, and `capacity` to be an integer.
- An additional instance variable of `flights` which should be set to an empty array in the constructor.
- An instance method `scheduleFlight` which takes in a flight as an argument, and inserts it into the `flights` array for that instance

`Flight` class (in a new `Flight.js`):

- Takes in a `date`, `takeoffTime`, `landingTime`, `origin`, and `destination`, and sets them as instance variables. Remember from Part 1 that all of these values should be strings.

`main.js`:

- Instantiates at least one airplane object
- Instantiates at least one flight object
- Uses `scheduleFlight` to add an existing flight to an existing airplane

Test to make sure all of your functionality is working using `console.log` statements or `debugger`s!

### Part Two - Adding Passengers and Tickets

Now that we've refactored our flight schedule to be class-based, let's start to account for all of the new passengers we're going to serve!

Make a `Passenger` class (in its own file) which is responsible for storing information about the _people_ who choose to fly our airlines. It should take a `name` string in to the constructor and set it as an instance variable. Also add an instance variable of `flights` which starts as an empty array.

Export and import that file into `main.js`, and instantiate a few passengers.

Then, make a `Ticket` class. This class will represent the joining of a Passenger and a particular Flight. The `Ticket` class constructor should take a `flight`, `passenger`, and `seatNumber` in as arguments, and store them as instance variables. While you don't need to know this in order to build your class, as a heads up: `flight` should be an instance of the `Flight` class, `passenger` should be an instance of the `Passenger` class, and `seatNumber` should be a string.

Don't worry about instantiating any tickets just yet.

### Part 3 - Assigning Tickets to Passengers

Now, comes the extra challenge! We're going to create some tickets, but we're going to try to model our code to represent the "real world" as much as possible. So let's talk about how this works in the real world! Technically, "purchasing a ticket" is a behavior of our passengers -- the passenger is the one who will select a flight and purchase access to a seat, thereby creating a ticket and adding it to the future flights that they have to look forward to. So since this is a behavior of the passenger, we're going to use an instance method in our `Passenger` class to create tickets.

First, let's make sure we exported our `Ticket` class from its file, and then `import` it into the `Passenger` file.

Add an instance method `purchaseTicket` into your `Passenger` class, which takes in the arguments of a `flight` and `seatNumber`. Inside of this method, we want to:

- Create a new ticket
- Add the flight into our `flights` array for that passenger
- `return` the new ticket so that it could be saved as a variable in our code if we wanted

Our first step can always be to "pseudocode" our method, which is to say, we can declare the method and add some comments to explain exactly what we want it to do, even before we start writing actual code. If we were to pseudocode this method, it would look something like this:

```javascript
class Passenger {
  constructor(name) {
    // ...
  }

  purchaseTicket(flight, seatNumber) {
    // Make a new Ticket using the flight and seat passed in
    // Add the flight into the `this.flights` array
    // `return` the new ticket so it can be accessed elsewhere
  }
}
```

Now that we have our pseudocode, try implementing the function in your class!

> **Hint:** Remember that we can use the `this` keyword inside of our `purchaseTicket` method to reference the entire passenger instance that we're dealing with! So if we're handed the `flight` and the `seatNumber` as arguments, we can use `this` to reference our third and missing piece to make a new Ticket: the `passenger`.

When you're done, create a few tickets back in `main.js` by running code similar to this:

```javascript
let maidenFlight = new Flight(
  'September 2',
  '10AM',
  '1PM',
  'Boston',
  'San Diego'
)
let amelia = new Passenger('Amelia Earhart')
let ticket1 = amelia.purchaseTicket(maidenFlight, '6E')
console.log(ticket1)
console.log(amelia.flights)
```

_Fantastic_ job! We now have a fully functional airline! Let's fly!
