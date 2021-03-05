import React from "react";

const Song = (props) => {
  const { data, selectedStatus, setSelectedSongId } = props;
  const songClick = () => (selectedStatus ? setSelectedSongId(null) : setSelectedSongId(data.id));
  return (
    <h5 className={selectedStatus ? "selected" : ""} onClick={songClick}>
      {data.artist}{" - "}{data.name}
    </h5>
  );
};

export default Song;
