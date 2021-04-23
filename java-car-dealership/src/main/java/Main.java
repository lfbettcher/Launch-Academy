import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Main {

  private static String ADD_CAR = "Add a Car";
  private static String LIST_CARS = "List Cars";
  private static String SEARCH_CARS = "Search for a Car";
  private static String EXIT = "Exit";

  private static Scanner inputScanner;

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence
        .createEntityManagerFactory("com.launchacademy.carDealership");
    EntityManager em = emf.createEntityManager();

    List<String> options = new ArrayList<String>();
    options.add(ADD_CAR);
    options.add(LIST_CARS);
    options.add(SEARCH_CARS);
    options.add(EXIT);

    String menuChoice = "";
    while (menuChoice != EXIT) {
      System.out.println("CARS! CARS! CARS!");
      System.out.println("***");
      System.out.println("Coffee is for closers.\n");

      int menuIndex = 1;
      for (String option : options) {
        System.out.println(menuIndex + ". " + option);
        menuIndex++;
      }

      System.out.println("\nWhat would you like to do?");
      inputScanner = new Scanner(System.in);

      while (!inputScanner.hasNextInt()) {
        if (!inputScanner.nextLine().isBlank()) {
          System.out.println("Please enter a valid menu option");
        }
      }
      int selectedOption = inputScanner.nextInt();
      inputScanner.nextLine();

      menuChoice = options.get(selectedOption - 1);
      if (menuChoice == ADD_CAR) {
        addCar(em);
      } else if (menuChoice == LIST_CARS) {
        List<Car> carList = CarMenu.getCarList(em);
        CarMenu.showCars(carList);
      } else if (menuChoice == SEARCH_CARS) {
        CarMenu.menu(em);
      }
    }
    em.close();
    emf.close();
  }

  private static boolean addCar(EntityManager em) {
    // get car info
    System.out.println("What is the VIN (5 to 20 characters?");
    String carVin = inputScanner.nextLine();

    System.out.println("What is the year? (1981 to 2029)");
    while (!inputScanner.hasNextInt()) {
      if (!inputScanner.nextLine().isBlank()) {
        System.out.println("Please enter an integer (1981 to 2029)");
      }
    }
    int carYear = inputScanner.nextInt();
    inputScanner.nextLine();

    System.out.println("What is the make?");
    String carMake = inputScanner.nextLine();
    while (carMake.isBlank()) {
      System.out.println("Car make cannot be blank");
      carMake = inputScanner.nextLine();
    }

    System.out.println("What is the asking price? (500 to 50000)");
    double carAskingPrice = CarMenu.getDoublePrice(inputScanner);

    System.out.println("What is the model?");
    String carModel = inputScanner.nextLine();

    // validate info
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();
    try {
      Make make = CarMenu.getMake(em, carMake);
      Car newCar = new Car(carVin, carYear, carAskingPrice, carModel, make);
      // validate entity
      Set<ConstraintViolation<Car>> violations = validator.validate(newCar);
      boolean uniqueVin = CarMenu.vinIsUnique(em, carVin);
      if (violations.isEmpty() && uniqueVin) {
        // persist car
        em.getTransaction().begin();
        em.persist(newCar);
        em.getTransaction().commit();
        System.out.println("Car " + newCar + " added!\n");
        return true;
      } else if (!uniqueVin) {
        System.out.printf("VIN: %s has already been added\n", carVin);
      }
      for (ConstraintViolation violation : violations) {
        System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Car could not be added.");
    return false;
  }

}
