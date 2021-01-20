Let's work with some conditionals to greet the students in our class!

## Introduction

We have a classroom full of students ready to learn, but the teacher sometimes runs late. We need to build a program that will greet the group of students, and tailors the response based on the number of students present. Let's get started!

```no-highlight
et get js-greet-the-class
cd js-greet-the-class
open index.html
code .
```

## Using Conditionals

Let's work through these user stories together. As you satisfy the criteria, log your work to the browser console.

```no-highlight
As a student
I want to be prompted to enter the number of students in the room
So that they can be greeted
```

Acceptance Criteria

- The program prompts the student to enter the number of students and stores it in a variable.

```no-highlight
As a student
I want to be greeted differently based on the number of students
So that my experience feels customized
```

Acceptance Criteria

- The program greets the student with "Welcome, student! Get yourself ready to learn some JavaScript!"
- When 2 - 9 students are present in the room, they should be greeted by the program as a group. The program should greet students with "Welcome, students! Get yourselves ready to learn some JavaScript!"
- When more than 10 students are present in the room, they should be greeted by the program as a group and encouraged to share desk space. The program should greet students with "Welcome, students! Please settle in two to a table, and prepare to learn some JavaScript!"

```no-highlight
As a student
I want to know if I entered a valid value
So that my greeting can be customized
```

Acceptance Criteria

- If I enter a nonnumeric string or I hit cancel on the `prompt`, I should be alerted that I did not enter a valid value, and the program should not provide any other output.

### Using Loops

```no-highlight
As a student
I want to greet each student designated by their number
So that we say hello to everyone in the room
```

Acceptance Criteria

- For every student, after the initial greeting, we should greet each individual student by their number `"Hello student #2."`
- There should be a greeting for each student

```no-highlight
As a student entering an invalid student count
I want to be continuously be prompted for a valid student count
So that a greeting can be displayed
```

- If I enter a nonnumeric string or I hit cancel on the `prompt`, I should be alerted that I did not enter a valid value, and the program should prompt me a again for the number of students.
