public class Light extends Device {

  private boolean switchedOn;

  public Light(String name) {
    super(name);
    this.switchedOn = false;
  }

  public boolean isSwitchedOn() {
    return this.switchedOn;
  }

  public void setSwitchedOn(boolean switchedOn) {
    if (this.isPoweredOn()) {
      this.switchedOn = switchedOn;
    }
  }

  @Override
  public void togglePower() {
    super.togglePower();
    if (!this.isPoweredOn()) {
      this.switchedOn = false;
    }
  }
}
