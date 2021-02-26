import Artist from "../Artist.js";
import Song from "../Song.js";
import Album from "../Album.js";

describe("Artist class", () => {
  const name = "Artist Name";
  const albums = [];
  const genres = [];
  let artist;

  beforeEach(() => {
    artist = new Artist(name, albums, genres);
  });

  it("defines artist", () => {
    expect(artist).toBeInstanceOf(Artist);
  });

  it("has an initialized name", () => {
    expect(artist.name).toEqual(name);
  });

  it("has an initialized albums array", () => {
    expect(artist.albums).toEqual(albums);
  });

  it("has an initialized genre, empty array", () => {
    expect(artist.genres).toEqual(genres);
  });

  describe("#addAlbums", () => {
    it("adds an Album to albums array", () => {
      const song1 = new Song("title1", "artist1", 1);
      const song2 = new Song("title2", "artist2", 2);
      const song3 = new Song("title3", "artist3", 3);
      const newAlbum = new Album("Album Name", [song1, song2, song3]);
      artist.addAlbums(newAlbum);
      expect(artist.albums.includes(newAlbum)).toEqual(true);
    });
  });

  describe("#addGenres", () => {
    it("adds an Genre to genres array", () => {
      const newGenre = "New Genre";
      artist.addGenres(newGenre);
      expect(artist.genres.includes(newGenre)).toEqual(true);
    });
  });
});
