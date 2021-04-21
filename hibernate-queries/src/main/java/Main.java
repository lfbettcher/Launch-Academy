import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.movies");
    EntityManager em = emf.createEntityManager();

    try {
      // Retrieval using JPQL
      TypedQuery<Movie> query = em
          .createQuery("SELECT m FROM Movie m WHERE m.title = 'Troll 2'", Movie.class);
      List<Movie> movies = query.getResultList();
      if (movies.size() > 0) {
        System.out.println(movies.get(0).getTitle());
      } else {
        System.out.println("NOT FOUND");
      }

      // Get single result will error if no record or more than one
      // NoResultException, NonUniqueResultException
      // TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE title = 'Troll 2'", Movie.class);
      // Movie standalone = query.getSingleResult();
      // System.out.println(standalone.getTitle());

      // Persist an Entity
      Movie movie = new Movie();
      movie.setTitle("Tequila Mockingbird");
      movie.setGenreId(2L);
      movie.setYear(2025);
      em.getTransaction().begin();
      em.persist(movie);
      em.getTransaction().commit();
      System.out.println(movie.getId());

      // Update an Entity
      TypedQuery<Movie> retrieval = em
          .createQuery("SELECT m FROM Movie m WHERE m.title = 'Tequila Mockingbird'", Movie.class);
      Movie bestEver = retrieval.getResultList().get(0);
      em.getTransaction().begin();
      bestEver.setRating(100);
      em.getTransaction().commit();

      // Update via JPQL
      em.getTransaction().begin();
      Query updateQuery = em
          .createQuery("UPDATE Movie SET rating = :newRating WHERE title = 'Tequila Mockingbird'");
      updateQuery.setParameter("newRating", 400);
      int updateCount = updateQuery.executeUpdate();
      System.out.println("Update count: " + updateCount);
      em.getTransaction().commit();

      // Delete Entity
      TypedQuery<Movie> retrievalD = em
          .createQuery("SELECT m FROM Movie m WHERE m.title = 'Tequila Mockingbird'", Movie.class);
      Movie bestEverD = retrievalD.getResultList().get(0);
      em.getTransaction().begin();
      em.remove(bestEverD);
      em.getTransaction().commit();

      // Delete with JPQL
      em.getTransaction().begin();
      Query deleteQuery = em.createQuery("DELETE FROM Movie WHERE title = 'Tequila Mockingbird'");
      int deleteCount = deleteQuery.executeUpdate();
      System.out.println("Delete count: " + deleteCount);
      em.getTransaction().commit();

    } finally {
      em.close();
      emf.close();
    }
  }
}