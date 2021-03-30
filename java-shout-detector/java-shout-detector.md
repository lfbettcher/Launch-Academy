## Getting Started

```no-highlight
et get java-shout-detector
cd java-shout-detector
idea .
```

We have provided you with a `ShoutDetector.java` - you can place your code there to complete the challenge.

## Core User Stories

### Gather input

```no-highlight
As a listener
I want to hear someone
So that I can engage them in conversation
```

Acceptance Criteria:

- Upon running the program, the user is asked to say something
- The computer will acknowledge the response by outputting the example below.

```no-highlight
You said: I love Java
Ok, thanks.
```

### Rebuff all caps

```no-highlight
As a listener
I don't want anyone shouting at me in all caps
So that I can protect my sensitive ears
```

Acceptance Criteria:

- When prompted, if the user enters something in all capital letters, the program requests the user stop shouting
- It otherwise accepts the input

```no-highlight
You said: I HATE C
Please stop yelling at me.
```

### Rebuff multiple exclamations

```no-highlight
As a listener
I don't want anyone shouting at me with multiple exclamations
So that I can protect my sensitive ears
```

Acceptance Criteria:

- When prompted, if the user enters something with more than one exclamation, the program requests the user stop shouting
- It otherwise accepts the input

```no-highlight
You said: C++ is the devil!!!!!!!
Please stop yelling at me.
```

### Rebuff multiple `!?`

```no-highlight
As a listener
I don't want anyone shouting at me with multiple exclamations
So that I can protect my sensitive ears
```

Acceptance Criteria:

- When prompted, if the user enters something with a string of multiple sequences of `!?`, the program requests the user stop shouting, but with slightly different output
- It otherwise accepts the input

```no-highlight
You said: You program in cobol!?!?
I don't respond to inflammatory questions
```

## Non-core User Stories

### Ask the user to try again

```no-highlight
As a sharer
I want to be given the opportunity to try again
So that I can calm down and communicate
```

Acceptance Criteria:

- If the program detects a shout or an inflammatory question, ask the user to try again until they enter something that is not detected as a shout. You may need to use your knowledge of other languages and your kick-butt Googling skillz to get creative here.

```no-highlight
You said: You program in cobol!?!?
I don't respond to inflammatory questions
Please try to share again.
> C++ is the devil!!!!!!!
You said: C++ is the devil!!!!!!!
Please stop yelling at me.
Please try to share again.
> Java is cool
You said: Java is cool
Ok, thanks.
```
