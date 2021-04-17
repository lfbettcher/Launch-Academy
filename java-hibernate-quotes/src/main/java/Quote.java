import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "quotes")
public class Quote {

  @Id
  @SequenceGenerator(name = "quotes_generator", sequenceName = "quotes_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quotes_generator")
  @Column(name = "id", nullable = false, unique = true)
  private long id;

  @Column(name = "quote", nullable = true)
  private String quote;

  @Column(name = "author", nullable = true, length = 255)
  private String author;

  @Column(name = "subject", nullable = true, length = 255)
  private String subject;

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getQuote() {
    return this.quote;
  }

  public void setQuote(String quote) {
    this.quote = quote;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

}