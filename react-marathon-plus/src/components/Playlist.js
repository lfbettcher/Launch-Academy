import React from "react";

const Playlist = (props) => {
  const { data, selectedStatus, setSelectedPlaylistId } = props;
  const playlistClick = () => selectedStatus ? setSelectedPlaylistId(null) : setSelectedPlaylistId(data.id);
  return (
    <h5 className={selectedStatus ? "selected" : ""} onClick={playlistClick}>
      {data.name}
    </h5>
  );
};

export default Playlist;
