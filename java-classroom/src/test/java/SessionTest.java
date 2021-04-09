import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {
  private Session session;

  @BeforeEach
  public void setupSession() {
    this.session = new Session("Java 101", "Java");
  }

  @Test
  @DisplayName("When initialized, a session's name is present")
  public void init_name_present() {
    assertEquals("Java 101", this.session.getName());
  }

  @Test
  @DisplayName("When initialized, a session's subject is present")
  public void init_subject_present() {
    assertEquals("Java", this.session.getSubject());
  }

  @Test
  @DisplayName("When initialized, a session's roster is empty")
  public void init_empty_roster() {
    assertEquals(0, this.session.getStudentRoster().size());
  }

  @Test
  @DisplayName("When a student enrolls in the session, they are added to the roster")
  public void enroll_roster_increases() {
    Student student = new Student("Jon", "Snow", "jon@example.com");
    this.session.enroll(student);
    assertEquals(1, this.session.getStudentRoster().size());
  }

  @Test
  @DisplayName("A student is not able to enroll twice")
  public void enroll_roster_twiceNoDupe() {
    Student student = new Student("Jon", "Snow", "jon@example.com");
    this.session.enroll(student);
    this.session.enroll(student);
    assertEquals(1, this.session.getStudentRoster().size());
  }

  @Test
  @DisplayName("After enrolling, a student's info is added to the directory")
  public void getDirectory_name_included() {
    Student student = new Student("Jon", "Snow", "jon@example.com");
    this.session.enroll(student);

    assert(this.session.getDirectory().contains("Snow, Jon, jon@example.com"));

  }

  @Test
  @DisplayName("Student assistants that are enrolled are shown in the student's entry in the directory")
  public void getDirectory_studentAssistant_designated() {
    StudentAssistant sa = new StudentAssistant("Sue", "Helper", "sue@example.com");
    this.session.enroll(sa);

    assert(this.session.getDirectory().contains("sue@example.com, (Student Assistant)"));

  }

  /*
    NOTE: Only make the tests pass below if you're seeking an Exceeds Expectation Grade
  */

  // @Test
  // @DisplayName("On initialization, a session has 10 computers")
  // public void exceeds_init_computers_length10() {
  //   assertEquals(10, session.getComputers().size());
  // }

  // @Test
  // @DisplayName("We are able to see a list of the unassigned computers")
  // public void exceeds_getUnassignedComputers_list_length8() {
  //   Student studentA = new Student("Amos", "Andre", "famousAmous@example.com");
  //   Student studentB = new Student("Bobby", "Boss", "bobbyIsBoss@example.com");
  //   session.getComputers().get(0).assignTo(studentA);
  //   session.getComputers().get(1).assignTo(studentB);
  //   assertEquals(8, session.getUnassignedComputers().size());
  // }
}
