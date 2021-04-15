import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {

  private static final int MIN_CHOICE = 1;
  private static final int MAX_CHOICE = 4;

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    Map<String, String> contacts = new HashMap<>();
    String menuOptions = "MAIN MENU\n1) Add a contact\n2) Look up a contact\n3) Generate distribution list\n4) Exit\n\n> ";
    int choice;
    boolean valid = false;
    do {
      do {
        System.out.print(menuOptions);
        while (!console.hasNextInt()) {
          System.out.println("Please enter a number.");
          console.nextLine();
        }
        choice = console.nextInt();
        console.nextLine();
        if (choice >= MIN_CHOICE && choice <= MAX_CHOICE) {
          valid = true;
        } else {
          System.out.println("Please choose a valid menu option.");
        }
      } while (!valid);

      switch (choice) {
        case 1:
          addContact(contacts);
          break;
        case 2:
          lookupContact(contacts);
          break;
        case 3:
          distributionList(contacts);
        default:
      }
    } while (choice != 4);
  }

  private static void addContact(Map<String, String> contacts) {
    Scanner console = new Scanner(System.in);
    System.out.println("I'm tired of writing menus and prompts so enter it right the first time.");
    System.out.println("Enter the contact's name");
    String name = console.nextLine();
    System.out.println("Enter the contact's email");
    String email = console.nextLine();
    contacts.put(name, email);
  }

  private static void lookupContact(Map<String, String> contacts) {
    Scanner console = new Scanner(System.in);
    System.out.println("Enter a name to look up");
    String name = console.nextLine();
    System.out.println(contacts.getOrDefault(name, "Contact not found"));
  }

  private static void distributionList(Map<String, String> contacts) {
    StringBuilder sb = new StringBuilder();
    for (String name : contacts.keySet()) {
      sb.append("\"").append(name).append("\"").append(" <").append(contacts.get(name))
          .append(">, ");
    }
    System.out.println(sb.substring(0, sb.toString().length() - 2));
  }
}