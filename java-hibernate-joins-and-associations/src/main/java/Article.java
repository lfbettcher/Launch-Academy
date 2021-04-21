import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
public class Article {

  @Id
  @SequenceGenerator(name = "article_generator", sequenceName = "articles_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "subject", nullable = false)
  private String subject;

  @Column(name = "story", nullable = false)
  private String story;

  @OneToMany(mappedBy = "article")
  private List<Comment> comments = new ArrayList<Comment>();

  public List<Comment> getComments() {
    return this.comments;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getStory() {
    return this.story;
  }

  public void setStory(String story) {
    this.story = story;
  }
}