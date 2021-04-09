public class Circle extends Shape {

  private double radius;

  public Circle(double radius) {
    this.radius = radius;
  }

  @Override
  public double perimeter() {
    return Math.PI * this.radius * 2;
  }

  @Override
  public double area() {
    return Math.PI * this.radius * this.radius;
  }
}
