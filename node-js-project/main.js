import Song from "./src/Song.js";
import Artist from "./src/Artist.js";
import Album from "./src/Album.js";
import Player from "./src/Player.js";
import search from "./src/search.js";
import Genre from "./src/Genre.js";

const genre1 = new Genre("genre1");
const genre2 = new Genre("genre2");
const genres = [genre1, genre2];

const song1 = new Song("title1", "artist1", 1, genres);
const song2 = new Song("title2", "artist2", 2, genres);
const song3 = new Song("title3", "artist3", 3, genres);

const album1 = new Album("Album1", [song1, song2], genres);
const album2 = new Album("Album2", [song3], genres);
const artist1 = new Artist("Artist1", [album1, album2], genres);

console.log(search(artist1, "Album1"));
console.log(search(artist1, "Album3"));
console.log(search(album1, "title1"));
console.log(search(album1, "title3"));

const player = new Player([song1, song2, song3]);
player.play();
player.random = true;
player.play();
