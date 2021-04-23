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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "cars")
public class Car {

  @Id
  @SequenceGenerator(name = "cars_generator", sequenceName = "cars_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cars_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @NotBlank
  @Length(min = 5, max = 20)
  @Column(name = "vin", nullable = false, unique = true)
  private String vin;

  @Range(min = 1981, max = 2029)
  @Column(name = "year", nullable = false)
  private Integer year;

  @Range(min = 500, max = 50000)
  @Column(name = "asking_price", nullable = false)
  private Double askingPrice;

  @NotBlank
  @Column(name = "model", nullable = false)
  private String model;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "make_id")
  private Make make;

  public Car() {
  }

  public Car(String vin, Integer year, Double askingPrice, String model, Make make) {
    this.vin = vin;
    this.year = year;
    this.askingPrice = askingPrice;
    this.model = model;
    this.make = make;
  }

  public Car(String vin, Integer year, Double askingPrice, String model) {
    this(vin, year, askingPrice, model, null);
  }

  public Make getMake() {
    return this.make;
  }

  public void setMake(Make make) {
    this.make = make;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getVin() {
    return this.vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public Integer getYear() {
    return this.year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Double getAskingPrice() {
    return this.askingPrice;
  }

  public void setAskingPrice(Double askingPrice) {
    this.askingPrice = askingPrice;
  }

  public String getModel() {
    return this.model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  @Override
  public String toString() {
    return String.format(
        "VIN: %s, make: %s, model: %s, year: %d, asking price: $%.2f",
        this.vin, this.make, this.model, this.year, this.askingPrice);
  }

}
