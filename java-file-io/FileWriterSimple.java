import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterSimple {
  public static final String GREETING_PATH = "./greeting.txt";
  public static final String GREETING_TO_WRITE = "Hey there!";

  public static void main(String[] args) throws IOException {
    File fileToCreate = new File(GREETING_PATH);
    FileWriter fileWriter = new FileWriter(fileToCreate);
    fileWriter.write(GREETING_TO_WRITE);
    fileWriter.close();

    if (fileToCreate.exists()) {
      System.out.println("File created.");
    }
  }
}