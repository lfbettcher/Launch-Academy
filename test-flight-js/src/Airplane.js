class Airplane {
  constructor(type, wingLoading, horsepower, fuel, fuelNeeded) {
    this.type = type;
    this.wingLoading = wingLoading;
    this.horsepower = horsepower;
    this.fuel = fuel;
    this.fuelNeeded = fuelNeeded;
    this.state = "off";
  }

  hasEnoughFuel(action) {
    const { fuel, fuelNeeded } = this;
    switch (action) {
      case "start":
        return fuel >= fuelNeeded.start;
      case "takeoff":
        return fuel >= fuelNeeded.takeoff + fuelNeeded.land;
      case "land":
        return fuel >= fuelNeeded.land;
      default:
        return false;
    }
  }

  start() {
    if (this.state === "started") return "airplane already started";
    if (!this.hasEnoughFuel("start")) return "airplane does not have enough fuel to start";
    if (this.state === "off") {
      this.state = "started";
      this.fuel -= this.fuelNeeded.start;
      return "airplane started";
    }
  }

  takeoff() {
    if (this.state === "flying") return "airplane already flying";
    if (this.state !== "started") return "airplane not started, please start";
    if (!this.hasEnoughFuel("takeoff")) return "not enough fuel to takeoff and land";
    this.state = "flying";
    this.fuel -= this.fuelNeeded.takeoff;
    return "airplane launched";
  }

  land() {
    if (this.state === "landed") return "airplane has already landed";
    if (this.state === "off") return "airplane not started, please start";
    if (this.state !== "flying") return "airplane has not taken off";
    if (!this.hasEnoughFuel("land")) return "houston, we have a problem!";
    this.state = "landed";
    this.fuel -= this.fuelNeeded.land;
    return "airplane landed";
  }
}
export default Airplane;
