// Part 1 - Building an Airplane
const createNewAirplane = (make, model, capacity) => ({
  make,
  model,
  capacity,
  flights: [],
  scheduleFlight(flight) {
    this.flights.push(flight);
  },
});

export default createNewAirplane;
