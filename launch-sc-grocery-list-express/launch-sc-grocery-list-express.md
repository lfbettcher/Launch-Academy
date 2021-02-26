### Instructions

Write a web application to manage a grocery list. It should display the groceries currently on the list and provide a form for adding a new item to buy.

### Getting Set Up

```no-highlight
et get launch-sc-grocery-list-express
cd launch-sc-grocery-list-express
yarn install
yarn run dev
```

### Meeting Expectations Requirements

Your first submission should satisfy the following requirements:

- Visiting `/` should redirect the user to `GET /groceries`.
- Visiting `/groceries` should display a list of groceries that you need to purchase and a link to the new item form page with the text "Add a Grocery Item".
- The name of each grocery item must be in [an `<li>` element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/li).
- The list of groceries is read from the `groceries.json` file, which stores each item, via a `Grocery` model.
- Visiting `/groceries/new` should display a form to add a new grocery to your list and a link with the text "Groceries Index Page" to direct back to `"/groceries"`.
- The form to add a new grocery item allows the grocery name to be specified.
- The form submits a `POST` to `/groceries` which saves the new item to the `groceries.json` file via the `Grocery` model.
- After submitting the form successfully, the user is redirected to the `/groceries` page where the grocery list is displayed with the new item.
- If the name of a grocery is not supplied when submitting the form, the user should not be redirected to `/groceries` and should instead remain on the form page.
- An unsuccessful form submission should not edit `groceries.json`

### Tip

* As always, take a moment to familiarize yourself with the codebase. Look at each provided file to get an idea of what is expected of you.

### Exceeding Expectations Requirements

**STOP and make sure you've submitted your Meets Expectations work.**

- Visiting `/groceries` should now give the user the option to add a random item to the existing list by clicking a `Add Random Item` button. Follow the below implementation details to implement this feature.
- Comment in lines 14 and 15 of the `rootRouter`, so that an `apiGroceriesRouter` is being imported and used.
- Create the new API-specific `groceriesRouter`, and add an API endpoint within it with a path of "/random", so that the API endpoint is active at <http://localhost:3000/api/v1/groceries/random>.
- A `GET` request to this API endpoint should return one random item from the `exceeds_random_items.json` file. Remember to use your model to obtain a random Grocery!
- Add a button to the groceries index page (in `index.hbs`) with the text "Add Random Item". 
- When this button is clicked, this should send off a `fetch` request to the API endpoint to obtain one random item.
- Once retrieved, the item should be added to the existing list on the page using DOM manipulation.
- Additionally, clicking the `Add Random Item` button should also add said item to the `groceries.json` file - such that upon refreshing the page, the random item is still listed.