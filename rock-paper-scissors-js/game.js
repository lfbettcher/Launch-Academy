console.log("Welcome to Rock, Paper, Scissors!");

const BEAT = { scissors: "rock", paper: "scissors", rock: "paper" };
const CHOICES_MAP = { r: "rock", p: "paper", s: "scissors" }; // for Player
const CHOICES_ARRAY = ["rock", "paper", "scissors"]; // for Computer

let playerWins = 0;
let playerLosses = 0;
let computerWins = 0;
let computerLosses = 0;
let ties = 0;

while (playerWins < 2 && computerWins < 2) {
  printScores("Current");
  gameRound();
}

const finalWinner = playerWins === 2 ? "Player" : "Computer";
console.log(`${finalWinner} wins the game!`);
printScores("Final");

function gameRound() {
  const choicePrompt = "Choose rock (r), paper (p), or scissors (s)";
  let playerChoice = prompt(choicePrompt).toLowerCase();
  while (!playerChoice || !(playerChoice === "r" || playerChoice === "p" || playerChoice === "s")) {
    playerChoice = prompt(`You must choose "r", "p", or "s"\n${choicePrompt}`).toLowerCase();
  }
  playerChoice = CHOICES_MAP[playerChoice];

  const computerChoice = CHOICES_ARRAY[Math.floor(Math.random() * 3)];

  console.log(`Player chose ${playerChoice}.\nComputer chose ${computerChoice}.`);

  if (playerChoice === computerChoice) {
    console.log("This round was a tie!");
    ties++;
  } else {
    const winner = playerChoice === BEAT[computerChoice] ? "Player" : "Computer";
    let winChoice;
    let loseChoice;
    if (winner === "Player") {
      winChoice = playerChoice.charAt(0).toUpperCase() + playerChoice.slice(1);
      loseChoice = computerChoice;
      playerWins++;
      computerLosses++;
    } else {
      winChoice = computerChoice.charAt(0).toUpperCase() + computerChoice.slice(1);
      loseChoice = playerChoice;
      computerWins++;
      playerLosses++;
    }
    console.log(`${winChoice} beats ${loseChoice}, ${winner} wins this round!`);
  }
}

function printScores(gameStage) {
  console.log(`${gameStage} scores:`);
  console.log(`Player ties ${ties} wins ${playerWins} losses ${playerLosses}!`);
  console.log(`Computer ties ${ties} wins ${computerWins} losses ${computerLosses}!`);
}
