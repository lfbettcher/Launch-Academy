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
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "makes")
public class Make {

  @Id
  @SequenceGenerator(name = "makes_generator", sequenceName = "makes_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "makes_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @NotBlank
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @OneToMany(mappedBy = "make")
  private List<Car> cars = new ArrayList<>();

  public Make() {
  }

  public Make(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }

  public List<Car> getCars() {
    return this.cars;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}