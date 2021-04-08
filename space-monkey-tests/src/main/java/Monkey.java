public class Monkey {

  private String firstName;
  private String lastName;
  private String favoriteFood;
  private String favoriteQuote;

  public Monkey(String firstName, String lastName, String favoriteFood, String favoriteQuote) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.favoriteFood = favoriteFood;
    this.favoriteQuote = favoriteQuote;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getFavoriteFood() {
    return this.favoriteFood;
  }

  public String getFavoriteQuote() {
    return this.favoriteQuote;
  }

  public String getFullName() {
    return this.getFirstName() + " " + this.getLastName();
  }
}
