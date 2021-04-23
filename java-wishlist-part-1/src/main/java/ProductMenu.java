import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ProductMenu {

  public static List<Product> getProductList(EntityManager em) {
    TypedQuery<Product> query =
        em.createQuery("SELECT p FROM Product p ORDER BY p.name", Product.class);
    return query.getResultList();
  }

  public static void showProducts(List<Product> productList) {
    if (productList.isEmpty()) {
      System.out.println("There are no products in the database");
      return;
    }
    for (int i = 0; i < productList.size(); i++) {
      System.out.printf("%d) %s\n", i, productList.get(i));
    }
  }

  public static boolean addProduct(EntityManager em) {
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

    System.out.println("Enter a category or null");
    String productCategory = scanner.nextLine();

    try {
      // create and persist the product
      Product newProduct = new Product(productName, productPrice, productUrl);
      // validate entity
      Set<ConstraintViolation<Product>> violations = validator.validate(newProduct);
      boolean uniqueName = nameIsUnique(em, productName);
      if (violations.isEmpty() && uniqueName) {
        if (!productCategory.isBlank() && !"null".equals(productCategory)) {
          // set category
          Category category = getCategory(em, productCategory);
          newProduct.setCategory(category);
        }
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

  private static void deleteProduct(EntityManager em, Product product) {
    if (product == null) {
      return;
    }
    try {
      em.getTransaction().begin();
      em.remove(product);
      em.getTransaction().commit();
      System.out.println(product.getName() + " was deleted");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println(product.getName() + " could not be deleted");
    }
  }

  public static void deleteProductPrompt(EntityManager em) {
    Product product = getProductChoice(em);
    deleteProduct(em, product);
  }

  public static Product getProductChoice(EntityManager em) {
    Scanner scanner = new Scanner(System.in);

    // display products
    List<Product> productList = getProductList(em);
    int numCategories = productList.size();
    String input;
    do {
      System.out.println("Select a product number or 'q' to return to the main menu.");
      showProducts(productList);
      System.out.print("> ");
      input = null;
      int inputNum = -1;
      try {
        input = scanner.nextLine();
        if ("q".equals(input)) {
          return null;
        }
        inputNum = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number");
      }

      if (inputNum >= 0 && inputNum < numCategories) {
        return productList.get(inputNum);
      } else {
        System.out.println("Please enter a valid category number");
      }
    } while (!"q".equals(input));
    return null;
  }

  private static boolean nameIsUnique(EntityManager em, String name) {
    TypedQuery<Product> query =
        em.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class)
            .setParameter("name", name);
    List<Product> products = query.getResultList();
    return products.isEmpty();
  }

  private static Category getCategory(EntityManager em, String categoryName) {
    // get or add category
    Category category = null;
    try {
      List<Category> categoryList = em
          .createQuery("SELECT c FROM Category c WHERE name = :name", Category.class)
          .setParameter("name", categoryName).getResultList();
      if (categoryList.isEmpty()) {
        // category doesn't exist yet, add category
        category = new Category(categoryName);
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
      } else {
        category = categoryList.get(0);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Unable to get Category");
    }
    return category;
  }
}