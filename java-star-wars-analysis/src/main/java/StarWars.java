import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StarWars {

  public static void main(String[] args) throws FileNotFoundException {
    // Create an ArrayList of All of the Character Names
    Scanner charScan = new Scanner(new File("src/main/resources/characters.txt"));
    List<String> characterList = new ArrayList<>();
    while (charScan.hasNextLine()) {
      characterList.add(charScan.nextLine());
    }
    charScan.close();
    System.out.println(characterList);

    // Add more people to the list, and get rid of Han
    characterList.add("Biggs");
    characterList.add("Wedge");
    characterList.add(characterList.indexOf("Leia"), "Darth Vader");
    characterList.remove("Han");
    System.out.println(characterList);

    // Convert to a HashMap
    Map<String, String> characterMap = new HashMap<>();
    for (String name : characterList) {
      characterMap.put(name, "Unknown");
    }
    System.out.println(characterMap);

    // Star wars fans are picky! Update to have the correct last names
    File lastNamesJson = new File("src/main/resources/last-names.json");
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> nameMap = new HashMap<>();
    try {
      nameMap = mapper.readValue(lastNamesJson, HashMap.class);
    } catch (IOException io) {
      io.printStackTrace();
    }
    for (String firstName : characterMap.keySet()) {
      characterMap.put(firstName, nameMap.get(firstName));
    }
    System.out.println(characterMap);

    // Parting is such sweet sorrow
    characterMap.remove("Obi-Wan");
    System.out.println(characterMap);

    // Pick a letter, any letter
    for (String firstName : characterMap.keySet()) {
      if (firstName.contains("L")) {
        System.out.println(characterMap.get(firstName));
      }
    }
    for (Map.Entry<String, String> name : characterMap.entrySet()) {
      if ("Antilles".equals(name.getValue())) {
        System.out.println(name.getKey());
      }
    }
  }
}
