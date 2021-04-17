import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.books");
    EntityManager em = emf.createEntityManager();

    try {
      // our database interactions can go here
      System.out.println("printed");
    } finally {
      em.close();
      emf.close();
    }
  }
}