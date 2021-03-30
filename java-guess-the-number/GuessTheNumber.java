import java.util.Random;

public class GuessTheNumber {
  public static final int NUMBER_CEILING = 10;
  public static int getRandom() {
    Random random = new Random();
    return random.nextInt(NUMBER_CEILING);
  }

  public static void main(String[] args) {
    int randomNum = getRandom();
    // System.out.println(randomNum);

    // your code here
  }

}
