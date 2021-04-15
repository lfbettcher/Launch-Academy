import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SWCharacter {

  private String name;
  private String height;
  private String mass;
  private String hairColor;
  private String skinColor;
  private String eyeColor;
  private String birthYear;
  private String gender;
  private String url;
  private String eyeColorHexValue;

  public SWCharacter() {
  }

  public String getName() {
    return this.name;
  }

  public String getHeight() {
    return this.height;
  }

  public String getMass() {
    return this.mass;
  }

  public String getHairColor() {
    return this.hairColor;
  }

  public String getSkinColor() {
    return this.skinColor;
  }

  public String getEyeColor() {
    return this.eyeColor;
  }

  public String getBirthYear() {
    return this.birthYear;
  }

  public String getGender() {
    return this.gender;
  }

  public String getUrl() {
    return this.url;
  }

  public String getEyeColorHexValue() {
    return this.eyeColorHexValue;
  }

  public void setEyeColorHexValue(String eyeColorHexValue) {
    this.eyeColorHexValue = eyeColorHexValue;
  }
}
