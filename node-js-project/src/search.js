import Artist from "./Artist.js";
import Album from "./Album.js";

const search = (searchIn, string) => {
  let found;
  if (searchIn instanceof Artist) {
    found = searchIn.albums.find((al) => al.name.includes(string));
  } else if (searchIn instanceof Album) {
    found = searchIn.songList.find((song) => song.title.includes(string));
  }

  if (found) return found;
  console.log(`no ${searchIn instanceof Artist ? "album" : "song"} found`);
  return false;
};

export default search;
