Now that we have completed all the core criteria for `news-aggregator-nodejs`, it's time to take it one step further by adding in `fetch`!

### Learning Objectives

- Create an API endpoint that will accept a `fetch` request.
- Make use of event listeners to attach functionality onto a button.

### Getting Setup

```no-highlight
et get news-aggregator-nodejs-fetch
cd news-aggregator-nodejs-fetch
yarn install
yarn run dev
```

### Overview

A completed version of `news-aggregator-nodejs` has been provided as a starting point. Currently we have an index page where we are able to view all the articles stored in `articles.json` and a new article page where there is a form to add a new article. The next step will be to add `random` page that users can visit and click on a button to see one random article at a time. We will accomplish this by using `fetch` to interact with an API endpoint that we will create!

#### Retrieving a Random Article with Fetch

```no-highlight
As a web-surfing procrastinator
I want to see a random article
So that I can spend less time choosing which article to read
```

Acceptance Criteria:

- When I visit `/random` it has a button with the text "Random Button".
- When the button is clicked, a fetch call generates a request to an API endpoint on the Express backend

Implementation Details:

- The API endpoint should be at `/api/v1/articles/random`.
- The API endpoint returns a random article as a response, and that article's information will need to append to the page.

Tips:

- You may wish to review past JavaScript DOM manipulation assignments in order to recall how to manipulate the DOM with JS on the front end and add JS event handlers.
- You'll need to find a folder titled `public` in the root of your application. That folder should also have a `js` folder in it e.g. `public/js`. This folder is where you should keep the JavaScript file that will run on your page e.g. `public/js/client.js`.
- In your JavaScript file, you'll want to add an event listener that will trigger your fetch request.
- Upon a successful fetch request, you will need to write JavaScript code that will append your article information to an element on the DOM
- In order to have that JavaScript run on your page, you will need to add a script tag to the bottom of the `random.hbs` template that you create.

```html
<script src="/js/app.js"></script>
```

- Use your new JSON skills to retrieve a random article from your `articles.json`. Ensure that you use the proper syntax for returning json data instead of HTML in your endpoint:

```js
// src/routes/api/v1/articlesRouter.js

articlesRouter.get("/random", (req, res) => {

  // retrieve a random article object

  res.set({ "Content-Type": "application/json" }).json(<INSERT-THE-JSON-OBJECT-YOU-WISH-TO-RETURN>)
})
```
