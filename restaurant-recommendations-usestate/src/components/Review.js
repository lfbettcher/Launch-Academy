import React from "react";

const Review = ({ restaurantId, name, rating, content }) => (
  <div className="mbl">
    <div className="grid-x">
      <div className="cell small-2">
        <strong> Name </strong>
      </div>
      <div className="cell auto">{name}</div>
    </div>

    <div className="grid-x">
      <div className="cell small-2">
        <strong> Score </strong>
      </div>
      <div className="cell auto">{(rating / 100) * 5} stars</div>
    </div>

    <div className="grid-x">
      <div className="cell small-2">
        <strong> Review </strong>
      </div>
      <div className="cell auto">{content}</div>
    </div>
  </div>
);

export default Review;
