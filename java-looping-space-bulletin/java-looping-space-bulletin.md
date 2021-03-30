Let's put a different spin on our space bulletin system.

## Getting Started

```no-highlight
et get java-looping-space-bulletin
cd java-looping-space-bulletin
idea .
```

Your work should go in the `Spaceship.java` file.

## Instructions

### Reading the Manifest

We still want to output the crew manifest, but this time, we want to output an asterisk `*` before the Captain. We cannot assume the Captain appears first in the manifest.

For passengers, output a `-` before their name.

Use a loop to read in each line until you've read through the entire file.

### Writing to the Messages File

The Captain may want to leave multiple messages. Ask the Captain if they want to leave a message continuously until they enter `done`. Like the original program, ensure you write those messages to a file for the next Captain.
