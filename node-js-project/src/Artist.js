class Artist {
  constructor(name, albums, genres) {
    this.name = name;
    this.albums = albums;
    this.genres = genres || [];
  }

  addAlbums(album) {
    this.albums.push(album);
  }

  addGenres(genre) {
    this.genres.push(genre);
  }
}

export default Artist;
