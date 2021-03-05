import React from "react";
import Song from "./Song";

const SongCollection = (props) => {
  const { songs, selectedSongId, selectedPlaylistSongIds, setSelectedSongId } = props;
  const songsList = [];

  songs.forEach((song) => {
    if (selectedPlaylistSongIds && !selectedPlaylistSongIds.includes(song.id)) {
      return; // don't add Song if a playlist is selected and this song's not on it
    }
    songsList.push(
      <Song
        key={song.id}
        data={song}
        selectedStatus={selectedSongId === song.id}
        setSelectedSongId={setSelectedSongId}
      />
    );
  });

  return (
    <>
      <h2>Songs</h2>
      {songsList}
    </>
  );
};

export default SongCollection;
