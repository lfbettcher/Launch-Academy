We've learned about HTML and the DOM, including briefly touching on the fact that the browser uses the DOM to turn your HTML into an object that you can manipulate with JavaScript code. Let's put that into practice and learn how to access DOM elements!

### Learning Goals

- Be able to programmatically locate the DOM node corresponding to a specific HTML element
- Modify the text content of an element

### Why This Matters

Being able to locate a DOM node and access its properties and children is the first step in writing JavaScript code that will modify what's displayed on your webpage.

## Getting Started

```no-highlight
et get accessing-the-dom
cd accessing-the-dom
code .
open index.html
```

Code along with this article by opening the console in your browser's dev tools.

## Introduction

We've decided to put together a web page describing the different coding-related careers that might appeal to different bootcamp grads. We've saved a first draft of this page in `index.html`, but we'd like to modify the description of a project manager. To do this, we need to

1. "Grab" the list item element with the manager description
2. Update its text
3. Pat ourselves on the back

### Finding the `project-manager` `<li>`

There are many ways to find a particular element in the DOM; in this case, we're going to use the document `getElementById()` method. We can do this because the list item we care about has an id: `project-manager`.

> Watch out that you properly capitalize `Id`: `getElementByID()` is **not** a function!

Run the following in your console:

```javascript
let projectManagerListItem = document.getElementById('project-manager')
```

Now, let's look at what we've stored in `projectManagerListItem`:

```javascript
projectManagerListItem

// <li id="project-manager" class="role">
//   Project manager: manages a team of engineers working on one or more
//   projects
// </li>
```

Great! We've now stored an HTML element as a variable. Or, more precisely, we've stored a DOM node as a variable -- remember that the DOM is how our browsers turn HTML documents into something that we can navigate with JavaScript, as we've done here.

### Modifying the `project-manager` `<li>`

Now that we've found the element we want to modify and saved it to a variable, let's change its text to

```no-highlight
Project manager: herder of cats; benevolent project overlord
```

To do this, we'll use `textContent`, a property of any node. Let's take a look at this property's current value:

```javascript
projectManagerListItem.textContent

// Project manager: manages a team of engineers working on one or more
// projects
```

Lookin' good! Now let's set this property to our new value:

```javascript
projectManagerListItem.textContent =
  'Project manager: herder of cats; benevolent project overlord'
```

Take a look at the page displayed in your browser -- it should now have the updated text!

## Accessing DOM Nodes

Now that we've seen how we can store DOM nodes as variables to manipulate, let's look at some additional ways of accessing DOM nodes:

### getElement(s)

First, let's look at a methods that target HTML elements based on class, id, or tag name. These methods are all provided to us by the built-in `Document` JavaScript class; your browser automatically instantiates `document` when the page loads, and that's what we call these methods on.

Try each example in the console with `index.html` open!

| `Document` method                         | Returns                                                     | Try it!                                      |
| ----------------------------------------- | ----------------------------------------------------------- | -------------------------------------------- |
| `getElementById("someId")`                | The element whose `id` attribute matches the provided value | `document.getElementById("project-manager")` |
| `getElementsByClassName("someClassName")` | Elements whose `class` attribute matches the provided value | `document.getElementsByClassName("role")`    |
| `getElementsByTagName("someTagName")`     | Elements with the provided tag                              | `document.getElementsByTagName("li")`        |

Note that `Element` is singular for `getElementById` but plural when searching by class or tag name -- this is because an id should be unique, so there should never be more than one!

Meanwhile, `getElementsByClassName` or `getElementsByTagName` will always return an array, even if there's zero or one result. (Technically, they return an `HTMLCollection`, but we can think of this as an array.)

### querySelector

What if we need to make a more specific query? Let's say we'd like to find `<li>` elements with `class="role"`; we don't want the `<p>` with `class="role"`. We can use the `querySelector()` or `querySelectorAll()`, passing in a query string composed of _CSS selectors_.

When writing a query string:

- the `li` selector indicates an element with a `<li>` tag
- `.role` selector indicates an element with a class of `role`
- `#project-manager` selector indicates an element with an id of `project-manager`

We can chain these together: to find a `<li>` with a class of `role`, we use `li.role`.

For a complete list of CSS selectors, check out the [MDN documentation][css-selectors]!

| `Document` method                 | What it returns                      | Try it!                                |
| --------------------------------- | ------------------------------------ | -------------------------------------- |
| `querySelector("queryString")`    | First element that matches the query | `document.querySelector("li.role")`    |
| `querySelectorAll("queryString")` | All elements that match the query    | `document.querySelectorAll("li.role")` |

The only difference between these methods is that `querySelector` returns only the first result, whereas `querySelectorAll` returns all of them. Note that `querySelectorAll` returns a `NodeList` -- once again, we can treat this exactly like an array.

### Finding Children, Parents, and Siblings

Once we've found one node, we can also find other nodes by relationship. Before running the examples in the table below, run the following in the console:

```javascript
let listItemElement = document.getElementById('fullstack')
let unorderedListElement = document.getElementById('role-list')
```

| Node property   | Returns...                            | Try it!                         |
| --------------- | ------------------------------------- | ------------------------------- |
| `parentElement` | The element's parent element          | `listItemElement.parentElement` |
| `children`      | The elements's children (as an array) | `unorderedListElement.children` |

### In Summary

In this article, we've seen different ways of locating a particular element in the DOM and saving it to a variable. We've also used `textContent` to modify the element we've found, changing its text. We'll soon see many more ways to modify an element!

[css-selectors]: https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Selectors
