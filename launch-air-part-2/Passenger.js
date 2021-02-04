import Ticket from "./Ticket.js";

class Passenger {
  constructor(name) {
    this.name = name;
    this.flights = [];
  }

  purchaseTicket(flight, seatNumber) {
    this.flights.push(flight);
    return new Ticket(flight, this, seatNumber);
  }
}

export default Passenger;
