import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

  private Session session;
  private Person hook;

  @BeforeEach
  public void setupSession() {
    hook = new Person("Captain", "Hook", "thecaptain@tiktok.com");
    this.session = new Session("Skull Rock", hook);
  }

  @Test
  @DisplayName("A session is instantiated with a name")
  public void init_name_present() {
    assertEquals("Skull Rock", this.session.getName());
  }

  @Test
  @DisplayName("A session is instantiated with a facilitator")
  public void init_facilitator_present() {
    assertEquals(hook, this.session.getFacilitator());
  }
}