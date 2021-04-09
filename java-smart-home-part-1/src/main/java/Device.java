import java.util.ArrayList;
import java.util.List;

public class Device {

  private String name;
  private boolean poweredOn;
  private static List<Device> allDevices = new ArrayList<>();

  Device(String name) {
    this.name = name;
    this.poweredOn = false;
  }

  public void togglePower() {
    this.poweredOn = !this.poweredOn;
  }

  public boolean isPoweredOn() {
    return this.poweredOn;
  }

  public static List<Device> getAllDevices() {
    return allDevices;
  }

  public static void addDevice(Device device) {
    allDevices.add(device);
  }

  @Override
  public String toString() {
    return this.name;
  }

  public static String allDevicesToString() {
    StringBuilder devicesString = new StringBuilder();
    for (int i = 0; i < allDevices.size(); ++i) {
      devicesString.append(i).append(") ").append(allDevices.get(i)).append("\n");
    }
    return devicesString.toString();
  }

}