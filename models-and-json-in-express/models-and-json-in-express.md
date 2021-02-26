As we build out Express apps in an MVC design pattern, we've covered how to use our routers as controllers, and Handlebars for our views. Now, let's talk about the purpose of models within our application.

### Learning Goals

- Discuss the benefits of JSON data and how to consume with it
- Identify the role of a Model in our Express apps
- Construct a model that represents our data and interacts with a JSON file

### Getting Started

```bash
et get models-and-json-in-express
cd models-and-json-in-express
yarn install
code .
```

Navigate to <http://localhost:3000/animals> and you should see a page with a heading that says "Animals from Down Under". Right now, we'll have an empty list on the page, which we'll work to populate with data from a file! But first, let's talk about what our strategy will be.

### What is a Model?

Within the MVC (Model, View, Controller) design pattern, models are the part of our code that hold our business logic -- for example, interacting with our data and building some behavior around it. Unlike our routers and our views, which had entirely different structures, our models are just nice, simple JavaScript classes, similar to the ones we've worked with before! They can hold state (some data or information) in the form of instance variables, and behavior having to do with that state, in the form of methods.

Since our models are the part of our code through which we interact with our data, we'll often use models to interact with some sort of persisted data storage within our app, whether it's a database, or something simpler like a single file. Our models will take the data that lives in those files, and use JavaScript code to represent that data in our application as objects. Our models can additionally have functions that allow us to perform some logic using that data. A common type of logic we will need to perform will be CRUD (Create, Read, Update, Delete) interactions with our data storage! We can build functions that find certain records within our existing data, create new data, or edit or delete existing data.

As our apps scale up, we will need to work with some kind of scalable form of persistence, such as a database. For now, however, we'll use a simple JSON file to store our data!

### Working with JSON Files

There are a number of different file types that we can use to store our data: `.txt`, `.csv`, and `.json` are some of the most popular. In JavaScript applications that use file storage to store data, JSON is the format of choice, for a number of reasons!
  
JSON stands for JavaScript Object Notation, and is the format that is often used when data is sent from a backend to a frontend, e.g. from a server to a web page. JSON allows us to format our data in the familiar structure of JS arrays and objects, with some slight differences. Namely: JSON data is what we call "stringified", meaning that all keys and values are formatted as strings! This helps to make JSON easily convertible into different languages, so that if we built an application with a Python backend and a ReactJS frontend, our data could be easily translated between the two. Of course, in our full-stack JavaScript applications, JSON is particularly friendly to work with.

We use the file extension `.json` to store JSON data in a file. Because our data is stringified, we need to **parse** it when we want to use it in JavaScript code. This turns JSON data from the stringified version into a typical JS object, so that we can interact with it as we would any other object full of data. We can parse our JSON data using the handy `JSON.parse` method as shown below:

```javascript
// assume jsonData is some JSON data read from a file
const parsedData = JSON.parse(jsonData)
```

In this application, we're storing a number of Aussie Animals inside of an `animals.json` file. Let's take a look at how to interact with that data.

### Setting Up our Model

As mentioned previously, our models are going to provide us with an _object_, or JS class, that can represent our data from our file storage. Before we begin to build our model, go ahead and open up `animals.json` to see what we're working with.

Inside that file, we'll see an array with a number of animal objects, each of which have an `id`, `name`, and `type`. As we know from our prior work with JS classes, we can set up a class to store these pieces of data in state.

Let's add our model into our application. Within the `src` directory, add a subdirectory called `models`. Inside that directory, add a file `Animal.js` with the below code:

```javascript
// src/models/Animal.js

import fs from 'fs'

const animalsPath = "animals.json"

class Animal {
  constructor({ id, name, type }) {
    this.id = id
    this.name = name
    this.type = type
  }

  static findAll() {
    const stringifiedData = fs.readFileSync(animalsPath)
    const animalData = JSON.parse(stringifiedData).animals
    const animals = animalData.map(animal => {
      return new Animal(animal)
    })
    return animals
  }
}

export default Animal
```

Looking at our constructor, we see that we now have an object that can represent each part of our data: for each `Animal`, we'll be able to keep track of their `id`, `name`, and `type`. As a reminder, we're using _object destructuring_ in our constructor to take in one object with multiple key-value pairs, and pull those out as individual variables.

But wait....this class is more complicated than we expected! Below our simple constructor, we've added a `static` method called `findAll`. When this method gets called, our code will read the `animals.json` file, interpret its data, and return an array of `Animal` objects to us.

This method is our first time working with a JSON file. To do so, we use the handy JavaScript module called `fs`, which gives us different methods to interact with our File System. Here, we define our `animalsPath` to tell our code where to find our file from the root. We then use `fs.readFileSync()`, which allows us to hand in a file path and receive back the contents of that file.  Finally, remember that we will need to _parse_ our JSON data in order to interact with it as a normal JS array of objects: we use `JSON.parse` to do so. 

Once we have our array of raw data, we use `.map()` to take our array of animal data and turn it into an array of `Animal` objects. We return this array as our return value.

We've built our first model! Our `Animal` class is able to store our data in an _object_ with _instance variables_, giving consistent structure to the data we're working with and allowing us to code in an _object-oriented_ way. It is also able to do the work of reading our file for us, so that we have all of the business logic pertaining to our animal data isolated to one organized class. 

Now that we have an object for reading and storing our data, let's work to use this functionality inside our Express routes!

### Adding our Animals to our Page

If we take a look at our `animalsRouter`, we'll see that we currently have a `GET` route set up for `/animals`, rendering our index page as so:

```javascript
animalsRouter.get("/", (req, res) => {
  res.render("index")
})
```

Inside of this route, we're going to want to access all of our `Animal` objects from our `animals.json` file, and then add them onto our page.

Luckily, we already created a helpful method to read our file and get `Animal` objects in return! We can use our `findAll` static method to get the data we need, and pass it over to our `index` view.

Update your `animalsRouter` to have the following code:

```javascript
// src/routes/animalsRouter.js

import express from "express"

import Animal from "../models/Animal.js"

const animalsRouter = new express.Router()

animalsRouter.get("/", (req, res) => {
  res.render("index", { animals: Animal.findAll() })
})

export default animalsRouter
```

Above, we use our `Animal` class' static `findAll` method to gain access to an array of all of the animals in our file. We pass that array to our `index` template as a variable called `animals` using `res.render("index", { animals: Animal.findAll() })`.

Now that we have our animal objects, we can update our `index.hbs` to put that list on the page:

```handlebars
<!-- views/index.hbs -->

<h2>Animals from Down Under</h2>

<ul>
  {{#each animals }}
    <li>{{ this.name }}, {{ this.type }}</li>
  {{/each}}
</ul>
```

Here, we use the `#each` to iterate over our list of animals in Handlebars. From there, we use the `this` keyword to call on each iterable, i.e. each single animal at a time. We can use `this` to access instance variables as so: `this.name`, `this.type`. We put a list of animals, with their name and type, onto the page.

Give your page a refresh and see it in action!

### Why This Matters

As our apps grow and we include more moving pieces, it may be confusing at first to navigate all of our different files. However, in the long run, our organization will pay off! Keeping all of our _business logic_ in our model, including both the organization _and_ the reading of our file, allows us to know where to look if we're looking for functionality surrounding the Animal data. It also allows us to keep our controller, or router, in adherence with the Single Responsibility Principle: our _controller_ handles an incoming request, uses a _model_ to get the data, and sends that data to the _view_ for rendering to the page. It then hands all relevant information to the browser via the HTTP response.

As we begin to need to store data within our application, we will need to use some kind of persisted data: either file storage or a database. In an MVC app, we want to utilize _models_ to interact with that data! There is a common design principle, "Fat Model, Skinny Controller" (made popular by [Ruby on Rails][rails]), that supports the Single Responsibility Principle by arguing that the controller should _only_ be responsible for routing, while the model should _only_ be responsible for interacting with data. By extracting our business logic into a model, we're keeping our application well-organized and smoothly running.

### In Summary

In this example, we got familiar with JSON files as data storage. We looked at how to use a model to represent our data, as well as how to _read_ JSON files via a static method in our model. We now have a complete picture of the three key parts of our **MVC design pattern**!

Here, we focused on a `GET` request and _reading_ data from our file. In the future, we'll build on this structure by adding other routes in our controllers (such as `POST` requests) and new logic in our models (such as _creating_ new animals) as well!

[rails]: https://rubyonrails.org/