We're going hiking, and we need to put together a list of things to bring.

## Learning Goals

* Implement a form that submits issuing post request
* Use `req.body` to persist data in a file

## Getting Started

```no-highlight
et get going-hiking-with-an-express-app
cd going-hiking-with-an-express-app
yarn install
yarn run dev
```

Navigate to http://localhost:3000/supplies to see the list of existing supplies.

## Instructions

We've started the list for you, and the application. Modify the program so that there is a form below the list that allows a user to add other items to their hiking list. Submitted items should show up on the page immediately following submission.

## Core User Story

```no-highlight
As a hiker planning their trip
I want to add an item
So that I don't forget it before I leave
```

Acceptance Criteria:

* When viewing the list of supplies, I am presented with a form that allows me to add a single item
* When I submit the item, it is added to the bottom of the list

Implementation Details:

* The new supply form should POST to `/supplies`

## Noncore User Story

```no-highlight
As an unscrupulous hiker planning their trip
I want to receive an error message if I don't enter a supply
So that I can correct the issue
```

Acceptance Criteria:

* If I submit a new supply without entering a value, or if I submit an entry with only spaces in it, the item should not be added to the list, and I should be presented with an error message.
* I should not see the error message on the first page load, and I should not see it when I successfully enter a supply that is added to the list.
