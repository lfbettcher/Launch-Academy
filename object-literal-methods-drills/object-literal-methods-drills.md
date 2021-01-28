Let's get some practice with object literal methods!

## Learning Goals

- Add, modify, and retrieve the properties of object literals
- Add methods to our object literals
- Update object literal properties conditionally

## Getting Started

```no-highlight
et get object-literal-methods-drills
cd object-literal-methods-drills
code .
```

Your work will go in the `main.js` file.

### Instructions

Let's create a JavaScript object literal representing classic adventure game hero, `guybrushThreepwood`, the protagonist of the pirate-themed Treasure of Monkey Island.

**Remember** to use `this` as needed when writing your methods!

#### Hi There

1. Set Guybrush's `firstName` to `Guybrush`, his `lastName` to `Threepwood`, and his `title` to `flooring inspector`.
2. Create a method, `greet`, that uses the values of these attributes to print out the greeting `I'm Guybrush Threepwood, flooring inspector`. Run the function in your console to make sure it works!
3. Change Guybrush's title to `mighty pirate` and rerun your function -- it should now print `I'm Guybrush Threepwood, mighty pirate`.

#### Under the Sea

Sometimes even the best of pirates find themselves sent to [Davy Jones' Locker](https://en.wikipedia.org/wiki/Davy_Jones'_Locker).

4. Luckily, Guybrush can hold his breath for 10 whole minutes! Adding new properties, set `guybrushThreepwood`'s `lungCapacityMinutes` to `10`, `breathHeldMinutes` to `0`, and `gameOver` to `false`
5. Create a method, `holdBreath`, that increments `breathHeldMinutes` by `1`.
6. Update the `holdBreath` method so that it includes some conditional logic. If the updated value of `breathHeldMinutes` is greater than or equal to `lungCapacityMinutes`, also set `gameOver` to `true` and print out a consolation message to the user. Try out your method to make sure it works!
6. We've added a lot of attributes to our object -- print out a list of all of `guybrushThreepwood`'s keys.
7. Finally, print out a list of all of its values.

#### [Grog](https://monkeyisland.fandom.com/wiki/Grog)

- Create an `inventory` attribute that is an empty array and a `haveGrog` attribute that is `false`
- Create a method, `addToInventory`, that takes a string as an argument. *If that string is not already in the `inventory` array*, your method should add it.
- Create a method, `checkGrogStatus`, to do the following: if `inventory` contains both `red dye #2` and `battery acid`, print `Makin' some grog!` to the console and set `haveGrog` to true. Otherwise, print `Where has all the grog gone?`.
- Add `red dye #2` to `inventory` using `addToInventory` and run `checkGrogStatus`. Check that the result is what you expected.
- Add `battery acid` to `inventory` using `addToInventory` and run `checkGrogStatus`. Check that the result is what you expected.
