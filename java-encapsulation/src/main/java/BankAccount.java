import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankAccount {

  private static final float MINIMUM_BALANCE = 50;
  private float balance;

  BankAccount(float initialDeposit) {
    this.balance = initialDeposit;
  }

  public boolean withdraw(float withdrawalAmount) {
    if (balance - withdrawalAmount > MINIMUM_BALANCE) {
      balance = balance - withdrawalAmount;
      System.out.println("\nAfter withdrawing " + withdrawalAmount + " your new balance is $" + balance);
      return true;
    } else {
      return false;
    }
  }

  public float getBalance() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    System.out.println("\nBALANCE ACCESSED AT " + dateFormat.format(date));
    return balance;
  }
}