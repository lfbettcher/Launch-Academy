import React from "react";

import Review from "./Review";

const ReviewList = ({ reviews }) => {
  const reviewList = reviews.map((review) => {
    return (
      <Review
        key={review.id}
        restaurantId={review.restaurant_id}
        name={review.name}
        rating={review.rating}
        content={review.content}
      />
    );
  });
  return <>{reviewList}</>;
};

export default ReviewList;
