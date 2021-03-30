import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

  public static final int NUMBER_CEILING = 10;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    do {
      playGame(sc);
    } while (getYesNo(sc));
    System.out.println("Thanks for playing!");
    sc.close();
  }

  public static int getRandom() {
    Random random = new Random();
    return random.nextInt(NUMBER_CEILING);
  }

  private static int getValidNumber(Scanner sc) {
    int userNum;
    do {
      System.out.println("Select a number from 0-9");
      while (!sc.hasNextInt()) {
        System.out.println("Please enter a number.");
        sc.next();
      }
      userNum = sc.nextInt();
    } while (userNum < 0 || userNum > 9);
    return userNum;
  }

  private static void playGame(Scanner sc) {
    int randomNum = getRandom();
    int guesses = 0;
    int userNum;
    do {
      userNum = getValidNumber(sc);
      ++guesses;
      if (userNum > randomNum) {
        System.out.println("Your number is higher.");
      } else if (userNum < randomNum) {
        System.out.println("Your number is lower.");
      }
    } while (userNum != randomNum);
    System.out.printf("You won! It took you %d guess%s.\n", guesses, guesses > 1 ? "es" : "");
  }

  private static boolean getYesNo(Scanner sc) {
    System.out.println("Do you want to play again? (Y/N)");
    while (true) {
      String user = sc.next();
      if (user.equalsIgnoreCase("Y") || user.equalsIgnoreCase("YES")) {
        return true;
      } else if (user.equalsIgnoreCase("N") || user.equalsIgnoreCase("NO")) {
        return false;
      } else {
        System.out.println("Please enter Y or N.");
      }
    }
  }
}
