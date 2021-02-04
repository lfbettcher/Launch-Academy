class Player {
  constructor(name, number, position, team) {
    this.name = name;
    this.number = number;
    this.position = position;
    this.team = team;
    this.stats = {
      goals: 0,
      assists: 0,
      ejections: 0,
    };
  }

  score() {
    console.log("GOOOOAAAALLL!!");
    this.stats.goals += 1;
  }

  assist() {
    console.log(`Great teamwork, ${this.name}.`);
    this.stats.assists += 1;
  }

  redCard() {
    console.log(`${this.name} is OUTTA here.`);
    this.stats.ejections += 1;
  }
}

export default Player;
