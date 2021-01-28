import createNewAirplane from "./createNewAirplane.js";
import createNewFlight from "./createNewFlight.js";

// Part 1 - Building an Airplane
const magic777 = createNewAirplane("Magic", "777", 150);
const plane1 = createNewAirplane("Plane1", "Model1", 123);

// Part 2 - Adding Flights
const maidenFlight = createNewFlight("September 2", "10AM", "1PM", "Boston", "San Diego");
const flight1 = createNewFlight("October 1", "1PM", "4PM", "Place1", "Place2");

// Part 3 - Assigning Flights
magic777.scheduleFlight(maidenFlight);
magic777.scheduleFlight(flight1);
console.log(magic777);
