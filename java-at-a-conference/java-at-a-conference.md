To compare and contrast object-oriented approaches, we're going to rebuild a JavaScript challenge in Java. Remember "At a Conference"?

## Getting Started

```no-highlight
et get java-at-a-conference
cd java-at-a-conference
```

This assignment is test-driven, so be sure to let the existing tests drive your development for the core user stories! Note that in order to go story-by-story, you'll need to run specific tests at a time. We've indicated which tests go with which story below.

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
- You can run `ConferenceTest`s #1-5 to test-drive your work!

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
- To test-drive, first run the `PersonTest`s. Then, run `ConferenceTest` #6

### Can't Register Twice

```no-highlight
As a conference attendee
I don't want to be able to register twice
So that I can't inflate the registration count
```

Acceptance Criteria:

- If I attempt to `register` for a conference as a person with an email address that's already registered, I'm not added to the list and the `register` method returns false
- To test-drive, run `ConferenceTest` #7

### Can't Register if the Conference is Full

```no-highlight
As an overbooked conference administrator
I don't want interested attendees to register
So that I can comply with building regulations
```

Acceptance Criteria:

- If I attempt to `register` for a conference that already has reached the maximum number of registrants, I'm not added to the list and the `register` method returns false
- To test-drive, run `ConferenceTest` #8

### Create a New Conference Session

```no-highlight
As a conference founder
I want to create a new session for a conference
So that people can attend the session
```

Acceptance Criteria:

- Once a conference is created, I can call an `addSession` instance function on it with a new `Session` object as its only argument. This method adds the session to the list of the conference's sessions
- A session must be created with a title, and a `Person` instance who will serve as the facilitator of the session
- To test-drive, first run the `SessionTest`s. Then, run `ConferenceTest` #9

### Print Conference Summary

```no-highlight
As a conference founder
I want to print a conference summary
So that I can determine how epic my conference is going to be
```

- The summary should include the conference name, the number of registrants, and each of their first name and last name
- The summary should include the number of sessions, their titles, and each of their facilitator's names
- The summary should indicate if registration is still open - registration is _open_ if the conference still has available seats (the maximum number of registrants have not been reached). If the conference is at capacity, registration is _closed_.
- To test-drive, run `ConferenceTest`s #10-12

## Non-core User Stories

There are no tests provided for the non-core user stories -- but it would be a great idea to practice writing them yourself!

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
