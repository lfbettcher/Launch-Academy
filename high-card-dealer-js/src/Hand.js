class Hand {
  constructor(cards) {
    this.cards = cards || [];
  }

  drawCard(card) {
    this.cards.push(card);
  }

  clearHand() {
    this.cards = [];
  }

  value() {
    return this.cards.reduce((acc, card) => acc + card.value(), 0);
  }

  toString() {
    return this.cards.map((card) => card.rank + card.suite).join(", ");
  }
}

export default Hand;
