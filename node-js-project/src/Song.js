class Song {
  constructor(title, artist, duration, genres, album) {
    this.title = title;
    this.artist = artist;
    this.duration = duration;
    this.genres = genres || null;
    this.album = album || null;
    this.isPlaying = false;
  }

  play() {
    this.isPlaying = true;
  }

  stop() {
    this.isPlaying = false;
  }
}

export default Song;
