const pizza = 15.0;
const extraCheese = 2.0;
const deliveryFee = 3.0;
let driverTip = 0.0;

// Part 1
/*
alert("Hello hungry friend! Welcome to Doordish, where the pizza comes to you!");

// Get user input for the order
const takeoutOrDelivery = prompt("Is your order takeout or delivery?").toLowerCase();
const numPizzas = parseInt(prompt("How many pizzas would you like?"));
const wantsExtraCheese = prompt("Would you like extra cheese?").toLowerCase();

// Output a sentence returning the order
const s = numPizzas === 1 ? "" : "s";
const cheese = wantsExtraCheese === "no" ? "no " : "";
const pickedupOrDelivered = takeoutOrDelivery === "takeout" ? "picked up at" : "delivered to";
const y = takeoutOrDelivery === "delivery" ? "y" : "";

alert(`We are preparing your ${numPizzas} pizza${s} with ${cheese}extra cheese to be ${pickedupOrDelivered} ${y}our door!`);
*/

// Part 2
/*
alert("Hello hungry friend! Welcome to Doordish, where the pizza comes to you!");

// Get user input for the order
const takeoutOrDelivery = prompt("Is your order takeout or delivery?").toLowerCase();
// Ask user for tip if delivery
if (takeoutOrDelivery === "delivery") {
  driverTip = parseFloat(prompt("How much do you want to tip the driver?"));
}
const numPizzas = parseInt(prompt("How many pizzas would you like?"));
const wantsExtraCheese = prompt("Would you like extra cheese?").toLowerCase();

// Construct output sentence
const s = numPizzas === 1 ? "" : "s";
const cheese = wantsExtraCheese === "no" ? "no " : "";
const pickedupOrDelivered = takeoutOrDelivery === "takeout" ? "picked up at our door!" : "delivered.";

let output = `We are preparing your ${numPizzas} pizza${s} with ${cheese}extra cheese to be ${pickedupOrDelivered}`;
if (takeoutOrDelivery === "delivery") {
  output += ` Thanks for tipping $${driverTip.toFixed(2)}!`;
}

alert(output);
*/

// Part 3
/*
alert("Hello hungry friend! Welcome to Doordish, where the pizza comes to you!");

// Get user input for the order
const takeoutOrDelivery = prompt("Is your order takeout or delivery?").toLowerCase();
// Ask user for tip if delivery
if (takeoutOrDelivery === "delivery") {
  driverTip = parseFloat(prompt("How much do you want to tip the driver?"));
}
const numPizzas = parseInt(prompt("How many pizzas would you like?"));
const wantsExtraCheese = prompt("Would you like extra cheese?").toLowerCase();
// Alert cheese cost
if (wantsExtraCheese === "yes") {
  alert(`That will cost you an additional $${(extraCheese * numPizzas).toFixed(2)}`);
}

// Construct output sentence
const s = numPizzas === 1 ? "" : "s";
const cheese = wantsExtraCheese === "no" ? "no " : "";
const pickedupOrDelivered = takeoutOrDelivery === "takeout" ? "picked up at our door!" : "delivered.";

let output = `We are preparing your ${numPizzas} pizza${s} with ${cheese}extra cheese to be ${pickedupOrDelivered}`;
if (takeoutOrDelivery === "delivery") {
  output += ` Thanks for tipping $${driverTip.toFixed(2)}!`;
}

alert(output);
*/

// Part 4
alert("Hello hungry friend! Welcome to Doordish, where the pizza comes to you!");

// Get user input for the order
const takeoutOrDelivery = prompt("Is your order takeout or delivery?").toLowerCase();
if (takeoutOrDelivery === "delivery") {
  driverTip = parseFloat(prompt("How much do you want to tip the driver?"));
}
const numPizzas = parseInt(prompt("How many pizzas would you like?"));
const wantsExtraCheese = prompt("Would you like extra cheese?").toLowerCase();
if (wantsExtraCheese === "yes") {
  alert(`That will cost you an additional $${(extraCheese * numPizzas).toFixed(2)}`);
}

// Calculate total cost and construct output sentences
let totalCost = numPizzas * pizza;
let costSummary = "Your total comes to $";

if (wantsExtraCheese === "yes") {
  totalCost += numPizzas * extraCheese;
}
if (takeoutOrDelivery === "delivery") {
  totalCost += driverTip + deliveryFee;
  costSummary = `With a $${driverTip.toFixed(2)} tip and a delivery fee, y${costSummary.slice(1)}${totalCost.toFixed(2)}`;
} else {
  costSummary += totalCost.toFixed(2);
}

const s = numPizzas === 1 ? "" : "s";
const cheese = wantsExtraCheese === "no" ? "no " : "";
const pickedupOrDelivered = takeoutOrDelivery === "takeout" ? "picked up at our door!" : "delivered.";

const orderSummary = `We are preparing your ${numPizzas} pizza${s} with ${cheese}extra cheese to be ${pickedupOrDelivered}`;

// Final output
alert(`${orderSummary} ${costSummary}.`);
