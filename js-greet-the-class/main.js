let numStudents
let invalid
// continuously prompt for valid student count
do {
    numStudents = parseInt(prompt("Enter the number of students in the room."))
    invalid = Number.isNaN(numStudents) || numStudents <= 0
    if (invalid) {
        alert("You did not enter a valid value")
    }
} while (invalid);

// greet differently based on number of students
if (numStudents === 1) {
    console.log("Welcome, student! Get yourself ready to learn some JavaScript!")
} else if (numStudents >= 2 && numStudents <= 9) {
    console.log("Welcome, students! Get yourselves ready to learn some JavaScript!")
} else if (numStudents > 10) {
    // instructions said "more than 10"
    console.log("Welcome, students! Please settle in two to a table, and prepare to learn some JavaScript!")
}

// greet each student individually by number
for (let i = 1; i <= numStudents; i++) {
    console.log(`Hello student #${i}`)
}