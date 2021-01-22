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

// 1 (written in two parts)
const isTomatoSeed = (seed) => seed.includes("tomato");
myGarden.push(...allSeeds.filter(isTomatoSeed));

// 2 (written in one part)
myGarden.push(...allSeeds.filter((seed) => seed.includes("berry")));

// 3
myGarden.push(...allSeeds.filter((seed) => seed.includes("pepper") && !seed.includes("cayenne")));

// 4
const availableSeeds = [...allSeeds.filter((seed) => !myGarden.includes(seed))].sort();

// 5
const notFruits = fruits.filter(
  (fruit) => fruit.includes("tomato") || fruit.includes("pepper") || fruit.includes("squash")
);
fruits = fruits.filter((fruit) => notFruits.indexOf(fruit) === -1);
vegetables = vegetables.concat(notFruits);

// 6
const allArrays = [allSeeds, fruits, vegetables, leafyGreens, myGarden, availableSeeds];
for (let i = 0; i < allArrays.length; i++) {
  allArrays[i] = allArrays[i].filter((item) => !item.includes("iceberg"));
}

// 7
myGarden.push(allSeeds.find((seed) => seed.includes("squash")));

// 8
const totalGardenPlants = myGarden.reduce((count, plant) => {
  console.log(plant);
  return count + true;
}, 0);
console.log(totalGardenPlants);
