import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Seeder {

  public static String CATALOG_FILE_PATH = "catalog.json";

  public static void seed() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream stream = classLoader.getResourceAsStream(CATALOG_FILE_PATH);
    ObjectMapper mapper = new ObjectMapper();
    List<Product> productList = new ArrayList<>();
    try {
      productList = mapper.readValue(stream, new TypeReference<List<Product>>() {
      });
    } catch (IOException io) {
      io.printStackTrace();
    }

    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.products");
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      for (Product product : productList) {
        String queryString = String
            .format("SELECT p FROM Product p WHERE p.name='%s'", product.getName());
        List<Product> results = em.createQuery(queryString, Product.class).getResultList();
        if (results.isEmpty()) {
          em.persist(product);
        }
      }
      em.getTransaction().commit();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      em.close();
      emf.close();
    }

    // TODO - Category Normalization
    // As a catalog manager
    // I want to manage a list of categories
    // So that the products are easier to find
    // Acceptance Criteria:
    // Create a categories table and populate it on the basis of what is in catalog.json as part of
    // your Seeder routine.
    // Load categories as you load your products into the database. Use a similar technique to what
    // you implemented above so that duplicate category names are not inserted.
    // Your products should reference this table in some way.
  }
}
