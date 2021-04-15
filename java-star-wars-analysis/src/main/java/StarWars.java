import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StarWars {

  private static final String DIR = "src/main/resources/";

  public static void main(String[] args) {
    System.out.println("Create an ArrayList of All of the Character Names");
    List<String> characterList = new ArrayList<>();
    try {
      Scanner charScan = new Scanner(new File(DIR + "characters.txt"));
      while (charScan.hasNextLine()) {
        characterList.add(charScan.nextLine());
      }
      charScan.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(characterList);

    System.out.println("\nAdd more people to the list, and get rid of Han");
    characterList.add("Biggs");
    characterList.add("Wedge");
    characterList.add(characterList.indexOf("Leia"), "Darth Vader");
    characterList.remove("Han");
    System.out.println(characterList);

    System.out.println("\nConvert to a HashMap");
    Map<String, String> characterMap = new HashMap<>();
    for (String name : characterList) {
      characterMap.put(name, "Unknown");
    }
    System.out.println(characterMap);

    System.out.println("\nStar wars fans are picky! Update to have the correct last names");
    File lastNamesJson = new File(DIR + "last-names.json");
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

    System.out.println("\nParting is such sweet sorrow");
    characterMap.remove("Obi-Wan");
    System.out.println(characterMap);

    System.out.println("\nPick a letter, any letter");
    System.out.println("Last names for characters whose first names contain an \"L\":");
    for (String firstName : characterMap.keySet()) {
      if (firstName.contains("L")) {
        System.out.println(characterMap.get(firstName));
      }
    }
    for (Map.Entry<String, String> name : characterMap.entrySet()) {
      if ("Antilles".equals(name.getValue())) {
        System.out.println("Key for \"Antilles\": " + name.getKey());
      }
    }

    /* Exceeds */
    File charactersJson = new File(DIR + "characters.json");
    List<SWCharacter> characterObjList = new ArrayList<>();
    try {
      characterObjList = Arrays.asList(mapper.readValue(charactersJson, SWCharacter[].class));
    } catch (IOException io) {
      io.printStackTrace();
    }

    System.out.println("\nCreate an ArrayList for all the Characters with Blue Eyes");
    List<String> charactersWithBlueEyesList = charactersWithBlueEyes(characterObjList);
    System.out.println(charactersWithBlueEyesList);

    System.out.println("\nCreate a HashMap of Each Character's Birth Year");
    Map<String, String> characterBirthYearMap = charactersBirthYear(characterObjList);
    System.out.println(characterBirthYearMap);
    System.out.println(
        "Biggs Darklighter's birth year: " + characterBirthYearMap.get("Biggs Darklighter"));

    System.out.println("\nCounts of Each Specified Gender");
    showGenderCount(characterObjList);

    // Hex-Colors for Eye Colors
    hexColorsForEyeColors(characterObjList, "hex-colors.json");

    // Write New JSON to File
    writeJsonFile(characterObjList, "modified_characters.json");
  }

  private static List<String> charactersWithBlueEyes(List<SWCharacter> characterList) {
    List<String> names = new ArrayList<>();
    for (SWCharacter character : characterList) {
      if ("blue".equals(character.getEyeColor())) {
        names.add(character.getName());
      }
    }
    return names;
  }

  private static Map<String, String> charactersBirthYear(List<SWCharacter> characterList) {
    Map<String, String> characterBirthYearMap = new HashMap<>();
    for (SWCharacter character : characterList) {
      characterBirthYearMap.put(character.getName(), character.getBirthYear());
    }
    return characterBirthYearMap;
  }

  private static void showGenderCount(List<SWCharacter> characterList) {
    int maleCount = 0;
    int naCount = 0;
    int femaleCount = 0;
    for (SWCharacter character : characterList) {
      String gender = character.getGender();
      if ("male".equals(gender)) {
        maleCount++;
      } else if ("female".equals(gender)) {
        femaleCount++;
      } else if ("n/a".equals(gender)) {
        naCount++;
      }
    }
    System.out.printf("male: %d\nn/a: %d\nfemale: %d\n", maleCount, naCount, femaleCount);
  }

  private static void hexColorsForEyeColors(List<SWCharacter> characterList, String fileName) {
    File hexColorsJson = new File(DIR + fileName);
    Map<String, String> hexColorMap = new HashMap<>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      hexColorMap = mapper.readValue(hexColorsJson, HashMap.class);
    } catch (IOException io) {
      io.printStackTrace();
    }
    for (SWCharacter character : characterList) {
      String hexColor = hexColorMap.get(character.getEyeColor());
      character.setEyeColorHexValue(hexColor);
    }
  }

  private static void writeJsonFile(List<SWCharacter> characterList, String fileName) {
    File writeFile = new File(DIR + fileName);
    ObjectMapper mapper = new ObjectMapper();
    DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
    prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
    try {
      mapper.writer(prettyPrinter).writeValue(writeFile, characterList);
    } catch (IOException io) {
      io.printStackTrace();
    }
    System.out.println("\nDone writing new file: " + fileName);
  }
}
