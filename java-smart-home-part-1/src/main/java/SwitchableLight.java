public class SwitchableLight extends Light {

  private boolean switchedOn;

  public SwitchableLight(String name) {
    super(name);
    this.switchedOn = false;
  }
}
