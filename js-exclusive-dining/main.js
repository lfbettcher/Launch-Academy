/**
 * Greets an interested party, determines if there is room for the party members
 * and ensure that all members are of age and dressed appropriately.
 */
function exclusiveDining() {
    // Greet user at the door
    alert("Greetings! Welcome to Exclusive Dining.")

    // prompt user for number of members in their party
    let membersInParty = getInteger("How many members are in your party? (enter a whole number)")
    // If party not equal to or less than 8, alert user it's too busy to let them in and end program
    if (membersInParty > 8) {
        alert("Sorry, we are too busy to let your party in.");
        return;
    }

    // Ask if all members are equal to or over 21 years of age, get yes/no answer
    let allOver21 = getYesNo("Are all members equal to or over 21 years of age? (yes/no)")
    // If not all members are >= 21, alert the party that all members must be over 21 and end program
    if (allOver21 === "no") {
        alert("All members must be over 21. Goodbye.")
        return;
    }

    // Ask user if all members have a valid ID with them
    let allHaveID = getYesNo("Do all members have valid ID with them? (yes/no)")
    // If not all members have ID, alert the party that all members must have a valid ID and end program
    if (allHaveID === "no") {
        alert("All members must have a valid ID. Goodbye.");
        return;
    }

    // Confirm with user that no one is wearing jeans
    let meetsDressCode = getYesNo("Do you confirm that no one is wearing jeans? (yes/no)")
    // If someone is wearing jeans, alert them there's a dress code and end program
    if (meetsDressCode === "no") {
        alert("Sorry, there is a dress code here. Jeans are not allowed. Goodbye.");
        return;
    }

    // All criteria have been met, welcome the user in
    alert("Welcome in!")
}
// Run main function
exclusiveDining();


/***** HELPER FUNCTIONS to get valid user input *****/

// Gets an integer answer from the user through prompt and returns the answer
function getInteger(promptText) {
    let answer;
    do {
        answer = prompt(promptText);
    } while (isNaN(answer) || !Number.isInteger(parseFloat(answer)));
    return parseInt(answer);
}

// Gets a "yes" or "no" answer from the user through prompt and returns the answer
function getYesNo(promptText) {
    let answer;
    do {
        answer = prompt(promptText);
        // If the user enters invalid input (anything not 'yes' or 'no') re-prompt them
    } while (answer !== "yes" && answer !== "no");
    return answer;
}