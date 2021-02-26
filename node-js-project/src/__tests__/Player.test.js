import Player from "../Player.js";
import Song from "../Song.js";

describe("Player class", () => {
  const song1 = new Song("title1", "artist1", 1);
  const song2 = new Song("title2", "artist2", 2);
  const song3 = new Song("title3", "artist3", 3);
  let songs;
  let player;

  beforeEach(() => {
    songs = [song1, song2, song3];
    player = new Player(songs);
  });

  it("defines player", () => {
    expect(player).toBeInstanceOf(Player);
  });

  it("has an initialized array of Songs", () => {
    expect(player.songs).toEqual(songs);
  });

  describe("#play", () => {
    const { log } = console;

    beforeEach(() => {
      console.log = jest.fn();
    });

    afterAll(() => {
      console.log = log;
    });

    it("if random is false, plays each song in order, logs song name and duration, and stops the song", () => {
      player.songs.forEach((song) => {
        song.play();
        expect(song.isPlaying).toEqual(true);
        // Test for console.log doesn't work
        expect(console.log).toHaveBeenCalled();
        const clog = console.log.mock.calls[0][0];
        expect(clog).toEqual(`Currently playing: ${song.title}, Duration: ${song.duration}`);
        console.log(clog);
        song.end();
        expect(song.isPlaying).toEqual(false);
      });
    });

    it("if random is true, plays each song in random order, logs song name and duration, and stops the song", () => {
      const consoleSpy = jest.spyOn(console, "log");
      player.random = true;
      const playedIndexes = [];
      const songCount = player.songs.length;
      while (playedIndexes.length !== songCount) {
        let index;
        do {
          index = Math.floor(Math.random() * songCount);
        } while (playedIndexes.includes(index));
        const song = player.songs[index];
        playedIndexes.push(index);
        song.play();
        expect(song.isPlaying).toEqual(true);
        // Test for console.log doesn't work
        expect(consoleSpy).toHaveBeenCalledWith(
          `Currently playing: ${song.title}, Duration: ${song.duration}`
        );
        song.end();
        expect(song.isPlaying).toEqual(false);
        expect(song.isPlaying).toEqual(true);
      }
    });
  });
});
