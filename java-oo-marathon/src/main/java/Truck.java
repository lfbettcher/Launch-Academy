public class Truck extends Automobile {

  private int bedLengthFeet;
  private int bedWidthFeet;
  private int containerHeightFeet;
  private int currentLoadCubicFeet;
  private int volumeCapacity;

  public Truck(String type, String model, String make, int mpg, int fuelCapacity, int bedLengthFeet,
      int bedWidthFeet) {
    super(type, model, make, mpg, fuelCapacity);
    this.setRunning(false);
    this.setInService(false);
    this.bedLengthFeet = bedLengthFeet;
    this.bedWidthFeet = bedWidthFeet;
    this.containerHeightFeet = 3;
    this.currentLoadCubicFeet = 0;
    this.volumeCapacity = bedLengthFeet * bedWidthFeet * containerHeightFeet;
  }

  public Truck(String type, String model, String make, int mpg, int fuelCapacity, int bedLengthFeet,
      int bedWidthFeet, int containerHeightFeet) {
    this(type, model, make, mpg, fuelCapacity, bedLengthFeet, bedWidthFeet);
    this.containerHeightFeet = containerHeightFeet;
    this.volumeCapacity = bedLengthFeet * bedWidthFeet * containerHeightFeet;
  }

  public String companyGreeting() {
    return "Thank you for shipping with gÜber.";
  }

  public boolean addCargo(int addLoad) {
    if (this.currentLoadCubicFeet + addLoad <= this.volumeCapacity) {
      this.currentLoadCubicFeet += addLoad;
      return true;
    }
    return false;
  }

  public int volumeCapacity() {
    return this.volumeCapacity;
  }

  public void emptyCargo() {
    this.currentLoadCubicFeet = 0;
  }

  public int getBedLengthFeet() {
    return this.bedLengthFeet;
  }

  public void setBedLengthFeet(int bedLengthFeet) {
    this.bedLengthFeet = bedLengthFeet;
  }

  public int getBedWidthFeet() {
    return this.bedWidthFeet;
  }

  public void setBedWidthFeet(int bedWidthFeet) {
    this.bedWidthFeet = bedWidthFeet;
  }

  public int getContainerHeightFeet() {
    return this.containerHeightFeet;
  }

  public void setContainerHeightFeet(int containerHeightFeet) {
    this.containerHeightFeet = containerHeightFeet;
  }

  public int getCurrentLoadCubicFeet() {
    return this.currentLoadCubicFeet;
  }

  public void setCurrentLoadCubicFeet(int currentLoadCubicFeet) {
    this.currentLoadCubicFeet = currentLoadCubicFeet;
  }

  public int getVolumeCapacity() {
    return this.volumeCapacity;
  }

  public void setVolumeCapacity(int volumeCapacity) {
    this.volumeCapacity = volumeCapacity;
  }
}
