It's important to stay connected with what's going on in the world (even if that means spending hours scouring reddit or news forums rather than accomplishing anything productive). Let's build a news aggregator so we can share some pointless articles and help others waste time effectively.

### Learning Objectives

- Build a NodeJS application that receives user input via HTTP POST requests.
- Persist user data on the server by writing to JSON documents.
- Make use of the HandleBars JS library for templating dynamic web pages.

### Getting Setup

```no-highlight
et get news-aggregator-nodejs
cd news-aggregator-nodejs
yarn install
yarn run dev
```

Remember that you can run your program with a debugger attached by running `yarn run dev:debug` instead!

### Overview

News Aggregator is an app where a user can view a list of articles and add to an existing list of articles. In this assignment, data will be maintained and persisted in the `articles.json` file provided in the root of your app. The code below shows examples of how one can interact with an `articles.json` file in your `Article.js`:

```js
import fs from "fs"

// path to your articles.json file
const articlesPath = "articles.json"

// read from the articles.json file
const articlesJson = () => {
  return JSON.parse(fs.readFileSync(articlesPath))
}

// add to the articles.json file.
const updateArticlesJsonData = (newArticleObject) => {
  const articles = articlesJson().articles
  articles.push(newArticleObject)

  const data = { articles }
  fs.writeFileSync(articlesPath, JSON.stringify(data))
}
```

- `articlesPath` is a helper variable to designate the path for our `articles.json` file
- `articlesJson` retrieves the current list of articles and returns them as an object with a key of `articles`. Make sure to examine this returned object closely so that you can use it correctly in Handlebars. Remember that a single key means the key and the value are the same variable (as in `{ articles: articles }`).
- `updateArticlesJsonData` takes an article object (that have keys of title, url and description) and persists it to the `articles.json` file.

##### Important Tips:

- In order to use `updateArticlesJsonData()` correctly, you will need to retrieve params and prepare an object to be passed to the method.
- A URL is valid if it begins with `http://` or `https://`.

### Instructions

Build a web application for news articles using NodeJS and Express that displays a list of articles that users have submitted.

The application should satisfy the following user stories:

#### Articles Index Page

```no-highlight
As a procrastinator
I want to be able to visit a page that shows me all the submitted articles
So that I can procrastinate
```

Acceptance Criteria:

- When I visit `/articles` I should be able to see all the articles that have been submitted.
- Each article should show the description, title, and URL.
- If I click on the URL it should take me to the website designated inside of a new tab.

Implementation Details:

- The articles should be made available using an `Article` model, which has a static `findAll()` method.
- `Article` takes in a `title` as an argument, a `url` as a second argument, and an `description` as an optional third argument
- This `Article` model should be used within your `articlesRouter` to set up the corresponding route's functionality.

#### New Article Page

```no-highlight
As a procrastinator
I want to be able to submit an incredibly interesting article
So that other procrastinators may benefit
```

Acceptance Criteria:

- When I visit `/articles/new` it has a form to submit a new article.
- The form accepts an article title, URL, and description.
- When I successfully post an article, it should be saved to a JSON file.

Implementation Details:

- The new article should be persisted via a `save()` method on the `Article` model, which saves an existing Article object to the `articles.json` file.

### Optional User Stories

For an extra **optional** challenge, implement the following additional user stores.

_Only tackle these additional user stories if you have completed the Core User Stories._ You may choose any order in which to approach these stories, but the order in which they appear is suggested.

#### Error Handling

```no-highlight
As an errant procrastinator
I want to receive an error message
When I submit an invalid article
```

Acceptance Criteria:

- If I do not specify a title, URL, and description, I receive an error message, and the submission form is re-rendered with the details I have previously submitted.
- If I specify an invalid URL, I receive an error message, and the submission form is re-rendered with the details I have previously submitted.
- If I specify a description that doesn't have 20 or more characters, I receive an error message, and the submission form is re-rendered with the details I have previously submitted.
- The submitted article is not saved in any of the above cases.

Implementation Details:

- The validation should be handled via an `isValid()` method on the `Article` model
- `isValid()` should create an object of arrays of errors as an instance variable `this.errors`, for example:

```javascript
{
  title: ["Can't be blank"],
  url: ["Can't be blank", "Must be valid"]
}
```

- `save()` should call on `isValid` to check for any errors before saving to the file

```no-highlight
As a plagiarizing procrastinator
I want to receive an error message
When I submit an article that has already been submitted
```

Acceptance Criteria:

- If I specify a URL that has already been submitted, I receive an error message, and the submission form is re-rendered with the details I have previously submitted.
- The submitted article is not saved in the above case.

#### Implement Pagination

- Limit the number of items on the index page to the first ten retrieved
- As a user, I should be able to see displayed what "page" of results I am on, so that I can select the next page and see the next ten results. I.e. When the index page is first loaded, I should see "1" rendered below my list, designating that I am on the first page of results. To the right of this one, should be links corresponding to the second, third and fourth pages (2, 3, 4 respectively).
  Note: you may wish to seed your CSV or JSON file with >20 entries to properly test this

#### Style Your App

- Choose a background theme, default header color and font style.
- Ensure that your article entries are centered on the page.
- Add a NavBar to help your user navigate your website
- Use flexbox or a framework like Foundation to add further styles to your site.
