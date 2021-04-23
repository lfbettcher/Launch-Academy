import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "movies")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @NotBlank(message = "title: must be between 1 and 255 characters")
  @Column(name = "title", nullable = false, length = 255)
  private String title;

  @Min(1920)
  @Column(name = "year", nullable = false)
  private Integer year;

  @Column(name = "synopsis")
  private String synopsis;

  @Null
  @Range(min = 0, max = 100)
  @Column(name = "rating")
  private Integer rating;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @ManyToOne
  @JoinColumn(name = "genre_id")
  private Genre genre;

  public Genre getGenre() {
    return this.genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  @ManyToOne
  @JoinColumn(name = "studio_id")
  private Studio studio;

  public Studio getStudio() {
    return this.studio;
  }

  public void setStudio(Studio studio) {
    this.studio = studio;
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

}
