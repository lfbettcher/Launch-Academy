import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutomobileTest {
  private Automobile automobile;

  @BeforeEach
//  public void setupAutomobile(){
//    this.automobile = new Automobile("sedan", "Camry", "Toyota", 29, 15);
//  }

  @Test
  @DisplayName("When initialized an Automobile has a type")
  public void init_automobile_hasType(){
    Assertions.assertEquals("sedan", this.automobile.getType());
  }

  @Test
  @DisplayName("When initialized an Automobile has a model")
  public void init_automobile_hasModel(){
    Assertions.assertEquals("Camry", this.automobile.getModel());
  }

  @Test
  @DisplayName("When initialized an Automobile has a make")
  public void init_automobile_hasMake(){
    Assertions.assertEquals("Toyota", this.automobile.getMake());
  }

  @Test
  @DisplayName("When initialized an Automobile has a mpg")
  public void init_automobile_hasMpg(){
    Assertions.assertEquals(29, this.automobile.getMpg());
  }

  @Test
  @DisplayName("When initialized an Automobile has a fuelCapacity")
  public void init_automobile_hasFuelCapacity(){
    Assertions.assertEquals(15, this.automobile.getFuelCapacity());
  }

  @Test
  @DisplayName("When initialized an Automobile is running by default")
  public void init_automobile_runningTrue(){
    Assertions.assertEquals(true, this.automobile.isRunning());
  }

  @Test
  @DisplayName("When toggleEngine is run an Automobile stops running")
  public void toggleEngine_stopsRunning(){
    this.automobile.toggleEngine();
    Assertions.assertEquals(false, this.automobile.isRunning());
  }
}
