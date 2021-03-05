import React, { useState, useEffect } from "react";
import PlaylistCollection from "./PlaylistCollection";
import SongCollection from "./SongCollection";

const App = (props) => {
  const { playlists, songs } = props.data;
  const [selectedPlaylistId, setSelectedPlaylistId] = useState(playlists[0].id);
  const selectedPlaylist = playlists.find((playlist) => playlist.id === selectedPlaylistId);
  const playlistSongIds = selectedPlaylist ? selectedPlaylist.songs : null;
  const [selectedSongId, setSelectedSongId] = useState(playlistSongIds ? playlistSongIds[0] : songs[0].id);

  // changes selected song when selected playlist changes
  useEffect(() => {
    playlistSongIds ? setSelectedSongId(playlistSongIds[0]) : setSelectedSongId(songs[0].id);
  }, [selectedPlaylistId]);

  return (
    <div className="grid-container app">
      <h1 className="title">React Music Player</h1>
      <div className="grid-x grid-margin-x">
        <div className="cell small-6">
          <PlaylistCollection
            playlists={playlists}
            selectedPlaylistId={selectedPlaylistId}
            setSelectedPlaylistId={setSelectedPlaylistId}
          />
        </div>
        <div className="cell small-6">
          <SongCollection
            songs={songs}
            selectedSongId={selectedSongId}
            selectedPlaylistSongIds={playlistSongIds}
            setSelectedSongId={setSelectedSongId}
          />
        </div>
      </div>
    </div>
  );
};

export default App;
