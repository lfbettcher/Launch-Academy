// Part One: Build a Musical
// factory function to generate musical objects
const createNewMusical = (title, composer, numberOfTonys) => ({
  title,
  composer,
  numberOfTonys,
  songs: [],
  addSong(song) {
    this.songs.push(song);
  },
});

export default createNewMusical;
