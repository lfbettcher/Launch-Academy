In this challenge, we're going to programmatically build the lyrics for 10 in the Bed.

## Getting Started

```no-highlight
et get java-10-in-the-bed
cd java-10-in-the-bed
idea .
```

You've been given an empty challenge, so use your past challenges to create the file(s) you need to proceed with building this challenge out!

## Core User Stories

### Output the Lyrics

```no-highlight
As a tired parent
I want the 10 in the Bed Lyrics to be generated
So that I don't have to use my brain
```

Acceptance Criteria:

- Start at 10 and count down until there's only one left in the bed.

Sample Output:

```no-highlight
There were 10 in the bed, and the little one said,
"Roll over! Roll over!"
So they all rolled over, and one fell out.

There were 9 in the bed, and the little one said,
"Roll over! Roll over!"
So they all rolled over, and one fell out.

There were 8 in the bed, and the little one said,
"Roll over! Roll over!"
So they all rolled over, and one fell out.

There were 7 in the bed, and the little one said,
"Roll over! Roll over!"
So they all rolled over, and one fell out.

There were 6 in the bed, and the little one said,
"Roll over! Roll over!"
So they all rolled over, and one fell out.

There were 5 in the bed, and the little one said,
"Roll over! Roll over!"
So they all rolled over, and one fell out.

There were 4 in the bed, and the little one said,
"Roll over! Roll over!"
So they all rolled over, and one fell out.

There were 3 in the bed, and the little one said,
"Roll over! Roll over!"
So they all rolled over, and one fell out.

There were 2 in the bed, and the little one said,
"Roll over! Roll over!"
So they all rolled over, and one fell out.

There were 1 in the bed, and the little one said,
"Roll over! Roll over!"
So they all rolled over, and one fell out.
```

### Say "Good Night!!"

```no-highlight
As a tired parent
I want an improved version of 10 in the Bed Lyrics to be generated
So that I don't have to use my brain
```

Acceptance Criteria:

- When only one the little one is left in the bed, they should say, "Good night!!" and no one should fall out.
- When there's only one left in bed, we should use the singular "was" instead of the plural "were"

## Non-core User Stories

### Prompt For the Number of Ones in the Bed

```no-highlight
As a tired parent
I want to limit the number of ones in the bed
So that I can get my own kids to bed
```

Acceptance Criteria:

- When launching the program, I'm prompted for how many little ones start in the bed
- The output should count down from the number I supplied
- If I enter the zero or less than zero, no lyrics are outputted

### Random Number of Ones in the Bed

```no-highlight
As a tired parent
I want to randomly start the lyrics of the song
So that I don't have to use my brain
```

Acceptance Criteria:

- When I'm prompted, if I type `-1`, it picks a random starting number from 5-30

### Validate the Number of Ones in the Bed

```no-highlight
As a tired parent
I want to get corrected if I don't type well
So that I'm reminded I'm not using my brain
```

Acceptance Criteria:

- If I don't provide a value from `-1` to `30`, I receive an error message
- I'm prompted to re-enter a starting number
