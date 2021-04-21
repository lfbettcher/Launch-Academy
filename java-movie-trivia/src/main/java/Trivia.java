import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Trivia {

  public static void main(String[] args) {
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.movies");
    EntityManager em = emf.createEntityManager();

    try {
      // What titles were released in 2001?
      TypedQuery<Movie> query2001 =
          em.createQuery("SELECT m FROM Movie m WHERE m.year = 2001", Movie.class);
      List<Movie> movies2001 = query2001.getResultList();
      System.out.println("\nTitles released in 2001");
      printTitles(movies2001);

      // What titles were released in 2002 and had a rating higher than 90?
      TypedQuery<Movie> query2002RatingoOver90 =
          em.createQuery("SELECT m FROM Movie m WHERE m.year = 2002 AND m.rating > 90",
              Movie.class);
      List<Movie> movies2002RatingoOver90 = query2002RatingoOver90.getResultList();
      System.out.println("\nTitles released in 2002 and had a rating higher than 90");
      printTitles(movies2002RatingoOver90);

      // What actors have the last name of Wilson?
      TypedQuery<Actor> queryActorsLastNameWilson = em
          .createQuery("SELECT a FROM Actor a WHERE a.name LIKE '% Wilson'", Actor.class);
      List<Actor> actorsLastNameWilson = queryActorsLastNameWilson.getResultList();
      System.out.println("\nActors have the last name of Wilson");
      printActors(actorsLastNameWilson);

      // What actors have the first name of Owen?
      TypedQuery<Actor> queryActorsFirstNameOwen = em
          .createQuery("SELECT a FROM Actor a WHERE a.name LIKE 'Owen %'", Actor.class);
      List<Actor> actorsFirstNameOwen = queryActorsFirstNameOwen.getResultList();
      System.out.println("\nActors have the first name of Owen");
      printActors(actorsFirstNameOwen);

      // What studios start with "Fox"?
      TypedQuery<Studio> queryStudiosStartWithFox = em
          .createQuery("SELECT s FROM Studio s WHERE s.name LIKE 'Fox %'", Studio.class);
      List<Studio> studiosStartWithFox = queryStudiosStartWithFox.getResultList();
      System.out.println("\nStudios start with \"Fox\"");
      printStudios(studiosStartWithFox);

      // What studios involve Disney?
      TypedQuery<Studio> queryStudiosInvolveDisney = em
          .createQuery("SELECT s FROM Studio s WHERE s.name LIKE '%Disney%'", Studio.class);
      List<Studio> studiosInvolveDisney = queryStudiosInvolveDisney.getResultList();
      System.out.println("\nStudios involve Disney");
      printStudios(studiosInvolveDisney);

      // What were the top 5 rated movies in 2004?
      TypedQuery<Movie> queryTop5RatedMovies2004 = em
          .createQuery(
              "SELECT m FROM Movie m WHERE m.year = 2004 ORDER BY m.rating DESC NULLS LAST",
              Movie.class);
      List<Movie> top5RatedMovies2004 = queryTop5RatedMovies2004.setMaxResults(5).getResultList();
      System.out.println("\nTop 5 rated movies in 2004");
      printTitles(top5RatedMovies2004);

      // What were the worst 10 movie titles and their ratings in 2003?
      TypedQuery<Movie> queryWorst10Movies2003 = em
          .createQuery(
              "SELECT m FROM Movie m WHERE m.year = 2004 ORDER BY m.rating ASC NULLS LAST",
              Movie.class);
      List<Movie> worst10Movies2003 = queryWorst10Movies2003.setMaxResults(10).getResultList();
      System.out.println("\nWorst 10 movies in 2003");
      printTitles(worst10Movies2003);
    } finally {
      em.close();
      emf.close();
    }
  }

  private static void printTitles(List<Movie> movies) {
    for (Movie m : movies) {
      System.out.println(m.getTitle());
    }
  }

  private static void printActors(List<Actor> actors) {
    for (Actor a : actors) {
      System.out.println(a.getName());
    }
  }

  private static void printStudios(List<Studio> studios) {
    for (Studio s : studios) {
      System.out.println(s.getName());
    }
  }
}