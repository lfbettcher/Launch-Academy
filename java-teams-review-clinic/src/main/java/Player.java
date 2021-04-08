public class Player {

  private String firstName;
  private String lastName;
  private String position;

  public Player(String firstName, String lastName, String position) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.position = position;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getPosition() {
    return this.position;
  }

  public String getRosterEntry() {
    return this.lastName + ", " + this.firstName + ", " + this.position;
  }
}