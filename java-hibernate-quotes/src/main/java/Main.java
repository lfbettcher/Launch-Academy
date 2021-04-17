import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.quotes");
    EntityManager em = emf.createEntityManager();

    try {
      Quote quote1 = new Quote();
      quote1.setQuote("Quote 1 text");
      quote1.setAuthor("Quote 1 author");
      quote1.setSubject("Quote 1 subject");
      Quote quote2 = new Quote();
      quote2.setQuote("Quote 2 text");
      quote2.setAuthor("Quote 2 author");
      quote2.setSubject("Quote 2 subject");
      Quote quote3 = new Quote();
      quote3.setQuote("Quote 3 text");
      quote3.setAuthor("Quote 3 author");
      quote3.setSubject("Quote 3 subject");

      Author author1 = new Author();
      author1.setFirstName("Author 1 first name");
      author1.setLastName("Author 1 last name");
      Author author2 = new Author();
      author2.setFirstName("Author 2 first name");
      author2.setLastName("Author 2 last name");
      Author author3 = new Author();
      author3.setFirstName("Author 3 first name");
      author3.setLastName("Author 3 last name");

      em.getTransaction().begin();
      em.persist(quote1);
      em.persist(quote2);
      em.persist(quote3);
      em.persist(author1);
      em.persist(author2);
      em.persist(author3);
      em.getTransaction().commit();
    } finally {
      em.close();
      emf.close();
    }
  }
}
