import createNewIngredient from "./createNewIngredient.js";
import createNewMeal from "./createNewMeal.js";
import createNewMenu from "./createNewMenu.js";

// console.log("Let's build a diner!");

// Part One

let egg = createNewIngredient("egg", "protein");
let tomato = createNewIngredient("tomato", "fruit");
let spinach = createNewIngredient("spinach", "vegetable");
let mushroom = createNewIngredient("mushroom", "vegetable");
let feta = createNewIngredient("feta", "cheese");
let cheddar = createNewIngredient("cheddar", "cheese");
let bacon = createNewIngredient("bacon", "meat");
// console.log(egg);
// console.log(tomato);
// console.log(tomato.name);

// Part Two

let greekOmelette = createNewMeal("Greek Omelette", [egg, tomato, spinach, feta]);
let baconCheeseOmelette = createNewMeal("Bacon and Cheese Omelette", [cheddar, egg, bacon]);
let mushroomOmelette = createNewMeal("Mushroom Omelette", [egg, mushroom]);
// console.log("baconCheeseOmelette", baconCheeseOmelette);
// console.log("Is it vegetarian?");
// console.log(baconCheeseOmelette.isVegetarian());
// console.log("Is it delicious?");
// console.log(baconCheeseOmelette.isDelicious());

// console.log("greekOmelette", greekOmelette);
// console.log("Is it vegetarian?");
// console.log(greekOmelette.isVegetarian());
// console.log("Is it delicious?");
// console.log(greekOmelette.isDelicious());

// console.log("mushroomOmelette", mushroomOmelette);
// console.log("Is it vegetarian?");
// console.log(mushroomOmelette.isVegetarian());
// console.log("Is it delicious?");
// console.log(mushroomOmelette.isDelicious());

// Part Three

let breakfastMenu = createNewMenu("Breakfast", 7, 12);
breakfastMenu.addMeal(greekOmelette);
breakfastMenu.addMeal(baconCheeseOmelette);
breakfastMenu.addMeal(mushroomOmelette);
// breakfastMenu.printMenu();

// Exceeds Part Two

let lunchMenu = createNewMenu("Lunch", 12, 16);
let dinnerMenu = createNewMenu("Dinner", 16, 24);
let currentMenus = [breakfastMenu, lunchMenu, dinnerMenu];

const welcomeCustomer = () => {
  // Note: Date.now() is not necessary here to get the current time.
  // I put it here because the instructions said to use it.
  const today = new Date(Date.now());
  const hour = today.getHours();
  let currentMenu;
  if (hour >= breakfastMenu.startTime && hour < breakfastMenu.endTime) {
    currentMenu = currentMenus[0];
  } else if (hour >= lunchMenu.startTime && hour < lunchMenu.endTime) {
    currentMenu = currentMenus[1];
  } else if (hour >= dinnerMenu.startTime && hour < dinnerMenu.endTime) {
    currentMenu = currentMenus[2];
  } else {
    console.log(`Sorry, none of the current menus are active at ${hour}.`);
    return;
  }
  const { title, endTime } = currentMenu;
  console.log(
    `Welcome to our diner! Here's our ${title} menu, which we're serving until ${endTime}.`
  );
  currentMenu.printMenu();
};

welcomeCustomer();
