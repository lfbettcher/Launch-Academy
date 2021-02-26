## My Favorite Movie Dot Com

There are many forms of entertainment in the world. Today we will be focusing on one of the most popular, the Movie.

This project serves as a place to show off your new skills in HTML, CSS, and some light JavaScript. You will create a web page dedicated to the movie of your choosing while ensuring that specific criteria are met. Each section should have at least 10px of space between them.

You can use IMDB or Wikipedia for details about the movie of your choosing to populate the information required.

### Movie Title

* Name of the movie in an appropriate level <h> tag.
* Centered on the page
* Styled to grab attention. For example one or more of the following:
  * Background Color
  * Eye Catching Font
  * Fun Border
  * Background Image

### Nav Bar
* Link to each section on the page. ***HINT: Use their IDs***
  * You will need to update this as you continue to build out your page
* Links should be arranged horizontally
* If using an `<ul>` or `<ol>` there should be no bullets / number per list item

### Synopsis

* Paragraph with a brief pitch of the movie

### Summary

* Long form description of the movie (feel free to pull from Wikiepedia or IMDB) must include at least the following:
  * Release Year
  * Rating
  * Studio
  * Overview of the plot

### List of Characters

* This section should contain the following information for at least 3 characters from the movie.
  * Character Name
  * Actor Name
  * Affiliation (Protagonist, Antagonist, Undeclared)
  * Quote you enjoy regarding the character or said by the character
  * Character Photo
  * Brief bio of character

### Soundtrack
* This section should contain a List of tracks with track Name and Artist
* Link to external site to purchase the soundtrack
  * If no site exists feel free to link to google

### Crew List
* This section should contain a list with the name of the:
  * Writer(s)
  * Director(s)
  * Top Listed Producer
  * Key Grip
  * Best Boy

### Tie it all together
* In the Summary - ensure that any character or crew name which is mentioned links to their list item on the appropriate section of the page

### Footer

 * A footer that is always visible at the bottom of the page with a link which takes you back to the top of the page.

### Styling
In addition to any requirements listed in the sections above:
* Ensure that each section is easily distinguished from the others
* Use fonts that are readable
* Use borders sparingly
* Give the page a consistent look and feel

### Functionality
Use JavaScript to build the following Functionality into buttons or links

* Generate a random quote
  * When this button is pressed a random quote from the movie should be displayed below the button
  * Pressing the button while a quote is displaying should display a new quote
    * The old quote should be removed
  * Quotes should be italicized
  * Quotes should list the character who says the quote. The character name should not be italicized.

* Hide a section
  * Build a function which hides a section of the page
  * Use this function to place buttons or links before each section which will hide the section below
  * When clicked the button updates to appropriately say hide or show the section
    * If hide {section} is clicked the button should now read show {section} and vice versa

## Additional Challenges
These challenges may include things you have yet to learn. Google is your friend :) They will all require some combination of HTML, CSS, and JavaScript. Part of the challenge is determining which tools to use.
(While W3 schools is not a favorite at Launch, its resources for HTML and CSS are very good.)

### Favorite Character
* Create functionality which allows the user to select their favorite character which produces the following effects:
* the entry for that character should be highlighted
  * feel free to get creative with what "highlighted" means
* the random quote should be set to a quote said by that character

### Comments
* Create a Comments section
* Create functionality that prompts the user for a comment about the movie and appends adds it to the comments section
  * We are not storing this information, so feel free to have a couple of comments pre-populated

### Sticky Nav
* Ensure that the Nav Bar is always visible at the top of the page

### Mobile Friendly
* Use Media Queries to make your page responsive to the device being used to browse it

### Gallery
* Add a section for stills from the movie
* Each image should be a thumbnail
* When you hover over the image it should become larger

### HTML Table
* Update your characters, soundtrack, and crew sections to make use of the HTML `<table>` tag

### Left Nav
* Move the nav bar so that it appears on the left hand side of the page taking up the full height of the page

### Add a trailer
* Under the synopsis create a section for the trailer.
* Give the user a way to play the trailer and see the movie in action
