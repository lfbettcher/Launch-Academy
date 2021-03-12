You will be building a simple blog in React!

This blog will have an index page showing recently submitted articles, as well as a form to add more articles. Clicking on the name of an article on your index page will bring the user to a show page for that article.

## Setup

From your challenges directory, run the following:

```no-highlight
et get launch-sc-react-simple-blog-express
cd launch-sc-react-simple-blog-express
yarn install
yarn run dev
```

Visit <http://localhost:3000> in your browser. You should see a page that simply says `Replace this div with your Router.` There should be no errors in your JavaScript console. You should plan to follow the instructions on the page as you work through this system check.

**Reminder:** Please be sure to do a **hard refresh** in your browser for changes to take effect (**Shift + Click Refresh** or **Command + Shift + R**).

## Meets Expectations Requirements

_Tip: You are encouraged to familiarize yourself with the codebase provided and then create an outline of the app before writing your code!_

The application right now is a work-in-progress. You'll need to add further functionality to have it work as intended. A list of requirements can be found below, please read through them all before starting in.

### Step 1

```no-highlight
As a user
I want to see a list of articles
So I can get a sense of what is going on in the world
```

Acceptance Criteria:

- When a user navigates to the root path (`/`), they should see the titles (but not the bodies) of all the articles in the application. (You do not need to handle for the path `/articles`.)

Hints:

- Be sure to carefully review the provided code to see what has already been implemented for you
- You'll need to use React Router to complete this step
- `ArticlesList` should keep track of the articles in state after fetching them from the backend when the component mounts to the page
- You may see an error in the console, `Failed prop type: You provided a value prop to a form field without an onChange handler.`. You may ignore this error for now; we'll address it in Step 3!

### Step 2

```no-highlight
As a user
I want to see a specific article
So I can get details about that article
```

Acceptance Criteria:

- Clicking on an article listed on the index page should direct the user to that article's show page, at `/articles/((article id here))`. For example, if I click on the title of an article with an id of `2`, I should be taken to `/articles/2`.
- Similarly, if I navigate directly to `/articles/2` in my browser (by typing in the URL), I should be taken to the show page for the article with an id of `2`
- On the `/articles/((article id here))` page, the user should see the article's title and the body (and not the title or body of any other article!).

Hints:

- You'll need to employ your router skillz again!
- Make sure you update your `clientRouter` on the Express side of things to allow React Router to take over for this path
- Your `ArticleShow` component should fetch the data for the article of interest (and only that article) from the backend and persist it in state. You'll need to use the dynamic id provided to you by the router to direct your fetch call.

### Step 3

```no-highlight
As a user
I want to type into a form
So I can contribute to an awesome list of articles
```

> ProTip: Use React Dev Tools to see the state update in real time!

Acceptance Criteria:

- A user should be able to type into the form, have the form update accordingly (displaying whatever they have typed), and have their typed information tracked in the form component's state.

Hints:

- Ignore the Clear button on this page, which is only for _Exceeds_.
- The user's input into the form (as they type, before they submit) **must** be stored in state in `ArticleForm`.
- The input fields for the title and content of your new article form should be fully controlled (whose values are passed down from the state of `ArticleForm`, and whose onChange events change state).
- Check the provided data in `server/articles.json` and the code in `server/src/routes/api/v1/articlesRouter.js` to determine how the submitted form data should be formatted.

### Step 4

```no-highlight
As a user
I want to submit what I've typed into the form
So I can keep track of the awesome articles I've contributed
```

Acceptance Criteria:

- When a user submits the form, the new article is added to our persisted data in the `articles.json` file
- The list of articles on our current page (the index page at the `/` path) should now include the new article, immediately below the previous articles.
- **The page should not refresh upon submitting the form.**

Additional Info:

- Make sure that the information you're submitting in the body of your fetch request matches the format of the data currently stored in your `articles.json` file.
- The post `/api/v1/articles` endpoint is expecting a new question JSON object that should match the key value pairs below:

```js
{
  "title": "What's bark box?",
  "content": "Monthly gifts for your favorite pupper!"
}
```

- Upon a successful POST, you should add the article returned from your server to the articles already in your `state`!

## Exceeds Expectations Requirements

This application is looking good, but we want to ensure that the user has a smooth user experience. For exceeding expectations:

- In your React code, validate that both the body and the title fields have been filled out by the user (relying on HTML properties like `required` is insufficient). When a user does not fill in all the required fields:
  - the form should not submit and there should be no POST request sent to `/api/v1/articles`.
  - Any fields that were filled out should stay filled in.
  - errors indicating _which fields_ are missing should appear on the page if the user attempts to submit the form with missing values
- After a new article has been submitted, redirect the user to that article's show page by using React Router.
- There already exists a button with the text "Clear" on it in the `ArticleForm` component. This button should clear out the state for the form's fields, and if text had been typed into those form fields, the text should no longer display. Add functionality to make this so.
