### Introduction

At Launch, we're big fans of karaoke, and there's nothing like karaoke to bring out one's inner theatre kid. Let's build a catalogue of musicals to keep track of all of our favorite songs.

### Getting Started

```no-highlight
et get musical-objects-js
cd musical-objects-js
code .
```

### Instructions

#### Part One: Build a Musical

When creating our list of favorite musicals, we'll want to be able to create some new `musical` objects. In a new file `createNewMusical.js`, create a new factory function called `createNewMusical`, which generates a JavaScript object and returns it. This object should have the following attributes, which the function takes in as arguments: `title`, `composer`, `numberOfTonys`. The object should also have an attribute, `songs`, which is initialized as an empty array.

Once your function is all set, export it from the `createNewMusical.js` file using `export default`, and import it into the top of your `main.js` file using an `import` statement. Then, create a few new musicals that can be set to different variables. `console.log` these variables to take a look at what you've built.

Example data:
```javascript
let phantom = createNewMusical('The Phantom of the Opera', 'Andrew Lloyd Webber', 7)
let hamilton = createNewMusical('Hamilton', 'Lin Manuel Miranda', 11)
```

#### Part Two: Build Some Songs

Now that we have our musicals set up, we need some `song` objects!

Create another new file, `createNewSong.js`, with a factory function `createNewSong` inside it. Export that function at the bottom of the file, and import it in `main.js`, before we forget!

Then, set up the function to create and return a song object. This object should have attributes of `title`, `characters` (an array), and `length`, all of which are taken in as arguments to the function.

Back in `main.js`, create some songs and set them to variables. `console.log` these variables as well to confirm all looks good.

Example data:
```javascript
let allIAsk = createNewSong('All I Ask of You', ['Raoul', 'Christine'], '4:11')
let masquerade = createNewSong('Masquerade', ['Andre', 'Firmin', 'Company'], '5:32')
```

#### Part Three: Add Songs to Musicals

Now that we have both our songs and our musicals, let's connect them! We need a way to add our songs into the `songs` array in our musicals.

In your `createNewMusical` function, update the object that is being returned to include a function `addSong`. This function should take in a song as an argument, and add it to the already existing `songs` attribute.

Once you've set up this function, run the function a few times in `main.js`, and `console.log` your musical's songs array to make sure it now includes your new songs!

**Hint!**
```javascript
phantom.addSong(allIAsk)
phantom.addSong(masquerade)
console.log(phantom.songs)
```

Remember to `et submit` once you're done!
