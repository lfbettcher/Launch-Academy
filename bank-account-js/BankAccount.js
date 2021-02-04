class BankAccount {
  constructor(initialBalance) {
    this.balance = initialBalance;
    this.transactions = [];
  }

  addTransaction(transaction) {
    if (this.balance >= -transaction) {
      this.transactions.push(transaction);
      this.balance += transaction;
      return "transaction completed";
    }
    return "insufficient funds";
  }

  getCurrentBalance() {
    return `$${this.balance.toFixed(2)}`;
  }
}

export default BankAccount;
