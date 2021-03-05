import React from "react";
import Playlist from "./Playlist";

const PlaylistCollection = (props) => {
  const { playlists, selectedPlaylistId, setSelectedPlaylistId } = props;

  const playlistList = playlists.map((playlist) => {
    return (
      <Playlist
        key={playlist.id}
        data={playlist}
        selectedStatus={selectedPlaylistId === playlist.id}
        setSelectedPlaylistId={setSelectedPlaylistId}
      />
    );
  });

  return (
    <>
      <h2>Playlists</h2>
      {playlistList}
    </>
  );
};

export default PlaylistCollection;
