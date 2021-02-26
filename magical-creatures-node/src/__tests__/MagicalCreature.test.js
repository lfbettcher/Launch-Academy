import MagicalCreature from "../models/MagicalCreature.js";

describe("MagicalCreature class", () => {
  const name = "Creature Name";
  const magicalAbility = "magical ability";
  const age = 150;
  let magicalCreature;

  beforeEach(() => {
    magicalCreature = new MagicalCreature(name, magicalAbility, age);
    creatureNoAge = new MagicalCreature(name, magicalAbility);
  });

  it("defines artist", () => {
    expect(magicalCreature).toBeInstanceOf(MagicalCreature);
  });

  it("has an initialized name", () => {
    expect(magicalCreature.name).toEqual(name);
  });

  it("has an initialized magicalAbility", () => {
    expect(magicalCreature.magicalAbility).toEqual(magicalAbility);
  });

  it("has an optional age", () => {
    expect(magicalCreature.age).toEqual(age);
    expect(creatureNoAge.age).toBe(null);
  });

  describe("#isAncient", () => {
    it("returns true if creature's age is null", () => {
      expect(creatureNoAge.isAncient()).toEqual(true);
    });

    it("returns true if creature's age is greater than 200", () => {
      magicalCreature.age = 201;
      expect(magicalCreature.isAncient()).toEqual(true);
    });

    it("returns false if creature's age is less than 200", () => {
      expect(magicalCreature.isAncient()).toEqual(false);
    });

    it("returns false if creature's age is equal to 200", () => {
      magicalCreature.age = 200;
      expect(magicalCreature.isAncient()).toEqual(false);
    });
  });

  // TODO - test static methods
  describe("#findAll", () => {
    it("", () => {
    });
  });

  describe("#find", () => {
    it("", () => {
    });
  });
});
