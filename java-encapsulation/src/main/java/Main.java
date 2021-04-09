public class Main {

  public static void main(String[] args) {
    BankAccount exampleAccount = new BankAccount(100);
    System.out.println(exampleAccount.getBalance());
    exampleAccount.withdraw(10);
    System.out.println(exampleAccount.getBalance());
  }
}