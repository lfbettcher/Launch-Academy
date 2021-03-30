import java.util.Scanner;

public class ShoutDetector {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Say something");

    do {
      String userInput = sc.nextLine();
      System.out.printf("You said: %s\n", userInput);

      if (userInput.split("!\\?", -1).length > 2) {
        System.out.println("I don't respond to inflammatory questions");
      } else if (userInput.equals(userInput.toUpperCase())
          || userInput.chars().filter(ch -> ch == '!').count() > 1) {
        System.out.println("Please stop yelling at me.");
      } else {
        System.out.println("Ok, thanks.");
        break;
      }
      System.out.println("Please try to share again.");
    } while (true);
    sc.close();
  }
}