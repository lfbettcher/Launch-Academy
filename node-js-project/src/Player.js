class Player {
  constructor(songs) {
    this.songs = songs;
    this.random = false;
  }

  play() {
    if (this.random) {
      this.#random();
    } else {
      this.songs.forEach((song) => {
        Player.#playLogStop(song);
      });
    }
  }

  #random() {
    const playedIndexes = [];
    const songCount = this.songs.length;
    while (playedIndexes.length !== songCount) {
      let index;
      do {
        index = Math.floor(Math.random() * songCount);
      } while (playedIndexes.includes(index));
      const song = this.songs[index];
      playedIndexes.push(index);
      Player.#playLogStop(song);
    }
  }
  
  static #playLogStop(song) {
    song.play();
    console.log(`Currently playing: ${song.title}, Duration: ${song.duration}`);
    song.stop();
  }
}

export default Player;
