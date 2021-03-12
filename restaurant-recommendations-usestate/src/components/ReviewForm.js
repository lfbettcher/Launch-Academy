import React, { useState } from "react";
import _ from "lodash";
import StarRating from "./StarRating";
import ErrorList from "./ErrorList";

const ReviewForm = ({ onAdd, restaurantId }) => {
  const [starRating, setStarRating] = useState(0);
  const [formRecord, setFormRecord] = useState({
    restaurant_id: restaurantId,
    name: "",
    content: "",
  });
  const [errors, setErrors] = useState({});

  const validFormSubmission = () => {
    let submitErrors = {};
    if (starRating === 0) {
      submitErrors = { ...submitErrors, rating: "is blank" };
    }
    const requiredFields = ["name", "content"];
    requiredFields.forEach((field) => {
      if (formRecord[field].trim() === "") {
        submitErrors = { ...submitErrors, [field]: "is blank" };
      }
    });
    setErrors(submitErrors);
    return _.isEmpty(submitErrors);
  };

  const handleInputChange = (event) => {
    const { value, name } = event.currentTarget;
    setFormRecord({ ...formRecord, [name]: value });
  };

  const changeStar = (event) => {
    setStarRating(parseInt(event.currentTarget.value));
  };

  const onSubmit = (event) => {
    event.preventDefault();
    if (validFormSubmission()) {
      onAdd({ ...formRecord, restaurant_id: restaurantId, rating: starRating });
      clearForm(event);
    }
  };

  const clearForm = (event) => {
    event.preventDefault();
    setFormRecord({ name: "", content: "" });
    setStarRating(0);
  };

  return (
    <form className="callout" onSubmit={onSubmit}>
      <ErrorList errors={errors} />
      <label htmlFor="name">
        Name:
        <input
          type="text"
          id="name"
          name="name"
          onChange={handleInputChange}
          value={formRecord.name}
        />
      </label>
      <StarRating onChange={changeStar} starRating={starRating} />
      <label htmlFor="content">
        Review:
        <textarea
          id="content"
          name="content"
          onChange={handleInputChange}
          value={formRecord.content}
        />
      </label>
      <div className="button-group">
        <button className="button" onClick={clearForm}>
          Clear
        </button>
        <input className="button" type="submit" value="Submit" />
      </div>
    </form>
  );
};

export default ReviewForm;
