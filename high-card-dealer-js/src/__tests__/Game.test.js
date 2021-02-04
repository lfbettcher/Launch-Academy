import Game from "../Game.js";

// Your tests here
describe("the game", () => {
  let game = new Game(4, 2);

  beforeEach(() => {
    game = new Game(4, 2);
    console.log = jest.fn();
  });

  it("prints 'GAME START!' when game begins", () => {
    game = new Game(4, 2);
    expect(console.log).toHaveBeenCalledWith("GAME START!");
  });

  it("starts with a 52 card deck", () => {
    expect(game.deck.cards.length).toEqual(52);
  });

  it("message confirms deck creation", () => {
    game.printDeckSize();
    expect(console.log).toHaveBeenCalled();
    const message = console.log.mock.calls[0][0];
    expect(message).toEqual(expect.stringContaining("There are 52 cards in the deck."));
  });

  it("two hands of four cards are created", () => {
    //
  });
});
