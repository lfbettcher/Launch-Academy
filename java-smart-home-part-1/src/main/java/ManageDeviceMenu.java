import java.util.List;
import java.util.Scanner;

public class ManageDeviceMenu {

  private List<Device> devices;

  ManageDeviceMenu(List<Device> devices) {
    this.devices = devices;
  }

  public static void promptUntilDone() {
    Scanner console = new Scanner(System.in);
    Device selectedDevice = null;
    do {
      List<Device> allDevices = Device.getAllDevices();
      String devicesString = Device.allDevicesToString();
      System.out.println(devicesString);
      System.out.println("Which device do you want to work with?");
      try {
        selectedDevice = allDevices.get(console.nextInt());
      } catch (Exception e) {
        System.out.println("Please select a valid device number.");
      }
    } while (selectedDevice == null);
    console.nextLine();

    System.out.println("Toggle Power (y/n)");
    if (getYesNo(console)) {
      selectedDevice.togglePower();
      System.out.println(
          selectedDevice + " is now powered " + (selectedDevice.isPoweredOn() ? "on" : "off"));
    }
  }

  public static boolean getYesNo(Scanner console) {
    while (true) {
      String user = console.nextLine();
      if (user.equalsIgnoreCase("y") || user.equalsIgnoreCase("yes")) {
        return true;
      } else if (user.equalsIgnoreCase("n") || user.equalsIgnoreCase("no")) {
        return false;
      } else {
        System.out.println("Please enter y or n.");
      }
    }
  }

  public String toString() {
    StringBuilder devicesString = new StringBuilder();
    for (int i = 0; i < this.devices.size(); i++) {
      devicesString.append(i).append(". ").append(this.devices.get(i).toString()).append("\n");
    }
    return devicesString.toString();
  }
}
