We can use the URL of a GET request to vary what an HTTP response returns to the client. In this article, we'll introduce route parameters and the query string, and we'll use both to create new behaviors in our Express applications.

## Learning Goals

- Use parts of the URL to create dynamic behavior
- Use the query string of a URL to create dynamic behavior

## Getting Started

```no-highlight
et get urls-and-dynamic-express-applications
cd urls-and-dynamic-express-applications
yarn install
code .
yarn run dev
```

First, let's look at the code available. We have an Express app set up with a `rootRouter`, `productsRouter`, and `categoriesRouter`. The `productsRouter` and `categoriesRouter` have been namespaced to have all paths start with `/products` and `/categories` respectively, but there are no routes defined yet!

## Parameters in the URL

We've learned that our programs become more interesting when the user provides input. In a web based context, there are many ways in which a user can provide programs with information. The URL they use to navigate, and the information they provide through web forms are both examples ways users can supply input. Before we dive into web forms, let's first explore how we can use request URL's to inform how our application behaves.

### Route Parameters

When we define our endpoints in Express, we can name segments of our paths. We can do so by using the colon `:` and a useful name to isolate parts of the URL.

In our `productsRouter`, add the following route above your `export` statement:

```javascript
// src/routes/productsRouter.js

productsRouter.get("/:productName", (req, res) => {
  res.status(200).send(`Product: ${req.params.productName}`)
})
```

With a pattern like this, our end users can now navigate to a URL like: `/products/Rocket`. In this case, the response body will contain the text `Product: Rocket`.

Similarly, we can navigate to `/products/Spacesuit` and we will achieve similar results.

While it's great to essentially read back parts of the URL to the end user, you can imagine how this paradigm could be useful when retrieving additional information. What if we had some actual data to play with? Update your `productsRouter` code to include the following:

```javascript
// src/routes/productsRouter.js

const products = {
  Rocket: {
    title: "Launch Rocket",
    description: "The fastest rocket in the universe"
  },
  Spacesuit: {
    title: "Launch Retro Spacesuit",
    description: "Chic, comfortable, and air tight."
  }
}

productsRouter.get("/:productName", (req, res) => {
  const productName = req.params.productName
  const productData = products[productName]
  if (productData) {
    res
      .status(200)
      .send(`<h1>${productData.title}</h1><p>${productData.description}</p>`)
  } else {
    res.status(404).send("Product not found.")
  }
})
```

Using the context of the `productName`, we're able to retrieve relevant product information and provide it to the end user. If we can't find the product in the catalog, we respond with a 404 status.

### Complex Route Parameters

We aren't limited to a single route parameter. If we wanted to, for SEO purposes, we could also use a product category to further refine our product catalog.

Inside of your `categoriesRouter.js` add the following code above your `export` statement:

```javascript
// src/routes/categoriesRouter.js

const catalog = {
  "space-gear": {
    rocket: {
      title: "Launch Rocket",
      description: "The fastest rocket in the universe"
    },
    spacesuit: {
      title: "Launch Retro Spacesuit",
      description: "Chic, comfortable, and air tight."
    }
  },
  "coding-schwag": {
    "launch-sticker": {
      title: "Launch Sticker for Your Laptop",
      description: "Instant nerd cred for your gear."
    },
    "launch-t-shirt": {
      title: "Launch T-Shirt",
      description: "Nerd Fashion. The Launch Shield is so in season."
    }
  }
}

categoriesRouter.get("/:categoryName/products/:productName", (req, res) => {
  const categoryName = req.params.categoryName
  const productName = req.params.productName
  const category = catalog[categoryName] || {}
  const product = category[productName]

  if (product) {
    res
      .status(200)
      .send(`<h1>${product.title}</h1><p>${product.description}</p>`)
  } else {
    res.status(404).send("Product not found.")
  }
})
```

If I navigate to `/categories/space-gear/products/spacesuit`, the appropriate product information will come up. If I, however, navigate to `/categories/coding-schwag/products/spacesuit`, I'll receive a 404, because the Spacesuit is not in the `coding-schwag` category.

For the first time, we're combining a data structure with our dynamic web application. Pretty cool, right?

Notice, too, that we've also modified the names that show up in the url to be lower case and hyphenated. This is consistent with good search engine optimization (SEO) practice, and it is a convention we will observe moving forward.

We can get increasingly sophisticated with our paths, so it's important to be thoughtful in how we organize our URL's. They should make logical sense to the end user, and we'll discuss good RESTful patterns for organizing your URL's in a later lesson.

### The Query String

There is an additional way for web browsers to supply contextual information in the URL. We can make use of the **querystring**.

At the time of this writing, if we navigate to Amazon.com and do a search for "FireTV", we're redirected to a URL that shows us a list of related products. Notice the resulting URL: `https://www.amazon.com/s?k=firetv&ref=nb_sb_noss_2`

Everything after the `?` is known as the query string. Having my search request in the query string allows me to share the URL with a friend that is looking for a media player. In this query string, there are two parameters, `k` and `ref`. Each parameter has a value after an `=` sign, and each key-value pair is separated by the `&` character.

Let's do another search on Amazon. If I decide to get more general with my search, and I choose to type "media player" in the search box, the resulting URL we're get sent to is `https://www.amazon.com/s?k=media+player&ref=nb_sb_noss_2`.

Notice here that the value for the `k` parameter is `media+player`. Why did the browser substitute "media player" with "media+player"?

In order for our strings to appear in a URL, they have to adhere to a standard format defined by the designers of the HTTP protocol. Therefore, we have to substitute special characters like a space. In order to successfully place our strings in a URL, they must be **url encoded**. Refer to the [MDN][mdn-percent-encoding] if you're interested in learning more about URL encoding.

### The Query String in Express

Ok, so how do we make use of the query string as backend web developers? With express, we can use it to further contextualize our requests.

Let's imagine we're building a feature that allows us to search the guest list for our big Launch ECommerce Store launch.

First, let's namespace a `guestsRouter` in our `rootRouter`:

```javascript
// src/routes/rootRouter.js

import guestsRouter from "./guestsRouter.js"
rootRouter.use("/guests", guestsRouter)
```

Then, let's add our `guestsRouter`:

```javascript
// src/routes/guestsRouter.js

import express from "express"

const guestsRouter = new express.Router()

const vipNerds = [
  "Grace Hopper",
  "Mark Zuckerberg",
  "Bill Gates",
  "Margaret Hamilton"
]

guestsRouter.get("/", (req, res) => {
  let nerdsFound = vipNerds.filter(name => {
    if (req.query.search) {
      return name.startsWith(req.query.search)
    } else {
      return true
    }
  })
  res.send(nerdsFound.join("<hr>"))
})

export default guestsRouter
```

With this endpoint in place, if we navigate to `/guests`, we get the full list of VIPs. If we navigate to `/guests?search=M`, the list is filtered to only display two of our celebrenerds.

As a quick challenge, see if you can extend this endpoint to display "No nerds found." if we use a search string that does not match any of our VIP's.

## Why This Matters

We're just now scratching the surface of what it means to be a backend web developer. Equipped with input from the user, we can start to build web applications that respond to that information in intelligent and programmatic ways. Later, we'll use parameters in URL's to perform database queries and other complex operations. Through these humble beginnings, we're already able to achieve some interesting user experiences.

## Resources

- [Express Routing][express-routing]
- [MDN Tutorial on Routing (Advanced)][mdn-routing-tutorial]

## Summary

We can use route parameters and query strings to contextualize HTTP requests. These mechanisms provide two ways in which the user can provide input to us in a web-based context. We can access route parameters through the `req.params` object and we can access the query string key-value pairs via `req.query`.

[mdn-percent-encoding]: https://developer.mozilla.org/en-US/docs/Glossary/percent-encoding
[mdn-routing-tutorial]: https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/routes
[express-routing]: https://expressjs.com/en/guide/routing.html
