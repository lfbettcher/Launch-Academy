import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Seeder {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.blog");
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Article article = new Article();
      article.setSubject("Testing Hibernate");
      article.setStory("It's quite an interesting story");

      Article otherArticle = new Article();
      otherArticle.setSubject("Working with the JPA");
      otherArticle.setStory("Just Persist Awesomeness");

      em.persist(article);
      em.persist(otherArticle);

      em.getTransaction().commit();

      em.getTransaction().begin();

      Comment comment = new Comment();
      comment.setArticle(article);
      comment.setBody("I like hibernate.");

      Comment otherComment = new Comment();
      otherComment.setArticle(article);
      otherComment.setBody("Yeah. Hibernate is cool.");

      em.persist(comment);
      em.persist(otherComment);

      em.getTransaction().commit();
    } finally {
      em.close();
      emf.close();
    }
  }
}