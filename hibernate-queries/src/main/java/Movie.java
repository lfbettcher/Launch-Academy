import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class Movie {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name="id", nullable=false, unique=true)
  private Long id;

  @Column(name="title", nullable=false)
  private String title;

  @Column(name="year", nullable=false)
  private Integer year;

  @Column(name="synopsis")
  private String synopsis;

  @Column(name="rating")
  private Integer rating;

  @Column(name="created_at")
  private Timestamp createdAt;

  @Column(name="updated_at")
  private Timestamp updatedAt;

  @Column(name="genre_id", nullable=false)
  private Long genreId;

  @Column(name="studio_id")
  private Long studioId;

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

  public long getGenreId() {
    return genreId;
  }

  public void setGenreId(Long genreId) {
    this.genreId = genreId;
  }

  public long getStudioId() {
    return studioId;
  }

  public void setStudio_id(Long studio_id) {
    this.studioId = studioId;
  }
}
