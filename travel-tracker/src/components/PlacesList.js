import React from "react";
import Place from "./Place";

const PlacesList = ({ places, setSelected }) => {
  const placesList = places.map((place) => {
    const setSelectedPlace = () => setSelected(place.id);
    return <Place key={place.id} name={place.name} setSelected={setSelectedPlace} />;
  });

  return <>{placesList}</>;
};

export default PlacesList;
