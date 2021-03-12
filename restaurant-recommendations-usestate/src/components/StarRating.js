import React from "react";

const StarRating = ({ onChange, starRating }) => {
  return (
    <label htmlFor="rating">
      Rating:
      <fieldset id="rating">
        <div className="rate">
          <input
            type="radio"
            id="star5"
            name="rating"
            value="100"
            onChange={onChange}
            checked={starRating === 100}
          />
          <label htmlFor="star5">5 stars</label>
          <input
            type="radio"
            id="star4"
            name="rating"
            value="80"
            onChange={onChange}
            checked={starRating === 80}
          />
          <label htmlFor="star4">4 stars</label>
          <input
            type="radio"
            id="star3"
            name="rating"
            value="60"
            onChange={onChange}
            checked={starRating === 60}
          />
          <label htmlFor="star3">3 stars</label>
          <input
            type="radio"
            id="star2"
            name="rating"
            value="40"
            onChange={onChange}
            checked={starRating === 40}
          />
          <label htmlFor="star2">2 stars</label>
          <input
            type="radio"
            id="star1"
            name="rating"
            value="20"
            onChange={onChange}
            checked={starRating === 20}
          />
          <label htmlFor="star1">1 star</label>
        </div>
      </fieldset>
    </label>
  );
};

export default StarRating;
