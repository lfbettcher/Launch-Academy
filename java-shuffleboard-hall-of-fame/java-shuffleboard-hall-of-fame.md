We're going to build a fully functional web application for keeping track of shuffleboard game results.

## Getting Started

```no-highlight
et get java-shuffleboard-hall-of-fame
cd java-shuffleboard-hall-of-fame
createdb shuffleboard_hof
idea .
```

## Core User Stories

### Create your Database

```no-highlight
As a shuffleboard player
I want to have a database
To store important Shuffleboard stats
```

- Create a migration for a `games` table containing the following fields
  - red_team_player_1
  - red_team_player_2
  - red_team_score
  - blue_team_player_1
  - blue_team_player_2
  - blue_team_score

- Create an `@Entity` for Game.


### Log a Game

```no-highlight
As a shuffleboard player
I want to keep track of scores
So that I can improve over time
```

Acceptance Criteria:

- When I navigate to `/games/new`, I am prompted to enter Red Team Player 1, Red Team Player 2, Red Team Score, Blue Team Player 1, Blue Team Player 2, and Blue Team Score into a form
  - This will require you to create a `servlet` (controller)
- When I provide the necessary information, the data is saved to the database via a doPost
  - For an extra challenge, implement Hibernate validations

*** You will eventually be asked to create a DAO for this, if you are comfortable with them you can simply create it now. ***

### Display Game Results

```no-highlight
As a shuffleboard player
I want to see the entire list of games
So that I can analyze the history
```

Acceptance Criteria:

- When I navigate to `/games`, I can see a list of completed games retrieved from the database
- For each game, I can see the players' names, the red team score, and the blue team score
- For each game, I can see who won, Blue or Red. The team with the highest score wins. Do not worry about ties.

### Create your DAO

```no-highlight
As a java developer
I need a Data Access Object
To ensure I'm programming to an interface
```

Acceptance Criteria:

- Create a `service` (DAO) for your Game objects to interact with the database.
- Extract any persistence and data retrieval from the `servlet` to the `service`.

## Non-Core User Stories


### Normalize Player Data

```no-highlight
As a shuffleboard site admin
I want a more scalable persistence mechanism
So that my site behaves more reliably
```

Implementation Detail:

- Normalize player data by extracting it to its own table. This will benefit you in subsequent user stories

### Player Hall of Fame

```no-highlight
As a shuffleboard player
I want to know who's the best in history
So that I can brag to all of my competitors`
```

Acceptance Criteria:

- When I navigate to `/players`, I can see a list of players sorted from most wins to least wins
- Each player's name is a link to `/player?playerId=<primarykey>` where `<primarykey>` is a unique identifier for the player

### View Player Detail

```no-highlight
As a shuffleboard player
I want to see the history of a player
So that I can see how competitive they are
```

Acceptance Criteria:

- When I navigate to `/player?playerId=<primaryKey>`, I can see the player's name, as well as a list of all of the games they played in.
- For each game, I can see who their partner was, who was on the opposing team, and whether they won or lost
- I can also see the player's total number of wins and losses

### You will remember me

```no-highlight
As a shuffleboard player
I want my name to be remembered
So that it's easy to enter game results
```

Acceptance Criteria:

- After I've entered a game, the name of Red Team Player 1 is remembered and suggested as the input value each subsequent time I go to enter a new game.
- Create a session or cookie to track this data, and populate the form
