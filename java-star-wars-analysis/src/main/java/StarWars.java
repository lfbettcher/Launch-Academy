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

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Create an ArrayList of All of the Character Names");
    Scanner charScan = new Scanner(new File(DIR + "characters.txt"));
    List<String> characterList = new ArrayList<>();
    while (charScan.hasNextLine()) {
      characterList.add(charScan.nextLine());
    }
    charScan.close();
    System.out.println(characterList);

    System.out.println("Add more people to the list, and get rid of Han");
    characterList.add("Biggs");
    characterList.add("Wedge");
    characterList.add(characterList.indexOf("Leia"), "Darth Vader");
    characterList.remove("Han");
    System.out.println(characterList);

    System.out.println("Convert to a HashMap");
    Map<String, String> characterMap = new HashMap<>();
    for (String name : characterList) {
      characterMap.put(name, "Unknown");
    }
    System.out.println(characterMap);

    System.out.println("Star wars fans are picky! Update to have the correct last names");
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

    System.out.println("Parting is such sweet sorrow");
    characterMap.remove("Obi-Wan");
    System.out.println(characterMap);

    System.out.println("Pick a letter, any letter");
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

    /* Exceeds */
    File charactersJson = new File(DIR + "characters.json");
    List<SWCharacter> charObjList = new ArrayList<>();
    try {
      charObjList = Arrays.asList(mapper.readValue(charactersJson, SWCharacter[].class));
    } catch (IOException io) {
      io.printStackTrace();
    }

    System.out.println("Create an ArrayList for all the Characters with Blue Eyes");
    List<String> charsWithBlueEyes = charactersWithBlueEyes(charObjList);
    System.out.println(charsWithBlueEyes);

    System.out.println("Create a HashMap of Each Character's Birth Year");
    Map<String, String> characterBirthYearMap = charactersBirthYear(charObjList);
    System.out.println(characterBirthYearMap);

    System.out.println("Counts of Each Specified Gender");
    showGenderCount(charObjList);

    System.out.println("Hex-Colors for Eye Colors");
    File hexColorsJson = new File(DIR + "hex-colors.json");
    Map<String, String> hexColorMap = new HashMap<>();
    try {
      hexColorMap = mapper.readValue(hexColorsJson, HashMap.class);
    } catch (IOException io) {
      io.printStackTrace();
    }
    for (SWCharacter character : charObjList) {
      String hexColor = hexColorMap.get(character.getEyeColor());
      character.setEyeColorHexValue(hexColor);
    }

    // Write New JSON to File
    String newFileName = "modified_characters.json";
    File modifiedCharactersFile = new File(DIR + newFileName);
    DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
    prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
    try {
      mapper.writer(prettyPrinter).writeValue(modifiedCharactersFile, charObjList);
    } catch (IOException io) {
      io.printStackTrace();
    }
    System.out.println(newFileName + " written!");
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
}
