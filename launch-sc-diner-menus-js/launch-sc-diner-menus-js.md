We know it's a bit blasphemous to argue that New York can do ANYTHING better than Massachusetts, but there _are_ a few things that our New Yorkers miss - NY bagels, pizza, and classic diners, to name a few. The old-school diners are something that you just can't easily find in the New England area: you know the ones, they seem to be open at all hours of the day, with a menu 10 times the length of any reasonable expectation, and there's at least one in every town.

In an effort to replicate the joys of diner meals past, let's build a virtual diner with all of our dream meals!

## Getting Started

```no-highlight
et get launch-sc-diner-menus-js
cd launch-sc-diner-menus-js
node main.js
```

## Instructions

### Meets Expectations

#### Part One - Ingredients

First, we'll want to set up our ingredients!

Add a file called `createNewIngredient.js` in the project directory. Inside it, add a new function `createNewIngredient` which takes in a `name` and `category` for the ingredient and returns an object with those attributes.

Be sure to export your function at the bottom of the file and import it at the top of the `main.js` file.

When you've finished creating this module, go to the `main.js` file and comment in the code under the "Part One" section. Add some `console.log`s under this code, and run `node main.js` to take a look at what we've created!

#### Part Two - Meals

Now that we have some ingredients, we can start cooking up some meals for our diner's endless menu.

Add a file called `createNewMeal.js` in the project directory. Inside it, add a new function `createNewMeal` which takes in a `name` and an array of `ingredients` for the meal and adds them to an object.

Also in that object, add two methods:

- `isVegetarian` should iterate through all of the ingredients of the meal, and determine if any of them is in the category "meat". If so, it should return `false`, but if not, it can return `true`.
- `isDelicious` should do the same kind of iteration through the ingredients, but this time, it should check if the meal includes a type of cheese. If so, `isDelicious` should be `true`. If not, it should be `false`.

Return the object at the end of the function.

Be sure to once again export your function at the bottom of the file and import it at the top of the `main.js` file. Now, you can comment out the code in `main.js` under "Part Two" to see this code in action.

#### Part Three - Menus

Finally, we have some meals planned! Let's figure out which meals we're going to offer at different times of day, so as to not totally overwhelm our kitchen staff.

Add a file called `createNewMenu.js`, with a function `createNewMenu` inside. This function should take in a title, start time, and end time. _Time can be a bit of a doozy to work with in JavaScript,_ so for now, for simplicity's sake, let's assume we're working with military time in hours only and are storing our times as integers: so for example, breakfast could be the **integers** of `7` to `12`, lunch from `12` to `16`, and dinner from `16` to `24`. Set these attributes as properties of an object. You can also add a property of `meals` which begins as an empty array.

Add a method called `addMeal` to your object. It should take in a new meal, and add it to the `meals` array.

Finally, add an additional method called `printMenu` whose job it is to output the menu in a nice format. This menu should:

- Show the menu title, followed by the timeframe.
- Add the name of each menu item on its own line.
- Print the full menu to the console.

Return the object at the end of your function, and connect your module to your `main.js` file.

You can now comment in the code under "Part Three" in `main.js`. Whether you need to or just want to practice, you can add a debugger right below this code, and hop in a debugger session using what you learned about `inspect-brk` to take a look at your menu! (Note that this debugger session won't mean changing your `main.js` file at all before submitting.)

Be sure to `et submit` your working _Meets_ code before moving on!

### Exceeds Expectations

#### Part One - Refactor

Our first step in exceeding expectations is to make sure our code is as neat as possible! Go through your code and see if there are any spots you can refactor. At a minimum, be sure to include the following refactors:

- Update your `createNewIngredient` function to use **property value shorthand** to make this object with as little repetition as possible.
- Make sure the logged output from `printMenu` is nicely formatted, with each bit of information on its own line.
- Refactor `printMenu` to use **object destructuring** to grab the name of each meal in order to add it to the list.

#### Part Two - Using the `Date` object

Right now, we have a start time and end time for each of our menus, but we're not actually using them other than to print them out! Our staff needs a way to know which menus to give to the customers.

To start, comment in the code under "Exceeds Part Two" in `main.js`. Then, at the bottom of your `main.js` file, define a new _arrow function_ called `welcomeCustomer`. This function should first determine which menu is currently active. It can use the provided `currentMenus` array as its list of options.

In your function, you should use `Date.now()` to determine the current time, and based on that time, figure out which menu is active using the `startTime` and `endTime` of each menu. You can take a look at the Date object documentation to learn more about the Date object and its functionalities: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date

Once the active menu has been determined, the function should `console.log` a message to the customer which says the following:

```no-highlight
Welcome to our diner! Here's our <name of menu> menu, which we're serving until <menu end time>.
```

Make sure to account for your edge cases! `console.log` a different and helpful message in the event that none of the current menus are active at the given time.

Finally, at the end of the file, _invoke_ `welcomeCustomer` to test out your functionality!

_Hint:_ You can test the different `if/else` cases by commenting out your call to `Date.now()` and temporarily replacing it with a date you create with a specific time. Just remember to change it back to use the current time before submitting!

[date-mdn]: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date
