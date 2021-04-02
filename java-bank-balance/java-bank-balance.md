You have been asked to mange the personal finances of some high profile clients. In this challenge, you'll process a register of transactions to determine their final bank balance, as well as some key indicators of how they're managing their money.

## Getting Started

```no-highlight
et get java-bank-balance
cd java-bank-balance
idea .
```

- Note you should not need to use Arrays for these stories.

## Meeting Expectations Requirements

Consider all numbers in the file to be a transaction, whether a deposit (positive transactions) or a withdrawal (negative transactions). A typical bank account should start with a balance of $0.

Read in Fry's file to satisfy the following requirements:

### Calculate Final Balance

```no-highlight
As a future famous person
I want to know my bank balance
So that I know how much I can spend
```

Acceptance Criteria:

- Calculate and output the bank balance by summing up all of the transactions found in the file

### Calculate Average Withdrawal

```no-highlight
As a future famous person
I want to know the average size of a withdrawal
So that I know how much I am spending
```

Acceptance Criteria:

- All of the withdrawals are the negative values in the file - calculate the average of those values

### Find the Largest Deposit

```no-highlight
As a future famous person
I want to know the largest deposit I made
So that I know when money is coming in
```

Acceptance Criteria:

- All of the deposits are the positive values in the file. Find and output the largest value.

### Help Fry Budget!

```no-highlight
As a future famous person
I want to know if I have enough money to buy a car
So I can drive for the lifestyle I want
```

Acceptance Criteria:

- Prompt Fry to find out how much the car he wants costs
- Check to see if Fry's balance is enough to buy the car
- If Fry does not have enough money in his account notify him that he can't afford that and prompt him to enter a lower value
- Once Fry provides a number he can afford give him the good news


## Exceeds Expectations Requirements

Your VIP accounting business is taking off, so you need to be able to handle more than one client.

### Prompt for File Name

```no-highlight
As a future famous person's accountant
I want to process a transaction file
So that I can share their financial status
```

Acceptance Criteria:

- When the program runs, it asks the user for a filename
- Use that file and process it to output the balance, average withdrawal, and largest deposit, and include the budgeting functionality described above

### Ask to Process Another File

```no-highlight
As a future famous person's accountant
I want to process another transaction file
So that I can be efficient in my reporting
```

Acceptance Criteria:

- After a file has been processed, I should be asked to process another file.
- If I specify that I want to process another file, it prompts me for another file name.
- If I specify that I don't want to process another file, the program terminates

### Validate File

```no-highlight
As a future famous person's accountant
I want to validate a file name
So that I can ensure I'm processing a correct file
```

Acceptance Criteria:

- If I supply a file name that doesn't exist, I'm continuously prompted to supply a valid file name

### Write Results to a File

```no-highlight
As a forgetful famous person's accountant
I want to persist all of my calculations
So that I don't lose tracks of the results
```

Acceptance Criteria:

- After I've processed a file, IE `fry.txt`, my calculations should be saved to `fry-results.txt`

Sample File Contents:

```no-highlight
Current Balance: XX
Average Withdrawal: XX.XX
Largest Deposit: XX
```
