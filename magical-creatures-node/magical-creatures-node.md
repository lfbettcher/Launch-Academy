There are many awesome and powerful magical creatures out there, and we want to track which ones are our favorites in this web application.

### Learning Objectives

- Build an Express application to serve dynamically generated HTML
- Understand the role of the server and views in a Express application and how they interact
- Discover a more extensive file-system for an Express/node app

## Getting Started

```no-highlight
et get magical-creatures-node
cd magical-creatures-node
yarn install
yarn run dev
```

Remember that you can run your program with a debugger attached by running `yarn run dev:debug` instead!

## Navigating the application

#### Server

The `src/routes/rootRouter.js` and `src/routes/magicalCreaturesRouter.js` files are where you will be writing code to configure the logic for each of the paths a user visits so that they can see some fantastical beasts.

Specifically, this is where you will:

- designate paths available in your app that a user can visit
- read data from the `src/creatures.js` to prepare it for use in a Handlebars template
- designate the Handlebars templates that you will use and pass data to them.

#### Public

Unless you wish to configure client-side JavaScript or styling, you will not need to edit the files in the `public` folder to complete this assignment.

#### Views

Add your custom Handlebars templates here! A `default.hbs` template has already been created to aid in rendering any items you wish to have on _every_ webpage (like a navbar or footer).

Remember to refer to the [Handlebars Documentation](https://handlebarsjs.com/guide/) for guidance on handlebars expressions.

## Instructions

Build a web application that has a homepage welcoming me to the website, a creatures _index_ page that lists our favorite magical creatures, and _show_ pages presenting each individual magical creature.

Tackle these user stories in the order that they're presented.

## User Stories

### Part 1

```no-highlight
As a huge fan of magical creatures
I want to arrive at a welcome page when I navigate to the root path
So that I can get excited about magical creatures
```

Acceptance Criteria:

- When I visit `/`, I can see a header with a welcome message to the website reading "Welcome One and All to the Magical Creatures Website!"
- Below the header is a short paragraph description of the website, which you can make up yourself!
- _Extra credit:_ Render an image of a magical creature of your choosing on the home page. You may wish to revisit how to use an HTML image tag to aid you.

**Tips:** Create a template in your `views` directory to render your welcome page. Remember that you can add a route for the root path `/` directly inside your `rootRouter`. You won't need to pass any variables to the view in your `src/rootRouter.js` to satisfy this story, since this information will be static.

### Part 2

```no-highlight
As a huge fan of magical creatures
I want to view a page with the names of each magical creature
So that I can remember which magical creatures are my favorite
```

Acceptance Criteria:

- When I visit `/magical-creatures` I can see list of all the magical creature names
- The list of magical creatures should come from the `creatures` array, which has already been provided

### Part 3

```no-highlight
As a huge fan of magical creatures
I want to view additional information about a creature
So that I can learn more about that creature
```

Acceptance Criteria:

- I can navigate to a distinct url and web page for each magical creature. Specifically, navigating to `/magical-creatures/unicorn` should bring bring me to a unicorn show page, and navigating to `/magical-creatures/dragon` should bring me to a dragon show page
- Each creature page displays the creature's name, age, and magical ability

**Tips:** _Note that until you finish Part 4 you will need to navigate to these show pages by editing the url in your browser directly._ We'll need to use dynamic routes to accomplish this task. Focus first on defining your dynamic route, then on rendering the creature's name at that route, and lastly on being able to render the creature's magical ability.

#### Part 4

```no-highlight
As a huge fan of magical creatures
I want to see links to each magical creatures show page
So that I can easily navigate the website
```

Acceptance Criteria:

- When I visit `/magical-creatures`, each name shown is a link to a magical creature's show page

**Tip:** You'll need to make use of `<a>` tags.

## Non-Core User Stories

Below are optional user stories that you may wish to tackle. Please finish the core user stories above before beginning. Note that you may have to read ahead to some of the Model material for guidance on how implementation of these stories.

#### Part 5

We are huge fans of object-oriented programming in the magical world, so we use objects to represent each of our creatures in our Express application. Specifically, a MagicalCreature class can help remove some of the busy logic from app.js and provide objects that better manage the state of a magical creature.

**Define a `MagicalCreature` class that has the following aspects:**

- takes in a `name` as an argument, a `magicalAbility` as a second argument, and an `age` as an optional third argument
- has a method `isAncient`, that returns true if the creature's age is greater than 200 or `null` and false if it is less than or equal to 200.

_Note: You can create your `MagicalCreature` class in a `src/models` directory and import it for use in magicalCreaturesRouter.js._

**Use your `MagicalCreature` class in your application**

- Create a static method `.findAll()` on the `MagicalCreature` class that reads from the `creatures` array and returns a MagicalCreature object for each of our creatures. Ensure that this method is being called when a user navigates to `/creatures` to populate the creature objects sent to your handlebars template.

- Create a static method `.find(creature_name)` on the `MagicalCreature` class that takes a `creature_name` and returns a MagicalCreature object that has the attributes of the corresponding creature in the `creatures` array. Ensure that this method is being called when a user navigates to `/creatures/:creature_name` to populate the creature object sent to your Handlebars template.

- Navigating to a creature's show page should now also show whether or not the animal is ancient, depending on their age.

#### Part 6

Write unit tests for your new functionality in `src/__tests__/MagicalCreature.test.js`. You should be testing any methods you define.
