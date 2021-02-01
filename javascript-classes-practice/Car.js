class Car {
  constructor(make, model, mileage) {
    this.make = make;
    this.model = model;
    this.mileage = mileage;
  }

  drive(miles) {
    this.mileage += miles;
  }
}

export default Car;
