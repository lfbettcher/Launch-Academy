import Session from "../Session.js";
import Person from "../Person.js";

describe("A session", () => {
  const title = "Session Title";
  const facilitator = new Person("FirstName", "LastName", "email@email.com");
  const startTime = 1330;
  const endTime = 1440;
  let session;

  beforeEach(() => {
    session = new Session(title, facilitator, startTime, endTime);
  });

  it("session is created with a title", () => {
    expect(session.title).toEqual(title);
  });

  it("session is created with a Person instance who is the facilitator", () => {
    expect(session.facilitator).toEqual(facilitator);
  });

  it("session is created with a start time", () => {
    expect(session.startTime).toEqual(startTime);
  });

  it("session is created with an end time", () => {
    expect(session.endTime).toEqual(endTime);
  });
});
