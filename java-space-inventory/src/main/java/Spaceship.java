import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Spaceship {

  private static final String CREW_FILE = "crewManifest.json";
  private static final String INVENTORY_FILE = "inventory.json";
  private static final String REMOVE_INVENTORY_FILE = "removeInventory.json";
  private static final String CAPTAIN = "Captain";

  public static void main(String[] args) {
    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    Map crewMap = new HashMap<String, String>();
    try {
      byte[] crewFileData = Files.readAllBytes(Paths.get(CREW_FILE));
      crewMap = objectMapper.readValue(crewFileData, HashMap.class);
    } catch (IOException io) {
      io.printStackTrace();
    }
    System.out.println("Crew:\n-----------");
    for (Object crew : crewMap.keySet()) {
      System.out.printf("Rank: %s, Name: %s\n", crew, crewMap.get(crew));
    }
    System.out.println();

    Map inventoryMap = new HashMap<String, String>();
    try {
      byte[] inventoryFileData = Files.readAllBytes(Paths.get(INVENTORY_FILE));
      inventoryMap = objectMapper.readValue(inventoryFileData, HashMap.class);
    } catch (IOException io) {
      io.printStackTrace();
    }

    Map removeMap = new HashMap<String, String>();
    try {
      byte[] removeFileData = Files.readAllBytes(Paths.get(REMOVE_INVENTORY_FILE));
      removeMap = objectMapper.readValue(removeFileData, HashMap.class);
    } catch (IOException io) {
      io.printStackTrace();
    }

    for (Object removeItem : removeMap.keySet()) {
      if (inventoryMap.containsKey(removeItem)) {
        inventoryMap.remove(removeItem);
      }
    }

    System.out.println("INVENTORY:\n--------");
    for (Object inventory : inventoryMap.keySet()) {
      System.out.printf("Item: %s: Count: %s\n", inventory, inventoryMap.get(inventory));
    }
  }
}
