// Part 1 - Allow a User to "Open" an Account
const openAccountPrompt = "Welcome to our banking app! Please enter your starting balance.";
const startingBalance = getValidInput(openAccountPrompt, 0.01, Number.MAX_VALUE);
// When the user inputs a value, console.log message
console.log(`Great! Your account has been opened. Your bank account has a balance of $${startingBalance.toFixed(2)}.`);

// Part 2 (validation done in function getValidInput)

let currentBalance = startingBalance;

// Part 3 - Allow a User to Deposit Money
const makeDeposit = confirm("Would you like to make a deposit?");

// If they say "OK", prompt them to input the amount of a deposit they would like to make.
if (makeDeposit) {
    // Validate that this input is a positive number
    const depositAmount = getValidInput("How much would you like to deposit?", 0.01, Number.MAX_VALUE);
    // Calculate the new account balance and console.log a message
    currentBalance = startingBalance + depositAmount;
    console.log(`Thank you for your deposit of $${depositAmount.toFixed(2)}. `
                + `Your new account balance is $${currentBalance.toFixed(2)}.`);
}

// Part 4 - Allow a User to Withdraw Money
const makeWithdrawal = confirm("Would you like to make a withdrawal?")

// If they say "Cancel", console.log a message
if (!makeWithdrawal) {
    console.log(`Your final balance is $${currentBalance}.`);
} else {
    // If they say "OK", prompt them to input the amount they'd like to withdraw
    const withdrawalPrompt = "Input the amount you'd like to withdraw."
    // Validate that this number is a positive number which is less than the current account balance
    const withdrawalAmount = getValidInput(withdrawalPrompt, 0.01, currentBalance, 
                                           {highText: "You can't withdraw more money than you have!"});
    currentBalance -= withdrawalAmount;
    console.log(`You have withdrawn $${withdrawalAmount.toFixed(2)}. ` 
                + `Your final balance is $${currentBalance.toFixed(2)}.`);
}

// Part 2 - Validate the inputs
/**
 * Gets and returns valid number input from user.
 * 
 * @param {string} promptText message to prompt the user
 * @param {number} minValue minimum valid input value, inclusive
 * @param {number} maxValue maximum valid input value, exclusive
 * @param {...string} { lowText, highText} text to display to user if input is less than minValue or greater than maxValue
 * @returns {number} valid number inputted by user
 */
function getValidInput(promptText, minValue, maxValue, { lowText, highText } = { }) {
    // Messages for invalid inputs
    lowText = lowText === undefined ? `\n${promptText}` : `\n${lowText}`;
    highText = highText === undefined ? `\n${promptText}` : `\n${highText}`;

    let amount = prompt(promptText);
    while (Number.isNaN(parseFloat(amount)) || amount < minValue || amount >= maxValue) {
        if (Number.isNaN(parseFloat(amount))) {
            // Input is not a number, prompt again for a valid number.
            amount = prompt(`Please enter a valid number.\n${promptText}`);
        } else if (amount < minValue) {
            amount = prompt(`Please enter a number greater than or equal to ${minValue}.${lowText}`);
        } else {
            // amount >= maxValue
            amount = prompt(`Please enter a number less than ${maxValue}.${highText}`);
        }
    }
    return parseFloat(amount);
}