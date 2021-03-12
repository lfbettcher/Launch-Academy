import React, { useState, useEffect } from "react";

import Restaurant from "./Restaurant";
import RestaurantForm from "./RestaurantForm";
import reviewsData from "../constants/reviews";
import restaurantsData from "../constants/restaurants";

import ReviewList from "./ReviewList";
import ReviewForm from "./ReviewForm";

const App = () => {
  const [reviews, setReviews] = useState(reviewsData);
  const [restaurants, setRestaurants] = useState(restaurantsData);
  const [selectedRestaurant, setSelectedRestaurant] = useState(restaurantsData[0].id);

  useEffect(() => {
    setReviews(reviewsData.filter((review) => review.restaurant_id === selectedRestaurant));
  }, [selectedRestaurant]);

  const addReview = (review) => {
    setReviews([...reviews, { ...review, id: reviews.length + 1 }]);
  };

  const addRestaurant = (restaurant) => {
    setRestaurants([...restaurants, { ...restaurant, id: restaurants.length + 1 }]);
  };

  return (
    <div className="grid-container">
      <div className="grid-x">
        <div className="restaurants cell small-3">
          <h3>Restaurants</h3>
          <RestaurantForm onAdd={addRestaurant} />
          {restaurants.map((restaurant) => {
            const selectRestaurant = () => setSelectedRestaurant(restaurant.id);
            return (
              <Restaurant
                key={restaurant.id}
                restaurant={restaurant}
                selected={selectedRestaurant}
                onClick={selectRestaurant}
              />
            );
          })}
        </div>
        <div className="reviews cell auto grid-x">
          <div className="cell">
            <h3>Reviews</h3>
            <ReviewList reviews={reviews} />
            <h3>Review Form</h3>
            <ReviewForm onAdd={addReview} restaurantId={selectedRestaurant} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default App;
