import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConferenceTest {

  private Conference conference;

  @BeforeEach
  @DisplayName("1. A conference is initialized with a name and a max number of registrants")
  public void setupConference() {
    this.conference = new Conference("Neverland", 3);
  }

  @Test
  @DisplayName("2. A conference is initialized with a name")
  public void init_name_present() {
    assertEquals("Neverland", this.conference.getName());
  }

  @Test
  @DisplayName("3. A conference is initialized with a number of max registrants")
  public void init_maxRegistrants_present() {
    assertEquals(3, this.conference.getMaxRegistrants());
  }

  @Test
  @DisplayName("4. A conference is initialized with an empty list of attendees")
  public void init_attendees_present() {
    assertEquals(new ArrayList(), this.conference.getAttendees());
  }

  @Test
  @DisplayName("5. A conference is initialized with an empty list of sessions")
  public void init_sessions_present() {
    assertEquals(new ArrayList(), this.conference.getSessions());
  }

  @Test
  @DisplayName("6. A conference has a register method which adds a Person to the attendees and returns true")
  public void register_adds_attendee() {
    Person peterPan = new Person("Peter", "Pan", "panny@neverland.com");
    assertEquals(true, this.conference.register(peterPan));
    assertEquals(this.conference.getAttendees().size(), 1);
    assertEquals(this.conference.getAttendees().get(0), peterPan);
  }

  @Test
  @DisplayName("7. If a person is already registered, it does not add them to the attendees and returns false")
  public void register_checks_attendees() {
    Person peterPan = new Person("Peter", "Pan", "panny@neverland.com");
    Person fakePeterPan = new Person("Captain", "Hook", "panny@neverland.com");
    assertEquals(true, this.conference.register(peterPan));
    assertEquals(false, this.conference.register(fakePeterPan));
    assertEquals(this.conference.getAttendees().size(), 1);
  }

  @Test
  @DisplayName("8. If the conference is full, it doesn't let anyone else register")
  public void register_checks_maxRegistrants() {
    Person peterPan = new Person("Peter", "Pan", "panny@neverland.com");
    Person wendy = new Person("Wendy", "Darling", "ladybird@gmail.com");
    Person smee = new Person("Mr.", "Smee", "smee@smee.me");
    Person hook = new Person("Captain", "Hook", "thecaptain@tiktok.com");
    this.conference.register(peterPan);
    this.conference.register(wendy);
    this.conference.register(smee);
    assertEquals(this.conference.getAttendees().size(), 3);
    assertEquals(false, this.conference.register(hook));
    assertEquals(this.conference.getAttendees().size(), 3);
  }

  @Test
  @DisplayName("9. A conference has an addSession method which adds sessions to the conference schedule")
  public void addSession_adds_session_to_list() {
    Person tinkerbell = new Person("Tinkerbell", "Fairy", "tink@neverland.com");
    Session session = new Session("Visit Mermaid Lagoon", tinkerbell);
    assertEquals(true, this.conference.addSession(session));
  }

  @Test
  @DisplayName("10. A conference has a getSummary method which displays conference information")
  public void getSummary_prints_conference_info() {
    Person peter = new Person("Peter", "Pan", "panny@neverland.com");
    Person wendy = new Person("Wendy", "Darling", "ladybird@gmail.com");
    Session session = new Session("Visit Mermaid Lagoon", peter);

    this.conference.register(peter);
    this.conference.register(wendy);
    this.conference.addSession(session);

    String conferenceSummary = this.conference.getSummary();

    assertTrue(conferenceSummary.contains(this.conference.getName()));
    assertTrue(conferenceSummary.contains(
        "Number of Attendees: " + this.conference.getAttendees().size()
    ));
    assertTrue(conferenceSummary.contains(peter.getFirstName()));
    assertTrue(conferenceSummary.contains(peter.getLastName()));
    assertTrue(conferenceSummary.contains(wendy.getFirstName()));
    assertTrue(conferenceSummary.contains(wendy.getLastName()));
    assertTrue(conferenceSummary.contains(
        "Number of Sessions: " + this.conference.getSessions().size()
    ));
    assertTrue(conferenceSummary.contains(
        session.getName() + ", facilitated by " + peter.getFirstName() + " " + peter.getLastName()
    ));
  }

  @Test
  @DisplayName("11. A conference has a getSummary method which indicates the conference is open if it's not at max registrants")
  public void getSummary_indicates_open() {
    Person peter = new Person("Peter", "Pan", "panny@neverland.com");

    this.conference.register(peter);

    String conferenceSummary = this.conference.getSummary();
    System.out.println(conferenceSummary);
    assertTrue(conferenceSummary.contains("Registration is still open"));
    assertFalse(conferenceSummary.contains("Registration is now closed"));
  }

  @Test
  @DisplayName("12. A conference has a getSummary method which indicates the conference is closed if it's at max registrants")
  public void getSummary_indicates_closed() {
    Person peter = new Person("Peter", "Pan", "panny@neverland.com");
    Person wendy = new Person("Wendy", "Darling", "ladybird@gmail.com");
    Person tinkerbell = new Person("Tinkerbell", "Fairy", "tink@neverland.com");

    this.conference.register(peter);
    this.conference.register(wendy);
    this.conference.register(tinkerbell);

    String conferenceSummary = this.conference.getSummary();

    assertFalse(conferenceSummary.contains("Registration is still open"));
    assertTrue(conferenceSummary.contains("Registration is now closed"));
  }

}