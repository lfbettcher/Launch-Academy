import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BankAccount {

  private static final Scanner CONSOLE = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    do {
      File readFile = getValidFile();
      processFile(readFile);
      System.out.println("Do you want to process another file? (y/n)");
    } while (getYesNo());
    System.out.println("Goodbye!");
  }

  public static File getValidFile() {
    System.out.println("Enter a file name.");
    String fileName = CONSOLE.nextLine();
    File readFile = new File(fileName);
    while (!readFile.exists()) {
      System.out.println("That file doesn't exist. Enter a valid file name.");
      fileName = CONSOLE.nextLine();
      readFile = new File(fileName);
    }
    return readFile;
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
    double avgWithdrawal = -1.0 * withdrawals / countWithdrawals;
    String avgWithdrawalString = String.format("Average Withdrawal: $%.2f", avgWithdrawal);
    System.out.println(avgWithdrawalString);

    // Largest deposit
    String largestDepositString = String.format("Largest Deposit: $%.2f", maxDeposit);
    System.out.println(largestDepositString);

    // Car budget
    double carCost = getDouble("How much does the car cost?");
    while (balance < carCost) {
      carCost = getDouble("You can't afford this car. Enter a lower value.");
    }
    System.out.println("Good news! You can buy the car.");

    // Write results to file
    String resultsFileName = file.getName().split(".txt")[0] + "-results.txt";
    File resultsFile = new File("./" + resultsFileName);
    FileWriter fw = new FileWriter(resultsFile);
    fw.write(balanceString + "\n" + avgWithdrawalString + "\n" + largestDepositString);
    fw.close();
  }

  public static double getDouble(String prompt) {
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

  public static boolean getYesNo() {
    while (true) {
      String user = CONSOLE.nextLine();
      if (user.equalsIgnoreCase("y") || user.equalsIgnoreCase("yes")) {
        return true;
      } else if (user.equalsIgnoreCase("n") || user.equalsIgnoreCase("no")) {
        return false;
      } else {
        System.out.println("Please enter y or n.");
      }
    }
  }
}
