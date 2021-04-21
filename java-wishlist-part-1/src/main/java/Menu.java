import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Menu {

  public static final String ADD_A_PRODUCT = "Add a Product";
  public static final String LIST_ALL_PRODUCTS = "Display all products";
  public static final String QUIT_TEXT = "Quit";

  public enum MenuOption {
    a(ADD_A_PRODUCT),
    b(LIST_ALL_PRODUCTS),
    c(QUIT_TEXT);

    private final String optionText;

    MenuOption(String optionText) {
      this.optionText = optionText;
    }

    public String toString() {
      return this.name() + ". " + this.optionText;
    }
  }

  @Override
  public String toString() {
    String output = "";
    for (MenuOption option : MenuOption.values()) {
      output += option.toString() + "\n";
    }
    return output;
  }

  public void promptUntilDone() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.wishlist");
    EntityManager em = emf.createEntityManager();
    Scanner scanner = new Scanner(System.in);

    MenuOption input;
    do {
      System.out.println(this);
      System.out.print("> ");
      input = null;
      try {
        input = MenuOption.valueOf(scanner.nextLine());
      } catch (IllegalArgumentException error) {
        System.out.println("Please choose a valid option.");
      }

      if (input == MenuOption.a) {
        // add product
        boolean added;
        do {
          added = addProduct(em);
        } while (!added);
      } else if (input == MenuOption.b) {
        // retrieve a list of all products
        TypedQuery<Product> query =
            em.createQuery("SELECT p FROM Product p ORDER BY p.name", Product.class);
        List<Product> allProducts = query.getResultList();
        for (Product product : allProducts) {
          System.out.println(product);
        }
      }
    } while (input != MenuOption.c);
    em.close();
    emf.close();
    System.out.println("Thanks! Come back soon ya hear!");
  }

  private boolean nameIsUnique(EntityManager em, String name) {
    TypedQuery<Product> query =
        em.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class);
    query.setParameter("name", name);
    List<Product> products = query.getResultList();
    return products.isEmpty();
  }

  private boolean addProduct(EntityManager em) {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();
    Scanner scanner = new Scanner(System.in);

    System.out.println("What is the name of the product?");
    String productName = scanner.nextLine();

    System.out.println("What is the price?");
    double productPrice = scanner.nextDouble();
    scanner.nextLine();

    System.out.println("What is the url?");
    String productUrl = scanner.nextLine();

    try {
      // create and persist the product
      Product newProduct = new Product(productName, productPrice, productUrl);
      // validate entity
      Set<ConstraintViolation<Product>> violations = validator.validate(newProduct);
      boolean uniqueName = nameIsUnique(em, productName);
      if (violations.isEmpty() && uniqueName) {
        em.getTransaction().begin();
        em.persist(newProduct);
        em.getTransaction().commit();
        System.out.println("Product added!\n");
        return true;
      } else if (!uniqueName) {
        System.out.printf("name: %s is already in the database\n", productName);
      }
      for (ConstraintViolation violation : violations) {
        System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Product could not be added. Try again.\n");
    return false;
  }
}
