import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonTest {

  private Person wendy;

  @BeforeEach
  public void setupPerson() {
    this.wendy = new Person("Wendy", "Darling", "ladybird@gmail.com");
  }

  @Test
  @DisplayName("A Person is initialized with a firstName")
  public void init_firstName_present() {
    assertEquals("Wendy", this.wendy.getFirstName());
  }

  @Test
  @DisplayName("A Person is initialized with a lastName")
  public void init_lastName_present() {
    assertEquals("Darling", this.wendy.getLastName());
  }

  @Test
  @DisplayName("A Person is initialized with an email")
  public void init_email_present() {
    assertEquals("ladybird@gmail.com", this.wendy.getEmail());
  }
}