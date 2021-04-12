import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Spaceship {

  public static final String INPUT_FILE = "crewManifest.txt";

  public static void main(String[] args) throws IOException {
    Map<String, String> crewManifest = new HashMap<>();
    Scanner scFile = new Scanner(new File(INPUT_FILE));
    while (scFile.hasNextLine()) {
      String[] roleName = scFile.nextLine().split(", ");
      crewManifest.put(roleName[1], roleName[0]);
    }
    for (String name : crewManifest.keySet()) {
      System.out.printf("%s (%s)\n", name, crewManifest.get(name));
    }
  }
}
