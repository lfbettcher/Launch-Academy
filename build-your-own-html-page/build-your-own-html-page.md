With our new knowledge of HTML and updating the DOM with JavaScript, let's create our first full webpage!

## Getting Started

```no-highlight
et get build-your-own-html-page
cd build-your-own-html-page
open index.html
code .
```

We've linked to a handy [HTML Cheat Sheet](https://cloud.netlifyusercontent.com/assets/344dbf88-fdf9-42bb-adb4-46f01eedd629/06ffd509-ac92-4b81-bfa0-9c3a34b3ab35/html5-cheat-sheet.pdf) for you to reference as needed. Download and save or bookmark it for future assignments. 

Take a look at the code that has been provided! There is an `index.html` file with an empty `main.js` file attached. The HTML file has the skeleton of a Grocery List webpage - a simple header, an unordered list with one item on it, and a notes section.

## Instructions

We're going to set up the full page using JavaScript! Work through the below instructions using JavaScript, _adding code into your `main.js` file_, in order to fill in your own grocery list webpage. You can hard-refresh your browser page in between each step to see your changes.

1. We've decided to go vegan. Change the existing list item from "Milk" to "Oat Milk".
2. We can't exist on oat milk alone! First, query your unordered list using its id, and store it in a variable. Then, create a variable `fruits` which is an array you create, full of strings of your favorite fruits. Iterate over that array, and for each fruit, create a new list item, add the fruit name to your list item, and add the list item as a child of your unordered list.
3. We need a list of the items in our pantry, so we don't buy anything we already have! Create a new div for your pantry section. Once you've created it, add an id of `pantry-section` to the div. Add this div underneath your `groceries-section`, and add a header to this section to indicate what it's for.
4. Add a new `ul` tag inside the new `pantry-section` div using `.appendChild` to add an unordered list, with an id of `pantry-list`. Then, add a few different list items to this list of things you already have in your kitchen using `.innerHTML`.
5. Woops! We ran out of the last item in our pantry before we went shopping. Remove the last item from your pantry list and add it to the shopping list.
6. Add a third div in your body with an id of `notes-section`. Add a header to indicate that this is the "Notes" section.
7. Add a paragraph tag with some notes on what you want to make for the week. Under the paragraph tag, also add a link to your favorite recipe, with the recipe name as the text appearing on your page.

Great work! You have now created your own grocery webpage!
