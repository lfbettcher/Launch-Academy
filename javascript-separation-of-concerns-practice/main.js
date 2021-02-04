import Coach from "./Coach.js";
import Player from "./Player.js";
import Team from "./Team.js";

const coachLampard = new Coach("Frank Lampard", 2000);
const coachSetien = new Coach("Quique Setién", 2010);
const coachSarri = new Coach("Maurizio Sarri", 2020);

const teamChelsea = new Team("Chelsea", coachLampard);
const teamBarcelona = new Team("Barcelona", coachSetien);
const teamJuventus = new Team("Juventus", coachSarri);

coachLampard.addTeam(teamChelsea);
coachSetien.addTeam(teamBarcelona);
coachSarri.addTeam(teamJuventus);

const drogba = new Player("Didier Drogba", 11, "Striker", teamChelsea);
const alonso = new Player("Marcos Alonso", 3, "Left back", teamChelsea);
const messi = new Player("Lionel Messi", 10, "Forward", teamBarcelona);
const ronaldo = new Player("Cristiano Ronaldo", 7, "Forward", teamJuventus);

teamChelsea.signPlayer(drogba);
teamChelsea.signPlayer(alonso);
teamBarcelona.signPlayer(messi);
teamJuventus.signPlayer(ronaldo);

drogba.score();
drogba.score();
drogba.assist();
alonso.score();
messi.assist();
messi.score();
ronaldo.assist();
ronaldo.redCard();

console.log("Drogba's stats:");
console.log(drogba.stats);
console.log("Alonso's stats:");
console.log(alonso.stats);
console.log("Messi's stats:");
console.log(messi.stats);
console.log("Ronaldo's stats:");
console.log(ronaldo.stats);

console.log(`${coachLampard.name} is the head coach for ${coachLampard.team.name}`);
console.log(`${coachSetien.name} is the head coach for ${coachSetien.team.name}`);
console.log(`${coachSarri.name} is the head coach for ${coachSarri.team.name}`);

console.log(`${coachLampard.name} stats:`);
console.log(coachLampard.team.stats);
console.log(`${coachSetien.name} stats:`);
console.log(coachSetien.team.stats);
console.log(`${coachSarri.name} stats:`);
console.log(coachSarri.team.stats);
