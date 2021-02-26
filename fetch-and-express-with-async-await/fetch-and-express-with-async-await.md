So far in our web applications, we have built apps that access data directly through our backend, in the form of JSON files. However, a very popular practice in our frontend JavaScript development is to retrieve data from sources external to our code: specifically, via data endpoints either provided by our own server or by an external website. Getting that data may take some time, which means the fetching of the data is _asynchronous_. Below, we'll discuss how we connect to those data endpoints to access that data, and how we make sure to handle for the asynchronicity of the process to make sure we're giving our code time to get the data it needs.

### Learning Goals

* Identify the role of APIs in our web apps
* Explain the need for `async/await` keywords to handle for asynchronous code
* Handle for errors while working with an API call using `try...catch`
* Implement a `GET` and `POST` `fetch` call in a web application

### Getting Started

```no-highlight
et get fetch-and-express-with-async-await
cd fetch-and-express-with-async-await
yarn install
code .
yarn run dev
```

We'll dive into some code examples shortly, but first, let's learn a bit about how these data endpoints work.

### Pulling Data into our Application

There are many reasons that we might choose to pull data from other applications or websites into our own apps. Maybe we want to display a map on our website, or show a feed of our business's latest tweets on our homepage. We might want to have a Google Calendar integration so that our users can book appointments, or build some kind of cool data visualization around the most popular artists in Spotify's Classic Rock playlist. All of these features require us to talk to an external app, and request certain data from them so that we can integrate it into our app.

The thing about requesting data from some source on the web is that it takes time! These requests are going to be sent via HTTP. From what we've learned about the HTTP request/response cycle, our requests take some time to be sent to the server, for the server to prepare a response, and for us to receive that response back. Typically, we see that HTTP request/response cycle happen in the browser: whether it's a `GET` request by going to a particular page in our browser, a `POST` by submitting a form, etc. In this article, however, we're going to learn how to make that request in our JavaScript code, so that we can access the data via our application, and perhaps display it on our page. We'll do this using a pre-built method called `fetch`, courtesy of the [Fetch API][fetch-api-mdn]. We'll learn how to tell our code to "wait" while the server puts some data together and sends it back to us _asynchronously_. 

### APIs: Application Programming Interfaces

In programming, we'll often use external pieces of code or data to build out some functionality in our application. We call these pieces of code _APIs_, or _Application Programming Interfaces_. Simplified, an API is just a tool that has been pre-built by a developer, which has rules for how we interact with it. We can use that tool in our code, to set up certain functionality or access certain data. Sometimes that tool gives us helpful functionality or methods, and sometimes it gives us some data to work with - the term "API" is fairly flexible to cover many different types of tools.

In this article, we're going to talk about *two* types of APIs: the _Fetch API_, and an _API endpoint_.

First, let's talk about API endpoints. This is how we refer to a set of data that has been made available out on the web, via a server! Various apps and companies can choose to make their data accessible for us, as developers, to use in our own applications. This data will come in the format of the `JSON` we're beginning to know and love! We'll interact with it much like we have interacted with JSON in our `.json` files.

Different APIs have different rules about how to access their data: what information you have to send along as a part of your request, what URL you can find it at, if you need security credentials, etc. Github, for example, has a fairly public API for accessing some of their data, including user information. To see an example of an API endpoint, go ahead and navigate to this URL in your browser: https://api.github.com/users/LaunchAcademy.

Thanks to our knowledge of HTTP, we know that our browser, as the client, sent a GET request to the Github server with a path of `/users/LaunchAcademy`. Thanks to Github's API, the response that we get back is a bunch of data about the `LaunchAcademy` user: things like their `name`, `url`, and number of `public_repos`! Go ahead and update the URL to have your own Github username instead. You should be able to see your user information as well!

This data lives at an _API Endpoint_. Github set up rules for how we can access their data, and gives us certain user data in return. An _endpoint_ is the location set up in the API where we can get that data. It's the place we send requests to. In other words, it's the _route_ that's set up on the API's server!

### Internal API Endpoints

We'll notice that unlike the web applications we've built so far, when we sent a request to Github, we got **JSON data** in our response rather than an HTML document. _This is an important feature of API endpoints_ - they return data to us in the form of JSON, rather than a webpage in the form of HTML. So far, we've accessed another application's API endpoint. However, we can have our own internal API endpoints too! Just like Github did, we can build a route where we return some data to the user. 

For example, let's say that we want to take data that lives in our JSON file, and make it available at an API endpoint via our Express server. Our own API endpoint routes will be set up much like the routes we've built so far. The biggest difference is that, instead of sending a Handlebars template/HTML, we'll send JSON! Express gives us a handy method to respond with JSON. Instead of `res.render(<OUR_TEMPLATE>)`, we'll be using `res.json(<OUR_JSON>)` to respond with JSON data.

One other big difference in how we'll set up these endpoints will be our _namespacing_. Previously, if we were rendering a page with a list of our favorite books, we would build a `src/routes/booksRouter.js` file and namespace it at the "/books" path in our `rootRouter`. However, with API endpoints, we want to specify that these paths are related to an API. We do this both through our path, and through our file structure.

Let's start to take a look at our application in your editor. Open up our `rootRouter`, and we'll see that we have one root route, and near the bottom, added namespacing for our `booksRouter`:

```javascript
rootRouter.use("/api/v1/books", booksRouter)
```

This is telling our app to namespace all of our API endpoints in that router at a "/api/v1/books" root path. Looking at our `routes` folder, we can also see that we've used directories to organize our API router, placing our `booksRouter` at `src/routes/api/v1/booksRouter.js`. This gives us another way to know that this router is full of JSON API endpoints: the folder structure tells us so!

From there, if we open up our `booksRouter`, we'll see that we have three API endpoints set up: two `GET` routes and a `POST` route. We'll dive further into _how_ we write the guts of our API endpoints later in the curriculum, but for now, we'll keep it simple and top-level. We can see that we're using the `.json()` Express method to return some data. Specifically, our `GET` "/" request is returning the Book data from our `books.json` file in our root.

You'll notice that we've included an additional namespace of `v1` here, in both our folders and our `rootRouter` path. With APIs, we always want to include some form of version control. The `v1` here stands for "version 1". By including this, we're proactively accounting for the possibility that we may want to change the structure of this data in the future, in a way that could break existing functionality. If we mark this as `v1`, we can allow parts of our app to continue using the original version without breaking them, while building out our new `v2`.

Now that we've done an overview of the code, go ahead and navigate to http://localhost:3000/api/v1/books, where you'll be able to see this JSON data being served up!

### Sending `GET` Requests with Fetch

We've now accessed two different API endpoints via our browser: one external API endpoint (via Github), and one of our own (our books). In this situation, the browser is acting as our client, sending the request and displaying the response. However, when we want to use this data within our JavaScript code, we need to use the Fetch API as our client instead!

To practice using JavaScript to talk to API endpoints, we're going to take it back old-school to a single JavaScript file linked up with an HTML document. If we navigate to http://localhost:3000, we can see that our `views/index.hbs` file gets loaded up. Our `public/js/client.js` file is being run through our HTML page, and if we open our JS console in the browser, we should see "Hello from client.js!" appear in the console.

Now, we get to dive into the [Fetch API][fetch-api-mdn]. Via the Fetch API, we have a function called `fetch` that's built into JavaScript for us and readily accessible for our code. `fetch()` expects a URL to be provided as an argument, so that it can go `GET` JSON data from an endpoint for us, acting as the _client_ in the place of our browser. If we want to do a `POST` or some other kind of request instead, we will need to provide additional arguments - we will take a look at that later on. 

Once `fetch` has gotten a response from the server, it gives us back a [Response object][response-object-mdn], which has a number of different attributes and methods we can call on it. Some of these attributes are the things we know well from our knowledge of HTTP responses: things like the `status` of the request!

It's worth mentioning that `fetch` runs on [Promises][promises-mdn], which are a special way of handling for asynchronous processes in JavaScript. Promises indicate to our application that something is going to take some time to run, and we need to wait for it to run in order to proceed with any code relying on its return value. We'll dive into more of the specifics of Promises once we have a better understanding of how `fetch` is working.

Let's write our first bit of code using `fetch`! We're going to define a function called `fetchBooks`, which uses our `fetch` function to make an HTTP request to our same "/api/v1/books" endpoint.

In `client.js`, add the following code:

```javascript
// public/js/client.js

const fetchBooks = () => {
  try {
    const response = fetch("http://localhost:3000/api/v1/books")
    console.log("response", response)
    console.log("response.status:", response.status)
    console.log("response.statusText:", response.statusText)
    console.log("response.ok:", response.ok)
  } catch(err) {
    console.error("Error in fetch!")
    console.error(err)
  }
}

fetchBooks()
```

Here, we're setting up a function that can fetch data from our `books` endpoint. We set the return value of our `fetch()` call to a variable called `response`, and then we try to look at its attributes: `status`, `statusText`, and `ok`. `ok` will return `true` or `false` based on if the status is in the range of 200-299 (`true`) or not.

Importantly, we wrap this call inside of a `try...catch` block. Since we're relying on an external resource, we need to account for the fact that our HTTP request might fail. Our code will `try` to do everything inside of our `try` block, and if it fails _anywhere_ along the way, it will skip everything else within `try` and hop right over to our `catch` block to _catch_ the error (which we're calling `err`) and allow us to log it into our console.

Go ahead and hard-refresh your page to see this run. We should get some feedback that we weren't expecting: our `response` says it's a `Promise {<pending>}`, and all of our attributes are undefined!

### Awaiting our Requests

As we mentioned earlier, the HTTP request/response cycle takes some time! Our browser is able to handle for this and load up the data when it's ready, but our JavaScript code doesn't know when to wait for something to finish up unless we tell it to. When using `fetch()`, we need to know how to tell our code to _wait_ until we get the data back from whatever server we request it from. Going back to our original ideas of how we might add an API to our app, we don't want to try to load up our tweets on our theoretical homepage if we don't yet have the data from Twitter! We need to know that we have successfully fetched the data we need, so that we can start putting it on the page. We'll do this using the keywords `async` and `await`.

`async` and `await` are [relatively new keywords][async-await-mdn] provided by JavaScript to handle for telling our code when _asynchronous_ things are happening. We need to add them to our `fetch` code in order to tell JavaScript when things might take a little extra time. `await` is the keyword that we'll use to tell our code _what_ lines might take some extra time. We can update our line with our `fetch` call to have an await:

```javascript
const response = await fetch("http://localhost:3000/api/v1/books")
```

If we make this update and hard-refresh our page, we should see an error in our console:

```no-highlight
Uncaught SyntaxError: await is only valid in async function
```

This error is telling us that we can only use the `await` keyword inside of an `async` function. This is because, if we're going to _wait_ for something to happen, we need to let our program know, "hey, this whole function that we're running right now has some asynchronous stuff happening in it!" We do this using the `async` keyword when defining our top level function. Under the hood, the `async` keyword makes `fetchBooks` return a Promise object: we'll dive more into what Promises are later on. Let's update our existing code to match the following by adding the `async` keyword in. Be sure you don't overwrite the `fetchBooks()` call at the bottom of the file when doing so:

```javascript
const fetchBooks = async () => {
  try {
    const response = await fetch("http://localhost:3000/api/v1/books")
    console.log("response", response)
    console.log("response.status:", response.status)
    console.log("response.statusText:", response.statusText)
    console.log("response.ok:", response.ok)
  } catch(err) {
    console.error("Error in fetch!")
    console.error(err)
  }
}
```

If we hard-refresh our page now, we should be able to see that our code _waited_ to try to `console.log` our response and its attributes until it _knew_ it got a response from our server. We can now see our `Response` object, our status of `200`, status text of `OK`, and "ok" boolean of `true` in our console. Success!

### Accessing our Response `body`

It's great that we have a `Response` object now, but we know that we need the book data living within it! That data lives inside the Response `body` - but we need to do some work to access it. First, let's try to access it directly. Update your `fetchBooks` method to read as follows:

```javascript
const fetchBooks = async () => {
  try {
    const response = await fetch("http://localhost:3000/api/v1/books")
    console.log("response", response)
    console.log("response.body:", response.body)
    return responseBody
  } catch(err) {
    console.error("Error in fetch!")
    console.error(err)
  }
}
```

When we hard-refresh our page, we should see that our `body` is being logged, but it's not in a format we're used to seeing. We're getting a `ReadableStream` object, which we need to translate to be some kind of usable data. We can do this using one of two methods that the `Response` object has defined: `text()` or `json()`. Since we know we want our data to be in JSON format (so we can access its key-value pairs), we'll use `.json()`. The `.json()` method will take the response body and _parse_ it into a JS object of key-value pairs for us.

Update your code as follows:

```javascript
const fetchBooks = async () => {
  try {
    const response = await fetch("http://localhost:3000/api/v1/books")
    const responseBody = response.json()
    console.log("responseBody:", responseBody)
    return responseBody
  } catch(err) {
    console.error("Error in fetch!")
    console.error(err)
  }
}
```

Hard-refresh your page, and what do you see? We're getting another `Promise {<pending>}`! `.json()` is another asynchronous method, because it needs to take some time to read the stream body of our response and turn it into a data format for us. Since it's asynchronous, that means we'll need another `await`! Update your `responseBody` line to the following:

```javascript
const responseBody = await response.json()
```

Hard-refresh your page, and you should see our array of two books appear in your console in a nice, ready-to-use data format! Notice that we're returning our `responseBody` once we've done all this work, so that it could be used elsewhere in our JavaScript code by calling `fetchBooks()` if we wanted to do something other than just `console.log` it.

> Tip: Try changing the `response.json()` call to `response.text()` (keep the `await` keyword -- it's still an asynchronous function!) How does this change the data that you're getting in your console? What's the difference? Hint: it's one big string instead of an object with key-value pairs! If we wanted to change it to an object after calling `response.text()`, we'd need to call `JSON.parse(responseBody)`. This is run behind the scenes for us when we use `.response.json()`.

### Handling for Failed Requests

Earlier, we made sure to wrap our entire block of code in a `try...catch` block in order to handle for any errors that might arise while fetching or parsing our data. In a well-handled `fetch` call, we also want to handle for a failed response code from the server. We can do this by using the `response.ok` boolean attribute to account for any response statuses outside of the 200-299 range!

Let's update our existing `fetchBooks` method to the following to add an `if` statement which uses `response.ok`:

```javascript
const fetchBooks = async () => {
  try {
    const response = await fetch("http://localhost:3000/api/v1/books")
    if(!response.ok) {
      const errorMessage = `${response.status} (${response.statusText})`
      const error = new Error(errorMessage)
      throw(error)
    }
    const responseBody = await response.json()
    console.log("responseBody:", responseBody)
    return responseBody
  } catch(err) {
    console.error("Error in fetch!")
    console.error(err)
  }
}
```

In this case, we say that if the `response` is **not** ok, we want to throw an error that displays the response `status` and `statusText`. We do so by creating a new `Error` object (this class is built into JavaScript for us), which holds our custom error message. Finally, we use the `throw` method to "throw" that error, which, simply enough, skips the rest of our `try` block and gets "caught" by our `catch` block! Otherwise, we will proceed with our existing logic. If we hard-refresh our page, this should all work the same as before, since we don't run into errors. 

To see this logic in action, let's add another `fetch` call, this time to a failing endpoint, which we already set up at "/api/v1/books/test-error".

Update your `public/js/client.js` file to replace **all** code with the following:

```javascript
// public/js/client.js

const fetchTestError = async () => {
  try {
    const response = await fetch("http://localhost:3000/api/v1/books/test-error")
    if(!response.ok) {
      const errorMessage = `${response.status} (${response.statusText})`
      const error = new Error(errorMessage)
      throw(error)
    }
    const responseBody = await response.json()
    console.log("responseBody:", responseBody)
    return responseBody
  } catch(err) {
    console.error("Error in fetch!")
    console.error(err)
  }
}

fetchTestError()
```

If we hard-refresh our page now, we should see some errors in our console: specifically, we'll expect to see our "Error in fetch!" error show up, followed by our error showing an `Error: 500 (Internal Server Error) at fetchTestError`. We've successfully managed to display an error if our `fetch` call fails to get a successful response.

### Sending POST Requests with Fetch

We've successfully sent two `GET` requests with `fetch`: one to a functioning API endpoint, and one to a failing one. Now, let's take a look at how we can send a `POST` HTTP request with `fetch`.

Looking in our `booksRouter`, we can see that we've already set up an API endpoint which can take in a `POST` request, complete with a new book in the body, and save that book to our JSON file. Now, rather than filling in a form and hitting submit via the browser, let's figure out how to hit that route using `fetch`.

`fetch` _defaults_ to a `GET` request, which means that previously, we didn't have to specify an HTTP method. However, if we want to send any other kind of request, we will need to specify our method, along with the body of the request, if needed.

Update your `client.js` file to **only** have the following code:

```javascript
// public/js/client.js

const postBookFetch = async () => {
  try {
    const data = {
      book: {
        title: "book from fetch"
      }
    }
    const jsonStringData = JSON.stringify(data)
    const response = await fetch("http://localhost:3000/api/v1/books", {
      method: "post",
      body: jsonStringData,
      headers: new Headers({
        "Content-Type": "application/json"
      })
    })
    if(!response.ok) {
      const errorMessage = `${response.status} (${response.statusText})`
      const error = new Error(errorMessage)
      throw(error)
    }
    const responseBody = await response.json()
    console.log("responseBody:", responseBody)
    return responseBody
  } catch(err) {
    console.error("Error in fetch!")
    console.error(err)
  }
}

postBookFetch()
```

In the above, we're using a lot of the same functionality as we had in our `GET` fetch. However, we first define a `data` variable that has the information for our new book. We _stringify_ that data in our variable `jsonStringData`, which is super important to do before sending it to our backend so that it can be sent over an HTTP request. This makes our data into a JSON string, which is a format that can be sent over HTTP and then parsed back to a JavaScript object on our backend. Finally, when we make our `fetch` call, we provide _two_ arguments this time: the first is still our URL, but the second is an object that looks like the below:

```javascript
{
  method: "post",
  body: jsonStringData,
  headers: new Headers({
    "Content-Type": "application/json"
  })
}
```

This object tells our `fetch` call to make a `POST` request instead of its default `GET`, to include our `body` in the request, and, lastly, to add a `Content-Type` header of "application/json". This last bit is important because it prevents potential issues when being received by our API endpoint - it allows our API endpoint to know for sure that we're sending a JSON request, so that our API endpoint can process it and send JSON back to us.

With your `client.js` file updated, hard-refresh your page. We should see some output in the console showing our `responseBody` with the following feedback of our new book (with its `id` added): 

```javascript
{ 
  id: 3,
  title: "book from fetch"
}
```

Even cooler, if we look in our `books.json` file in our root directory, we'll see that our book was added to the list there. Our `POST` request was successful!

### Why This Matters

Often, we will want to make an HTTP request to fetch data from a JSON API Endpoint in our applications, whether its our own internal data endpoint or that of an external API. Using the Fetch API and proper `async`/`await` syntax, we can request that data from within our JavaScript code, and use it to update our webpages!

### In Summary

Building on our knowledge of the HTTP request/response cycle, we've learned how to use the `fetch()` method to make a request to an API endpoint, where some JSON data lives. Using `async` and `await`, we tell our JavaScript code where it needs to "pause" in order to wait for us to get a response or a finalized return value back. Using `try` and `catch`, we properly handle for any errors that may happen along the way. We can now begin to use API endpoints to update our webpages using JavaScript!

### Resources
- [MDN Async/Await Docs][async-await-mdn]
- [MDN Fetch API Docs][fetch-api-mdn]
- [MDN Promises Docs][promises-mdn]
- [MDN Response Object Docs][response-object-mdn]

[async-await-mdn]: https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Asynchronous/Async_await
[fetch-api-mdn]: https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API
[promises-mdn]: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise
[response-object-mdn]: https://developer.mozilla.org/en-US/docs/Web/API/Response