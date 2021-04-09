import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest {
  private Car car;

  @BeforeEach
  public void setupCar(){
    this.car = new Car("hatchback", "Elantra", "Hyundai", 31, 11, 5, 2);
  }

  @Test
  @DisplayName("Car inherits Automobile: When initialized a car has a type")
  public void init_car_hasType(){
    Assertions.assertEquals("hatchback", this.car.getType());
  }

  @Test
  @DisplayName("Car inherits Automobile: When initialized a car has a model")
  public void init_car_hasModel(){
    Assertions.assertEquals("Elantra", this.car.getModel());
  }

  @Test
  @DisplayName("Car inherits Automobile: When initialized a car has a make")
  public void init_car_hasMake(){
    Assertions.assertEquals("Hyundai", this.car.getMake());
  }

  @Test
  @DisplayName("Car inherits Automobile: When initialized a car has a mpg")
  public void init_car_hasMpg(){
    Assertions.assertEquals(31, this.car.getMpg());
  }

  @Test
  @DisplayName("Car inherits Automobile: When initialized a car has a fuelCapacity")
  public void init_car_hasFuelCapacity(){
    Assertions.assertEquals(11, this.car.getFuelCapacity());
  }

  @Test
  @DisplayName("When initialized a car is not running (rather than running like automobiles)")
  public void init_car_runningTrue(){
    Assertions.assertEquals(false, this.car.isRunning());
  }

  @Test
  @DisplayName("When initialized a car has a capacity")
  public void init_car_capacity(){
    Assertions.assertEquals(5, this.car.getCapacity());
  }

  @Test
  @DisplayName("When initialized a car has a getNumPassengers")
  public void init_car_hasNumPassengers(){
    Assertions.assertEquals(2, this.car.getNumPassengers());
  }

  @Test
  @DisplayName("addPassengers adds to numPassengers and returns true")
  public void addPassengers_add_numPassengers(){
    Assertions.assertTrue(this.car.addPassengers(3));
    Assertions.assertEquals(5, this.car.getNumPassengers());
  }

  @Test
  @DisplayName("addPassengers cannot load past capacity")
  public void addPassengers_cannot_load_past_capacity(){
    Assertions.assertFalse(this.car.addPassengers(5));
    Assertions.assertEquals(2, this.car.getNumPassengers());
  }

  @Test
  @DisplayName("exitPassengers removes passengers from numPassengers")
  public void exitPassengers_subtracts_from_numPassengers(){
    this.car.exitPassengers(1);
    Assertions.assertEquals(1, this.car.getNumPassengers());
  }

  @Test
  @DisplayName("exitPassengers cannot make numPassengers less than zero")
  public void exitPassengers_cannot_make_numPassengers_lessThan_zero(){
    this.car.exitPassengers(4);
    Assertions.assertEquals(0, this.car.getNumPassengers());
  }
}
