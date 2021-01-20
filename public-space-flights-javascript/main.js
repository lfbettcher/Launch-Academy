// Your code here!
// Part 1
console.log("Hello brave customer! Welcome to our exclusive space flight tours.");
const adultTickets = prompt("How many adult tickets would you like?");
const childTickets = prompt("How many child tickets would you like?");
console.log(`Here are your ${adultTickets} adult tickets and ${childTickets} child tickets!`);

// Part 2
const dehydratedBanana = 1.27
const tofuCake = 4.17
const cheeseSpread = 3.79
const dehydratedIceCreamSandwich = 0.75

// Write a line of code that calculates the cost of the order:
// Two tofu cakes and two dehydrated ice cream sandwiches
const order1 = (2 * tofuCake + 2 * dehydratedIceCreamSandwich).toFixed(2);

// Eight cheese spreads and a dehydrated banana
const order2 = (8 * cheeseSpread + dehydratedBanana).toFixed(2);

// Three of each item on the menu
const order3 = (3 * (dehydratedBanana + tofuCake + cheeseSpread + dehydratedIceCreamSandwich)).toFixed(2);

// Print each answer to the console:
console.log(`'Two tofu cakes and two dehydrated ice cream sandwiches cost $${order1}'`);
console.log(`'Eight cheese spreads and a dehydrated banana cost $${order2}'`)
console.log(`'Three of each item on the menu cost $${order3}'`);