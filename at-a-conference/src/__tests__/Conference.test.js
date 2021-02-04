import Conference from "../Conference.js";
import Person from "../Person.js";
import Session from "../Session.js";

describe("A conference", () => {
  const conferenceName = "Conference Name";
  const maxRegistrants = 100;
  let conference;
  let newPerson;
  let person2;
  let session1;
  let session2;

  beforeEach(() => {
    conference = new Conference(conferenceName, maxRegistrants);
    newPerson = new Person("FirstName", "LastName", "email@email.com");
    person2 = new Person("First2", "Last2", "p2@email.com");
    session1 = new Session("Session 1 Title", newPerson, 1000, 1100);
    session2 = new Session("Session 2 Title", person2, 1230, 1330);
  });

  it("has a name defined when created", () => {
    expect(conference.name).toEqual(conferenceName);
  });

  it("has a maximum number of registrants defined when it is created", () => {
    expect(conference.maxRegistrants).toEqual(maxRegistrants);
  });

  it("starts with a blank list of attendees", () => {
    expect(conference.attendees).toEqual([]);
  });

  it("starts with a blank list of sessions", () => {
    expect(conference.sessions).toEqual([]);
  });

  describe("#register", () => {
    it("registers a Person as an attendee and returns true", () => {
      expect(conference.register(newPerson)).toEqual(true);
      expect(conference.attendees).toEqual([newPerson]);
    });

    it("do not register a person if that email address has already been registered and returns false", () => {
      conference.attendees = [newPerson];
      const personSameEmail = new Person("NameFirst", "NameLast", newPerson.email);
      expect(conference.register(personSameEmail)).toEqual(false);
      expect(conference.attendees).toEqual([newPerson]);
    });

    it("register does not add a person to the list if the conference has reached the maximum number of registrants and returns false", () => {
      conference.attendees = [newPerson];
      conference.maxRegistrants = conference.attendees.length;
      expect(conference.register(person2)).toEqual(false);
      expect(conference.attendees).toEqual([newPerson]);
    });
  });

  describe("#addSession", () => {
    it("takes one Session object as its argument and adds the session to the list of the conference's sessions, returns true", () => {
      conference.attendees = [session1.facilitator];
      expect(conference.addSession(session1)).toEqual(true);
      expect(conference.sessions).toEqual([session1]);
    });

    it("return false if attempting to add a Session facilitated by an email address not found on the list of attendees", () => {
      conference.attendees = [newPerson];
      const personNotRegistered = new Person("Not", "Registered", `not${newPerson.email}`);
      const sessionReject = new Session("Session No Title", personNotRegistered, 800, 1000);
      expect(conference.addSession(sessionReject)).toEqual(false);
      expect(conference.sessions).toEqual([]);
    });

    it("does not add a Session that spans time already scheduled with another session and returns false", () => {
      conference.attendees = [newPerson, person2];
      conference.sessions = [session1, session2];
      const sessionOverlap = new Session("Session Overlap", person2, 1030, 1130);
      expect(conference.addSession(sessionOverlap)).toEqual(false);
      expect(conference.sessions.sort()).toEqual([session1, session2].sort());
    });
  });

  describe("#summary", () => {
    beforeEach(() => {
      conference.attendees = [newPerson, person2];
      conference.sessions = [session1, session2];
    });

    it(
      "prints a summary which includes conference name, number of registrants, registrant names, " +
        "sessions titles, facilitators, start and end times (sorted by start time), and if registration is still open",
      () => {
        conference.maxRegistrants = conference.attendees.length + 1;
        expect(conference.summary()).toEqual(
          `${conference.name} has ${
            conference.attendees.length
          } registrants: ${newPerson.fullName()}, ${person2.fullName()}.\nThere are ${
            conference.sessions.length
          } sessions: ${session1.title} is from ${session1.startTime} to ${
            session1.endTime
          } and is facilitated by ${session1.facilitator.fullName()}, ${session2.title} is from ${
            session2.startTime
          } to ${
            session2.endTime
          } and is facilitated by ${session2.facilitator.fullName()}.\nRegistration is open.`
        );
      }
    );

    it("prints registration is closed if conference is at capacity", () => {
      conference.maxRegistrants = conference.attendees.length;
      expect(conference.summary()).toEqual(
        `${conference.name} has ${
          conference.attendees.length
        } registrants: ${newPerson.fullName()}, ${person2.fullName()}.\nThere are ${
          conference.sessions.length
        } sessions: ${session1.title} is from ${session1.startTime} to ${
          session1.endTime
        } and is facilitated by ${session1.facilitator.fullName()}, ${session2.title} is from ${
          session2.startTime
        } to ${
          session2.endTime
        } and is facilitated by ${session2.facilitator.fullName()}.\nRegistration is closed.`
      );
    });
  });
});
