Let's get some practice in with Java loops.

## Getting Started

```no-highlight
et get java-looping-drills
cd java-looping-drills
idea .
```

## Drills

### Count Down

Count down from 1000 to 711, printing each number on its own line. Skip numbers divisible by 15.

### Sum Numbers

- Ask the user for two numbers
- Assume the user will always enter integers
- Validate that the second number is larger than the first. If the user enters invalid numbers, exit the program
- Calculate the sum of the numbers that are in between the two provided numbers, without including the numbers inputted. IE, if the user enters 2 and 6, the resulting sum would be `12` (3 + 4 + 5)

### Multiplication Table Prompt

- Ask the user for a number
- Print the products of that number with every number from 1 - 12

IE, for 4 the sample output would be:

```no-highlight
4 8 12 16 20 24 28 32 36 40 44 48
```

### Odd-Even Checker

- Ask the user for a number
- Output whether it is odd or even
- Ask the user to enter another number to check
- Continue this until the user types "exit"
- Be sure to handle for bad input (when Java integer parsing fails) - allow the user to attempt to re-enter a number

### Prime Checker

A number is prime if it is not the product of two smaller natural numbers. IE, 7 is prime because it is only evenly divisible by 7 (itself) and 1.

Write a program that uses a loop to determine if a user supplied number is prime or not.

## Non-Core Drills

## Multiplication Table

- Use the code from "Multiplication Table Prompt"
- Automatically generate the multiplication table for every number from 1 to 12 without prompting the user for any data
- Extract a method that takes a single argument, and returns a string representing the multiplication table

## Continuous Sum

- Write a program that asks the user to add numbers until they specify "done"
- With each number entered, add it to a running total of all numbers entered
- Be sure to handle for the user specifying an invalid number. The user should be given an opportunity to re-enter a number to continue on with the calculation
