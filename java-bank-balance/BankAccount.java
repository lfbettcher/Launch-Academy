import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BankAccount {

  private static final String FRY_FILE = "./fry.txt";

  public static void main(String[] args) throws IOException {
    File fryFile = new File(FRY_FILE);
    Scanner scFry = new Scanner(fryFile);
    double fryBalance = 0.0;
    double fryWithdrawals = 0.0;
    double fryMaxDeposit = 0.0;
    int fryCountWithdrawals = 0;
    while (scFry.hasNextInt()) {
      int num = scFry.nextInt();
      fryBalance += num;
      if (num < 0) {
        fryWithdrawals += num;
        fryCountWithdrawals++;
      }
      if (num > fryMaxDeposit) {
        fryMaxDeposit = num;
      }
    }
    scFry.close();
    System.out.printf("Fry's balance is $%.2f\n", fryBalance);
    double fryAvgWithdrawal = fryWithdrawals / fryCountWithdrawals;
    System.out.printf("Fry's average withdrawal is $%.2f\n", -1 * fryAvgWithdrawal);
    System.out.printf("Fry's largest deposit is $%.2f\n", fryMaxDeposit);

    Scanner sc = new Scanner(System.in);
    double carCost = getDouble("How much does the car cost?", sc);
    while (fryBalance < carCost) {
      carCost = getDouble("You can't afford this car. Enter a lower value", sc);
    }
    System.out.println("Good news! You can buy the car.");
  }

  public static double getDouble(String prompt, Scanner sc) {
    System.out.println(prompt);
    while (!sc.hasNextDouble()) {
      String line = sc.nextLine();
      if (!line.isBlank()) {
        System.out.println("Please enter a number.");
      }
    }
    return sc.nextDouble();
  }
}
