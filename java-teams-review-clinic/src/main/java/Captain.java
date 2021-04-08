public class Captain extends Player {

  public Captain(String firstName, String lastName, String position) {
    super(firstName, lastName, position);
  }

  @Override
  public String getRosterEntry() {
    return super.getRosterEntry() + " (Captain)";
  }

}