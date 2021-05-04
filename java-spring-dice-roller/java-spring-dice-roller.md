In this challenge, you will create a web-based dice rolling game.

## Getting Started

```bash
et get java-spring-dice-roller
cd java-spring-dice-roller
./mvnw spring-boot:run
```

For this project, we have configured Spring Boot so that you can use JSP and JSTL views. We have managed this configuration for you. Your controller along with your first JSP has been created in the appropriate place in the filesystem at `src/main/webapp/rolls/show.jsp`.

In future projects, we will be using a new templating library, called Thymeleaf. For now, let's first get acclimated to Spring controllers before adding that layer of complexity into the mix.

## Core User Stories

### Roll a Die

```no-highlight
As a gambler
I want to roll a die
So that I can get a random result
```

Acceptance Criteria:

- When I navigate to `/rolls/new`, the server will give me a random number between 1 and 6
- Each time the die is rolled, it should provide a new result
- The web page should output `You rolled a <number>`

Implementation Details:

- Use a `@Component` Bean `DiceRoll` that handles the generation of the random number

### Roll a Die with a Guess

```no-highlight
As a gambler
I want to guess what will be rolled
So that I can play a game of change
```

Acceptance Criteria:

- When I navigate to `/rolls/new?guess=<num>`, the system rolls a die and compares it to the supplied guess
- If the visitor guesses correctly, it should say "YOU GUESSED THE NUMBER" in bold letters
- If the guess was less than the number, it should render "Nope. Sorry. Try Again"
- If the guess was more than the number, it should render "Close. If we were playing price is right rules, you would have won."
- The result of the roll should still be reflected in the rendering

### Roll Multiple Dice

```no-highlight
As a gambler
I want to roll multiple dice
So that I can increase the stakes of the game
```

Acceptance Criteria

- When I navigate to `/rolls/new?guess=<num>&diceCount=<number of dice>`, the `<number of dice>` should reflect the number of dice that are rolled.
- This should increase the range of the roll result. IE, if I specify 3 for the `diceCount` querystring parameter, it is possible for me to roll an 18.
- The number of dice parameter should be respected even if I don't specify a guess.

## Non-Core User Stories

### Roll Multiple Times

```no-highlight
As a gambler
I want to roll multiple times
So that I can play the game multiple times
```

- When I navigate to `/batchRolls/new?guess=<num>&diceCount=<number of dice>&rollCount=<number of rolls>`, the application should render the results of each roll.

#### Implementation Details

- Consider using an `ArrayList` of `DiceRoll` objects to complete this task.
- Consider how you can use an `include` to reduce duplication between your `/rolls/new` view and your `batchRolls/new` view

### Use FontAwesome to Render Dice

```no-highlight
As a gambler
I want to see actual dice icons
So that my gambling experience is enhanced
```

Acceptance Criteria:

- For every roll, the actual dice that reflect the result should be rendered

#### Implementation Details

- FontAwesome supplies a Die icon for each side of a six-sided die, e.g. https://fontawesome.com/icons/dice-four