const itemName = prompt("Enter the name of the item")
const itemQty = prompt("How many of each item?")
const itemPrice = getRandomNumber(1, 100).toFixed(2)
const days = Math.round(getRandomNumber(3, 12))

// pluralize item name
const s = itemQty > 1 ? "s" : ""

console.log(`You would like to order ${itemQty} ${itemName}${s}, \
costing $${itemPrice} each. \
Your total cost is $${(itemQty * itemPrice).toFixed(2)} \
and the order will be delivered in ${days} days.`)

// Gets a random number between min and max (inclusive)
function getRandomNumber(min, max) {
    return Math.random() * (max - min + 1) + min;
}