import java.util.Random;
import java.util.Scanner;

public class BedTimeSong {

  public static final int MIN = 5;
  public static final int MAX = 30;

  public static void main(String[] args) {
    int count = getNumber();
    String lines = "\"Roll over! Roll over!\"\nSo they all rolled over, and one fell out.\n";
    for (int i = count; i > 0; --i) {
      System.out.printf("There %s %d in the bed, and the little one said,\n", i == 1 ? "was" : "were", i);
      System.out.println(i == 1 ? "\"Good night!!\"" : lines);
    }
  }

  private static int getNumber() {
    Scanner sc = new Scanner(System.in);
    System.out.println("How many little ones are in the bed?");
    int userNum;
    do {
      System.out.println("Enter a number between -1 and " + MAX + ". (-1 for a random number)");
      while (!sc.hasNextInt()) {
        String line = sc.nextLine();
        if (!line.isBlank()) {
          System.out.println("Please enter a number.");
        }
      }
      userNum = sc.nextInt();
    } while (userNum < -1 || userNum > MAX);

    if (userNum == -1) {
      Random rand = new Random();
      return rand.nextInt(MAX - MIN + 1) + MIN;
    }
    return userNum;
  }
}
