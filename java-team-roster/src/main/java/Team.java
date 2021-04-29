import java.util.ArrayList;
import java.util.List;

public class Team {

  private String teamName;
  private List<Player> players;

  public Team(String teamName) {
    this.teamName = teamName;
    this.players = new ArrayList<Player>();
  }

  @Override
  public String toString() {
    return this.teamName;
  }

  public void addPlayer(Player player) {
    this.players.add(player);
  }

  public String getTeamName() {
    return this.teamName;
  }

  public List<Player> getPlayers() {
    return this.players;
  }
}
