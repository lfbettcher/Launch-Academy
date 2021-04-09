import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class StudentAssistantTest {
  @Test
  @DisplayName("StudentAssistant inherits from the Student class")
  public void StudentAssistant_inherits_Student() {
    StudentAssistant sa = new StudentAssistant("Sam", "Helperton", "sam@example.com");
    Assertions.assertEquals("Sam", sa.getFirstName());
    Assertions.assertEquals("Helperton", sa.getLastName());
    Assertions.assertEquals("sam@example.com", sa.getEmail());
  }

  @Test
  @DisplayName("Student assistant's directory entries also includes their designation")
  public void getDirectoryEntry_has_designation() {
    StudentAssistant sa = new StudentAssistant("Sam", "Helperton", "sam@example.com");

    assert sa.getDirectoryEntry().contains("(Student Assistant)");

  }
}
