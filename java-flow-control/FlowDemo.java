import java.io.IOException;

public class FlowDemo {

  public static void main(String[] args) throws IOException {
    byte[] buffer = new byte[1];
    int offset = 0;
    System.out.println("Enter a letter grade.");
    System.in.read(buffer, offset, buffer.length);
    char userInput = (char) buffer[0];

    System.out.println("You said: " + userInput);

    if (userInput == 'a') {
      System.out.println("The student scored a 90 or better");
    } else if (userInput == 'b') {
      System.out.println("The student scored somewhere between an 80 and an 89");
    } else if (userInput == 'c') {
      System.out.println("The student scored somewhere between an 70 and an 79");
    } else if (userInput == 'd') {
      System.out.println("The student scored somewhere between an 60 and an 69");
    } else if (userInput == 'f') {
      System.out.println("The student scored lower than a 60");
    } else {
      System.out.println(userInput + " is not a valid letter grade");
    }
  }
}
