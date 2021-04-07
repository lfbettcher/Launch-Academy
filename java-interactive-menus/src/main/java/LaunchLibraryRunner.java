public class LaunchLibraryRunner {

  public static void main(String[] args) {
    CoffeeSelection selection = new CoffeeSelection(CoffeeSelection.Size.m);
    System.out.println(selection.toString());
    System.out.println(CoffeeSelection.sizeOptionList());
    MainMenu menu = new MainMenu();
    menu.promptUntilQuit();
  }
}
