import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MonkeyTest {

  private Monkey monkey;

  @BeforeEach
  public void setupMonkey() {
    this.monkey = new Monkey("Donkey", "Kong", "Banana Tacos",
        "It is possible to commit no errors and still lose. That is not a weakness. That is life.");
  }

  @Test
  @DisplayName("When initialized a monkey's first name is present")
  public void init_firstName_present() {
    Assertions.assertEquals("Donkey", this.monkey.getFirstName());
  }

  @Test
  @DisplayName("When initialized a monkey's last name is present")
  public void init_lastName_present() {
    Assertions.assertEquals("Kong", this.monkey.getLastName());
  }

  @Test
  @DisplayName("When initialized a monkey's favorite food is present")
  public void init_food_present() {
    Assertions.assertEquals("Banana Tacos", this.monkey.getFavoriteFood());
  }

  @Test
  @DisplayName("When initialized a monkey's favorite quote is present")
  public void init_quote_present() {
    Assertions.assertEquals(
        "It is possible to commit no errors and still lose. That is not a weakness. That is life.",
        this.monkey.getFavoriteQuote());
  }

  @Test
  @DisplayName("When initialized a monkey's full name is present")
  public void init_FullName_present() {
    Assertions.assertEquals("Donkey Kong", this.monkey.getFullName());
  }

}
