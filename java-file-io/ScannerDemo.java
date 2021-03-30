import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class ScannerDemo {
  public static String BOOK_READ_PATH = "./booklist.txt";

  public static void main(String[] args) throws IOException {
    File bookList = new File(BOOK_READ_PATH);
//    String content = new Scanner(bookList).useDelimiter("\\n").next();
    String content = new Scanner(bookList).useDelimiter("\\z").next();  // \z designates EOF
    System.out.println(content);
  }
}
