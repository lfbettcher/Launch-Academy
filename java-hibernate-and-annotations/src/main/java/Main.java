import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main {

  public static void main(String[] args) {
    // load the hibernate configure file
    Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    configuration.addResource("Author.hbm.xml");

    // Create the Hibernate service Registry
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
        applySettings(configuration.
            getProperties()).
        build();

    // instantiate the session factory
    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    Session session = sessionFactory.openSession();

    session.beginTransaction();
    Author author = new Author();
    author.setLastName("Stine");
    author.setFirstName("RL");
    session.save(author);
    session.getTransaction().commit();
    sessionFactory.close();
  }
}
