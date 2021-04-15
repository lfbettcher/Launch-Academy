import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ErrandManager {

  public static void main(String[] args) throws FileNotFoundException {
    List<String> toDoList = new ArrayList<>();
    File toDo = new File("src/main/resources/todo.txt");
    Scanner toDoScanner = new Scanner(toDo);

    while (toDoScanner.hasNextLine()) {
      toDoList.add(toDoScanner.nextLine());
    }
    toDoScanner.close();

    toDoList.add(toDoList.indexOf("buy groceries"), "go to ATM");

    Map<String, String> toDoMap = new HashMap<>();
    for (String task : toDoList) {
      toDoMap.put(task, "pending");
    }

    toDoMap.put("trim hedges", "complete");

    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    Map<String, Integer> groceriesMap = new HashMap<>();
    try {
//      byte[] groceriesJson = Files.readAllBytes(Paths.get("src/main/resources/groceries.json"));
      File groceriesJson = new File("src/main/resources/groceries.json");
      groceriesMap = mapper.readValue(groceriesJson, HashMap.class);
    } catch (IOException io) {
      io.printStackTrace();
    }

    groceriesMap.put("Loaf of Bread", 1);
    groceriesMap.put("Gallon of Milk", 1);

    Map<String, Integer> groceryPricesMap = new HashMap<>();
    try {
//      byte[] groceryPricesJson = Files.readAllBytes(Paths.get("src/main/resources/grocery_prices.json"));
      File groceryPricesJson = new File("src/main/resources/grocery_prices.json");
      groceryPricesMap = mapper.readValue(groceryPricesJson, HashMap.class);
    } catch (IOException io) {
      io.printStackTrace();
    }

    int groceriesSubtotal = 0;
    for (String item : groceriesMap.keySet()) {
      int quantity = groceriesMap.get(item);
      groceriesSubtotal += groceryPricesMap.get(item) * quantity;
    }

    int atmIndex = toDoList.indexOf("go to ATM");
    String atmTask = String.format("withdraw $%.2f from ATM", groceriesSubtotal / 100.0);
    toDoList.set(atmIndex, atmTask);
    toDoMap.remove("go to ATM");
    toDoMap.put(atmTask, "pending");

    Map<String, Double> runningRoute = new HashMap<>();
    try {
//      byte[] routeJson = Files.readAllBytes(Paths.get("src/main/resources/streets.json"));
      File routeJson = new File("src/main/resources/streets.json");
      runningRoute = mapper.readValue(routeJson, HashMap.class);
    } catch (IOException io) {
      io.printStackTrace();
    }

    List<String> pathList1 = new ArrayList<>(
        List.of("South St", "Forest St", "Vernon St", "South St")
    );
    List<String> pathList2 = new ArrayList<>(
        List.of("South St", "Forest St", "Sally Way", "Sally Way", "South St"));
    System.out.println("Path 1: " + getTotalDistance(pathList2, runningRoute));
    System.out.println("Path 2: " + getTotalDistance(pathList1, runningRoute));
  }

  private static double getTotalDistance(List<String> pathList, Map<String, Double> pathMap) {
    double pathDistance = 0.0;
    for (String path : pathList) {
      pathDistance += pathMap.get(path);
    }
    return pathDistance;
  }
}
