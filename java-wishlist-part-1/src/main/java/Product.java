import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @SequenceGenerator(name = "products_generator", sequenceName = "products_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @NotBlank(message = "Name cannot be blank")
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Range(min = 0, max = 1_000_000)
  @Column(name = "price", nullable = false)
  private Double price;

  @URL(message = "You must have a URL which starts with http: or https:")
  @Column(name = "url")
  private String url;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public Category getCategory() {
    return this.category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Product() {
  }

  public Product(String name, Double price, String url) {
    this.name = name;
    this.price = price;
    this.url = url;
  }

  @Override
  public String toString() {
    return String.format("%s, $%.2f, URL: %s, category: %s",
        this.name, this.price, this.url, this.getCategory());
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
