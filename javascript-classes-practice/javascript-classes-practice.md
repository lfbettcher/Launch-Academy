Let's get some practice writing JavaScript classes! There are so many objects in the real world that can be represented using Object-Oriented Programming. Let's build out the below classes in order to start building up our OOP way of thinking.

## Getting Started

Run the following commands in your terminal:

```no-highlight
et get javascript-classes-practice
cd javascript-classes-practice
code .
```

For each challenge below, create any necessary classes in their own file within this project directory. The file should have the same name as the class. Make sure to export your class at the bottom of the file! Then, import that class into the provided `main.js` file. We have provided a `Person` class as a reference for you to model your own classes after.

Once you've done that, you will be able to add code into your `main.js` file and run this file with the command `node main.js`. Try giving it a run right now to see the output from our sample `Person` class!

### Challenge One

Oh, the places we'll go! There are so many methods of transportation out there, and we can model them with classes to keep track of all of our options. Here, we'll focus on driving and flying.

First, we need to think about what classes we should use to model transportation. We could build separate classes for each type of car (e.g., `Sedan`, `PickupTruck`) and separate classes for each type of plane (e.g., `Biplane`, `JumboJet`). However, we'd probably find ourselves repeating a lot of attributes and methods among the car classes and among the plane classes. Instead, let's break our classes down by _mode_ of transportation.

First, let's build a `Car` class. Our cars should take in a `make`, `model`, and `mileage` and store them as attributes. Add a `drive` method to this class, which takes a number of miles driven as an argument and adds this number to the car's mileage.

Next, let's add a `Plane` class! Our `Plane` constructor should have parameters of the plane's `make` and `model`, just like our `Car`. But our planes should also take in a `capacity` so that we know how many seats there are! Finally, our constructor should add a `passengerCount` attribute that starts at 0.

Finally, add a `board` method to our `Plane` class that doesn't take any arguments. Each time `board` is called, it should check if the plane is at capacity. If it is not at capacity, the function should add a passenger to our `passengerCount`, and console.log the following string: 'The plane has (passengerCount) passengers of an allowed (capacity)". If the plane _is_ at capacity, the method shouldn't alter the `passengerCount`; instead, it should simply output a string that says "This plane is at capacity!"

##### Testing your Code:

Add the following code to your `main.js` file:

```javascript
// add our "Toyota Camry" with a mileage of 5000
let car = new Car('Toyota', 'Camry', 5000)
console.log(car)
console.log(`Our car's mileage is ${car.mileage}`)
car.drive(467)
console.log(`Our car's mileage is ${car.mileage}`)

// add our "Launch 783" plane with a capacity of 2
let plane = new Plane('Launch', '783', 2)
console.log(plane)
plane.board()
plane.board()
plane.board()
```

When you run `main.js`, you can expect output as follows:

```no-highlight
Car { make: 'Toyota', model: 'Camry', mileage: 5000 }
Our car's mileage is 5000
Our car's mileage is 5467
Plane { make: 'Launch', model: '783', capacity: 2, passengerCount: 0 }
The plane has 1 passengers of an allowed 2
The plane has 2 passengers of an allowed 2
This plane is at capacity!
```

### Challenge Two

Let's build some classes that interact with each other! How about a deck of cards?

First, create a `Card` class that stores the `rank` and `suit` as attributes.

Then, create a `Deck` class that instantiates with no arguments, and an empty `cards` array as an instance variable.

Add an `addCard` method to your `Deck` class which takes in a card object as an argument, and adds it to the `cards` array.

##### Testing your Code:

Add the following code to your `main.js` file:

```javascript
let card = new Card('10', 'diamonds')
console.log(card)
let deck = new Deck()
console.log(deck)
deck.addCard(card)
console.log(deck)
console.log(deck.cards)
```

When you run `main.js`, you can expect output as follows:

```no-highlight
Card { rank: '10', suit: 'diamonds' }
Deck { cards: [] }
Deck { cards: [ Card { rank: '10', suit: 'diamonds' } ] }
[ Card { rank: '10', suit: 'diamonds' } ]
```

### Challenge Three

Let's build a library! Launchers have quite the diverse book collection, and we want to keep track of these books.

Before we can have books, we need to have authors! Create an `Author` class, with attributes of `firstName`, `lastName`, and `genre`. Add an `anthology` attribute which is initialized as an empty array.

Then, let's create a `Book` class. A book should store a `title`, `pageLength`, and `publicationYear`.

Finally, add a method to your `Author` class called `writeBook`, which takes in a `book` object as an argument and adds it to the author's anthology.

Try writing some code in `main.js` to create some objects and `console.log` to test your output!

### Challenge Four - Build Your Own!

Take a minute to think about _your_ favorite hobby, and how you might represent it using code and object-oriented programming. What classes would you have? What state, or attributes, would they need to store? What behaviors, or methods, would they have? How might they interact with each other?

Try it out for yourself! Build your classes and `console.log` some information to take a look at what you've created.
