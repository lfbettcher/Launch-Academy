import React, { useState } from "react";

const RestaurantForm = ({ onAdd }) => {
  const [formRecord, setFormRecord] = useState({
    name: "",
    location: "",
    image: "",
    website: "",
  });

  const handleInputChange = (event) => {
    setFormRecord({
      ...formRecord,
      [event.currentTarget.name]: event.currentTarget.value,
    });
  };

  const onSubmit = (event) => {
    event.preventDefault();
    onAdd(formRecord);
    clearForm(event);
  };

  const clearForm = (event) => {
    event.preventDefault();
    setFormRecord({
      name: "",
      location: "",
      image: "",
      website: "",
    });
  };

  return (
    <form className="callout" onSubmit={onSubmit}>
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
      <label htmlFor="location">
        Location:
        <input
          type="text"
          id="location"
          name="location"
          onChange={handleInputChange}
          value={formRecord.location}
        />
      </label>
      <label htmlFor="image">
        Image URL:
        <input
          type="text"
          id="image"
          name="image"
          onChange={handleInputChange}
          value={formRecord.image}
        />
      </label>

      <label htmlFor="website">
        Website:
        <textarea
          id="website"
          name="website"
          onChange={handleInputChange}
          value={formRecord.website}
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

export default RestaurantForm;
