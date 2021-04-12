import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    int[] prices = new int[4];
    prices[0] = 34;
    prices[1] = 52;
    prices[2] = 85;

    for(int i = 0; i < prices.length; i++) {
      System.out.println(prices[i]);
    }

    ArrayList<String> exampleArray = new ArrayList<>();
    exampleArray.add("Sisko");
    exampleArray.add("Picard");
    exampleArray.add("Janeway");

//exampleArray = = ["Sisko", "Kirk" "Picard", "Janeway", "Shepard"]
    exampleArray.contains("Kirk");
//returns true

    exampleArray.contains("Warf");
// returns false

    exampleArray.indexOf("Janeway");
//returns 3

    exampleArray.indexOf("Pike");
//returns -1

    exampleArray.size();
//returns 5

    exampleArray.trimToSize();
//changes the max size of exampleArray to 5
  }
}
