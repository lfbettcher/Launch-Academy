import Airplane from "../Airplane.js";

describe("An Airplane", () => {
  let type;
  let wingLoading;
  let horsepower;
  let fuel;
  let fuelNeeded;
  let state;
  let fuelStartTakeoffLand;
  let fuelTakeoffLand;

  let newAirplane;

  beforeEach(() => {
    type = "Cesna";
    wingLoading = 10;
    horsepower = 150;
    fuel = 0;
    fuelNeeded = { start: 10, takeoff: 50, land: 20 };
    fuelStartTakeoffLand = fuelNeeded.start + fuelNeeded.takeoff + fuelNeeded.land;
    fuelTakeoffLand = fuelNeeded.takeoff + fuelNeeded.land;
    state = "off";
    newAirplane = new Airplane(type, wingLoading, horsepower, fuel, fuelNeeded);
  });

  describe("initializing a new airplane", () => {
    it("is an Airplane class instance", () => {
      expect(newAirplane).toBeInstanceOf(Airplane);
    });

    it("is initialized with type", () => {
      expect(newAirplane.type).toEqual(type);
    });

    it("is initialized with wingLoading", () => {
      expect(newAirplane.wingLoading).toEqual(wingLoading);
    });

    it("is initialized with horsepower", () => {
      expect(newAirplane.horsepower).toEqual(horsepower);
    });

    it("is initialized with fuel", () => {
      expect(newAirplane.fuel).toEqual(fuel);
    });

    it("is initialized with fuel needed object with fuel needed to start, takeoff, and land", () => {
      expect(newAirplane.fuelNeeded).toEqual(fuelNeeded);
    });

    it("is initialized with state defaulted", () => {
      expect(newAirplane.state).toEqual(state);
    });
  });

  describe("#start", () => {
    it("turns on the engine if it isn't already running and there is enough fuel", () => {
      newAirplane.fuel = fuelNeeded.start;
      expect(newAirplane.start()).toEqual("airplane started");
    });

    it("does not start if there is not enough fuel", () => {
      newAirplane.fuel = fuelNeeded.start - 1;
      expect(newAirplane.start()).toEqual("airplane does not have enough fuel to start");
    });

    it("returns if the airplane is already running", () => {
      newAirplane.fuel = 2 * fuelNeeded.start;
      newAirplane.start();
      expect(newAirplane.start()).toEqual("airplane already started");
    });
  });

  describe("#takeoff", () => {
    it(
      "changes the state of the airplane to flying if the engine has been" +
        "started and has enough fuel to takeoff and land",
      () => {
        newAirplane.fuel = fuelStartTakeoffLand;
        newAirplane.start();
        expect(newAirplane.takeoff()).toEqual("airplane launched");
      }
    );

    it("does not takeoff if there is not enough fuel to takeoff and land", () => {
      newAirplane.fuel = fuelStartTakeoffLand - 1;
      newAirplane.start();
      expect(newAirplane.takeoff()).toEqual("not enough fuel to takeoff and land");
    });

    it("returns if the airplane engine has not been started and cannot takeoff", () => {
      newAirplane.fuel = fuelTakeoffLand;
      expect(newAirplane.takeoff()).toEqual("airplane not started, please start");
    });
  });

  describe("#land", () => {
    it("returns if the airplane has not been started", () => {
      newAirplane.fuel = fuelNeeded.land;
      expect(newAirplane.land()).toEqual("airplane not started, please start");
    });

    it("returns if the airplane is on the ground (has not taken off)", () => {
      newAirplane.fuel = fuelNeeded.start + fuelNeeded.land;
      newAirplane.start();
      expect(newAirplane.land()).toEqual("airplane has not taken off");
    });

    it("lands the airplane if it is currently flying and there is enough fuel to land", () => {
      newAirplane.fuel = fuelStartTakeoffLand;
      newAirplane.start();
      newAirplane.takeoff();
      expect(newAirplane.land()).toEqual("airplane landed");
    });

    it("cannot land the airplane if there is not enough fuel", () => {
      newAirplane.fuel = fuelStartTakeoffLand;
      newAirplane.start();
      newAirplane.takeoff();
      newAirplane.fuel = fuelNeeded.land - 1;
      expect(newAirplane.land()).toEqual("houston, we have a problem!");
    });

    it("returns if airplane is already on the ground", () => {
      newAirplane.fuel = fuelStartTakeoffLand + fuelNeeded.land;
      newAirplane.start();
      newAirplane.takeoff();
      newAirplane.land();
      expect(newAirplane.land()).toEqual("airplane has already landed");
    });
  });
});
