const eggs = {
  itemName: "dozen eggs",
  quantity: 2,
  price: 0.99,
};

const milk = {
  itemName: "gallon of milk",
  quantity: 1,
  price: 2.99,
};

const bread = {
  itemName: "loaf of bread",
  quantity: 1,
  price: 3.5,
};

const coffee = {
  itemName: "lbs. of coffee",
  quantity: 10,
  price: 8.99,
};

const shoppingCart = [eggs, milk, bread, coffee];

// What's the price?
// Write code to answer the following questions about the items in the shopping cart:
// How much is one pound (lb.) of coffee?
const coffeeItem = shoppingCart.find((item) => item === coffee);
console.log(`One pound of coffee is $${coffeeItem.price}.`);

// How many gallons of milk are in the shopping cart?
const milkItem = shoppingCart.find((item) => item === milk);
const milkQty = milkItem.quantity;
console.log(
  `There ${milkQty === 1 ? "is" : "are"} ${milkQty} gallon${
    milkQty === 1 ? "" : "s"
  } of milk in the cart.`
);

// Subtotal and Total
// Iterate over the array, and calculate the sub-total of the items in the shopping cart.
// This would be the total cost of the items without tax. Print this value to the console.
let subtotal = 0;
shoppingCart.forEach((item) => {
  subtotal += item.price * item.quantity;
});
console.log(`The subtotal is $${subtotal.toFixed(2)}.`);

// Next, calculate the total cost of the items, including tax. Use 6.25% as the tax rate.
const taxRate = 6.25;
console.log(`The total is $${(subtotal * (1 + taxRate / 100)).toFixed(2)}.`);
