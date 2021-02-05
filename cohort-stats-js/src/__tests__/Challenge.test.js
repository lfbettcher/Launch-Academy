import Challenge from "../Challenge.js";

describe("Challenge class", () => {
  const name = "Challenge Name";
  const body = "Challenge Body";
  let challenge;

  beforeEach(() => {
    challenge = new Challenge(name, body);
  });

  it("defines challenge", () => {
    expect(challenge).toBeInstanceOf(Challenge);
  });

  it("has an initialized name", () => {
    expect(challenge.name).toEqual(name);
  });

  it("has an initialized body", () => {
    expect(challenge.body).toEqual(body);
  });

  describe("#submittable", () => {
    it("returns true", () => {
      expect(challenge.submittable()).toEqual(true);
    });
  });
});
