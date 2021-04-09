public class StudentAssistant extends Student {

  public StudentAssistant(String firstName, String lastName, String email) {
    super(firstName, lastName, email);
  }

  @Override
  public String getDirectoryEntry() {
    return this.getEmail() + ", " + "(Student Assistant)";
  }
}
