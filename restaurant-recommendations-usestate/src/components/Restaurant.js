import React from "react";

const Restaurant = ({ restaurant, selected, onClick }) => {
  const { id, name, location, image, website } = restaurant;

  return (
    <div
      className={`grid-x grid-margin-x grid-padding-y ${selected === id && "selected"}`}
      onClick={onClick}
    >
      <div className="cell">
        <img src={image} alt={name} />
      </div>
      <div className="cell">
        <p>
          <a href={website} target="_blank" rel="noreferrer">
            <strong>{name}</strong>
          </a>
        </p>
        <p>{location}</p>
      </div>
    </div>
  );
};

export default Restaurant;
