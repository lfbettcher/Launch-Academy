In this challenge, you'll read in a file that contains a list of math problems. You'll parse the math problem, and output the solution to a file.

## Getting Started

```no-highlight
et get java-file-calculator
cd java-file-calculator
idea .
```

Notice we've supplied `problem.txt` and `problems.txt`. These files have a special way in which they were written. Let's look at `problem.txt`

```no-highlight
+
3
4
=
```

The first line indicates what mathematical operation we want to use. You only need to support addition and multiplication. Numbers will be supplied after the operation. When an `=` sign is encountered, you're done reading in the numbers. You will calculate the result and write it to a file. In this case, the value `7` should be written to `results.txt`.

## Core User Stories

### Make a calculation

```no-highlight
As an unsavvy math student
I want to have a computer do my homework
So that I don't have to
```

Acceptance Criteria:

- The problem file should be read in, and the sum or product should be outputted

_Your program only needs to read in `problem.txt` for this step._

### Write the Calculation to File

```no-highlight
As a forgetful math student
I want to write my results to a file
So that I don't forget the answers
```

Acceptance Criteria:

- The result of my calculation should be written to a `results.txt` file

### Handle Multiple Calculations

```no-highlight
As an unsavvy math student
I want to have a computer do more of my homework
So that I don't have to
```

Acceptance Criteria:

- The problems file should be read in, and the sum or product should be outputted for each problem
- The results should be written to a `results.txt` file, with each solution on a separate line

_Your program only needs to read in `problems.txt` for this step._

## Non-Core User Stories

_For these Stories you will need to alter the problems.txt file. Make sure updating does not cause your existing functionality to break!_

### Support Subtraction

```no-highlight
As an unsavvy math student
I want to have a computer do all of my math homework
So that I don't have to
```

Acceptance Criteria:

- Update your calculator to support the `-` operation

### Support Division

```no-highlight
As an unsavvy math student
I want to have a computer do all of my math homework
So that I don't have to
```

Acceptance Criteria:

- Update your calculator to support the `/` operation

### Support Supplying the Filename at Runtime

```no-highlight
As an unsavvy math student
I want to have a computer do a certain math assignment
So that I don't have to
```

Acceptance Criteria:

- Use the `String args[]` arguments of our `static void main` function to allow the user to specify what file to read for the math problems

### Handle for a Non-Existant Problem File

```no-highlight
As an unsavvy match student
I want to receive an error message when I supply an invalid file
So that I can correct my mistake
```

Acceptance Criteria:

- If the supplied file does not exist, output an error message and the program is not run
