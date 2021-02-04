class Conference {
  constructor(name, maxRegistrants) {
    this.name = name;
    this.maxRegistrants = maxRegistrants;
    this.attendees = [];
    this.sessions = [];
  }

  register(newPerson) {
    if (this.isFull() || this.attendees.some((attendee) => attendee.email === newPerson.email))
      return false;
    this.attendees.push(newPerson);
    return true;
  }

  addSession(session) {
    if (
      !this.isSessionOverlap(session) &&
      this.attendees.some((attendee) => attendee.email === session.facilitator.email)
    )
      this.sessions.push(session);
    else return false;
  }

  summary() {
    this.sortSessions();
    const conferenceString = `${this.name} has ${this.attendees.length} registrants:`;
    const attendeeString = this.attendees
      .map((attendee) => `${attendee.firstName} ${attendee.lastName}`)
      .join(", ");
    const sessionString = this.sessions
      .map(
        (session) =>
          `${session.title} is from ${session.startTime} to ${session.endTime} and is facilitated by ${session.facilitator.firstName} ${session.facilitator.lastName}`
      )
      .join(", ");
    return `${conferenceString} ${attendeeString}.\nThere are ${
      this.sessions.length
    } sessions: ${sessionString}.\nRegistration is ${this.isFull() ? "closed" : "open"}.`;
  }

  isFull() {
    return this.attendees.length === this.maxRegistrants;
  }

  sortSessions() {
    this.sessions.sort((a, b) => a.startTime - b.startTime);
  }

  isSessionOverlap(newSession) {
    return this.sessions.some(
      (session) =>
        (newSession.startTime > session.startTime && newSession.startTime < session.endTime) ||
        (newSession.endTime > session.startTime && newSession.endTime < session.endTime)
    );
  }
}

export default Conference;
