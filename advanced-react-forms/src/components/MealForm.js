import React, { useState } from "react";

const meals = ["", "breakfast", "lunch", "dinner", "snack"];

const MealForm = (props) => {
  const mealOptions = meals.map((meal) => {
    return (
      <option key={meal} value={meal}>
        {meal}
      </option>
    );
  });

  return (
    <form className="callout">
      <label htmlFor="food">
        I ate:
        <input type="text" name="food" />
      </label>

      <label>
        Meal
        <select name="meal">{mealOptions}</select>
      </label>

      <div className="button-group">
        <button className="button">Clear</button>
        <input className="button" type="submit" value="Submit" />
      </div>
    </form>
  );
};

export default MealForm;
