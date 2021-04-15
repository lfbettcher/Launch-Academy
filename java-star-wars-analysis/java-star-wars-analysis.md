## Java Star Wars Analysis

Love it or hate it, if you want to be a nerd, it's a rite of passage to know a little something about Star Wars.

To help you get to know the saga better, we've supplied a modified version of a [SWAPI][swapi-api] result set.

We'll ask you to use Java to perform some analysis on the data set(s) we've supplied to you.

## Getting Started

```no-highlight
et get java-star-wars-analysis
cd java-star-wars-analysis
idea .
```

Hardcoding values will result in a DNME. Use your knowledge of data structures to meet the requirements below.

Wherever you see the word `output` assume that it is asking you to print it to the console. You can do this via a `main` method in your own Java class.

We have pre-supplied you with the Jackson JAR's as dependencies to save you time and for your benefit.

## Meets Expectations Criteria

For this section you will need to use `characters.txt` and `last-names.json`

### Create an ArrayList of All of the Character Names

- Create an array list of names using the information in `characters.txt`.
- Output the list

## Add more people to the list, and get rid of Han

- Add `Biggs`, and `Wedge` to the characters array
- Add `Darth Vader` to the list between Luke and Leia
- Remove `Han` from the list
- Output the updated list

## Convert to a HashMap

- Create a HashMap for characters and their last names
- Use the existing list you've made above to populate the keys
- Set each last name to "Unknown" to start
- Output the hashmap

## Star wars fans are picky! Update to have the correct last names

- You should use the `last-names.json` file to populate your list.
- Using the keys from your hash of characters update the value for each using the value from the `last-names.json`
- Output the updated HashMap

## Parting is such sweet sorrow

- Remove "Obi-Wan Kenobi" from the character HashMap
- Output the updated HashMap

## Pick a letter, any letter

- Using your HashMap output the last names for only the characters whose first names contain an `L`
- Using your HashMap output only the key for "Antilles"




## Exceeds Expectations Criteria

For this section you will need to use the characters.json. Play close attention to the data structure in the file.
You will also need the `hex-colors.json`

### Create an ArrayList for all the Characters with Blue Eyes

- Create an array list of names based on the JSON provided.
- Only include the names of characters that have blue eyes.
- Do not include characters who have blue-gray eyes
- Output the list

### Create a HashMap of Each Character's Birth Year

- Using the character's name as a key, store their correlating birth year in a `HashMap`.
- Output the `HashMap` with a `System.out.println`
- Using the `HashMap`, output Biggs Darklighter's birth year.

### Counts of Each Specified Gender

- Provide a count of characters per gender specified in the list of characters.

Output should resemble:

```no-highlight
male: NNN
n/a: NNN
female: NNN
```

Where NNN is the total number of characters that designate the correlating gender.

### Hex-Colors for Eye Colors

We're going to create a website to catalog these timeless characters. In order to do so, we'll need the hex code colors for each eye color value.

- Use the provided JSON `hex-colors.json` file to add a `eye_color_hex_value` key to each character's `HashMap`.

### Write New JSON to File

- Write the new JSON, including the `eye_color_hex_value` to `modified_characters.json`

[swapi-api]: https://swapi.co/
