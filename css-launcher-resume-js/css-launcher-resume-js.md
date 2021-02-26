In a previous challenge, CSS Launcher Resume, you created and styled your Launcher resume. Now we're going to kick things up a notch and use JavaScript to further modify your resume.

## Getting Started

```no-highlight
et get css-launcher-resume-js
cd css-launcher-resume-js
open index.html
code .
```

### Launcher Resume: Part 2

To get set up:

1. Copy the _body_ of the HTML from your previous challenge into the location indicated by a comment in the provided `index.html`. We'll discuss the rest of this file soon!
2. Copy the CSS from your previous challenge into this challenge's `style.css` file
3. Copy any image files you may have added in the last challenge into this directory as well

Let's take a moment to look at the code provided in `index.html` and `main.js`.

In `index.html`, we've been given a bunch of buttons, each with some text and an `id` (unique, of course!). The final element of the body is the `<script>` element that points to our `main.js` file, so that we can run its code.

In `main.js`, we've been provided with more code than we're used to seeing! For each button in `index.html`, we have

1. saved the button element to a variable
2. used some JavaScript we haven't seen yet to tell our application that we want each of these buttons to _listen_ for a click, at which point they should fire the function we've specified
3. defined the function that the click event should call -- for example, `yell`

Your code will be added to the functions called when the click happens (`yell`, etc.), where indicated by comments in `main.js`. This setup allows us to conditionally run your code only when the buttons are clicked.

Finally, for your convenience, a refresh button has been provided that will refresh the page, restoring it to its initial state. You could achieve the same thing simply by refreshing the page in your browser.

### Instructions

#### Warm-ups

1. When the user clicks the `Yell real loud` button, replace the text in the header containing your name with the same text, but in ALL CAPS. Don't worry about finding a way to convert a string into all-caps -- just set the text to a hardcoded string, e.g., using `let allCapsHeading = "MY NAME IS GEORGE, FEAR ME"`)
2. When the user clicks the `Make me fierce` button, replace your photo with a photo of your favorite superhero. You should be able to do this without creating any new elements!
3. When the user clicks the `Easter egg` button, add an image of an Easter egg to the bottom of the page (as the last child of the `<body>`)

#### Stylin'

Now let's mix up the styling!

1. In your stylesheet, create a rule that will apply a thick red outline to any element with a class of `"selected"`
2. Now, modify the `selectFirstParagraph` function so that it adds a class of `selected` to the first paragraph on the page (it's okay if you only have one paragraph!)
3. Modify function `deselectFirstParagraph` to remove the `selected` class from the first paragraph (you can do this by setting its class to `""`)

#### Now you see me...

Finally, we want to toggle the visibility of the image of your face when the user clicks the `Toggle my face!` button.

1. In your stylesheet, define a rule that sets `display: none` for any element with a class of `hidden` (note: this is [not an accessibility best practice][display-none]!)
2. In the `toggleMyFace` function, check whether the image has a class of `hidden`. If so, remove that class. If not, set its value to "hidden".

You should now be able to hide or show your picture by clicking on the button!

[display-none]: https://developer.mozilla.org/en-US/docs/Web/CSS/display#Accessibility_concerns
