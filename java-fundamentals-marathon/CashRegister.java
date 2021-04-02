import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CashRegister {

  private static final Scanner CONSOLE = new Scanner(System.in);
  private static final String RECEIPT_READ_PATH = "./outstandingReceipts.txt";

  public static void main(String[] args) throws IOException {

    double cashRegisterBalance = getMoneyValue("How much money do you have?");

    File receiptList = new File(RECEIPT_READ_PATH);
    Scanner receiptScanner = new Scanner(receiptList);
    while (receiptScanner.hasNextLine()) {
      cashRegisterBalance += receiptScanner.nextInt();
    }
    System.out.println("cashRegister balance is " + cashRegisterBalance);

    double customerOrder = getMoneyValue("How much is the customers order?");
    System.out.println("customer order is " + customerOrder);
    double customerPayment = getMoneyValue("What is the Customer's payment amount?");
    System.out.println("customerPayment is " + customerPayment);

    while (customerPayment < customerOrder) {
      customerPayment = getMoneyValue("Not enough to cover order. Please provide more money.");
    }
    receiptScanner.close();

    //Calculate change
    double changeDue = customerPayment - customerOrder;
    while (cashRegisterBalance < changeDue || customerPayment < customerOrder) {
      if (cashRegisterBalance < changeDue) {
        customerPayment = getMoneyValue("Please provide cash closer to the amount due.");
      } else {
        customerPayment = getMoneyValue("Not enough to cover order. Please provide more money.");
      }
      changeDue = customerPayment - customerOrder;
    }
    System.out.println("Change due: $" + changeDue);
  }

  private static double getMoneyValue(String prompt) {
      System.out.println(prompt);
      while (!CONSOLE.hasNextDouble()) {
        if (!CONSOLE.nextLine().isBlank()) {
          System.out.println("Please enter a number.");
        }
      }
      double userDouble = CONSOLE.nextDouble();
      CONSOLE.nextLine();
      return userDouble;
  }
}
