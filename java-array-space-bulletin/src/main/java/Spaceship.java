import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spaceship {

  public static final String INPUT_FILE = "crewManifest.txt";

  public static void main(String[] args) throws IOException {
    // load manifest as ArrayList
    List<String> crewManifest = new ArrayList<>();
    Scanner scFile = new Scanner(new File(INPUT_FILE));
    while (scFile.hasNextLine()) {
      crewManifest.add(scFile.nextLine());
    }
    // find captain
    for (String crew : crewManifest) {
      if (crew.contains("Captain")) {
        System.out.printf("Welcome aboard! I am %s and we'll be on our way shortly.\n", crew);
      }
    }
  }
}
