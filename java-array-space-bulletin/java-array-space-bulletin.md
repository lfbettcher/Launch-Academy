## Getting Started

```no-highlight
et get java-array-space-bulletin
cd java-array-space-bulletin
idea .
```

## Instructions

This assignment will build off of java-space-bulletin from week 10. If you didn't complete that assignment revisit the challenge and start with this in mind.

Copy and modify your bulletin system so that it loads the manifest as an `ArrayList`. You'll need it for the User Story below. Be sure to use a generic so that your list only contains `String` objects.

We're going to make the bulletin customer facing. So, let's include a warm welcome from the ship's captain!

## A Captain's Welcome

```no-highlight
As a passenger
I want to receive a welcome from the captain
So that I have a good initial experience
```

Acceptance Criteria:

- Use `ArrayList` methods to find the ship's captain
- The bulletin should begin with "Welcome aboard! I am <Captain's Name> and we'll be on our way shortly."
- We cannot assume the Captain shows up first on the manifest
- We can assume `Captain` will appear in front of the Captain's name
