import Album from "../Album.js";
import Song from "../Song.js";
import Genre from "../Genre.js";

describe("Album class", () => {
  const name = "album Name";
  const genre1 = new Genre("genre1");
  const genre2 = new Genre("genre2");
  const genres = [genre1, genre2];
  const song1 = new Song("title1", "artist1", 1, genres);
  const song2 = new Song("title2", "artist2", 2, genres);
  const song3 = new Song("title3", "artist3", 3, genres);
  let songList;
  let album;

  beforeEach(() => {
    songList = [song1, song2, song3];
    album = new Album(name, songList, genres);
  });

  it("defines album", () => {
    expect(album).toBeInstanceOf(Album);
  });

  it("has an initialized name", () => {
    expect(album.name).toEqual(name);
  });

  it("has an initialized songList array", () => {
    expect(album.songList).toEqual(songList);
  });

  it("has an initialized array of Genre objects", () => {
    expect(album.genres).toEqual(genres);
  });

  describe("#duration", () => {
    it("returns the sum of all the Songs' durations", () => {
      expect(album.duration()).toEqual(6);
    });
  });

  describe("#trackCount", () => {
    it("returns the number of Songs on the Album", () => {
      expect(album.trackCount()).toEqual(3);
    });
  });

  describe("#isPlaying", () => {
    it("returns true if any of the Songs on the album are playing, otherwise false", () => {
      expect(album.isPlaying()).toEqual(false);
      album.songList[2].isPlaying = true;
      expect(album.isPlaying()).toEqual(true);
    });
  });

  describe("#addSong", () => {
    it("adds a Song to the songList", () => {
      const newSong = new Song("title4", "artist4", 4);
      album.addSong(newSong);
      expect(album.songList.length).toEqual(4);
      expect(album.songList.includes(newSong)).toEqual(true);
    });
  });

  describe("#removeSong", () => {
    it("removes a Song from the songList", () => {
      album.removeSong(song2);
      expect(album.songList.length).toEqual(2);
      expect(album.songList.includes(song2)).toEqual(false);
    });
  });
});
