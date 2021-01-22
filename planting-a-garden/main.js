allSeeds = [
  "arugula",
  "kale",
  "romaine",
  "iceberg",
  "beet",
  "potato",
  "carrot",
  "garlic",
  "onion",
  "strawberry",
  "raspberry",
  "blackberry",
  "blueberry",
  "roma tomato",
  "acorn squash",
  "ugly tomato",
  "cherry tomato",
  "butternut squash",
  "jalapeno pepper",
  "cayenne pepper",
  "banana pepper",
  "poblano pepper",
];

fruits = [
  "strawberry",
  "raspberry",
  "blackberry",
  "blueberry",
  "roma tomato",
  "ugly tomato",
  "acorn squash",
  "cherry tomato",
  "butternut squash",
  "jalapeno pepper",
  "cayenne pepper",
  "banana pepper",
  "poblano pepper",
];

vegetables = ["beet", "potato", "carrot", "garlic", "onion"];

leafyGreens = ["arugula", "kale", "romaine", "iceberg"];

myGarden = [];

// We love making homemade sauce (or gravy) for pasta dinners.
// Let's write a line of code to return every kind of tomato seed you can order from the allSeeds array,
// then add those seed names to the array myGarden.
const isTomatoSeed = (seed) => seed.includes("tomato");
myGarden.push(...allSeeds.filter(isTomatoSeed));

// We love having fresh berries on our yogurt.
// Write a line of code to return every berry seed in the allSeeds array,
// then add those seed names to the array myGarden.
const isBerrySeed = (seed) => seed.includes("berry");
myGarden.push(...allSeeds.filter(isBerrySeed));

// We've lost track of what we want to order.
// Write some code that logs each item from your myGarden array,
// then tells you how many plants you've selected in total.
const totalGardenPlants = myGarden.reduce((count, plant) => {
  console.log(plant);
  return count + true;
}, 0);
console.log(totalGardenPlants);
