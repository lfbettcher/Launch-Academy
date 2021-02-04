import Airplane from "./Airplane.js";
import Flight from "./Flight.js";
import Passenger from "./Passenger.js";

const plane1 = new Airplane("Plane Make", "Plane Model", 100);
const flight1 = new Flight("October 1", "1PM", "4PM", "Place1", "Place2");
plane1.scheduleFlight(flight1);

console.log(plane1);
console.log(flight1);

let maidenFlight = new Flight("September 2", "10AM", "1PM", "Boston", "San Diego");
let amelia = new Passenger("Amelia Earhart");
let ticket1 = amelia.purchaseTicket(maidenFlight, "6E");
console.log(ticket1);
console.log(amelia.flights);
