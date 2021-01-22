const shoppingList = ["pop tarts", "ramen noodles", "chips", "salsa", "coffee"];
shoppingList.push("fruit loops");
shoppingList[shoppingList.indexOf("coffee")] = "fair trade coffee";
shoppingList[shoppingList.indexOf("chips")] = "rice";
shoppingList[shoppingList.indexOf("salsa")] = "beans";

const cart = [];
cart.push(shoppingList.pop());
cart.push(shoppingList.shift());

while (shoppingList.length > 0) {
  cart.push(shoppingList.pop());
}

cart.sort().reverse();
console.log(cart.join(", "));
