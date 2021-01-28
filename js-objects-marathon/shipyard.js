import createNewSpaceship from "./createNewSpaceship.js";
import createNewCrewMember from "./createNewCrewMember.js";
import createNewRocket from "./createNewRocket.js";

// Step 0 - Create a launchpad
const launchpad = (ship, crewArray, rocket) => {
  console.log("Initiating preflight procedures!");
  console.log(`Welcome aboard to the ${ship.name}!`); // Step 1 - Build a spaceship!
  ship.loadCrew(crewArray); // Step 5 - Announce the crew!
  console.log(`${ship.captain().name} is the Captain!`); // Step 6 - Pick a random captain!
  ship.mountPropulsion(rocket); // Step 9 - Mount your rocket!
  ship.propulsion.addFuel(5); // Step 10 - Blast off!
  // ship.takeoff(); // Step 10 - Blast off!
  ship.countdown(10); // Step 11 - Add a countdown sequence!
};

// Step 2 - Round up your crew!
const crewNames = ["Person One", "Person Two", "Person Three"];

// Step 3 - Train your crew!
const trainCrew = (nameStrings) => {
  const trainedCrew = [];
  nameStrings.forEach((name) => {
    const crewMember = createNewCrewMember(name);
    crewMember.trained = true;
    trainedCrew.push(crewMember);
  });
  return trainedCrew;
};

const trainedCrewArray = trainCrew(crewNames);

// Step 1 - Build a spaceship!
const ourShip = createNewSpaceship("ElongToTheMoon");

// Step 7 - Building your spaceship's rockets!
const ourFirstRocket = createNewRocket();

launchpad(ourShip, trainedCrewArray, ourFirstRocket);
