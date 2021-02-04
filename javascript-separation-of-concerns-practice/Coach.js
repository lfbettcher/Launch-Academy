class Coach {
  constructor(name, firstYear) {
    this.name = name;
    this.firstYear = firstYear;
    this.team;
  }

  addTeam(team) {
    this.team = team;
  }
}

export default Coach;
