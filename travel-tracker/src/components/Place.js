import React, { useState } from "react";

const Place = ({ name, setSelected }) => {
  const [crossedOff, setCrossedOff] = useState(false);
  const handleClick = () => {
    setCrossedOff(!crossedOff);
    setSelected();
  };

  return (
    <li className={crossedOff ? "done" : ""} onClick={handleClick}>
      {name}
    </li>
  );
};

export default Place;
