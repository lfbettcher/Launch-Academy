import com.launchacademy.greeter.Greeting;

public class ProgramRunner {

  public static void main(String[] args) {
    Greeting greeting = new Greeting("Jon");
    System.out.println(greeting.sayHi());
  }
}
