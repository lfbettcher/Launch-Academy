import java.util.ArrayList;
import java.util.List;

public class Session {

  private String name;
  private String subject;
  private List<Student> studentRoster;
  private List<String> directory;

  public Session(String name, String subject) {
    this.name = name;
    this.subject = subject;
    this.studentRoster = new ArrayList<Student>();
    this.directory = new ArrayList<String>();
  }

  public String getName() {
    return this.name;
  }

  public String getSubject() {
    return this.subject;
  }

  public List<Student> getStudentRoster() {
    return this.studentRoster;
  }

  public void enroll(Student student) {
    if (!this.studentRoster.contains(student)) {
      this.studentRoster.add(student);
      this.directory.add(student.getDirectoryEntry());
    }
  }

  public List<String> getDirectory() {
    return this.directory;
  }
}
