Now that we can find elements in the DOM, let's modify them!

### Learning Goals

- Be able to modify the text, inner html, class, or id of an element
- Create a new element and place it in the desired location within the DOM
- Remove an element from the DOM

### Why This Matters

Being able to modify the DOM is what gives our JavaScript code the power to make our HTML webpages dynamic, responding to user input and other events.

## Getting Started

```no-highlight
et get modifying-the-dom
cd modifying-the-dom
code .
open index.html
```

## Introduction

In a previous article, we looked into different ways of accessing elements in the DOM, including ways to

- get an element based on its properties, like `getElementById()`
- get an element based on a CSS query, like `querySelector()`
- get an element based on its relationship to another element, such as `parentElement`

Now that we've got all these ways to find an element, let's look at some ways we can manipulate them!

## Modifying an Existing Element

### Basic Modifications

JavaScript provides us with many ways of modifying elements; let's take a look a few, and then we'll put them into practice!

First, we have some properties that we can use to get or set different aspects of an element:

| Node property | Is...                                                                               |
| ------------- | ----------------------------------------------------------------------------------- |
| `id`          | the node's id (as a string)                                                         |
| `className`   | the node's class(es) (as a string, where multiple classes are separated by a space) |
| `textContent` | the text contained by the element and its children (as a string)                    |
| `innerHTML`   | the HTML corresponding to the node's children (as a string)                         |

Open up `index.html` in your browser and open the console. Let's grab the element for Customer Support:

```javascript
let csElement = document.getElementById('costumer-support')
```

Eagle-eyed readers may have noticed that there's a typo in the element id! "Costumer support" would mean providing support for someone who makes costumes. Let's fix this!

```javascript
csElement.id = 'customer-support'
// customer-support
csElement.id
// customer-support
```

If you right-click on the customer support bullet on the webpage and choose "Inspect", which will automatically open the relevant element in the Elements tab in the dev tools, you'll see that we've successfully changed the `id`!

Now let's look at `textContent` and `innerHTML`, using the same element as before. Run each command separately, so that the result prints to the console.

```javascript
csElement.textContent
csElement.innerHTML
```

These two commands should return exactly the same thing: the text of the list item we're examining. This is because this element doesn't have any children. Let's try the same thing, except for our `<ul>` (again, running each command separately):

```javascript
let list = document.getElementById('role-list')
list.innerHTML
list.textContent
```

`innerHTML` should have returned something like

```no-highlight
 <li id="frontend" class="role">Frontend developer: creates and manages code run by the browser</li>
 <li id="backend" class="role">Backend developer: ...
```

whereas `textContent` returned

```no-highlight
Frontend developer: creates and manages code run by the browser
Backend developer: ...
```

When choosing which property to use, you'll often want to use `textContent` when modifying the content of an element without any children; if you were to try

```javascript
list.textContent = 'My new list'
```

you'd find that you've replaced all `list`'s children with the specified text. (If you run that command in the console, be sure to refresh the page afterwards to revert to the original HTML!)

In contrast,

```javascript
csElement.textContent = 'My new description'
```

works just as we'd hope, since that element doesn't have any children.

### Modifying Other Attributes

We've seen specific ways of accessing and modifying the `class` and `id` attributes, but what if we want to modify other attributes? We'll use the following methods:

| Node method                                | Does...                                                                    |
| ------------------------------------------ | -------------------------------------------------------------------------- |
| `hasAttribute("someName")`                 | returns whether the element has an attribute named someName (as a boolean) |
| `getAttribute("someName")`                 | returns the value of the attribute named someName (as a string)            |
| `setAttribute("someName", "someNewValue")` | sets the value of the someName attribute to someNewValue                   |
| `removeAttribute("someName")`              | removes the attribute named someName                                       |

Let's take a look at the only anchor tag in our document:

```javascript
let anchor = document.getElementsByTagName('a')[0]
```

Remember that, even though there's only one anchor element in the document, `getElementsByTagName` will always return an array. So, we're selecting the first result in that array, since it's the only result.

Now, let's check whether that element has certain attributes:

```javascript
anchor.hasAttribute('href')
// true

anchor.hasAttribute('books')
// false
```

and then get the value of its `href` attribute:

```javascript
anchor.getAttribute('href')
// http://www.xkcd.com
```

Now let's modify that attribute:

```javascript
anchor.setAttribute('href', 'http://fatcatart.com')
// http://fatcatart.com
```

Your anchor should now send users to a magnificent site dedicated to fat cat art.

## Adding or Removing Elements from the DOM

### Create the New Element

Sometimes, instead of modifying an existing element, we want to add a whole new one!

First, we need to create the node; we can do this using the methods below:

| Document method            | Does...                                        |
| -------------------------- | ---------------------------------------------- |
| `createElement("tagName")` | creates a new node with the specified tag type |

Once we've created the node, we can use the skills we learned previously to modify it to our liking. Let's give it a try:

```javascript
let myNewNode = document.createElement('li')
myNewNode.textContent = 'Hi there!'
myNewNode.className = 'greeting'
```

If we look at `myNewNode` in the console, we see

```javascript
myNewNode
// <li class="greeting>Hi there!</li>
```

However, this node isn't on the page yet! Let's add it.

### Insert the New Element

Next, we need to insert our new node into the DOM. We have a few methods to choose from, depending on whether we want to add the new element as the last child of a parent node, replace a current child, or insert before a current child.

All of these methods are called on the parent.

| Method                                   | Does...                                                      |
| ---------------------------------------- | ------------------------------------------------------------ |
| `appendChild(someNode)`                  | adds the provided node as the last child of the current node |
| `replaceChild(newChild, oldChild)`       | replaces the old child node with the new child node          |
| `insertBefore(newChild, referenceChild)` | inserts the new child node before the reference child node   |

Let's look at `appendChild`; the other two methods are very similar.

Working with `myNewNode` from before, let's add it to the end of the list of traits at the bottom of the page.

```javascript
let traitList = document.getElementById('traits')
traitList.appendChild(myNewNode)
```

Viola! We've added a new list item; you should see our node immediately added to the bottom of the list.

## Removing Elements from the DOM

To remove a node, we're going to call `removeChild` on its parent and hand in the specific element we're interested in.

| Method                  | Does...                                                    |
| ----------------------- | ---------------------------------------------------------- |
| `removeChild(someNode)` | removes the provided node from the current node's children |

Let's take out the fullstack developer position.

```javascript
let fullstack = document.getElementById('fullstack')
let fullstackParent = fullstack.parentNode
fullstackParent.removeChild(fullstack)
```

And that's it! Goodbye, fullstack developers!

### In Summary

This article walked us through how to use JavaScript to modify existing HTML elements and create or remove new elements. Being able to modify webpages with JavaScript opens a whole new world to us in terms of making awesome webpages that respond to the user!

[css-selectors]: https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Selectors
