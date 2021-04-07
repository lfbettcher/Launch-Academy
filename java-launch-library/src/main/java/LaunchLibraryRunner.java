import java.util.Scanner;

public class LaunchLibraryRunner {
  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    MainMenu menu = new MainMenu();
    menu.promptUntilQuit(console);
  }
}
