console.log("Welcome to Rock, Paper, Scissors!");

const BEAT = { Scissors: "Rock", Paper: "Scissors", Rock: "Paper" };
const CHOICES_MAP = { r: "Rock", p: "Paper", s: "Scissors" }; // for Player
const CHOICES_ARRAY = ["Rock", "Paper", "Scissors"]; // for Computer

const choicePrompt = "Choose rock (r), paper (p), or scissors (s)";
let playerChoice = prompt(choicePrompt).toLowerCase();
while (!(playerChoice === "r" || playerChoice === "p" || playerChoice === "s")) {
  playerChoice = prompt(`You must choose "r", "p", or "s"\n${choicePrompt}`).toLowerCase();
}
playerChoice = CHOICES_MAP[playerChoice];

const computerChoice = CHOICES_ARRAY[Math.floor(Math.random() * 3)];

console.log(`Player chose ${playerChoice}.\nComputer chose ${computerChoice}.`);

if (playerChoice === computerChoice) {
  console.log("Tie!");
} else {
  const winner = playerChoice === BEAT[computerChoice] ? "Player" : "Computer";
  const winChoice = winner === "Player" ? playerChoice : computerChoice;
  const loseChoice = winner !== "Player" ? playerChoice : computerChoice;
  console.log(`${winChoice} beats ${loseChoice}, ${winner} wins!`);
}
