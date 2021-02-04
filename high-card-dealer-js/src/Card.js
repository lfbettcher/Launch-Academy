class Card {
  constructor(rank, suite) {
    this.rank = rank;
    this.suite = suite;
  }

  value() {
    if (Number.parseInt(this.rank)) return Number.parseInt(this.rank);
    return { J: 11, Q: 12, K: 13, A: 14 }[this.rank];
  }
}

export default Card;
