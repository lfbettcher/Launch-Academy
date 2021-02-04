You've been tasked with building the system that supports the latest tech conference! In this challenge, you'll create a system of classes that will need to work together. Writing automated tests will help to guide your implementation.

## Getting Started

```no-highlight
et get at-a-conference
cd at-a-conference
yarn install
```

## Core User Stories

### Create a Conference

```no-highlight
As a conference founder
I want to create a new conference
So that people can attend it
```

Acceptance Criteria:

- A conference has a name defined when it is created
- A conference has a maximum number of registrants defined when it is created
- A conference starts with a blank list of attendees
- A conference starts with a blank list of sessions

### Register for the Conference

```no-highlight
As a conference founder
I want people to register for my conference
So that we can all get together and talk about software
```

Acceptance Criteria:

- Once a conference is created, I can call a `register` instance function on it with a new `Person` object as its only argument.
- A `Person` instance must have a first name, last name, and email address
- The `register` method returns true if I've successfully registered

### Can't Register Twice

```no-highlight
As a conference attendee
I don't want to register twice
So that I don't inflate the registration count
```

Acceptance Criteria:

- If I attempt to `register` for a conference as a person with an email address that's already registered, I'm not added to the list and the `register` method returns false

### Can't Register if the Conference is Full

```no-highlight
As an overbooked conference administrator
I don't want interested attendees to register
So that I can comply with building regulations
```

Acceptance Criteria:

- If I attempt to `register` for a conference that already has reached the maximum number of registrants, I'm not added to the list and the `register` method returns false

### Create a New Conference Session

```no-highlight
As a conference founder
I want to create a new session for a conference
So that people can attend the session
```

Acceptance Criteria:

- Once a conference is created, I can call an `addSession` instance method on it with a new `Session` object as its only argument. This method adds the session to the list of the conference's sessions
- A session must be created with a title, and a `Person` instance who will serve as the facilitator of the session

### Print Conference Summary

```no-highlight
As a conference founder
I want to print a conference summary
So that I can determine how epic my conference is going to be
```

- The summary should include the conference name, the number of registrants, and each of their first name and last name
- The summary should include the number of sessions, their titles, and each of their facilitator's names
- The summary should indicate if registration is still open - registration is _open_ if the conference still has available seats (the maximum number of registrants have not been reached). If the conference is at capacity, registration is _closed_.

## Noncore User Stories

### Require Sessions to Be Facilitated by a Registrant

```no-highlight
As a conference founder
I want to ensure our session facilitators are registrants
So that we have an accurate headcount
```

Acceptance Criteria:

- If I attempt to `addSession` and the `Session` object is being facilitated by an email address not found on the list of attendees, the function should return `false` and the session should not be added to the list

### Add Start Time and End Time to Session

```no-highlight
As a conference founder
I want to manage the session schedule
So that I can coordinate what sessions are happening when
```

Acceptance Criteria:

- When I create a new session, I must supply a start time and an end time
- The start and end times should be added to the conference summary

### Sort Session Summary by Start Time

```no-highlight
As a conference registrant
I want to see a chronological list of sessions
So that I can follow the schedule
```

Acceptance Criteria:

- The conference summary produced in a previous user story should list out the sessions starting with the earliest start time and ending with the latest start time.

### Can't Add Session that Overlaps with Another

```no-highlight
As a conference founder
I want to ensure sessions don't overlap
So that attendees can attend all of the sessions
```

Acceptance Criteria:

- When I attempt to register a session that spans time that is already scheduled with another session, the `addSession` instance function should return `false` and the session should not be added to the list.
