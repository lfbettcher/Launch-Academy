// Step 1 - Build a spaceship!
const createNewSpaceship = (name) => ({
  name,
  // Step 4 - Board the spaceship!
  crew: [],
  loadCrew(trainedCrewMembers) {
    trainedCrewMembers.forEach((crewMember) => {
      this.crew.push(crewMember);
      // Step 5 - Announce the crew! (use object destructuring for crew member name)
      const { name: crewName } = crewMember;
      console.log(`${crewName} is now aboard the ${this.name}.`);
    });
  },
  // Step 6 - Pick a random captain!
  captain() {
    return this.crew[Math.floor(Math.random() * this.crew.length)];
  },
  // Step 9 - Mount your rocket!
  propulsion: null,
  mountPropulsion(rocket) {
    this.propulsion = rocket;
    console.log("The propulsion is mounted!");
  },
  // Step 10 - Blast off!
  takeoff() {
    this.propulsion.fire()
      ? console.log("Some convincing takeoff noise")
      : console.log("Takeoff was unsuccessful");
  },
  // Step 11 - Add a countdown sequence!
  countdown(startCountdown) {
    // Step 12 - Slow down the countdown sequence!
    setTimeout(() => {
      if (startCountdown === 0) {
        this.takeoff(); // Step 13 - The Final Countdown
        console.log("Blastoff!");
        return;
      }
      console.log(startCountdown--);
      this.countdown(startCountdown);
    }, 1000);
  },
});

export default createNewSpaceship;
