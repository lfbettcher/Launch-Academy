import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Entity
@Table(name = "products")
public class Product {

  @Id
  @SequenceGenerator(name = "products_generator", sequenceName = "products_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_generator")
  @Column(name = "id", nullable = false, unique = true)
  private long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", columnDefinition = "TEXT", nullable = false)
  private String description;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "featured", nullable = false)
  private boolean featured;

  @Column(name = "category_name", nullable = false)
  private String categoryName;

//  @Column(name = "category_id", nullable = false, insertable = false, updatable = false)
//  private long categoryId;

//  @ManyToOne
//  @JoinColumn(name = "category_id", nullable = false)
//  private Category category;

//  public Category getCategory() {
//    return this.category;
//  }

//  public void setCategory(Category category) {
//    this.category = category;
//  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public boolean isFeatured() {
    return this.featured;
  }

  public void setFeatured(boolean featured) {
    this.featured = featured;
  }

//  public long getCategoryId() {
//    return this.categoryId;
//  }
//
//  public void setCategoryId(long categoryId) {
//    this.categoryId = categoryId;
//  }


  public String getCategoryName() {
    return this.categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
