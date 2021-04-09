import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentTest {
  private Student student;

  @BeforeEach
  public void setupStudent() {
    this.student = new Student("Sam", "Seaborn", "sam@example.com");
  }

  @Test
  @DisplayName("When initialized a student's first name is present")
  public void init_firstName_present() {
    Assertions.assertEquals("Sam", this.student.getFirstName());
  }

  @Test
  @DisplayName("When initialized a student's last name is present")
  public void init_lastName_present() {
    Assertions.assertEquals("Seaborn", this.student.getLastName());
  }

  @Test
  @DisplayName("When initialized a student's email is present")
  public void init_email_present() {
    Assertions.assertEquals("sam@example.com", this.student.getEmail());
  }

  @Test
  @DisplayName("The student's email is present in the student's directory entry")
  public void getDirectoryEntry_includesEmail() {
    assert this.student.getDirectoryEntry().contains(this.student.getEmail());
  }

  @Test
  @DisplayName("The student's contact info displays correctly in the directory entry")
  public void getDirectoryEntry_formattedCorrectly() {
    Assertions.assertEquals("Seaborn, Sam, sam@example.com", this.student.getDirectoryEntry());
  }
}
