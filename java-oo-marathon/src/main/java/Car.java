public class Car extends Automobile {

  private int capacity;
  private int numPassengers;

  public Car(String type, String model, String make, int mpg, int fuelCapacity, int capacity,
      int numPassengers) {
    super(type, model, make, mpg, fuelCapacity);
    this.capacity = capacity;
    this.numPassengers = numPassengers;
    this.setRunning(false);
  }

  public String companyGreeting() {
    return "Thank you for riding with gÜber, we hope you enjoy your ride!";
  }

  public int getCapacity() {
    return this.capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public int getNumPassengers() {
    return this.numPassengers;
  }

  public void setNumPassengers(int numPassengers) {
    this.numPassengers = numPassengers;
  }

  public boolean addPassengers(int numAdd) {
    if (this.getNumPassengers() + numAdd <= this.getCapacity()) {
      this.setNumPassengers(this.getNumPassengers() + numAdd);
      return true;
    }
    return false;
  }

  public void exitPassengers(int numExit) {
    if (numExit <= this.getNumPassengers()) {
      this.setNumPassengers(this.getNumPassengers() - numExit);
    } else {
      this.setNumPassengers(0);
    }
  }
}
