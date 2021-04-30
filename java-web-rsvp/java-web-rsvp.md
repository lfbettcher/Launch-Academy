We're going to build the evite for hackathons with our Launchvites application.

Our prototype will allow a user to leave their name and email so we know they're attending a upcoming event.

## Getting Started

```no-highlight
createdb java_rsvps_development
et get java-web-rsvp
cd java-web-rsvp
idea .
```

Read through the entire challenge to outline an architectural approach. You will have to use flyway migrations to modify the schema as you progress through the user stories.

## Core User Story

### Express Interest for an Event

```no-highlight
As an interested hackathon participant
I want to express interest for an event
So that it can be prioritized
```

Acceptance Criteria:

- When I navigate to `/rsvps/new`, I am prompted to enter my first name, last name, email address, phone number, and if I want to be contacted by phone or text
- All fields are required (you can use HTML5 validations for now)
- When submitted, this information should be saved as a database record

## Non-Core User Stories

### Create an Event

```no-highlight
As a hackathon admin
I want to add an event
So that I can gather rsvps
```

Acceptance Criteria:

- When I navigate to `/admin/events/new`, I am prompted to supply an event title, street, city, state, and postal code
- When submitted, this information should be saved as a database record

### View Events

```no-highlight
As an interested hackathon participant
I want to see all of the events
So that I can decide what I want to attend
```

Acceptance Criteria:

- When I navigate to `/`, I'm presented with a list of events
- Events are sorted alphabetically by title in ascending order
- For each event, I can see the event title, city, and state
- Each event title is a link that takes me to `/rsvps/new?eventId=<primary key>`

### RSVP for an Event

```no-highlight
As an interested hackathon participant
I want to RSVP for a specific event
So that I can let the organizer know I am attending
```

Acceptance Criteria:

- When I navigate to `/rsvps/new?eventId=<event id>`, I am prompted to enter my first name, last name, email address, phone number, and if I want to be contacted by phone or text
- This information should be saved to a database record, and there should be an appropriate relationship between the event and my new rsvp

### Admin Views RSVP's for an Event

```no-highlight
As a hackathon admin
I want to view the RSVPs for my event
So that I know who to contact
```

Acceptance Criteria:

- When I navigate to `/admin/rsvps?eventId=<eventId>`, I can see a list of RSVP's for that specific event
- RSVP's are ordered by last name, and then first name, in ascending order
- For each attendee, I can see their last name, first name, email, and phone number
