import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TeamTrivia {

  public static void main(String[] args) {
    ObjectMapper mapper = new ObjectMapper();
    List<HashMap<String, String>> teamData = new ArrayList<HashMap<String, String>>();
    try {
      File teamsJson = new File("src/main/resources/teams.json");
//      byte[] teamsJson = Files.readAllBytes(Paths.get("src/main/resources/teams.json"));
//      List<Team> teamList = new ArrayList<>();
      teamData = mapper.readValue(teamsJson, ArrayList.class);
//      teamList = mapper.readValue(teamsJson, new TypeReference<List<Team>>(){});
      System.out.println(teamData);

    } catch (IOException io) {
      io.printStackTrace();
    }
    System.out.println(teamData);
    System.out.println(teamData.get(0));
    List<String> teamNames = new ArrayList<>();
    for (Map<String, String> teamMap : teamData) {
      teamNames.add(teamMap.get("name"));
    }

    System.out.println("Team names: " + teamNames);
    List<String> teamsBefore1930 = new ArrayList<>();
    for (Map<String, String> teamMap : teamData) {
      if (Integer.parseInt(teamMap.get("foundingYear")) < 1930) {
        teamsBefore1930.add(teamMap.get("name"));
      }
    }
    System.out.println(teamsBefore1930);

    Map<String, String> teamYears = new HashMap<>();
    for (Map<String, String> teamMap : teamData) {
      teamYears.put(teamMap.get("name"), teamMap.get("foundingYear"));
    }
    System.out.println(teamYears);

    int mostWins = Integer.parseInt(teamData.get(0).get("wins"));
    String teamNameWithMostWins = teamData.get(0).get("name");
    int mostLosses = Integer.parseInt(teamData.get(0).get("losses"));
    String teamNameWithMostLosses = teamData.get(0).get("name");

    for (Map<String, String> teamMap : teamData) {
      int wins = Integer.parseInt(teamMap.get("wins"));
      if (wins > mostWins) {
        mostWins = wins;
        teamNameWithMostWins = teamMap.get("name");
      }
      int losses = Integer.parseInt(teamMap.get("losses"));
      if (losses > mostLosses) {
        mostLosses = losses;
        teamNameWithMostLosses = teamMap.get("name");
      }
    }
  }
}