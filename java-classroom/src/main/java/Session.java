import java.util.ArrayList;
import java.util.List;

public class Session {

  private String name;
  private String subject;
  private List<Student> studentRoster;
  private List<String> directory;
  private List<Computer> computers;

  public Session(String name, String subject) {
    this.name = name;
    this.subject = subject;
    this.studentRoster = new ArrayList<Student>();
    this.directory = new ArrayList<String>();
    this.computers = new ArrayList<Computer>();
    for (int i = 0; i < 5; i++) {
      this.computers.add(new PC(16384, 1000000));
      this.computers.add(new Mac(8192, 500000));
    }
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

  public List<String> getDirectory() {
    return this.directory;
  }

  public List<Computer> getComputers() {
    return this.computers;
  }

  public void enroll(Student student) {
    if (!this.studentRoster.contains(student)) {
      this.studentRoster.add(student);
      this.directory.add(student.getDirectoryEntry());
    }
  }

  public List<Computer> getUnassignedComputers() {
    List<Computer> unassignedComputers = new ArrayList<>();
    for (Computer computer : this.computers) {
      if (computer.getCurrentStudent() == null) {
        unassignedComputers.add(computer);
      }
    }
    return unassignedComputers;
  }

  public String computerInventoryReport() {
    StringBuilder inventory = new StringBuilder();
    for (int i = 0; i < this.computers.size(); i++) {
      inventory.append(i).append(") ").append(this.computers.get(i).toString()).append("\n");
    }
    return inventory.toString();
  }
}
