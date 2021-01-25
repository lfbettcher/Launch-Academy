The team often gets bored while out in space, so they pass the time with a few games of [hangman][hangman-article].

## Getting Started
```no-highlight
et get hangman-in-space-js
cd hangman-in-space-js
code .
```

### Instructions

The team has asked you to create a method `printPuzzle` that serves as a fun method to help us play the game of hangman. `printPuzzle` should initially print dashes that correspond to the word that we are trying to guess. This word should be passed in as the first argument to `printPuzzle`. The number of dashes should equal the amount of characters in the word. *For now, assume that no hyphenated words can be given to guess.*

`printPuzzle` must also optionally take an array containing all of the letters that have already been guessed.

### Sample Output

Given `printPuzzle` called with the word "perimeter" as an argument, your output should look like the following:

```no-highlight
printPuzzle('perimeter')
=> _ _ _ _ _ _ _ _ _
```

Given `printPuzzle` called with the word "triangle" as the first argument and an array of letters guessed: `t,s,g`,
your method should output:

```no-highlight
printPuzzle('triangle', ['t','s','g'])
=> t _ _ _ _ g _ _
```

#### Tips

* First, define your method and identify what arguments it may need
* This method will need to return one string with either letters or dashes (underscores).
* We'll have to find a way to iterate over each character in the supplied word. You may wish to split the supplied word into an array of strings where each element of the array is a character in the supplied word.
* Alternatively, you can iterate over each character using `for()` or a `while()` loop
* We'll need a conditional statement (flow control) that determines whether or not one of the guessed letters exist in the supplied word
* Still stuck? Break the problem down. Iterate over each letter in a supplied word, and return that letter first. Then, update your method with flow control second!

[hangman-article]: http://en.wikipedia.org/wiki/Hangman_%28game%29
