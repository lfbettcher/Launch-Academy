> "Words have to find a man's mind before they can touch his heart, and some men's minds are woeful small targets. Music touches their hearts directly no matter how small or stubborn the mind of the man who listens.” - Patrick Rothfuss

## Getting Started

```no-highlight
et get node-js-project
cd node-js-project
```

**You'll be doing your work in Node in the terminal**

Everyone taps their foot to at least one beat. We're going to recreate the wheel and make a new and improved version of everyone's favorite iMusicPlayer. `main.js` will serve as your music iMusicPlayer which relies on several other classes to perform its function... serving up bangers!

Use `main.js` to instantiate objects using your classes and ensure that state and behavior is working.

## Core Challenge 1
Create the following classes using the listed state and behavior.

### Song
Create a `Song` class. This class should have a constructor which accepts:
* title
* artist
* duration
  * for ease of doing math round up to the nearest whole number. i.e 3:03 rounds up to `4`
* album (optional)
* genre (optional)

In addition a `Song` should have the following

* an isPlaying property which starts as `false`
* a #play method which changes the isPlaying property to `true`
* a #stop method which changes the isPlaying to `false`

### Artist
Create an `Artist` class. This class should have a constructor which accepts:
* name
* albums array
* genres(optional)
  * initialized as an empty array if no genres are provided in the constructor

In addition an `Artist` should have the following

* an #addAlbums method which adds an `Album` to the albums array
* an #addGenres method which adds a `Genre` to the genres array

### Album
Create an `Album` class. This class should have a constructor which accepts:
  * name
  * songList array

In addition an `Album` should have the following

* #duration method which sums all the 'Song' durations and returns the total duration
* #trackCount method which counts and returns the number of `Song`s on the `Album`
* #isPlaying method which returns `true` if any of the `Song`s on the album are playing
* #addSong method which adds a `Song` to the `songList`
* #removeSong method which removes a `Song` from the `songList`

## Core Challenge 2

### Player

Create a `Player` class. This class should have a constructor which accepts:
  * an array of `Songs`

In addition a `Player` should have the following

* #play method which iterates over the songs array and performs the following in order
  * calls the song's #play method
  * logs a message which tells the user what song is playing and how long it will play for
  * calls the song's #stop method
* #random method which iterates over the songs array and performs the following in order
  * selects a random song from the songs array
  * calls the song's #play method
  * logs a message which tells the user what song is playing and how long it will play for
  * calls the song's #stop method

**Note** the #random method should not play the same song twice. There are several array methods which could facilitate this behavior.

### Search

In `main.js`

* create an #albumSearch function which takes in an `Artist` and a `string` and returns an `Album` object if the `Artist` has an album which contains the `string`
  * It should return `false` if no album is found and log that no album was found
* create a #songSearch function which takes in an `Album` and a `string` and returns a `Song` object if the `Album` has a song which contains the `string`.
  * It should return `false` if no song is found and log that no song was found

### Refactor

  * refactor the `Player` so that `random` is a property which defaults to `false` but can be set to `true`.
  * refactor the `play` method so that it will play in order if `random` is `false` or play at random if `random` is true
  * Ensure that when playing `random` that no song is played twice

## Additional Challenges

### Let's dry up our code:

* extract the `search` function into module called `search.js` which will allow a user to search for either a song or an album.

* create a `Genre` class which takes in a single argument "name". Refactor the `Album` and `Song` to expect the `genre` array in each to expect a `Genre` object

### Testing 1,2,3

Write Unit Tests for each class. The tests should ensure that each class can be instantiated and that each of its methods works as intended
