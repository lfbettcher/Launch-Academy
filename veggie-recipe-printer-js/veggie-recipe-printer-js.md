Welcome to the Veggie Recipe Printer Express app. It's purpose is to spread the good word about delicious veggie-centric recipes to advance our mission of global veggie domination. You don't need to enjoy veggies to use it...but you will have to obey our every veggie-command to complete this assignment.

In this app, we will have a few web pages that share veggie based messages, and one page with a sinister recipe for brussels sprouts (a most sinister and cruciferous veggie).

### Learning Goals

* Generate a web page in response to a `GET` request.
* Utilize Handlebars to render views with express.

### Getting Set Up

```no-highlight
et get veggie-recipe-printer-js
cd veggie-recipe-printer-js
yarn install
yarn run dev
```

Navigate to http://localhost:3000/brussels-sprouts in your browser and you should see the following text: "Veggie Recipe Printer JS".

### Overview

We will be focusing on adding code to the routes in our `rootRouter.js` and `brusselsSproutsRouter.js` and to files that we create in the `views` folder, where our Handlebars templates will reside.

Keep in mind:
* if we navigate to a path of "/brussels-sprouts" in our browser, we need to configure a route in the pre-namespaced `brusselsSproutsRouter.js` using `app.get("/", ...)` in order to display some information to a user.
* when we navigate to a route, we choose what to send back. Normally, we send back HTML rendered by a Handlebars (hbs) template, but we can send back plain text as well!
* Handlebars templates can handle any HTML we add to them.
* Our application becomes dynamic when we take some data we have in Node (maintained in our router files) and display it in our hbs files.

### Instructions

### Step 1: Returning plain text from a route

When a user navigates to the root path (that is, the home page, or route that has a path of "/"), a user should see a message of "VEGGIE POWER" appear on the page. Check out the `rootRouter` to find this path.

No need for Handlebars or html for this route, so use the `res.send` callback to show some plain text on the page exclaiming "VEGGIE POWER".

That way, users immediately see the strength of the Great Veggie.

### Step 2: Static HBS Page
Your first feature isn't powerful enough. We need MORE veggie power. *Would you kindly make a static webpage to show how to make Roasted Brussels Sprouts?*

When a user navigates to `localhost:3000/brussels-sprouts`, we should see the following text appear on the screen.

```
#==================================#
# Recipe: Roasted Brussels Sprouts #
#==================================#

Ingredients
-----------

1 1/2 pounds Brussels sprouts
3 tablespoons good olive oil
3/4 teaspoon kosher salt
1/2 teaspoon freshly ground black pepper

Directions
----------

1. Preheat oven to 400 degrees F.

2. Cut off the brown ends of the Brussels sprouts.

3. Pull off any yellow outer leaves.

4. Mix them in a bowl with the olive oil, salt and pepper.

5. Pour them on a sheet pan and roast for 35 to 40 minutes.

6. They should be until crisp on the outside and tender on the inside.

7. Shake the pan from time to time to brown the sprouts evenly.

8. Sprinkle with more kosher salt ( I like these salty like French fries).

9. Serve and enjoy!
```

**Note:** a `brussels-sprouts.hbs` is already being returned at a route that has been created for you, so you won't need to add anything to `brusselsSproutsRouter.js` for this one. Instead, add to the `brussels-sprouts.hbs` file such that the above words appear. It's probably more straightforward than you think!

### Step 3: Dynamic Templates & Subliminal Messaging

You did good work with your last web page. *Would you kindly make one that dynamically uses data from the express backend?*.

The Committee for Vegetable Propaganda instructs that the data for a good brussels sprouts recipe should not be hardcoded into an `hbs` template.

Instead, when a user navigates to `localhost:3000/brussels-sprouts/of-doom`, they should see a new and improved recipe for roasted brussels sprouts using the data found below.

A route has already been setup for you, and the data has been defined in a variable of `recipe` in the code block for that route.


```js
let recipe = {
  name: "Roasted Brussels Sprouts (of Doom)",
  ingredients: [
    "1 1/2 pounds Brussels sprouts picked on a blood moon",
    "3 tablespoons evil olive oil",
    "3/4 teaspoon of salt processed from tears",
    "1/2 teaspoon freshly ground black pepper"
  ],
  directions: [
    "Preheat oven to 4000 degrees C.",
    "Cut off the brown ends of the Brussels sprouts.",
    "Pull off any yellow outer leaves BUT leave leaves that the color of a lemon on a cold fall morning",
    "Mix them in a bowl with the evil olive oil, tear-salt and pepper.",
    "Pour them on a sheet pan and roast for 35 minutes to 400 years.",
    "They should be until crisp on the outside and tender on the inside.",
    "Shake the pan from time to time to brown the sprouts evenly.",
    "Lightly salt and consider how you can best show your appreciation for these sacred spheres of bitter greenness ",
    "Serve and enjoy!",
    "Obey. Obey. Obey"
  ]
}
```

**Hint:** You will need to create a Handlebars template called `brussels-sprouts-of-doom.hbs` and pass data in `recipe` to the template. You will then need to use handlebars expressions (e.g. `<p>{{data-from-app-js}}</p>)` ) to render that data on the page. You will want to render this Handlebars template from your `brusselsSproutsRouter` file.

Use whatever HTML elements or styles that you wish to really make instructions engaging. World domination should look swanky.
