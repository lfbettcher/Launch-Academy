// Simulates rolling a pair of dice
let sides = parseInt(prompt("How many sides does your dice have?"))
let times = parseInt(prompt("How many times would you like to roll your pair of dice?"))

let rollAgain
do {
    for (i = 0; i < times; i++) {
        let dice1 = rollDice(sides);
        let dice2 = rollDice(sides);
        console.log(`You rolled a ${dice1} and a ${dice2}. Total: ${dice1 + dice2}`)
    }
    rollAgain = prompt("Roll Again? (y/n)")
} while (rollAgain === "y")


// Returns an integer between 1 and sides (inclusive)
function rollDice(sides) {
    return Math.floor(Math.random() * sides) + 1;
}