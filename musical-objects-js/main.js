import createNewMusical from "./createNewMusical.js";
import createNewSong from "./createNewSong.js";

const musical1 = createNewMusical("Musical1 Title", "Musical1 Composer", 3);
const musical2 = createNewMusical("Musical2 TItle", "Musical2 Composer", 12);
console.log(musical1.songs);
console.log(musical2);

const song1 = createNewSong("Song1 Title", ["Char", "Act", "Ters"], "1:23");
const song2 = createNewSong("Song2 Title", ["Names", "Of", "Characters"], "4:56");
console.log(song1);
console.log(song2);

// Part Three: Add Songs to Musicals
musical1.addSong(song1);
musical2.addSong(song2);
console.log(musical2.songs);
