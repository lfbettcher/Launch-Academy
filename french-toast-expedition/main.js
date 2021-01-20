// your code, here
const loafOfBread = 1.99;
const gallonOfMilk = 3.89;
const dozenEggs = 2.29;
let totalCost = (loafOfBread + gallonOfMilk + dozenEggs).toFixed(2);

// Nor'easter causes ingredients to cost 3x
totalCost = (3 * totalCost).toFixed(2);

console.log(`Your total cost is $${totalCost}`)