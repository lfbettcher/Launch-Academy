public class DimmableLight extends Light {
  private int dimLevel;

  public DimmableLight(String name, int dimLevel) {
    super(name);
    this.setDimLevel(dimLevel);
  }

  public int getDimLevel() {
    return this.dimLevel;
  }

  public void setDimLevel(int dimLevel) {
    if (dimLevel == 0) {
      this.setSwitchedOn(false);
      this.dimLevel = dimLevel;
    } else if (dimLevel > 0) {
      this.setSwitchedOn(true);
      if (dimLevel <= 100) {
        this.dimLevel = dimLevel;
      }
    }
  }

  @Override
  public void setSwitchedOn(boolean switchedOn) {
    super.setSwitchedOn(switchedOn);
    this.dimLevel = 100;
  }
}
