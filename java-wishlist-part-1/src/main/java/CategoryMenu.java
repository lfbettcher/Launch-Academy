import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CategoryMenu {

  private static List<Category> getCategoryList(EntityManager em) {
    em.clear();
    TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
    return query.getResultList();
  }

  private static void showCategories(List<Category> categoryList) {
    if (categoryList.isEmpty()) {
      System.out.println("There are no categories in the database");
      return;
    }
    for (int i = 0; i < categoryList.size(); i++) {
      Category category = categoryList.get(i);
      List<Product> productList = category.getProducts();
      int numProducts = productList.size();
      double sum = productList.stream()
          .map(Product::getPrice).reduce(0.0, Double::sum);
      System.out.println("sum " + sum);
      System.out.printf("%d) %s (%d) [$%.2f]\n",
          i, category.getName(), numProducts, sum / numProducts);
    }
  }

  public static void promptUntilDone(EntityManager em) {
    Scanner scanner = new Scanner(System.in);

    // get all categories
    List<Category> categoryList = getCategoryList(em);
    if (categoryList.isEmpty()) {
      System.out.println("There are no categories in the database.");
      return;
    }

    int numCategories = categoryList.size();
    String input;
    do {
      System.out.println("Select a category number or 'q' to return to the main menu.");
      showCategories(categoryList);
      System.out.print("> ");
      input = null;
      int inputNum = -1;
      try {
        input = scanner.nextLine();
        if ("q".equals(input)) {
          return;
        }
        inputNum = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number");
      }

      if (inputNum >= 0 && inputNum < numCategories) {
        // display products
        List<Product> productList = categoryList.get(inputNum).getProducts();
        System.out.println(categoryList.get(inputNum).getName() + ", count: " + productList.size());
        for (Product product : productList) {
          System.out.println(product);
        }
      } else {
        System.out.println("Please enter a valid category number");
      }
    } while (!"q".equals(input));
  }
}
