public class CastMember extends TeamMember {

  private String role;

  public CastMember(String firstName, String lastName, String role) {
    super(firstName, lastName);
    this.role = role;
  }

  @Override
  public String getCreditLine() {
    return this.role + " - " + super.getCreditLine();
  }
}
