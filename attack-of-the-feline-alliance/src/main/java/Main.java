import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence
        .createEntityManagerFactory("com.launchacademy.feline_alliance");
    EntityManager em = emf.createEntityManager();

    // Persist Blaster and LaserCannon
    Blaster newBlaster = new Blaster("Blaster One", "B1", 5);
    LaserCannon newLaserCannon = new LaserCannon("Laser Cannon One", 1, 2, true);
    try {
      em.getTransaction().begin();
      em.persist(newBlaster);
      em.persist(newLaserCannon);
      em.getTransaction().commit();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      em.close();
      emf.close();
    }
  }
}
