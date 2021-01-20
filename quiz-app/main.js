// declare variables for each person's score
// Team 1 - JustJavaScript
const rami = 88
const natoya = 92
const max = 68
const lynn = 100
const team1 = [rami, natoya, max, lynn]

// Team 2 - We Are Ruby
const sasha = 82
const mohammed = 98
const jennifer = 94
const denise = 92
const team2 = [sasha, mohammed, jennifer, denise]

// do calculations with variables
// calculate the average score for each team and for both teams together
let team1Sum = 0
team1.forEach(person => team1Sum += person)
const team1Avg = team1Sum / team1.length

let team2Sum = 0
team2.forEach(person => team2Sum += person)
const team2Avg = team2Sum / team1.length

const bothTeamAvg = (team1Sum + team2Sum) / (team1.length + team2.length)

// round the averages to the nearest integer for display
const team1AvgInt = Math.round(team1Avg)
const team2AvgInt = Math.round(team2Avg)
const bothTeamAvgInt = Math.round(bothTeamAvg)

// calculate the number of explorers passing the quiz (grade >= 70%)
let explorersPassing = 0
const explorers = team1.concat(team2)
explorers.forEach(explorer => {
    if (explorer >= 70) {
        explorersPassing++;
    }
});

// output results in console
console.log(`The average score for Team 1 is ${team1AvgInt}%.
The average score for Team 2 is ${team2AvgInt}%.
The average score for both teams is ${bothTeamAvgInt}%.
The number of explorers passing the quiz (grade >= 70%) is ${explorersPassing}.`)