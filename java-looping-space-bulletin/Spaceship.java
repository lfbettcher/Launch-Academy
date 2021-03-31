import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Spaceship {

  private static final String CAPTAIN_FILE = "captainMsg.txt";
  private static final String INPUT_FILE = "crewManifest.txt";

  public static void main(String[] args) throws IOException {
    // Reading the Manifest
    Scanner scFile = new Scanner(new File(INPUT_FILE));
    while (scFile.hasNextLine()) {
      String[] crewMember = scFile.nextLine().split(" ", 2);
      if ("Captain".equals(crewMember[0])) {
        System.out.printf("*%s %s\n", crewMember[0], crewMember[1]);
      } else {
        System.out.printf("%s - %s\n", crewMember[0], crewMember[1]);
      }
    }
    scFile.close();

    // Display Captain's Messages
    File captainFile = new File(CAPTAIN_FILE);
    if (captainFile.exists()) {
      System.out.println("\nThe Captain's message:\n" + getFileContent(captainFile).trim());
    }

    // Writing to the Messages File
    Scanner sc = new Scanner(System.in);
    System.out.println("\nCaptain, leave a message:");
    String line = sc.nextLine();
    StringBuilder captainMsg = new StringBuilder();
    while (!"done".equalsIgnoreCase(line)) {
      captainMsg.append(line).append("\n");
      System.out.println("Leave another message or 'done' to quit.");
      line = sc.nextLine();
    }
    sc.close();

    FileWriter fw = new FileWriter("./captainMsg.txt", true);
    if (!captainMsg.toString().isBlank()) {
      fw.write(captainMsg.toString() + "\n");
    }
    fw.close();
  }

  private static String getFileContent(File file) throws IOException {
    return new Scanner(file).useDelimiter("\\z").next();
  }
}
