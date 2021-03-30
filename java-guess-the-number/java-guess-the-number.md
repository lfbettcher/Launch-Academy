## Getting Started

```no-highlight
et get java-guess-the-number
cd java-guess-the-number
idea .
```

## Observing What's Been Provided

For the first time, we're providing a `static` function for you to make use of in your software.

```java
public static final int NUMBER_CEILING = 10;
public static int getRandom() {
  Random random = new Random();
  return random.nextInt(NUMBER_CEILING);
}
```

Each time you invoke this function via `getRandom()` from within your `main`, the function will **return** a random number from 0-9. You can assign the result of this function to a variable, which we've done for you.

## Core User Story

### Play the Game

```no-highlight
As a daring gambler
I want to guess the number
So that I can see if I'm right
```

Acceptance Criteria:

- The user should be prompted to select a number from 0-9
- If the user's input matches the random number generated, tell them that they've won
- If the user's input does not match the random number generated, reveal the number generated and inform them that they've lost

### Validate the User's Choice

```no-highlight
As an unscrupulous gambler
I want to receive an error if I enter an invalid number
So that I can play
```

Acceptance Criteria:

- If a user enters something other than 0-9, output an error message and tell them to play again

## Non-core User Stories

These stories will require you to read ahead to learn about looping.

### Continuous Validation

```no-highlight
As an unscrupulous gambler
I want the opportunity to correct an errantly entered number
So that I can play the game
```

Acceptance Criteria:

- If a user enters something other than 0-9, output an error message and ask them to provide a new number
- Continue prompting the user until they enter a valid number 

### Play Again

```no-highlight
As an addicted, low-stakes gambler
I want to play again after I've guessed the number
So that I can improve my guessing skills
```

Acceptance Criteria:

- Once a user has played the game, ask them if they want to play again.
- If they enter 'Y', allow them to play the game again
- If they enter 'N', quit the game

### Higher or Lower

```no-highlight
As a low-stakes gambler
I want to be informed if my number is higher or lower
So that I can guess the right number
```

Acceptance Criteria:

- If the random number is less than the number inputted, tell the user, and allow them to guess again
- If the random number is more than the number inputted, tell the user, and allow them to guess again
- If the random number is equal to the number inputted, the game is won
- When the game is won, output the number of guesses it took
