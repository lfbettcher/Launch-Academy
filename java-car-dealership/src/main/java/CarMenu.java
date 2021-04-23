import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CarMenu {

  public static void menu(EntityManager em) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter VIN to search");
    String searchVin = scanner.nextLine();
    Car car = getCarByVin(em, searchVin);
    if (car == null) {
      System.out.println("VIN not found");
      return;
    }
    System.out.println(car);
    String input;
    int inputNum = -1;
    do {
      System.out.println("Select an option");
      System.out.println("1. Update Car Price\n2. Delete Car\n3. Exit to main menu\n");
      System.out.print("> ");
      inputNum = -1;
      try {
        input = scanner.nextLine();
        inputNum = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number");
      }
      if (inputNum == 1) {
        // update price
        double newAskingPrice;
        boolean validPrice = false;
        System.out.println("What is the new asking price? (500 to 50000)");
        do {
          newAskingPrice = getDoublePrice(scanner);
          if (newAskingPrice >= 500 && newAskingPrice <= 50000) {
            validPrice = true;
          } else {
            System.out.println("Please enter a valid price (500 to 50000)");
          }
        } while (!validPrice);
        updateAskingPrice(em, car, newAskingPrice);
        return;
      } else if (inputNum == 2) {
        // delete
        System.out.println("Are you sure you want to delete this car? (Y/N)");
        boolean confirmDelete = getYesNo(scanner);
        if (confirmDelete) {
          deleteCar(em, car);
        } else {
          System.out.println("Delete action was cancelled");
        }
        return;
      } else if (inputNum != 3) {
        System.out.println("Please enter a valid option");
      }
    } while (inputNum != 3);
  }

  public static List<Car> getCarList(EntityManager em) {
    TypedQuery<Car> query = em
        .createQuery("SELECT c FROM Car c ORDER BY asking_price DESC", Car.class);
    return query.getResultList();
  }

  public static void showCars(List<Car> carList) {
    if (carList.isEmpty()) {
      System.out.println("There are no cars in the database");
      return;
    }
    for (Car car : carList) {
      System.out.println(car);
    }
  }

  public static Make getMake(EntityManager em, String makeName) {
    // get or add make
    Make make = null;
    try {
      List<Make> makeList = em
          .createQuery("SELECT m FROM Make m WHERE name = :name", Make.class)
          .setParameter("name", makeName).getResultList();
      if (makeList.isEmpty()) {
        // make doesn't exist yet, add make
        make = new Make(makeName);
        em.getTransaction().begin();
        em.persist(make);
        em.getTransaction().commit();
      } else {
        make = makeList.get(0);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Unable to get Make");
    }
    return make;
  }

  public static Car getCarByVin(EntityManager em, String vin) {
    String vinCheck = "SELECT c FROM Car c WHERE vin = :vin";
    TypedQuery<Car> vinCheckQuery = em.createQuery(vinCheck, Car.class);
    vinCheckQuery.setParameter("vin", vin);
    List<Car> carList = vinCheckQuery.setMaxResults(1).getResultList();
    if (carList.isEmpty()) {
      return null;
    }
    return carList.get(0);
  }

  public static boolean vinIsUnique(EntityManager em, String vin) {
    Car car = getCarByVin(em, vin);
    return car == null;
  }

  public static double getDoublePrice(Scanner scanner) {
    while (!scanner.hasNextDouble()) {
      if (!scanner.nextLine().isBlank()) {
        System.out.println("Please enter a valid price (500 to 50000)");
      }
    }
    double price = scanner.nextDouble();
    scanner.nextLine();
    return price;
  }

  private static void updateAskingPrice(EntityManager em, Car car, double price) {
    try {
      em.getTransaction().begin();
      car.setAskingPrice(price);
      em.getTransaction().commit();
      System.out.println("Asking price was updated");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Asking price could not be updated");
    }
  }

  private static void deleteCar(EntityManager em, Car car) {
    try {
      em.getTransaction().begin();
      em.remove(car);
      em.getTransaction().commit();
      System.out.println(car + " was deleted");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println(car + " could not be deleted");
    }
  }

  public static boolean getYesNo(Scanner scanner) {
    while (true) {
      String user = scanner.nextLine();
      if (user.equalsIgnoreCase("y") || user.equalsIgnoreCase("yes")) {
        return true;
      } else if (user.equalsIgnoreCase("n") || user.equalsIgnoreCase("no")) {
        return false;
      } else {
        System.out.println("Please enter Y or N.");
      }
    }
  }
}
