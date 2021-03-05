import React, { useState } from "react";
import PlacesList from "./PlacesList";

const App = ({ data }) => {
  const { places, favoritePlaceId } = data;
  const [selected, setSelected] = useState();

  return (
    <div id="wishlist-div">
      <div className="grid-container">
        <div className="small-12 text-center">
          <h3>Wanderlust Wishlist</h3>
          <PlacesList places={places} setSelected={setSelected} />
          {selected === favoritePlaceId ? <div><h1>What a beauty!</h1></div> : null}
        </div>
      </div>
    </div>
  );
};

export default App;
