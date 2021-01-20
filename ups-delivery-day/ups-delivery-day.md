It's not easy getting next day delivery when businesses are on different planets. That's where Universal Package Deliveries comes in! UPS is _the_ number one delivery service in the Universe. Next-day isn't possible, but they sure do try. Let's help build a program that makes their work even easier. 

## Getting Started

From your `challenges` folder in the terminal, type the following commands:

```no-highlight
et get ups-delivery-day
cd ups-delivery-day
open index.html
code .
```

Create your new `main.js` file:
```no-highlight
touch main.js
```

> your work will go in `main.js`

In the browser, open up the JavaScript console. You can refresh the page in order to see your changes.

### Instructions

#### Part 1

As a business, I want to enter the item I am ordering, and receive a price and a confirmation back.

Prompt the user to enter the name of the item, randomly assign a price between $1 - $100, and `console.log` the following :

Example output:
```no-highlight
"You would like to order 1 laser, costing $61.00 each."
```

Using `.random()` can be tricky at first! Here's a step by step overview of how it works, sourced from the ever-wonderful [StackOverflow](https://stackoverflow.com/questions/1527803/generating-random-whole-numbers-in-javascript-in-a-specific-range)


#### Part 2

So far, we can only order one item at a time. Let's make it easier to order items in bulk. Update your program so it now also asks the user how many of each item they'd like, calculate the total based on the randomized price, and `console.log` the total cost.

Example output:
```no-highlight
"You would like to order 3 staplers, costing $12.00 each. Your total cost is $36.00."
``` 

#### Part 3

It's great that customers can order multiple items and learn the cost, but what they really want to know is how many days it will take to reach them. Randomly generate an integer between 3 - 12 and assign it to a length of delivery variable. Then `console.log` your final message to the user.

Example output:
```no-highlight
"You would like to order 5 phasers, costing $70.00 each. Your total cost is $350.00 and the order will be delivered in 7 days."
``` 

### Submitting Your Code

Once you have completed this exercise, use the `et submit` command to submit your code from this project's folder.


