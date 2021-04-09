import java.util.Scanner;

public class AddDeviceMenu {

  public enum DeviceType {
    a("Device"),
    s("Switchable Light"),
    d("Dimmable Light");
    private String deviceType;

    DeviceType(String deviceType) {
      this.deviceType = deviceType;
    }

    public String toString() {
      return this.name() + ") " + this.deviceType + "\n";
    }
  }

  public String toString() {
    StringBuilder output = new StringBuilder();
    for (DeviceType option : DeviceType.values()) {
      output.append(option.toString());
    }
    return output.toString();
  }

  public static void prompt() {
    Scanner console = new Scanner(System.in);
    AddDeviceMenu addMenu = new AddDeviceMenu();
    DeviceType deviceType = null;
    do {
      System.out.println("What type of device do you want to add?");
      System.out.println(addMenu);
      System.out.print("> ");
      try {
        deviceType = DeviceType.valueOf(console.nextLine());
      } catch (Exception e) {
        System.out.println("Please select a valid device type.");
      }
    } while (deviceType == null);

    System.out.printf("What is the name of the %s?\n", deviceType);
    String deviceName = console.nextLine();
    while (deviceName.isBlank()) {
      System.out.println("Please enter a name for the device you want to add.");
      deviceName = console.nextLine();
    }

    if (deviceType == DeviceType.a) {
      Device.addDevice(new Device(deviceName));
    } else if (deviceType == DeviceType.s) {
      Device.addDevice(new SwitchableLight(deviceName));
    } else if (deviceType == DeviceType.d) {
      int dimLevel = 0;
      // get dimLevel
      Device.addDevice(new DimmableLight(deviceName, dimLevel));
    }
    System.out.println(deviceName + " added!");
  }
}
