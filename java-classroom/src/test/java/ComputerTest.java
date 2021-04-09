import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ComputerTest {
  private Computer computer;
  @BeforeEach
  public void setupComputer() {
    computer = new Computer(4000, 100000);
  }

  @Test
  @DisplayName("When initialized a computer has an available memory value")
  public void init_memory_hasValue() {
    assertEquals(4000, computer.getAvailableMemoryMb());
  }

  @Test
  @DisplayName("When initialized a computer has an available diskspace value")
  public void init_diskSpace_hasValue() {
    assertEquals(100000, computer.getAvailableDiskSpaceMb());
  }

  @Test
  @DisplayName("When initialized a computer has no assigned student")
  public void init_student_isNull() {
    assertEquals(null, computer.getCurrentStudent());
  }

  @Test
  @DisplayName("A computer can be assigned a student as a user")
  public void assignUser_currentUser_notNull() {
    Student student = new Student("Sally", "Something", "sally@example.com");
    computer.assignTo(student);
    assertNotNull(computer.getCurrentStudent());
    assertEquals(student, computer.getCurrentStudent());
  }
}
