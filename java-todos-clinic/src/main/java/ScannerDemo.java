import java.util.Scanner;

/*
  This class is used to demonstrate why we must have a redundant scanner.nextLine()
  after a nextInt() call

  basically nextInt does not consider the carriage return at the end of the line
  so when we go and execute nextLine() it effectively reads in what’s left from when we selected our Main Menu item (everything after 1 or 2, which is the newline character \n)
  so we actually had it right, in that we need to “advance” the scanner from the line of input where we asked for a menu item to the next line of input we want to get from the user


 */
public class ScannerDemo {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    //enter "7 is my favorite number" here
    System.out.println("What is your favorite number?");
    int number = scanner.nextInt();

    System.out.println("What is your favorite color?");
    String color = scanner.nextLine();

    System.out.println("Your favorite number is: " + number);
    System.out.println("Your favorite color is: " + color);

    //INSTEAD DO THIS
    //enter "7 is my favorite number" here
    System.out.println("What is your favorite number?");
    int aNumber = scanner.nextInt();

    System.out.println("What is your favorite color?");

    //This extra call will effectively "ignore" the "is my favorite number\n"
    //on what remains of the line of input
    scanner.nextLine();
    String aColor = scanner.nextLine();

    System.out.println("Your favorite number is: " + number);
    System.out.println("Your favorite color is: " + aColor);

  }
}
