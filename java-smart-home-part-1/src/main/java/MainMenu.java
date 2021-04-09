import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

  public static final String ADD_DEVICE = "Add a Device";
  public static final String MANAGE_DEVICES = "Manage Devices";
  public static final String VIEW_HOUSE_STATUS = "View House Status";
  public static final String QUIT_TEXT = "Quit";

  public enum MenuOption {

    a(ADD_DEVICE),
    m(MANAGE_DEVICES),
    v(VIEW_HOUSE_STATUS),
    q(QUIT_TEXT);

    private String optionText;

    MenuOption(String optionText) {
      this.optionText = optionText;
    }

    public String toString() {
      return this.name() + ") " + this.optionText + "\n";
    }
  }

  public String toString() {
    StringBuilder output = new StringBuilder("\nMAIN MENU\n");
    for (MenuOption option : MenuOption.values()) {
      output.append(option.toString());
    }
    return output.toString();
  }

  public static List<Device> getDevices() {
    List<Device> devices = new ArrayList<>();
    devices.add(new Device("The Little Prince"));
    devices.add(new Device("Tale of Two Cities"));
    return devices;
  }

  public static void promptUntilQuit() {
    Scanner scanner = new Scanner(System.in);
    MainMenu menu = new MainMenu();
    MenuOption selectedOption = null;
    do {
      System.out.println(menu);
      System.out.print("> ");
      try {
        selectedOption = MenuOption.valueOf(scanner.nextLine());
      } catch (IllegalArgumentException exception) {
        System.out.println("That isn't a valid menu option. Please try again");
      }

      if (selectedOption == MenuOption.a) {
        AddDeviceMenu.prompt();
      } else if (selectedOption == MenuOption.m) {
        ManageDeviceMenu.promptUntilDone();
      }
    } while (selectedOption != MenuOption.q);
    System.out.println("Goodbye!");
  }
}