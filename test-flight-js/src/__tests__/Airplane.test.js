import Airplane from "../Airplane.js";

describe("An Airplane", () => {
  let type;
  let wingLoading;
  let horsepower;
  let fuel;
  let fuelNeeded;
  let defaultState;

  let newAirplane;

  beforeEach(() => {
    type = "Cesna";
    wingLoading = 10;
    horsepower = 150;
    fuel = 0;
    fuelNeeded = { start: 10, takeoff: 50, land: 20 };
    defaultState = "off";
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
      expect(newAirplane.state).toEqual(defaultState);
    });
  });

  describe("#start", () => {
    beforeEach(() => {
      newAirplane.fuel = fuelNeeded.start;
    });

    it("starts airplane if it isn't already running and there is enough fuel", () => {
      expect(newAirplane.start()).toEqual("airplane started");
      expect(newAirplane.state).toEqual("started");
    });

    it("does not start if there is not enough fuel", () => {
      newAirplane.fuel -= 1;
      expect(newAirplane.start()).toEqual("airplane does not have enough fuel to start");
      expect(newAirplane.state).toEqual("off");
    });

    it("returns if the airplane is already running", () => {
      newAirplane.state = "started";
      expect(newAirplane.start()).toEqual("airplane already started");
      expect(newAirplane.state).toEqual("started");
    });
  });

  describe("#takeoff", () => {
    beforeEach(() => {
      newAirplane.fuel = fuelNeeded.takeoff + fuelNeeded.land;
    });

    it(
      "changes the state of the airplane to flying if the engine has been" +
        "started and has enough fuel to takeoff and land",
      () => {
        newAirplane.state = "started";
        expect(newAirplane.takeoff()).toEqual("airplane launched");
        expect(newAirplane.state).toEqual("flying");
      }
    );

    it("does not takeoff if there is not enough fuel to takeoff and land", () => {
      newAirplane.fuel -= 1;
      newAirplane.state = "started";
      expect(newAirplane.takeoff()).toEqual("not enough fuel to takeoff and land");
      expect(newAirplane.state).toEqual("started");
    });

    it("returns if the airplane engine has not been started and cannot takeoff", () => {
      newAirplane.state = "off";
      expect(newAirplane.takeoff()).toEqual("airplane not started, please start");
      expect(newAirplane.state).toEqual("off");
    });
  });

  describe("#land", () => {
    beforeEach(() => {
      newAirplane.fuel = fuelNeeded.land;
    });

    it("returns if the airplane has not been started", () => {
      newAirplane.state = "off";
      expect(newAirplane.land()).toEqual("airplane not started, please start");
      expect(newAirplane.state).toEqual("off");
    });

    it("returns if the airplane is on the ground (has not taken off)", () => {
      newAirplane.state = "started";
      expect(newAirplane.land()).toEqual("airplane has not taken off");
      expect(newAirplane.state).toEqual("started");
    });

    it("lands the airplane if it is currently flying and there is enough fuel to land", () => {
      newAirplane.state = "flying";
      expect(newAirplane.land()).toEqual("airplane landed");
      expect(newAirplane.state).toEqual("landed");
    });

    it("cannot land the airplane if there is not enough fuel", () => {
      newAirplane.fuel -= 1;
      newAirplane.state = "flying";
      expect(newAirplane.land()).toEqual("houston, we have a problem!");
      expect(newAirplane.state).toEqual("flying");
    });

    it("returns if airplane is already on the ground", () => {
      newAirplane.state = "landed";
      expect(newAirplane.land()).toEqual("airplane has already landed");
      expect(newAirplane.state).toEqual("landed");
    });
  });
});
