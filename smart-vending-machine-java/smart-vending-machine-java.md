## Getting Started

```no-highlight
et get smart-vending-machine-java
cd smart-vending-machine-java
code .
```

## Part 1

You think you've tested your prior programs enough to now make a fully-functional vending machine! Well, at least one that properly dispenses words. Write a program that achieves the following steps:

1. Ask the user what vending machine item they would like.
2. Accept the user answer and save it as a variable.
3. Ask the user how many of the vending machine item they would like.
4. Accept the user's answer (should be a number) and save it as a variable
5. Use the answer to print the word the user asked for, and the number that they specified
6. If the user supplied 0 as a number then output "You selected an item, but not a quantity. Please come back when you can decide on both"

### Sample Output

```no-highlight
Hello, I'm the first and only Talking Vending Machine.
What would you like today?
> Bag of chips
How many of those would you like?
> 3
Thank you, here are your 3 Bag of Chips

There you go! Come again!
```

```no-highlight
Hello, I'm the first and only Talking Vending Machine.
What would you like today?
> Bag of chips
How many of those would you like?
> 0

"You selected an item, but not a quantity. Please come back when you can decide on both"
```
