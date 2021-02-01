Finally, the warm days of summer have arrived! We're installing a new pool in our backyard, and notice that the pool company is asking for a bunch of different information on the size of the space we have available for our pool. It would be great to simply be able to measure the space, and not have to manually calculate all of the extra information they're asking for. Let's work on a program that allows us to simply plug in dimensions for our rectangular pool space, and let the computer do the rest of the work!

## Getting Started

```no-highlight
et get pool-measurements-js
cd pool-measurements-js
code .
```

### Instructions

Define a new `class` that represents a `Pool` in `Pool.js`. Make sure to export it at the bottom of the file using `export default`!

Constructing a `Pool` should require two arguments, the `width` and the `length`. This information should be retained as part of each object's **state**, also known as **attributes**.

Add the below code into your `main.js` file. Once saved, you should be able to replicate the below output by running `node main.js`.

In `main.js`:

```javascript
import Pool from './Pool.js'

console.log(new Pool(12, 10))
console.log(new Pool(4, 6))
console.log(new Pool(10, 15))
```

In your terminal, you should see

```no-highlight
Pool { width: 12, length: 10 }
Pool { width: 4, length: 6 }
Pool { width: 10, length: 15 }
```

### Allow Square Pools

We're also in the market for a hot tub, and since we're already trying to make our lives easier, we only want to input the bare minimum of information! Modify your `Pool` class so that it only requires a single argument, but allows an optional, second argument.

If a single argument is specified, assume the `Pool` is a square, where both the `width` and `length` are equal. Remember that we can add logic into our constructor as needed!

In `main.js`:

```javascript
console.log(new Pool(12, 10))
console.log(new Pool(8, 6))
console.log(new Pool(5))
console.log(new Pool(9))
```

In your terminal, you should see

```no-highlight
Pool { width: 12, length: 10 }
Pool { width: 8, length: 6 }
Pool { width: 5, length: 5 }
Pool { width: 9, length: 9 }
```

### Give the Pool Behavior

Our pool company needs to know the perimeter of our proposed pool so that they can calculate pricing for the tile along the outsides. Add a method to your `Pool` class that calculates the area of a `Pool` object, based on its width and length.

In `main.js`:

```javascript
let pool = new Pool(10, 15)
console.log(pool.area())
let anotherPool = new Pool(5)
console.log(anotherPool.area())
```

In your terminal, you should see

```no-highlight
150
25
```

**Hint**

- The area of a rectangle is defined as its `width` multiplied by its `length`
- You will need to create a method in order to calculate and return the area of the Pool object.

Finally, our pool company needs to know how much water to use to fill our pool up! Add a method to your `Pool` class that calculates the volume of the `Pool` in cubic feet, based on an expected water depth of 5 feet.

In `main.js`:

```javascript
let pool = new Pool(10, 15)
console.log(pool.volume())
let anotherPool = new Pool(5)
console.log(anotherPool.volume())
```

In your terminal, you should see

```no-highlight
750
125
```


**Hint**
- The volume of a rectangle is defined as its `width` multiplied by its `length` multiplied by its `depth`
- You will need to create a method in order to calculate and return the volume of the Pool object.
- Is there a way to use the code you've already written to your advantage? 
