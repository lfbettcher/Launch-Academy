public class Computer {

  private int availableMemoryMb;
  private int availableDiskSpaceMb;
  private Student currentStudent;

  public Computer(int availableMemoryMb, int availableDiskSpaceMb) {
    this.availableMemoryMb = availableMemoryMb;
    this.availableDiskSpaceMb = availableDiskSpaceMb;
    this.currentStudent = null;
  }

  public int getAvailableMemoryMb() {
    return this.availableMemoryMb;
  }

  public int getAvailableDiskSpaceMb() {
    return this.availableDiskSpaceMb;
  }

  public Student getCurrentStudent() {
    return this.currentStudent;
  }

  public void assignTo(Student student) {
    this.currentStudent = student;
  }
}
