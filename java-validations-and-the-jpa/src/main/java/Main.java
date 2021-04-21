import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Main {

  public static void main(String[] args) {
    Contact contact = new Contact();
    contact.setLastName("Smith");
    contact.setFirstName("Sally");
    contact.setEmail("user@example.com");
    contact.setPhoneNumber("6176975309");
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    if (violations.size() > 0) {
      for (ConstraintViolation<Contact> violation : violations) {
        System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
      }
    } else {
      System.out.println("Ready to persist");
      EntityManagerFactory emf = Persistence
          .createEntityManagerFactory("com.launchacademy.contacts");
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      em.persist(contact);
      em.getTransaction().commit();
    }
  }
}
