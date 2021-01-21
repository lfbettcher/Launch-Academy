## Instructions

Write a program where you can face off with a computer in **Rock, Paper, Scissors**. You should be able to run this code in your Google Chrome console and have it work!

### Rules of the Game

Taken from [Wikipedia](http://en.wikipedia.org/wiki/Rock-paper-scissors):

> Rock-paper-scissors is a hand game usually played by two people, where players simultaneously form one of three shapes with an outstretched hand. The "rock" beats "scissors", the "scissors" beats "paper" and the "paper" beats "rock"; if both players throw the same shape, the game is tied.

### Getting Started

```no-highlight
cd challenges
et get rock-paper-scissors-js
cd rock-paper-scissors-js
```

You're tasked with building a game your astronauts can play on their own while traveling through space! This game should follow standard rock, paper, scissors rules, and allow for user input. Open Google Chrome dev tools in your browser to see your console output. Feel free to use methods that have not been covered yet. Remember, there can be many right ways to get the correct output.

Running `open index.html` or using your folder navigation and opening `index.html` manually should trigger your default browser to open, render the text in the html file, and run the code found in `game.js`.

## Meeting Expectations Requirements

Your finished code should be added to the `game.js`.

The following features are required:

- A game consists of a single round.
- `Welcome to Rock, Paper, Scissors!` should appear in the console.
- The player is prompted to choose a selection by typing `"r"` (rock), `"p"` (paper), or `"s"` (scissors).
- The computer opponent randomly chooses between rock, paper, and scissors.
- Output player and computer player choices. Examples:
  - `Player chose rock`
  - `Computer chose scissors`
- Output the result of the game before exiting. Examples:
  - `Player wins!`
  - `Computer loses!`
  - `Tie!`
- If the player enters an invalid option, print an error message.
- If both players choose the same option, it is a tie.

**Submit your Meets code BEFORE attempting Exceeds**

## Exceeding Expectations Requirements

- All Meets Expectations Requirements have been met
- The game ends when the player or the computer wins the best out of three rounds played
- Ties do not count as a win
- Output the results of each round. Examples:
  - `Player chose rock.`
  - `Computer chose rock.`
  - `This round was a tie!`
- Output the winner of all three rounds
  - `Computer wins the game!`
- Output the result of the game totals before exiting. Examples:
  - `Player ties 1 wins 2 loses 0!`
  - `Computer ties 1 wins 1 loses 1!`

### Sample Console Output

**Tie:**

```no-highlight
Welcome to Rock, Paper, Scissors!
[Prompt] Choose rock (r), paper (p), or scissors (s): r
Player chose rock.
Computer chose rock.
Tie!
```

**Player wins:**

```no-highlight
Welcome to Rock, Paper, Scissors!
[Prompt] Choose rock (r), paper (p), or scissors (s): p
Player chose paper.
Computer chose rock.
Paper beats rock, Player wins!
```

**Computer wins:**

```no-highlight
Welcome to Rock, Paper, Scissors!
[Prompt] Choose rock (r), paper (p), or scissors (s): s
Player chose scissors.
Computer chose rock.
Rock beats scissors, Computer wins!
```

#### Tips

- To simulate a computer player picking a shape you might want to use the `Math.random()` in combination with `Math.floor()` to randomly generate a number. The former returns a floating-point, random number between 0 and 1, and the later returns the largest integer less than or equal to a given number.

```javascript
Math.floor(Math.random() * 10 + 1)
// => 4
Math.floor(Math.random() * 10 + 1)
// => 10
// returns a random number between 1 and 10
```

- You can check for multiple conditions in a single expression using the **or** (`||`) or **and** (`&&`) operators. The following example will check if a user typed in _foo_ **or** _bar_:

```js
let playerInput = prompt("What do you want to say?")
// player types "foo"

if (playerInput === "foo") || (playerInput === "bar") {
  console.log("You've entered either foo or bar")
} else {
  console.log("You didn't enter either foo or bar")
}
```
