public class TeamMember {

  private String firstName;
  private String lastName;

  public TeamMember(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public TeamMember(String fullName) {
    String[] name = fullName.split(" ");
    this.firstName = name[0];
    this.lastName = name[1];
  }

  public String getCreditLine() {
    return this.firstName + " " + this.lastName;
  }
}
