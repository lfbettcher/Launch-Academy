import Deck from "./Deck.js";
import Hand from "./Hand.js";
import Player from "./Player.js";

const STARTING_CARDS = 4;
const NUMBER_OF_PLAYERS = 3;

class Game {
  constructor(playerCount, startingCards) {
    this.playerCount = playerCount;
    this.startingCards = startingCards;
    this.deck = new Deck();
    this.players = [];
    this.addPlayers();
    console.log("GAME START!");
  }

  addPlayers() {
    for (let i = 1; i <= this.playerCount; ++i) {
      this.players.push(new Player(`Player ${i}`));
    }
  }

  playRound() {
    this.printDeckSize();
    this.dealCards();
    this.showHands();
    this.showValues();
    this.processScores();
  }

  printDeckSize() {
    console.log(`There are ${this.deck.size()} cards in the deck.\n`);
  }

  dealCards() {
    this.players.forEach((player) => player.newHand(new Hand(this.deck.deal(this.startingCards))));
  }

  showHands() {
    this.players.forEach((player) => console.log(player.handToString()));
  }

  showValues() {
    this.players.forEach((player) => console.log(player.valueToString()));
  }

  processScores() {
    const maxValue = Math.max(...this.players.map((player) => player.hand.value()));

    // increment losses for losers
    this.players
      .filter((player) => player.hand.value() < maxValue)
      .forEach((player) => ++player.scores.losses);

    // 1 winner or winners tie
    const winners = this.players.filter((player) => player.hand.value() === maxValue);
    if (winners.length === 1) {
      ++winners[0].scores.wins;
      console.log(`\n${winners[0].name} wins this round!\n`);
    } else {
      winners.forEach((player) => ++player.scores.ties);
      console.log(`\n${winners.map((player) => player.name).join(" and ")} tied!\n`);
    }

    console.log("SCORES:");
    this.players.forEach((player) => console.log(player.scoresToString()));
  }

  enoughCards() {
    return this.playerCount * this.startingCards <= this.deck.size();
  }
}

/* // play game until no more cards
const game = new Game(NUMBER_OF_PLAYERS, STARTING_CARDS);
let round = 1;
while (game.enoughCards()) {
  console.log(`\nROUND: ${round++}`);
  game.playRound();
}
console.log("\nThere are not enough cards to play again. Game over!");
*/

export default Game;
