Keeping track of your expenses when running an entire space fleet can get overwhelming. In this exercise, we'll model a bank account in JavaScript using the object-oriented programming design pattern.

## Getting Started

```no-highlight
et get bank-account-js
cd bank-account-js
touch BankAccount.js
code .
```

### Instructions

In your new file, create a class named `BankAccount`. It should take the **initial balance** as an argument to its constructor, and store this as an instance variable. As an additional instance variable, it should store any **transactions** made against the account in an array. There should be a way to **add a transaction** as well as **calculate the current balance** on the account by using the two instance variables.

Based on this description, what instance methods do you need to add to your class?

Be sure to import your class into your `main.js` file. Then, you should be able to run the below code found in `main.js` to test the functionality of your class.

```javascript
import BankAccount from "./BankAccount.js"

const initialBalance = 1000
const transactions = [-45, -99.95, 60.00, -34.43]
const bankAccount = new BankAccount(initialBalance)
transactions.forEach(transaction => {
  bankAccount.addTransaction(transaction)
})
console.log(bankAccount.getCurrentBalance())
// your output should be a log of the current balance of the account with all transactions accounted for
```
