import java.io.IOException;

public class ReadStdIn {

  public static void main(String[] args) throws IOException {
    byte[] buffer = new byte[10];
    int offset = 0;
    System.in.read(buffer, offset, buffer.length);
    String userInput = new String(buffer);
    for (int i = 0; i < buffer.length; i++) {
      System.out.println(buffer[i]);
    }
    System.out.println("You said: " + userInput);
  }
}