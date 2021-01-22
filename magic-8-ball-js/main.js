const responses = [
  "It is certain",
  "It is decidedly so",
  "Without a doubt",
  "Yes, definitely",
  "You may rely on it",
  "As I see it, yes",
  "Most likely",
  "Outlook good",
  "Yes",
  "Signs point to yes",
  "Reply hazy try again",
  "Ask again later",
  "Better not tell you now",
  "Can't predict now",
  "Concentrate and ask again",
  "Don't count on it",
  "My reply is no",
  "My sources say no",
  "Outlook not so good",
  "Very doubtful",
];
// your code, here
// Confirm the correct number of responses have been stored by printing the number to the console (`20`)
console.log(`Number of responses: ${responses.length}`);

// Ask the user what their question is, and store it as a variable called `question`
const question = prompt("What is your question?");

// Output a random response to the user's question
console.log(responses[Math.floor(Math.random() * responses.length)]);

// Output the number of responses that include the word "yes"
// Output the number of responses that include the words "no" or "not"
let yesCount = 0;
let noCount = 0;
responses.forEach((response) => {
  if (response.includes("yes")) yesCount++;
  if (response.includes("no") || response.includes("not")) noCount++;
});
console.log(`Number of "yes" responses: ${yesCount}`);
console.log(`Number of "no" or "not" responses: ${noCount}`);

// Output the alphabetized list of responses
// Generally not good practice to modify the original list, but later steps seem to suggest it
console.log(responses.sort());

// Output the first and last responses from the alphabetized list
console.log(`First response: ${responses[0]}`);
console.log(`Last response: ${responses[responses.length - 1]}`);

// Delete two of the negative responses
let deleted = 0;
for (i = 0; i < responses.length; i++) {
  if (deleted === 2) break;
  const responseWords = responses[i].split(" ");
  if (responseWords.includes("no") || responseWords.includes("not")) {
    responses.splice(i--, 1);
    deleted++;
  }
}

// Reassign 'Ask again later' to the beginning of the array
const reassign = "Ask again later";
if (responses.indexOf(reassign) > 0) {
  responses.splice(responses.indexOf(reassign), 1);
  responses.unshift(reassign);
}

// Add 1 response of your choosing to the current list
responses.push("1 response of your choosing");

// The next instruction does not match the 2nd to last bullet point online
// Concat an **array** of 3 additional responses to the current list
/* concat() is non-mutative and responses is a const, so results from 
responses.concat() cannot be reassigned to responses */

// Add 3 more responses all at once to the current list!
responses.push(...["3 more responses", "all at once", "to the current list"]);

// Confirm the correct number of responses have been removed and added by printing the number of responses to the console (`22`)
console.log(`Number of responses: ${responses.length}`);
