import java.util.ArrayList;
import java.util.List;

public class Conference {

  private String name;
  private int maxRegistrants;
  private List<Person> attendees;
  private List<Session> sessions;

  public Conference(String name, int maxRegistrants) {
    this.name = name;
    this.maxRegistrants = maxRegistrants;
    this.attendees = new ArrayList<Person>();
    this.sessions = new ArrayList<Session>();
  }

  private boolean isFull() {
    return this.attendees.size() >= this.maxRegistrants;
  }

  public String getName() {
    return this.name;
  }

  public int getMaxRegistrants() {
    return this.maxRegistrants;
  }

  public List<Person> getAttendees() {
    return this.attendees;
  }

  public List<Session> getSessions() {
    return this.sessions;
  }

  public boolean register(Person person) {
    if (this.isFull()) {
      return false;
    }
    for (Person attendee : this.attendees) {
      if (attendee.getEmail().equals(person.getEmail())) {
        return false;
      }
    }
    this.attendees.add(person);
    return true;
  }

  public boolean addSession(Session session) {
    this.sessions.add(session);
    return true;
  }

  public String getSummary() {
    StringBuilder summary = new StringBuilder(this.name);
    summary.append("\nNumber of Attendees: ").append(this.attendees.size()).append("\n");
    for (Person p : this.attendees) {
      summary.append(p.getFirstName()).append(" ").append(p.getLastName()).append("\n");
    }
    summary.append("Number of Sessions: ").append(this.sessions.size()).append("\n");
    for (Session s : this.sessions) {
      summary.append(s.getName()).append(", facilitated by ")
          .append(s.getFacilitator().getFullName()).append("\n");
    }
    summary.append("Registration is ").append(this.isFull() ? "now closed" : "still open")
        .append("\n");
    return summary.toString();
  }
}
