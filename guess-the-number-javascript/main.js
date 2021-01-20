// Generate a random number between 1 and 10, to string to compare with user input
const randomNumber = (Math.floor(Math.random() * 10) + 1).toString();

// Get the user's name
const userName = prompt("What is your name?")

// Ask the user to enter a number
const userNumber = prompt("Enter a number.")

// If the number equals the random number let the user know that they've won, 
// otherwise let them know that they lost
if (userNumber === randomNumber) {
    alert(`${userName}, you won!`)
} else {
    alert(`${userName}, you lost :(`)
}