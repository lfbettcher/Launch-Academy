console.log("Hello, I'm the first and only Talking Vending Machine.");

// Ask the user what vending machine item they would like.
let item;
do {
  item = prompt("What would you like today?");
} while (!item);

// Ask the user how many of the vending machine item they would like.
const quantityPrompt = `How many ${item}s would you like?`;
let quantity = prompt(quantityPrompt);
while (!quantity.includes("tons") && Number.isNaN(parseInt(quantity))) {
  quantity = prompt(`Please enter an integer or 'tons'.\n${quantityPrompt}`);
}

// If "tons" in answer, print the item a random number of times less than 20.
if (quantity.includes("tons")) {
  // random number of times less than 20
  quantity = Math.floor(Math.random() * 20);
} else {
  quantity = parseInt(quantity);
}

// Use a while loop to print the item the user asked for, the number of times they specified
while (quantity > 0) {
  console.log(item);
  quantity--;
}
console.log("There you go! Come again!");
