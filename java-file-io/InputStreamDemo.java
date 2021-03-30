import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputStreamDemo {
  public static String BOOK_READ_PATH = "./booklist.txt";

  public static void main(String[] args) throws IOException {
    File bookList = new File(BOOK_READ_PATH);
    InputStream iStream = new FileInputStream(bookList);
    // InputStream reader
//    int c = iStream.read();
//    System.out.println((char)c);
    // BufferedReader
    BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
    String line = br.readLine();
    System.out.println(line);
  }
}