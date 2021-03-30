import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class WriteToFileDemo {
  public static final String GREETING_PATH = "./greeting.txt";
  public static final String GREETING_TO_WRITE = "Hey there!";

  public static void main(String[] args) throws IOException {
    File fileToCreate = new File(GREETING_PATH);
//    OutputStream oStream = new FileOutputStream(greetingFile);
    OutputStream oStream = new FileOutputStream(fileToCreate);
    BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(oStream));
    bw.write(GREETING_TO_WRITE);
    if (fileToCreate.exists()) {
      System.out.println("File created.");
    }
    bw.close();
  }
}
