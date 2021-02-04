import Hand from "./Hand.js";

class Player {
  constructor(name, hand) {
    this.name = name;
    this.hand = hand || new Hand();
    this.scores = { wins: 0, losses: 0, ties: 0 };
  }

  newHand(hand) {
    this.hand = hand;
  }

  handToString() {
    return `${this.name} was dealt ${this.hand.toString()}`;
  }

  valueToString() {
    return `${this.name}'s hand value: ${this.hand.value()}`;
  }

  scoresToString() {
    const { wins, losses, ties } = this.scores;
    return `${this.name} wins: ${wins}, losses: ${losses}, ties: ${ties}`;
  }
}

export default Player;
