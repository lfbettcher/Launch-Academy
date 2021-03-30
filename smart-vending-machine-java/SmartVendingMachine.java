import java.util.Scanner;

public class SmartVendingMachine {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println(
        "Hello, I'm the first and only Talking Vending Machine.\nWhat would you like today?");
    String item = sc.next();

    System.out.printf("How many %ss would you like?\n", item);
    while (!sc.hasNextInt()) {
      System.out.println("Please enter a number.");
      sc.next();
    }
    int number = sc.nextInt();

    if (number == 0) {
      System.out.println(
          "You selected an item, but not a quantity. Please come back when you can decide on both.");
    } else {
      System.out.printf("Thank you, here are your %d %ss.", number, item);
    }
    sc.close();
  }
}