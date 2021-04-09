## Getting Started

```no-highlight
et get java-classroom
cd java-classroom
idea .
```

For this week's final challenge, we're in the Java **Class**room. Get it?!?

Anyhow, we're building a system that organizes the students into a session. Since we're teaching Java, we also have a bit of a lab setup with computers that can be assigned to students. Your job is to get all of these objects talking nicely to each other.

## Meets Expectations Requirements

Get the following test files to pass. You are not permitted to modify the tests. Work in the order provided below:

1. `StudentTest`
2. `StudentAssistantTest`
3. `ComputerTest`
4. `SessionTest`

_The last two tests that are commented out in `SessionTest` with the `exceeds` prefix are not required to meet expectations. Please only worry about making these tests pass if you're seeking an exceeds expectation grade._

## Exceeds Expectations

### Exceeds Provided Tests

Comment in the last two tests that are commented out in `SessionTest` with the `exceeds` prefix and make them pass.

### Macs and PC's

- Modify your `Session` class to create 5 PC Computers and 5 Mac Computers
- Update your `Session` tests to assert that your list of computers contains 5 PC's and 5 Macs

### Teacher's Computer Inventory Display

- Modify your `Session` class to have a `computerInventoryReport()`
- Write tests to ensure that this method includes the following for each computer:
  - The Computer's Index in the list
  - Whether the computer is assigned or not
  - If the computer is assigned, it should list the student's full name
  - What operating system it is running

### Upgrade

- All computer subclasses should implement an `upgradeMemory` method
- It should take an argument, the amount of memory we want to add (in MB)
- For PC's it should only add the memory to the computer's available memory
- For Mac's, it should throw an exception that the memory cannot be upgraded
- Do not worry about writing tests for this method
