import java.util.Scanner;

public class MainMenu {

  public static final String ADD_BOOK_TEXT = "Contribute a book";
  public static final String CHECKOUT_TEXT = "Check out a book";
  public static final String RETURN_BOOK_TEXT = "Return a book";
  public static final String QUIT_TEXT = "Quit";

  public enum MenuOption {
    a(ADD_BOOK_TEXT),
    c(CHECKOUT_TEXT),
    r(RETURN_BOOK_TEXT),
    q(QUIT_TEXT);

    private String optionText;

    MenuOption(String optionText) {
      this.optionText = optionText;
    }

    public String toString() {
      return this.name() + ". " + this.optionText;
    }
  }

  @Override
  public String toString() {
    String output = "";
    for (MenuOption option : MenuOption.values()) {
      output += option.toString() + "\n";
    }
    return output;
  }

  public void promptUntilQuit(Scanner console) {
    System.out.println(this.toString());
    MenuOption input = null;
    do {
      System.out.print("> ");
      try {
        input = MenuOption.valueOf(console.next());
      } catch (IllegalArgumentException error) {
        System.out.println("Please make a valid selection!");
      }

      if (input == MenuOption.a) {
        //allow the user to add a book to the library
      } else if (input == MenuOption.c) {
        //allow the user to check out a book from the library
      } else if (input == MenuOption.r) {
        //allow the user to return a book to the library
      }

    } while (input != MenuOption.q);
    System.out.println("Thanks! Come to the library again.");
//    scanner.close();
  }
}