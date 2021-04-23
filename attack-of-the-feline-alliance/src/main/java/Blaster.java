import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "blasters")
public class Blaster {

  @Id
  @SequenceGenerator(name = "blasters_generator", sequenceName = "blasters_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blasters_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "recharge_time", nullable = false)
  private Integer rechargeTime;

  @Column(name = "attachment")
  private String attachment;

  public Blaster() {
  }

  public Blaster(String name, String type, Integer rechargeTime, String attachment) {
    this.name = name;
    this.type = type;
    this.rechargeTime = rechargeTime;
    this.attachment = attachment;
  }

  public Blaster(String name, String type, Integer rechargeTime) {
    this(name, type, rechargeTime, null);
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getRechargeTime() {
    return this.rechargeTime;
  }

  public void setRechargeTime(Integer rechargeTime) {
    this.rechargeTime = rechargeTime;
  }

  public String getAttachment() {
    return this.attachment;
  }

  public void setAttachment(String attachment) {
    this.attachment = attachment;
  }
}
