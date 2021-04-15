import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class RobotLine {

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scFile = new Scanner(new File("initial-line.txt"));
    LinkedList<String> robotLine = new LinkedList<>();
    while (scFile.hasNextLine()) {
      robotLine.add(scFile.nextLine());
    }
    robotLine.add("Jim");
    robotLine.add("Joan");
    robotLine.add("Lisa");
    robotLine.add("Marc");
    robotLine.add(robotLine.indexOf("Jim"), "Fran");
    robotLine.remove("Jim");
    robotLine.remove("Fran");
    for (int i = 0; i < 5; i++) {
      System.out.println(robotLine.poll());
    }
    for (int i = 0; i < 3; i++) {
      robotLine.pollLast();
    }
    System.out.println(robotLine.size());
    robotLine.remove("Alex");
    System.out.println(robotLine.peek());
  }
}
