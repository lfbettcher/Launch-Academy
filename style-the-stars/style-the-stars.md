Let's get some practice with styling!

## Getting Started

```no-highlight
et get style-the-stars
cd style-the-stars
open index.html
code .
```

You should see a webpage open in your browser with tons of cool information about stars, but it looks super boring -- what's up with that?

### Looking at what's provided

Before getting started on styling, let's take a look at the HTML code we've been provided.

If we take a look at the provided `index.html` file, we can see that the `<head>` has a `link` element that points at our `style.css` file. When we look at that file, though, we see it's empty! No wonder our page looks so dang boring!

The `body` of our HTML document includes a top header "Style the Stars", and then three sections, one for each star. There's a header with the name of the star, and a paragraph about the star. Then, there's a "Facts" section with an unordered list with a few bullet points of facts on the that particular star.

### Providing some Stellar Styling

Complete the below styling steps, using the [CSS Cheat Sheet][css-cheat-sheet] and [Hex Codes calculator][hex-codes] for reference if need be. When you're done, your page should look like some personal variation of the absolutely beautiful (😆) image below!

![Sample version of styled webpage][style-the-stars-sample]

#### Checklist

- Use the element type to add a unique text color to each of the following elements. Make sure you're using hex codes!
  - The main "Style the Stars" header at the top of the page
  - The paragraphs
  - The list of facts
- Choose another text color and, using the element types, add it to both the name of the star and the "Facts about..." header. Try to only add one ruleset to apply this color to both element types.
- Using a `class` (feel free to edit your `index.html` file), apply styling to center the text of the main "Style the Stars" header, as well as the main Star Name headers.
- Using a `class`, apply a solid border around the "Facts" sections in a color you haven't used yet.
- Change the font for the entire webpage to a sans-serif option, such as Helvetica or Arial.
- Finally, add a background image of the stars! (We used [this one][star-background])

[css-cheat-sheet]: http://overapi.com/css
[hex-codes]: https://www.color-hex.com/
[star-background]: https://img.freepik.com/free-vector/space-with-stars-universe-space-infinity-background_78474-99.jpg?size=626&ext=jpg
[style-the-stars-sample]: https://s3.amazonaws.com/horizon-production/images/challenge/style-the-stars/style-the-stars-sample.png
