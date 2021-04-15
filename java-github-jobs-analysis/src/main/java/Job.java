import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Job {

  private String id;
  private String type;
  private String url;
  private String createdAt;
  private String company;
  private String companyUrl;
  private String location;
  private String title;
  private String description;
  private String howToApply;
  private String companyLogo;

  public Job() {
  }

  public Job(String id, String type, String url, String createdAt, String company,
      String companyUrl, String location, String title, String description,
      String howToApply, String companyLogo) {
    this.id = id;
    this.type = type;
    this.url = url;
    this.createdAt = createdAt;
    this.company = company;
    this.companyUrl = companyUrl;
    this.location = location;
    this.title = title;
    this.description = description;
    this.howToApply = howToApply;
    this.companyLogo = companyLogo;
  }


  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getCompany() {
    return this.company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getCompanyUrl() {
    return this.companyUrl;
  }

  public void setCompanyUrl(String companyUrl) {
    this.companyUrl = companyUrl;
  }

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String locations) {
    this.location = location;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHowToApply() {
    return this.howToApply;
  }

  public void setHowToApply(String howToApply) {
    this.howToApply = howToApply;
  }

  public String getCompanyLogo() {
    return this.companyLogo;
  }

  public void setCompanyLogo(String companyLogo) {
    this.companyLogo = companyLogo;
  }

}
