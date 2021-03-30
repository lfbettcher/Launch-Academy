We have a little more complicated exercise for you. You will have to read in both data from the command line AND a file.

## Getting Started

```no-highlight
et get java-space-bulletin
cd java-space-bulletin
idea .
```

Your work should go in the `Spaceship.java` file.

## Instructions

We're building a bulletin announcement system for our departing spaceship.

## User Stories

### Confirm the Manifest

```no-highlight
As a departing ship captain
I want to confirm my crew manifest
So that I can ensure all folks are accounted for
```

Acceptance Criteria:

- The bulletin outputs the entire contents of `crewManifest.txt` to get all the crew on the ship

### Allow the Captain to Leave a Message

```no-highlight
As a departing ship captain
I want to alert the next outgoing captain of issues
So that they can be informed
```

Acceptance Criteria:

- After outputting the bulletin, the captain is asked to leave a message
- Write the contents of that message to a file
- When the program runs a second time, it should output the contents of the newly written file to alert the next captain of the message.
