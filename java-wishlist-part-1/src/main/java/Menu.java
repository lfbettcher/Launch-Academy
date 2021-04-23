import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Menu {

  public static final String ADD_A_PRODUCT = "Add a Product";
  public static final String LIST_ALL_PRODUCTS = "Display all products";
  public static final String DELETE_PRODUCT = "Delete a product";
  public static final String LIST_PRODUCTS_IN_CATEGORY = "View Products In a Category";
  public static final String QUIT_TEXT = "Quit";

  public enum MenuOption {
    a(ADD_A_PRODUCT),
    p(LIST_ALL_PRODUCTS),
    d(DELETE_PRODUCT),
    c(LIST_PRODUCTS_IN_CATEGORY),
    q(QUIT_TEXT);

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

      if (input == MenuOption.a) {  // add product
        boolean added;
        do {
          added = ProductMenu.addProduct(em);
        } while (!added);
      } else if (input == MenuOption.p) {  // show products
        List<Product> productList = ProductMenu.getProductList(em);
        ProductMenu.showProducts(productList);
      } else if (input == MenuOption.d) {  // delete a product
        ProductMenu.deleteProductPrompt(em);
      } else if (input == MenuOption.c) {  // category menu
        CategoryMenu.promptUntilDone(em);
      }
    } while (input != MenuOption.q);
    em.close();
    emf.close();
    System.out.println("Thanks! Come back soon ya hear!");
  }
}