public abstract class Automobile {

  private String type;
  private String model;
  private String make;
  private int mpg;
  private int fuelCapacity;
  private boolean running;
  private boolean inService;

  public Automobile(String type, String model, String make, int mpg, int fuelCapacity) {
    this.type = type;
    this.model = model;
    this.make = make;
    this.mpg = mpg;
    this.fuelCapacity = fuelCapacity;
    this.running = true;
    this.inService = true;
  }

  public abstract String companyGreeting();

  public void repair() {
    this.inService = true;
  }

  public void toggleEngine() {
    this.setRunning(!this.isRunning());
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getModel() {
    return this.model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getMake() {
    return this.make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public int getMpg() {
    return this.mpg;
  }

  public void setMpg(int mpg) {
    this.mpg = mpg;
  }

  public int getFuelCapacity() {
    return this.fuelCapacity;
  }

  public void setFuelCapacity(int fuelCapacity) {
    this.fuelCapacity = fuelCapacity;
  }

  public boolean isRunning() {
    return this.running;
  }

  public void setRunning(boolean running) {
    this.running = running;
  }

  public void setInService(boolean inService) {
    this.inService = inService;
  }

  public boolean isInService() {
    return this.inService;
  }

}
