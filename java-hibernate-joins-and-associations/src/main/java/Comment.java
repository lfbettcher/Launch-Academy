import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

  @Id
  @SequenceGenerator(name = "comment_generator", sequenceName = "comments_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "article_id", nullable = false, insertable = false, updatable = false)
  private Long articleId;

  @Column(name = "body")
  private String body;

  @ManyToOne
  @JoinColumn(name = "article_id", nullable = false)
  private Article article;

  public Article getArticle() {
    return this.article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getArticleId() {
    return this.articleId;
  }

  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}