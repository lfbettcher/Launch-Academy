class Team {
  constructor(name, coach) {
    this.name = name;
    this.coach = coach;
    this.players = [];
    this.stats = {
      wins: 0,
      losses: 0,
      draws: 0,
      matchesPlayed: 0,
    };
  }

  signPlayer(player) {
    this.players.push(player);
  }

  gameOver(outcome) {
    if (outcome === "W") ++this.stats.wins;
    else if (outcome === "L") ++this.stats.losses;
    else if (outcome === "D") ++this.stats.draws;
    ++this.matchesPlayed;
  }
}

export default Team;
