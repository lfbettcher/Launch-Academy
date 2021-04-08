import java.util.ArrayList;
import java.util.List;

public class Team {

  private String name;
  private String sport;
  private List<Player> members;
  private List<Line> lines;

  public Team(String name, String sport) {
    this.name = name;
    this.sport = sport;
    this.members = new ArrayList<Player>();
    this.lines = new ArrayList<Line>();
    for (int i = 0; i < 4; i++) {
      this.lines.add(new Line(i + 1));
    }
  }

  public String getName() {
    return this.name;
  }

  public String getSport() {
    return this.sport;
  }

  public List<Player> getMembers() {
    return this.members;
  }

  public void sign(Player player) {
    if (!this.members.contains(player)) {
      this.members.add(player);
    }
  }

  public String getRoster() {
    StringBuilder roster = new StringBuilder();
    for (Player p : this.members) {
      roster.append(p.getRosterEntry()).append("\n");
    }
    return roster.toString();
  }

  public List<Line> getLines() {
    return this.lines;
  }

  public List<Line> getOpenLines() {
    List<Line> openLines = new ArrayList<>();
    for (Line line : this.lines) {
      if (line.hasNoPlayers()) {
        openLines.add(line);
      }
    }
    return openLines;
  }
}