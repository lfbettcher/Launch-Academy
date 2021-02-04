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
    it("registers a Person as an attendee", () => {
      conference.register(newPerson);
      expect(conference.attendees).toEqual([newPerson]);
    });

    it("returns true if successfully registered", () => {
      const registerBool = conference.register(newPerson);
      if (conference.attendees === [newPerson]) expect(registerBool).toEqual(true);
    });

    it("do not register a person if that email address has already been registered", () => {
      conference.register(newPerson);
      const personSameEmail = new Person("NameFirst", "NameLast", "email@email.com");
      conference.register(personSameEmail);
      expect(conference.attendees).toEqual([newPerson]);
    });

    it("returns false if person with an already registered email address is not added to the list", () => {
      conference.register(newPerson);
      const personSameEmail = new Person("NameFirst", "NameLast", "email@email.com");
      expect(conference.register(personSameEmail)).toEqual(false);
    });

    it("register does not add a person to the list if the conference has reached the maximum number of registrants", () => {
      conference.maxRegistrants = 1;
      conference.register(newPerson);
      conference.register(person2);
      expect(conference.attendees).toEqual([newPerson]);
    });

    it("returns false if conference is full and person is not added to the list", () => {
      conference.maxRegistrants = 1;
      conference.register(newPerson);
      expect(conference.register(person2)).toEqual(false);
    });
  });

  describe("#addSession", () => {
    it("takes one Session object as its argument and adds the session to the list of the conference's sessions", () => {
      conference.register(newPerson);
      conference.addSession(session1);
      expect(conference.sessions).toEqual([session1]);
    });

    it("return false if attempting to add a Session facilitated by an email address not found on the list of attendees", () => {
      const personNotRegistered = new Person("Not", "Registered", "nr@email.com");
      const sessionReject = new Session("Session No Title", personNotRegistered, 800, 1000);
      conference.addSession(sessionReject);
      expect(conference.addSession(sessionReject)).toEqual(false);
    });

    it("does not add a Session that spans time already scheduled with another session", () => {
      conference.register(newPerson);
      conference.register(person2);
      conference.addSession(session1);
      conference.addSession(session2);
      const sessionOverlap = new Session("Session Overlap", person2, 1030, 1130);
      conference.addSession(sessionOverlap);
      expect(conference.sessions.sort()).toEqual([session1, session2].sort());
    });

    it("return false if attempting to add a Session that spans time already scheduled with another session", () => {
      conference.register(newPerson);
      conference.register(person2);
      conference.addSession(session1);
      conference.addSession(session2);
      const sessionOverlap = new Session("Session Overlap", person2, 1030, 1130);
      expect(conference.addSession(sessionOverlap)).toEqual(false);
    });
  });

  describe("#summary", () => {
    it(
      "prints a summary which includes conference name, number of registrants, registrant names, " +
        "sessions titles, facilitators, start and end times (sorted by start time), and if registration is still open",
      () => {
        conference.maxRegistrants = 3;
        conference.register(newPerson);
        conference.register(person2);
        conference.addSession(session1);
        conference.addSession(session2);
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
      conference.maxRegistrants = 2;
      conference.register(newPerson);
      conference.register(person2);
      conference.addSession(session1);
      conference.addSession(session2);
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
