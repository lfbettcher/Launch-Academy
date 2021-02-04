Let's get some practice with separating our concerns in a class.

### Getting Started

```no-highlight
et get javascript-separation-of-concerns-practice
cd javascript-separation-of-concerns-practice
node main.js
```

You should see the output for each soccer player print to the terminal.

### Instructions

In `Player.js`, you'll find a `Player` class defined. This `Player` class is keeping track of each player's name, number, and position, but also the team name and the head coach's name.

### Part One

Refactor the `Player` class to no longer be responsible for their team information. Create a `Team` class in a new `Team.js` file which is responsible for storing the information of team name and coach. The `Team` class should also store a `players` array as an instance variable, and include a `signPlayer` method which adds players to the array. Import your `Team` class into the `main.js` file, and update the code in this file to correctly instantiate the refactored players, and create and populate the teams. You can add `console.log` statements into `main.js` to test and make sure your teams have the proper players added.

### Part Two

Our `Team` class is still storing information for a separate concern: our `Coach`es! Add a separate `Coach` class in its own file, and store the coach's name and first year as coach in instance variables. Also add a `stats` object as an instance variable: but this one will store the following stats:

```javascript
{
  wins: 0,
  losses: 0,
  draws: 0,
  matchesPlayed: 0
}
```

Add a `gameOver` method which takes in an argument of `W`, `L`, or `D`. This method should look at the input and conditionally determine if it should add a win, loss, or draw to the stats. It should then increment the `matchesPlayed`. Feel free to add any fun output you would like!

Notice that we did not make a `Human` class to encompass both our Players and Coaches. This is important, because they need to keep track of different information!

### Part Three

Update `main.js` to output the following information:

**For each coach:**

`(Coach name) is the head coach for (team name).`

`(Coach name) stats: (stats)`
