import java.util.Scanner;

public class LoopingDrills {

  public static final Scanner CONSOLE = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println();
    countDown();
    System.out.println();
    sumNumbers();
    System.out.println();
    multiplicationTablePrompt();
    System.out.println();
    oddEvenChecker();
    System.out.println();
    primeChecker();
    System.out.println();
    System.out.println("Multiplication Table from 1 to 12");
    for (int i = 1; i <= 12; ++i) {
      System.out.println(multiplicationTable(i));
    }
    System.out.println();
    continuousSum();
  }

  private static void countDown() {
    System.out.println("Count Down");
    for (int i = 1000; i >= 711; --i) {
      if (i % 15 != 0) {
        System.out.println(i);
      }
    }
  }

  private static void sumNumbers() {
    System.out.println("Sum Numbers");
    System.out.println("Enter a number:");
    int a = getNumber();
    System.out.printf("Enter a second number, larger than %d:\n", a);
    int b = getNumber();
    if (b > a) {
      int sum = 0;
      for (int i = a + 1; i < b; ++i) {
        sum += i;
      }
      System.out.printf("The sum of numbers in between %d and %d is %d\n", a, b, sum);
    } else {
      System.out.printf("Invalid: %d is not greater than %d.\n", b, a);
    }
  }

  private static void multiplicationTablePrompt() {
    System.out.println("Multiplication Table Prompt");
    System.out.println("Enter a number to multiply by every number from 1 - 12");
    int a = getNumber();
    System.out.println(multiplicationTable(a));
  }

  private static String multiplicationTable(int num) {
    StringBuilder row = new StringBuilder();
    for (int i = 1; i <= 12; ++i) {
      row.append(num * i).append(" ");
    }
    return row.toString();
  }

  private static void oddEvenChecker() {
    System.out.println("Odd-Even Checker");
    String instruction = "Enter a number to see if it is even or odd. Type 'exit' to quit.";
    String response;
    do {
      System.out.println(instruction);
      response = CONSOLE.nextLine();
      try {
        int a = Integer.parseInt(response);
        System.out.print(a + " is " + (a % 2 == 0 ? "even" : "odd") + ".\n");
      } catch (NumberFormatException ignored) {
      }
    } while (!"exit".equalsIgnoreCase(response));
  }

  private static void primeChecker() {
    System.out.println("Prime Checker\nEnter a number to find out if it is prime.");
    int a = getNumber();
    for (int i = a; i > 1; --i) {
      if (a % i == 0) {
        System.out.println(a + " is not prime.");
        return;
      }
    }
    System.out.println(a + " is prime.");
  }

  private static void continuousSum() {
    System.out.println("Continuous Sum");
    int sum = 0;
    String instruction = "Enter a number or 'done'";
    String userInput;
    do {
      System.out.println(instruction);
      userInput = CONSOLE.nextLine();
      try {
        int n = Integer.parseInt(userInput);
        sum += n;
      } catch (NumberFormatException ignored) {
      }
    } while (!"done".equalsIgnoreCase(userInput));
    System.out.println("The sum is " + sum);
  }

  private static int getNumber() {
    while (!CONSOLE.hasNextInt()) {
      String line = CONSOLE.nextLine();
      if (!line.isBlank()) {
        System.out.println("Please enter a number.");
      }
    }
    int num = CONSOLE.nextInt();
    CONSOLE.nextLine();
    return num;
  }
}
