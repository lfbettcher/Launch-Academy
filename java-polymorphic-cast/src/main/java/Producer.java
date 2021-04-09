public class Producer extends TeamMember {

  public Producer(String firstName, String lastName) {
    super(firstName, lastName);
  }

  public Producer(String fullName) {
    super(fullName);
  }

  @Override
  public String getCreditLine() {
    return "Producer - " + super.getCreditLine();
  }
}
