import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static String ADD_CAR = "Add a Car";
  private static String LIST_CARS = "List Cars";
  private static String SEARCH_CARS = "Search for a Car";
  private static String EXIT = "Exit";

  private static Scanner inputScanner;

  public static void main(String[] args) {
    List<String> options = new ArrayList<String>();
    options.add(ADD_CAR);
    options.add(LIST_CARS);
//    options.add(SEARCH_CARS);
    options.add(EXIT);

    String menuChoice = "";
    while(menuChoice != EXIT) {
      System.out.println("CARS! CARS! CARS!");
      System.out.println("***");
      System.out.println("Coffee is for closers.\n");

      int menuIndex = 1;
      for(String option : options) {
        System.out.println(menuIndex + ". " + option);
        menuIndex++;
      }

      System.out.println("\nWhat would you like to do?");
      inputScanner = new Scanner(System.in);

      int selectedOption = inputScanner.nextInt();
      menuChoice = options.get(selectedOption - 1);
      if(menuChoice == ADD_CAR) {
        //add car logic
      }
      else if(menuChoice == LIST_CARS) {
        //list car logic
      }
    }
  }
}
