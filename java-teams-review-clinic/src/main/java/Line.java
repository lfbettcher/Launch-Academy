import java.util.ArrayList;
import java.util.List;

public class Line {

  private int rank;
  private List<Player> players;

  public Line(int rank) {
    this.rank = rank;
    this.players = new ArrayList<Player>();
  }

  public int getRank() {
    return this.rank;
  }

  public List<Player> getPlayers() {
    return this.players;
  }

  public boolean hasNoPlayers() {
    return this.players.isEmpty();
  }

  public void assign(Player player) {
    this.players.add(player);
  }
}