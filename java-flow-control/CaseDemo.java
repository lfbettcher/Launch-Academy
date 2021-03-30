import java.io.IOException;

public class CaseDemo {

  public static void main(String[] args) throws IOException {
    byte[] buffer = new byte[1];
    int offset = 0;
    System.out.println("Enter a letter grade.");
    System.in.read(buffer, offset, buffer.length);
    char userInput = (char)buffer[0];

    System.out.println("You said: " + userInput);

    switch(userInput) {
    case 'a':
      System.out.println("The student scored a 90 or better");
      break;
    case 'b':
      System.out.println("The student scored somewhere between an 80 and an 89");
      break;
    case 'c':
      System.out.println("The student scored somewhere between an 70 and an 79");
      break;
    case 'd':
      System.out.println("The student scored somewhere between an 60 and an 69");
      break;
    case 'f':
      System.out.println("The student scored lower than a 60");
      break;
    default:
      System.out.println(userInput + " is not a valid letter grade");
    }
  }
}
