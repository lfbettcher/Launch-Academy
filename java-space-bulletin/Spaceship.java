import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Spaceship {

  public static final String INPUT_FILE = "crewManifest.txt";
  public static final String CAPTAIN_FILE = "captainMsg.txt";

  /**
   * Print the crew manifest and save the Captain's message. When the program runs a second time,
   * print Captain's message.
   *
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    File captainFile = new File(CAPTAIN_FILE);

    if (captainFile.exists()) {
      System.out.println("The Captain's message:\n" + getFileContent(captainFile));
      return;
    }

    System.out.println(getFileContent(new File(INPUT_FILE)));

    Scanner sc = new Scanner(System.in);
    System.out.println("Captain, please leave a message:");
    StringBuilder captainMsg = new StringBuilder(sc.nextLine());
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      if (line == null || line.isEmpty()) {
        break;
      }
      captainMsg.append("\n").append(line);
    }
    sc.close();

    FileWriter fw = new FileWriter("./captainMsg.txt");
    fw.write(captainMsg.toString());
    fw.close();
  }

  private static String getFileContent(File file) throws IOException {
    return new Scanner(file).useDelimiter("\\z").next();
  }
}
