import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BankAccount {

  private static final Scanner CONSOLE = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    System.out.println("Enter a file name.");
    String fileName = CONSOLE.nextLine();
    File readFile = new File(fileName);
    while (!readFile.exists()) {
      System.out.println("That file doesn't exist. Enter a valid file name.");
      fileName = CONSOLE.nextLine();
      readFile = new File(fileName);
    }
    processFile(readFile);

    System.out.println("Enter another filename or 'done'");
    fileName = CONSOLE.nextLine();
    while (!"done".equalsIgnoreCase(fileName)) {
      readFile = new File(fileName);
      while (!readFile.exists()) {
        System.out.println("That file doesn't exist. Enter a valid file name.");
        fileName = CONSOLE.nextLine();
        readFile = new File(fileName);
      }
      processFile(readFile);
      System.out.println("Enter another filename or 'done'");
      fileName = CONSOLE.nextLine();
    }
    System.out.println("Goodbye!");
  }

  public static double getDouble(String prompt) {
    System.out.println(prompt);
    while (!CONSOLE.hasNextDouble()) {
      String line = CONSOLE.nextLine();
      if (!line.isBlank()) {
        System.out.println("Please enter a number.");
      }
    }
    return CONSOLE.nextDouble();
  }

  public static void processFile(File file) throws IOException {
    Scanner scFile = new Scanner(file);
    double balance = 0.0;
    double withdrawals = 0.0;
    double maxDeposit = 0.0;
    int countWithdrawals = 0;
    while (scFile.hasNextInt()) {
      int num = scFile.nextInt();
      balance += num;
      if (num < 0) {
        withdrawals += num;
        countWithdrawals++;
      }
      if (num > maxDeposit) {
        maxDeposit = num;
      }
    }
    scFile.close();

    // Balance
    String balanceString = String.format("Current Balance: $%.2f", balance);
    System.out.println(balanceString);

    // Average withdrawal
    double avgWithdrawal = withdrawals / countWithdrawals;
    String avgWithdrawalString = String.format("Average Withdrawal: $%.2f", -1 * avgWithdrawal);
    System.out.println(avgWithdrawalString);

    // Largest deposit
    String largestDepositString = String.format("Largest Deposit: $%.2f", maxDeposit);
    System.out.println(largestDepositString);

    // Car budget
    double carCost = getDouble("How much does the car cost?");
    while (balance < carCost) {
      carCost = getDouble("You can't afford this car. Enter a lower value");
    }
    System.out.println("Good news! You can buy the car.");
    CONSOLE.nextLine();

    // Write results to file
    String resultsFileName = file.getName().split(".txt")[0] + "-results.txt";
    File resultsFile = new File("./" + resultsFileName);
    FileWriter fw = new FileWriter(resultsFile);
    fw.write(balanceString + "\n" + avgWithdrawalString + "\n" + largestDepositString);
    fw.close();
  }
}
