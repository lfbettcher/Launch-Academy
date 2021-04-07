public class Book {

  private String title;

  Book(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  public String toString() {
    return this.getTitle();
  }
}
