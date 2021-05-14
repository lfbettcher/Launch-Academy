Here at Launch, we're a big fan of parties - spontaneous dance parties, board game nights, karaoke parties, puppy parties (which is mostly just something we wish was a very common type of party), the list goes on! We need help keeping track of all of these parties, and all of the wonderful friends we want to invite to each of them.

## Introduction

You'll be building a website that helps us manage our invitation lists, letting us create events, keep track of all of our friends, list which friends we want to invite to each of our parties, and even let our friends leave RSVPs on the page for us!

Party Planner is primarily focused on various aspects of Spring, and the end result will be a full-fledged, database-backed web application!

Note: This challenge is a **longer, more comprehensive project** than some of the ones you may have worked on to date, so you can expect to spend several such coding sessions working through it. Full web applications are usually not built in a day, so take your time with this challenge.

This assignment will pull together many of the key concepts that we have covered to date in a way that starts to simulate the real-world experience of a professional web developer.

### Getting Started

When tackling any sizable body of work, it's important to break down that work into manageable chunks. To guide your progress, we've split out the work into three parts, with each part focusing on a different aspect of Migrations, Associations, and Validations.

As a rough guideline, you can expect each part to require the time and effort of one "normal" challenge.

Be sure to review the lessons on the relevant topics before diving into the work itself, and reference them as you go along.

To get started, run:

```no-highlight
et get java-party-planner
cd java-party-planner
idea .
```

### A Guide to Each Day

To start each day, read through all of the core user stories and instructions for that day to learn about what the app should do (both through the user experience, and behind the scenes).

For each user story, you will want to think about how to design and set up the schema for the app. You will need to plan out what tables you need, and what columns each one should have. Use a tool like [draw.io](https://www.draw.io/) to create/update an ER diagram for your schema.

When your ER diagram is finished for the day, create the migrations required to set up your database. Create a related Entity for each table you add to your database, so that you can use a Repository to access your data. These models should be saved under a `models` namespace.

## Core User Stories

Each section contains user stories and acceptance criteria, with further instructions at the bottom of the section.

### Day One

#### Viewing and Creating Parties

```no-highlight
As a party planner
I want to view a list of all parties I'm planning
So that I can figure out who might be interested in joining me for each
```

Acceptance Criteria:

- If I go to the root path, I should be redirected to `/parties`.
- On the parties index page at `/parties`, I should see the name of each party.
- Each of the parties should be parties retrieved from my database.

```no-highlight
As a party planner
I want to view the details of a party
So that I can learn more about what fun is planned
```

Acceptance Criteria:

- On the parties index page, the name of each party should be a link to the party's show page.
- On the show page, I should see the name, description, and location of the party.

```no-highlight
As a party planner
I want to create a party
So that I can plan all the fun things I want to do
```

Acceptance Criteria:

- There should be a link from the parties index page that takes you to the parties new page. On this page there is a form to create a new party.
- I must supply a name, location, and description of the party.
- If the form submission is successful, I should be brought to the party's show page.

#### Viewing and Creating Friends

```no-highlight
As a party planner
I want to view a list of all of my friends
So I can see how popular I am
```

Acceptance Criteria:

- On the friends index page at `/friends`, I should see the first and last name of each of my friends.
- Friends should be listed alphabetically by first name.

```no-highlight
As a party planner
I want to add new friends to my friends list
So that I can invite them to all of my parties
```

Acceptance Criteria:

- There should be a link from the friends index page that takes you to the friends new page. On this page there is a form to create a new friend.
- I must supply a first and last name for my friend.
- If the form submission is successful, I should be brought back to the friends index page.

### Day Two

#### Party API

```no-highlight
As a party planner
I want a list API endpoint for my parties
So I can see my parties as a JSON
```

Acceptance Criteria

- When I go to `/api/v1/parties` I should see my list of parties in JSON Format

```no-highlight
As a party planner
I want a list API endpoint for my friends
So I can see my friends as a JSON
```

Acceptance Criteria

- When I go to `/api/v1/friends` I should see my list of my friends in JSON Format


#### Inviting Friends to a Party

```no-highlight
As a party planner
I want to see who I have already invited to my party
So that I can plan who else to invite
```

_Note: Initially, you won't have friends invited to your parties until you have created an invite form. You may wish to hardcode an invite for a friend until the form is functional._

Acceptance Criteria:

- On a party's show page, I should see a list of the friends that I have invited to the party.
- I should see each person's first and last name.

```no-highlight
As a party planner
I want to add a friend to my invitee list
So that I can know who is invited to each party
```

Acceptance Criteria:

- On a party's show page, I should see a form with a dropdown field that shows all of my friends.
- When I select a friend and click the "Invite" button, their name should show in my list of invited friends.

#### Validations and Error Handling

```no-highlight
As a party planner
I want to receive error messages on my new party form
So I know why my new party is not submitting
```

- If the new party form submission is unsuccessful, I should remain on the new parties page. The form should still be filled with the values that were provided when the form was previously submitted (this is already happening as a part of a prior story).
- I should see error messages explaining why the form submission was unsuccessful.

```no-highlight
As a party planner
I want to receive error messages on my new friend form
So that I know why I am not able to add my friend
```

Acceptance Criteria:

- If the new friend form submission is unsuccessful, I should remain on the new friend page. The form should be filled with the values that were provided when the form was last submitted (this is already happening as a part of a prior story).
- I should see error messages explaining why the form submission was unsuccessful.

## Optional User Stories

**PLEASE DO NOT START THESE STORIES UNTIL YOU HAVE FINISHED THE CORE USER STORIES**:

```no-highlight
As a party planner
I want to change my party's details
So the party has all of the most up-to-date information
```

Acceptance Criteria:

- For each of my parties, there should be a link from the party's show page that takes you to the party's edit page. On this page there is a form to edit the party, and it is pre-filled with the party's details.
- I must supply a name, location, and description.
- If the form submission is successful, I should be brought to the party's show page, and I should see a message that lets me know that the party has been successfully updated.
- If the form submission is unsuccessful, I should remain on the party's edit page, and I should see error messages explaining why the form submission was unsuccessful. The form should be pre-filled with the values that were provided when the form was submitted.

```no-highlight
As a party creator
I want to cancel my party
So nobody comes to a party that is not taking place
```

Acceptance Criteria:

- There should be a delete button on the party's show page.
- If I click on the delete button, the party should be deleted and I should be redirected to the parties index page. Also, all of the party's invitations should be deleted as well.

```no-highlight
As a party member
I want to delete friends from the invitation list
If I no longer want to invite them
```

Acceptance Criteria:

- On a party's show page, there should be a button to delete each invitee.
- If I click the button, I should see a message that says that the invitee has been deleted, and their name should no longer show up on the show page.

```no-highlight
As a party planner
I want to see RSVPs to a party
So I know which friends will be attending
```

Acceptance Criteria:

- On a party's show page, there should be a list of RSVPs. Each RSVP should include whether they can attend or not ("YES"/"NO"), the party member who left the RSVP, an optional included message, and the date of when the RSVP was created.
- RSVPs should be listed most recent first.

```no-highlight
As a party member
I want to submit my RSVP to a party
So that I can communicate with the host whether I can attend or not
```

Acceptance Criteria:

- On the party's show page, there should be a form to create a RSVP on the party.
- I must select if I can attend or not, and select my name from a dropdown that only shows the people invited to this particular party. I can optionally supply a message for the RSVP.
- If the new RSVP form submission is successful, I should be brought to the party's show page, and I should see a success message that lets me know that the RSVP has been successfully created and added to the list of RSVPs for this party.
- If the new RSVP form submission is unsuccessful, I should remain on the party's show page, and I should see error messages explaining why the form submission was unsuccessful. The form should be pre-filled with the values that were provided when the form was submitted.
