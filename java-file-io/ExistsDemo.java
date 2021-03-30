import java.io.File;

public class ExistsDemo {
  public static String BOOK_READ_PATH = "./booklist.txt";

  public static void main(String[] args) {
    File bookList = new File(BOOK_READ_PATH);
    System.out.println(bookList.exists());
    System.out.println(bookList.getAbsolutePath());
  }
}