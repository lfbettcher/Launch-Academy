class Album {
  constructor(name, songList, genres) {
    this.name = name;
    this.songList = songList;
    this.genres = genres;
  }

  duration() {
    return this.songList.reduce((acc, song) => acc + song.duration, 0);
  }

  trackCount() {
    return this.songList.length;
  }

  isPlaying() {
    return this.songList.some((song) => song.isPlaying);
  }

  addSong(song) {
    this.songList.push(song);
  }

  removeSong(removeSong) {
    this.songList = this.songList.filter((song) => song !== removeSong);
  }
}

export default Album;
