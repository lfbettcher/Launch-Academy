// 1
const greaterThanOrEqualToZero = (number) => number >= 0;

console.log(`5 is greater than or equal to zero: ${greaterThanOrEqualToZero(5)}`);
console.log(`-5 is greater than or equal to zero: ${greaterThanOrEqualToZero(-5)}`);

// 2
const divisibleBy5 = (number) => number % 5 === 0;

console.log(`5 is divisible by 5: ${divisibleBy5(5)}`);
console.log(`71 is divisible by 5: ${divisibleBy5(71)}`);

// 3
const fullName = (firstName, lastName) => `${lastName}, ${firstName}`;

console.log(fullName("Islay", "Mae"));
console.log(fullName("Fred", "Rogers"));

// 4
const sumUp = (number) => {
  let sum = 0;
  for (let i = 1; i <= number; i++) {
    sum += i;
  }
  return sum;
};

console.log(`The sum of numbers from 1 to 4 is ${sumUp(4)}`);
console.log(`The sum of numbers from 1 to 3 is ${sumUp(3)}`);

// 5
const countChar = (findChar, searchString) => {
  let count = 0;
  searchString.split("").forEach((char) => {
    if (char === findChar) count++;
  });
  return count;
};

console.log(`The number of "a"s in "Islay May" is ${countChar("a", "Islay May")}`);
console.log(`The number of "x"s in "Geoffrey" is ${countChar("x", "Geoffrey")}`);

// 6
const getSortedArray = (arr) => [...arr].sort();

const originalArray = [2, 13, 7, 43, 27];
const sortedArray = getSortedArray(originalArray);
console.log("Sorted Array:");
console.log(sortedArray);
console.log("Original Array:");
console.log(originalArray); // check that original was not modified

// 7
const repeatName = (number, name) => {
  for (let i = 0; i < number; i++) {
    console.log(name);
  }
};

console.log('Repeat "Mae" 3 times:');
repeatName(3, "Mae");

// 8
const minutesToSeconds = (minutes) => `${minutes * 60} seconds`;

console.log(`3 minutes = ${minutesToSeconds(3)}`);

// 9
const firstLast = (arr) => [arr[0], arr[arr.length - 1]];

const array1 = ["duck", "duck", "quail", "goose"];
const array1FirstLast = firstLast(array1);
console.log("Array 1:");
console.log(array1);
console.log("Array 1 first and last element:");
console.log(array1FirstLast);

const array2 = [2, 4, 6, 8, 10];
const array2FirstLast = firstLast(array2);
console.log("Array 2:");
console.log(array2);
console.log("Array 2 first and last element:");
console.log(array2FirstLast);

// 10
// Expand on #4 sumUp. Prints the equation showing addition: 1 + 2 + 3 + 4 = 10
// This is a "fencepost problem" which requires handling the "off-by-one error".
// If the + sign is printed before the number, there will be an unwanted + before 1
// If the + sign is printed after the number, there will be an unwanted + after 4

// Version 1: Print + sign before iterated number
const sumUpEquation1 = (number) => {
  let sum = 1;
  let equation = "1"; // establish "fencepost" at the front
  for (let i = 2; i <= number; i++) {
    // i goes to the last number
    equation += ` + ${i}`;
    sum += i;
  }
  return `${equation} = ${sum}`;
};

console.log(sumUpEquation1(4));
console.log(sumUpEquation1(3));

// Version 2: Print + sign after iterated number
const sumUpEquation2 = (number) => {
  let sum = 0;
  let equation = "";
  for (let i = 1; i < number; i++) {
    // i stops 1 short of the last number
    equation += `${i} + `;
    sum += i;
  }
  equation += number; // last number
  sum += number;
  return `${equation} = ${sum}`;
};

console.log(sumUpEquation2(4));
console.log(sumUpEquation2(3));
