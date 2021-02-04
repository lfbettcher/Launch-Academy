import _ from "lodash";
import Card from "./Card.js";

const suits = ["♦", "♣", "♠", "♥"];
const ranks = [2, 3, 4, 5, 6, 7, 8, 9, 10, "J", "Q", "K", "A"];

class Deck {
  constructor() {
    this.cards = this.buildDeck();
  }

  buildDeck() {
    const cards = [];
    suits.forEach((suit) => {
      ranks.forEach((rank) => {
        cards.push(new Card(rank, suit));
      });
    });
    return _.shuffle(cards);
  }

  deal(count) {
    const dealtCards = [];
    for (let i = 0; i < count; ++i) {
      dealtCards.push(this.cards.pop());
    }
    return dealtCards;
  }

  size() {
    return this.cards.length;
  }
}

export default Deck;
