import Song from "../Song.js";
import Genre from "../Genre.js";

describe("Song class", () => {
  const title = "Song Title";
  const artist = "Song Artist";
  const duration = 4;
  const album = "Album";
  const genre1 = new Genre("genre1");
  const genre2 = new Genre("genre2");
  const genres = [genre1, genre2];
  let song;

  beforeEach(() => {
    song = new Song(title, artist, duration, genres);
  });

  it("defines song", () => {
    expect(song).toBeInstanceOf(Song);
  });

  it("has an initialized title", () => {
    expect(song.title).toEqual(title);
  });

  it("has an initialized artist", () => {
    expect(song.artist).toEqual(artist);
  });

  it("has an initialized duration", () => {
    expect(song.duration).toEqual(duration);
  });

  it("has an initialized array of Genre objects", () => {
    expect(song.genres).toEqual(genres);
  });

  it("has an initialized isPlaying is false", () => {
    expect(song.isPlaying).toEqual(false);
  });

  describe("#play", () => {
    it("changes isPlaying property to true", () => {
      song.play();
      expect(song.isPlaying).toEqual(true);
    });
  });

  describe("#stop", () => {
    it("changes isPlaying property to false", () => {
      song.stop();
      expect(song.isPlaying).toEqual(false);
    });
  });
});
