let supplies = [
  "space helmet",
  "canister of oxygen",
  "water recycler",
  "big red button",
  "freeze dried ice cream",
  "tang drink mix",
];

// 1. Supply Check
const supplyCheck = (suppliesArray) =>
  suppliesArray.forEach((item) => console.log(`${item} is logged and accounted for.`));

supplyCheck(supplies);

// 2. Stowing Supplies
const stowSupplies = (suppliesArray) =>
  suppliesArray.forEach((item, index) => console.log(`${item} is in locker ${index + 1}.`));

stowSupplies(supplies);

// 3. Sorting Supplies and Adding New Ones
const addAlphabetically = (suppliesArray, newItem) => {
  const newArray = [...suppliesArray];
  newArray.push(newItem);
  newArray.sort();
  return newArray;
};

supplies = addAlphabetically(supplies, "laser pistol");

// 4. Checking Our Inventory Checklist
const inventoryChecklist = [
  "big red button",
  "canister of oxygen",
  "freeze dried ice cream",
  "jetpack",
  "tang drink mix",
  "space helmet",
  "space brussels sprouts",
  "water recycler",
  "welding torch",
];

// Returns array of items that are missing from checklist
const stockCheck = (checklistArray, suppliesArray) =>
  checklistArray.filter((item) => !suppliesArray.includes(item));

// 5. Combining our Supply Arrays
const missingSupplies = stockCheck(inventoryChecklist, supplies);

const addMissingSupplies = (suppliesArray, missingArray) =>
  suppliesArray.concat(missingArray).sort();

supplies = addMissingSupplies(supplies, missingSupplies);
