### Introduction

Let's write a program that runs some standard validations on user input for a bank account.

### Getting Started

Open up the terminal and use the following commands to get started.

```no-highlight
cd ~/challenges
et get bank-balance-js
cd bank-balance-js
open index.html
code .
```

Enter any new code in `main.js`, and run by hard-refreshing your webpage.

### Instructions

#### Part 1 - Allow a User to "Open" an Account

As a banker, I want to be able to open a new account. Add a prompt into your program with the following text: "Welcome to our banking app! Please enter your starting balance." When the user inputs a value, `console.log` the following message:

```no-highlight
Great! Your account has been opened. Your bank account has a balance of $<starting balance>.
```

#### Part 2 - Validate the Starting Balance

Update your initial code to add conditional logic to validate the user input. Ensure that they have entered a positive number. If the user entered a negative number, or something that is _not_ a number, prompt them again for a valid number.

#### Part 3 - Allow a User to Deposit Money

After the initial balance is displayed, display a confirmation box asking the user if they would like to make a deposit. If they say "Cancel", move on without action. If they say "OK", prompt them to input the amount of a deposit they would like to make. Validate that this input is a positive number, and calculate the new account balance. Add a `console.log` with the following message:

```no-highlight
Thank you for your deposit of $<deposit amount>. Your new account balance is $<new balance>.
```

#### Part 4 - Allow a User to Withdraw Money

Once the user has determined if they will deposit funds or not, display a confirmation box asking the user if they would like to take a withdrawal. If they say "Cancel", `console.log` a message with the below text:

```no-highlight
Your final balance is $<final balance>.
```

If they say "OK", prompt them to input the amount of a deposit they would like to make. Validate that this number is a positive number which is less than the current account balance.

- So long as the withdrawal number is less than the account balance, `console.log` the following:

```no-highlight
You have withdrawn $<withdrawal amount>. Your final balance is $<final balance>.
```

- If the withdrawal number is negative or not a number, prompt them again for a valid number.
- If the withdrawal number is a positive number that is larger than the available balance, prompt them again, asking them to make sure to not withdrawal more money than they have!

When you're done, remember to `et submit`!
