import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static String ADD_TASK = "Add a task";
  private static String LIST_TASKS = "List Tasks";
  private static String EXIT = "Exit";

  private static Scanner inputScanner;

  public static void main(String[] args) {
    List<String> menuOptions = new ArrayList<String>();
    menuOptions.add(ADD_TASK);
    menuOptions.add(LIST_TASKS);
    menuOptions.add(EXIT);

    inputScanner = new Scanner(System.in);
    String selectedOption = null;

    while(selectedOption != EXIT) {

      int menuCount = 1;
      for(String option : menuOptions) {
        System.out.println(menuCount + ". " + option);
        menuCount++;
      }

      System.out.println("Select option: ");
      int menuOption = inputScanner.nextInt();
      selectedOption = menuOptions.get(menuOption - 1);

      if(selectedOption == ADD_TASK) {
        //add logic
        addTask();
      }
      else if(selectedOption == LIST_TASKS) {
        //list logic
        listTasks();
      }
    }

  }
}
