To make interactive web applications, users often need a way to submit information back to the server.
In this article we'll discuss how HTTP POST requests can be used to submit user input entered via HTML forms.

### Learning Goals

* Add an HTML form to a web page.
* Pass user input to the web server via an HTTP POST request.
* Persist user input using the filesystem.

### Getting Started

```no-highlight
et get http-post-and-forms-with-express
cd http-post-and-forms-with-express
yarn install
yarn run dev
```

### HTML Forms

We return to our familiar Aussie Animals app, to learn how to add new animals! The code has been provided for you along with this lesson.

It all begins with the ability to add an animal. Users most commonly provide web applications with input through **forms**. Forms allow for a variety of different inputs: typing in a text field, selecting options from a drop-down list, attaching a file for upload, etc. When a user is finished filling out a form, they can click a button to *submit* the form back to a web server along with all of their input.

To add a form to a web page we use the `<form>` element. Within the form we can define all of our inputs (e.g. text fields, select lists, etc.) but we also need to specify what happens when a user submits their info. Submitting a form is similar to clicking on a link in that it sends an HTTP request back to the server. The primary difference is that with a form we have the option to send an HTTP POST request rather than an HTTP GET. GET requests are intended for viewing web pages whereas POST requests are used when we want to modify or update something in our web app. Since we want to add a new task to our app, a POST request would be more appropriate here.

Every HTTP request has both a method and a path. Since we're creating a new task we can use the `/animals` path but this time, we'll define a POST endpoint. Note that `POST /animals` maps to a different endpoint than `GET /animals`. They're both using the same path, but the POST is *submitting* information to be saved whereas the GET would be intended *retrieve* existing information.

To create a form that will send a POST request to the `/animals` path we could start with the following HTML:

```HTML
<form action="/animals" method="post">
```

The *action* attribute specifies the path that the form submission will go to and the *method* attribute is used to choose between sending a POST request and a GET request. If our form is submitting information to be saved or otherwise change the state of our application then we should use POST. GET requests should only be used when a form does not modify or update anything on the server (e.g. using a form to search a site).

The `<form>` element by itself doesn't do much other than describe the endpoint for the request once it is submitted. To add various components we can use `<input>` elements. An input can represent a text field, a select list, checkboxes, a submit button, and many other widgets (a full list of input types can be found [here](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#attr-type)).

For our form we'll need to add two components: a text field where the user can type in the name of the task and a button so they can submit the form:

```HTML
<form action="/animals" method="post">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name">

  <label for="type">Type:</label>
  <input type="text" id="type" name="type">

  <input type="submit">
</form>
```

Here we've added three `<input>` elements: two text fields with `type="text"`, and a submit button with `type="submit"`. We've also included a `<label>` element for each text field indicating what the user should fill out. To ensure that the label is attached to the right input we match the `for` attribute on the label with `id` on the text field.

Another important attribute is the `name` attribute on the text fields. This is used to identify what the user typed into that particular field when it is passed along to the server. Since there are multiple input fields, we can distinguish between them based on their name attributes.

Add the above form into your `index.hbs`, and let's take a look at our complete `views/index.hbs` file.

```HTML
<h2>Animals from Down Under</h2>

<ul>
  {{#each animals }}
    <li>{{ this.name }}, {{ this.type }}</li>
  {{/each}}
</ul>

<form action="/animals" method="post">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name">

  <label for="type">Type:</label>
  <input type="text" id="type" name="type">

  <input type="submit">
</form>
```

If you haven't already, let's start up our server with `yarn run dev`. When we navigate to <http://localhost:3000/animals>, we should see the form discussed above.

After filling in the text field and hitting submit, we'll see that the animal has been added. But, how does it all work?

When the form is submitted, we're sending an HTTP POST request to the `/animals` path.

### HTTP POST

Before we study our `src/routes/animalsRouter.js` file, let's see what a POST request looks like. When a user submits the form their browser will send something like the following:

```no-highlight
POST /animals HTTP/1.1
Host: localhost
Content-Length: 35

name=Tasmanian+Devil&type=Marsupial
```

The first line defines both the method (*POST*) and the path (`/animals`). The main distinction between a POST and a GET request is the request body. This is where all of the user input is stored in key-value pairs:

```no-highlight
name=Tasmanian+Devil&type=Marsupial
```

Since we have a text field input with an attribute `name="name"` and another with `name="type"`, the browser will take whatever the user entered into those fields and form the matching pairs of `name=<user input>` and `type=<user input>`, respectively. If the user typed "Tasmanian Devil" and "Marsupial", we'll end up with the key-value pairs `name=Tasmanian+Devil` and `type=Marsupial`, joined together by an ampersand `&`. Notice how the spaces have been replaced by *+* symbols. This is known as URL encoding and allows us to send special characters (such as whitespace) in the request body (you might also see *%20* as an encoding for spaces).

We also have to include the size of the HTTP request body using the *Content-Length* header so that the web server knows how much data to expect. In this case our `name=Tasmanian+Devil&type=Marsupial` body is 35 characters long so we just have to specify `Content-Length: 35`.

### Updating Our Webserver

We have the form set up in our view template, now how do we handle the incoming POST request on the server? Let's study the `post` endpoint, which is already set up for us in our `animalsRouter` and therefore namespaced at the `/animals` path.

```javascript
// src/routes/animalsRouter.js

animalsRouter.post("/", (req, res) => {
  const animalName = req.body.name
  const animalType = req.body.type
  const newAnimal = new Animal({name: animalName, type: animalType})
  newAnimal.save()
  res.redirect("/animals")
})
```

Here, we're grabbing the new animal's name and type from the body of the request. As we discussed above, because the form input's `name` attributes are set to `name` and `type`, we can use `req.body.name` and `req.body.type` to access the values that the user specified prior to pressing the submit button.

We take that value and create a new `Animal` object, using our model. Finally, we invoke a method called `.save()` on that animal to save it to our JSON file. When we're done with that work, we redirect the user back to the index _using `res.redirect()`_, a built-in method of Express. This view should reflect the newly added animal, and it provides the user with the opportunity to add another animal.

To understand what's happening via our model here, let's take a peek at the provided `save` method in `Animal.js`:

```javascript
// src/models/Animal.js

...

static getNextAnimalId() {
  const maxAnimal = _.maxBy(this.findAll(), animal => animal.id)
  return maxAnimal.id + 1
}

save() {
  this.id = this.constructor.getNextAnimalId()
  const animals = this.constructor.findAll()
  animals.push(this)
  fs.writeFileSync(animalsPath, JSON.stringify({ animals: animals }))
}
```

Here, we have an instance method called `save` which is called on an existing `Animal` object to save it to our JSON file. First, the method uses a static method called `getNextAnimalId`, which checks what the highest id currently existing in our JSON file is, and adds one to that number in order to provide our next id. Since we never want to reuse or duplicate ids (they're meant to be unique!), we need this helper method to do that logic for us.

Then, our `save` method uses the static `findAll()` method to get all animals currently in our JSON file. Note that we need to use the syntax `this.constructor` if we want to call on a _static_ method from within an _instance_ method -- we can't just call `this.findAll()` because it will think it needs to look for an instance method. 

Finally, `save()` adds our new animal to that array, and writes to our `animal.json` file with the newly updated array of animals. Remember that, in order to "translate" from our JavaScript array back to JSON format, we need to `stringify` that data first!

#### Accessing Form Values

Everything that the user ever submits through a form will be accessible via the `req.body` object. Thanks to the `body-parser` middleware that we installed as a dependency for you, Express does the work of parsing the request body and putting into a more sensible JSON object. With `body-parser` properly installed and configured, `req.body` will always be be available for us to _use_ whatever a user typed in a form. (You can take a closer look at `src/app.js` if you want to see how this has been configured!)

Since `req.body` is a JSON object, it will have key-value pairs.  The number of key-value pairs will vary, depending on how complicated your form is. No matter how many pairs there are, you will be able to access the information in the same way you access any values in a JSON object: `req.body.inputName`. For our form, `req.body` should give us two key-value pairs:

```javascript
{ 
  "name": "Tasmanian Devil",
  "type": "Marsupial"
}
```

### Debugging

Remember that we can always run our Node programs with `--inspect-brk` if we want to debug them. In your Express apps, we provide an additional script to run the server called `dev:debug` which includes this flag. If you run `yarn run dev:debug` to start your server, you can access any `debugger`s in your Express code by navigating to <chrome://inspect> and opening up your Dedicated Dev Tools.

Now in `src/routes/animalsRouter.js`, let's place a debugger right below our `animalName` and `animalType` assignments:

```javascript
// src/routes/animalsRouter.js

animalsRouter.post("/", (req, res) => {
  const animalName = req.body.name
  const animalType = req.body.type
  debugger;
  const newAnimal = new Animal({name: animalName, type: animalType})
  newAnimal.save()
  res.redirect("/animals")
})
```

When we submit the form, Chrome should halt execution of the script, and we can use the console to explore what our provided `req.body` is.

### In Summary

Accepting user input and persisting data are two essential activities for most non-trivial web apps. Understanding how data is transferred from a client to the server and the difference between GET and POST requests is important for building web apps.

Whenever a user is **retrieving** information without intentionally modifying anything, use an **HTTP GET request**. If a user is submitting information back to the web application to either be persisted or otherwise modify any state (e.g. a user logging in), prefer an **HTTP POST request** (or variants of POST such as PUT, PATCH, DELETE, etc.).

A POST request contains any information being submitted in the **request body**. The request body contains a series of key-value pairs that have been **URL encoded** to ensure any special characters are transferred correctly. The most common way to submit information is via an **HTML form** where the parameters are grabbed from the form inputs.

### External Resources

* [MDN: My first HTML form](https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Forms/My_first_HTML_form)
* [MDN: Sending and retrieving form data](https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Forms/Sending_and_retrieving_form_data)
