public class BankAccount {

  private double balance;
  private String accountNumber;

  public BankAccount(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public String getAccountNumber() {
    return this.accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public void deposit(double amount) {
    this.setBalance(this.getBalance() + amount);
  }

  public double withdraw(double amount) {
    if (amount <= this.getBalance()) {
      this.setBalance(this.getBalance() - amount);
      return amount;
    }
    double withdrawAmount = this.getBalance();
    this.setBalance(0);
    return withdrawAmount;
  }

}
