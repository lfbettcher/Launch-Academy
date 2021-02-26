We've discussed how the HTTP request/response cycle works. Now, let's examine how we can build our web applications to respond to any desired HTTP requests in an organized and scalable way.

### Learning Goals

- Understand how our Express server runs
- Discuss the role of Routers in Express
- Create and connect our own Express routers
- Set up a functional route for our user to visit

### Getting Started

```
et get express-routing
cd express-routing
yarn install
code .
```

### Taking a Look at our Express App

Previously, we set up an `app.js` file to render an `index.html` file. In that application, we simply loaded a static `index.html` file at `http://localhost:3000/index.html`. However, what if we wanted to set our application up to handle multiple different pages and paths?

Let's say that we want our user to be able to visit `http://localhost:3000/messages` and see a list of all of the messages found in `messages.txt`. In order to set up this functionality, we need to set up a _route_ for that behavior to exist!

### Express Routers

The way we can set up new routes, especially if we want to organize our code in an MVC-patterned way, is to use a `Router`. Routers allow us to tie specific paths (such as `/messages`) to specific blocks of code using _routes_. We can think of our _routers_ as our _Controllers_ within our MVC apps.

In an MVC app, we want to separate our routers based on the _resource_, or _entity_, they're dealing with. Here, we're interacting with `messages`, but what if we also built `users` or `categories` into our application? In a well-organized MVC app, we would want to have a separate router for each of these entities. It is conventional to name our router based on the plural version of our entity. For this application, we're going to build a `messagesRouter`.

Start by adding a subdirectory of the existing `src`, called `routes`. Inside of that directory, create a file called `messagesRouter.js`. Copy the following code into that file:

```javascript
// src/routes/messagesRouter.js

import express from "express"

const messagesRouter = new express.Router()

export default messagesRouter
```

We have created our first Express router! By importing the `Router` functionality from the `express` package, we're able to create and export a router from our file. Right now, we're going to focus on setup so that our router is connected to our overall Express app. We'll get into setting up the route itself a little later on.

### Connecting our Router to our Server

Now that we've created our `messagesRouter`, we want to make sure it's being used by our Express server. The first thing we want to do is tell our application what path we want our `messagesRouter` to be based on.

Inside of `src/routes/`, create another new file called `rootRouter.js`. This will be our single "root" router that pulls all other routers together before connecting them to our server. Once we have multiple entities at play, having one root router will be extremely helpful!

This file is responsible for telling our application what path to use certain routers at. Copy the below code into the file:

```javascript
// src/routes/rootRouter.js

import express from "express"
import messagesRouter from "./messagesRouter.js"

const rootRouter = new express.Router()

rootRouter.use("/messages", messagesRouter)

export default rootRouter
```

In this file, we create a new router which will behave as our root. We then tell this root router to use the `messagesRouter` (which we imported at the top of the file), and attach it to the `/messages` path. This means that any and all routes that we set up in our `messagesRouter` will have paths that start with `/messages`.

If we had routers for `users` or `categories`, we might set them up with root paths similarly as so:

```javascript
router.use("/users", usersRouter)
router.use("/categories", categoriesRouter)
```

This is called _namespacing_, and it allows us to have one clear place to look to figure out where to find the logic within our app for specific HTTP requests.

Our final step is to attach our root router to our Express server. Let's open up our `src/app.js` file. As a refresher, remember that we created an Express server using the line shown below:

```javascript
const app = express()
```

This server is exported and used to run our program, so we want to attach our existing routers to the server so that our new route gets included!

At the top of this file, add the following import statement:

```javascript
// src/app.js

import rootRouter from "./routes/rootRouter.js";
```

And right above the `app.listen...` line, add the below code:

```javascript
// src/app.js

app.use(rootRouter);
```

Here, we're telling our Express server to use all of the routes that are set up and imported into our `rootRouter`.

Whew! That was a good amount of setup! The benefit here is that we've built a structure that's incredibly scalable -- while it seems like a lot for one measly route, it's going to be fantastic once we start adding more and more functionality into our applications.

### Setting Up Routes

Now that we have our routers all set up and connected, we want to add some actual functionality in. Let's add a new `GET` route inside of our `messagesRouter`, to respond to any requests that come to `localhost:3000/messages` with a list of all of the messages in our text file.

We set up a route by using some methods provided to us by Express, which match up with our HTTP verbs. This method takes a path as its first argument, and a _callback function_ as its second argument. Inside of the callback function, we tell our app what it is that we want to happen when a user sends in this request.

For a standard `GET` request, this will look like the below:

```javascript
messagesRouter.get("/", (req, res) => {
  // tell our application how it should respond to this request here
})
```

As a reminder, we set up our `messagesRouter` to be _namespaced_ at the `/messages` path. This means that any and all HTTP requests sent to a path starting with `/messages` will be directed to our `messagesRouter` for processing. Once we're inside of our `messagesRouter`, all we have to worry about is the _rest_ of the path, after `/messages`. For example, a request to `/messages/banana` will be delegated to the `messagesRouter`, and would match a route defined in that router like `messagesRouter.get("/banana", (req, res) => {...`. Notice that we only indicate the `"/banana"` part of the path in the route inside of our `messagesRouter`.

Let's fill in that route and add it to our router. Update your `messagesRouter.js` file to match the below:

```javascript
// src/routes/messagesRouter.js

import express from "express"
import fs from "fs"

const messagesRouter = new express.Router()

const messagePath = "messages.txt"

const getMessages = () => {
  return fs
    .readFileSync(messagePath)
    .toString()
    .split("\n")
}

messagesRouter.get("/", (req, res) => {
  res.contentType("text/html").send(
    getMessages()
      .map(message => `<br/>${message}`)
      .join("\n")
  )
})

export default messagesRouter
```

The first part of our new code is simply adding some helper methods to access and read our `messages.txt` file. Our route is specifically this section here:

```javascript
messagesRouter.get("/", (req, res) => {
  res.contentType("text/html").send(
    getMessages()
      .map(message => `<br/>${message}`)
      .join("\n")
  )
})
```

Here, we're telling our application that if a GET request is sent to the root path for this router, we want to _respond_ with a `contentType` header of `"text/html"`, and with a `body` of all of our messages joined together in a formatted string.

> A Note: We've learned that there are multiple different HTTP verbs: `GET`, `POST`, `PUT`, `PATCH`, and `DELETE`. Any time we want to add requests with different verbs, we'll use their corresponding Express method. For example, if we were trying to add a `DELETE` endpoint, it would look something like this:
>
> ```javascript
> messagesRouter.delete("/", (req, res) => {
>   ...
> })
> ```

In the future, we'll learn how to respond with actual HTML files at these routes, but for now, this HTML string will suffice. We should now have a working route in our Express app! Run the following command to boot up your server:

```no-highlight
yarn run dev
```

Now, head to http://localhost:3000/messages to check out our list of messages.

### Why This Matters

It is crucial to follow best practices in organizing our Express servers, so that our apps have a clear organizational design and are easy to build and scale as our web applications become more complex!

As we learn more about building our Express applications, we'll be building towards using a "Model, View, Controller" (MVC) design pattern. We'll learn more about this design pattern once we've learned about the separate pieces!

### In Summary

In this article, we've reviewed the best ways to organize our Express routes in order to adhere to a "Controller" pattern. Using a `rootRouter` and separate router files for each entity allows us to build a scalable and well-organized set of routes for our server. We've now learned how to organize the _Controller_ side of our MVC Express applications! In the future, we'll begin to talk more about the _View_ and _Model_ parts of our app, and how they interact with our controllers.