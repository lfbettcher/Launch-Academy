import React, { useState, useEffect } from "react";

import DrinkTile from "./DrinkTile";
import DrinkFormContainer from "./DrinkFormContainer";

const DrinksIndexContainer = (props) => {
  const [drinks, setDrinks] = useState([]);

  const fetchDrinks = async () => {
    try {
      const response = await fetch("/api/v1/drinks");
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`;
        throw new Error(errorMessage);
      }
      const body = await response.json();
      setDrinks(body.drinks);
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`);
    }
  };

  useEffect(() => {
    fetchDrinks();
  }, []);

  // const addNewDrink = async (formPayload) => {
  //   try {
  //     const response = await fetch("/api/v1/drinks", {
  //       method: "POST",
  //       headers: new Headers({
  //         "Content-Type": "application/json",
  //       }),
  //       body: JSON.stringify(formPayload),
  //     });
  //     if (!response.ok) {
  //       if (response.status === 422) {
  //         const body = await response.json();
  //         return setErrors(body.errors);
  //       }
  //       const errorMessage = `${response.status} (${response.statusText})`;
  //       throw new Error(errorMessage);
  //     }
  //     const body = await response.json();
  //     // setErrors({});
  //     setDrinks([...drinks, body.drink]);
  //   } catch (err) {
  //     console.error(`Error in fetch: ${err.message}`);
  //   }
  // };

  const drinkTiles = drinks.map((drink) => {
    return <DrinkTile key={drink.id} id={drink.id} title={drink.title} body={drink.body} />;
  });

  return (
    <div className="grid-y grid-margin-y grid-padding-y">
      <div className="cell small-6 large-8">
        <h1>LaunchTails</h1>
        <hr />
        {drinkTiles}
      </div>
      {/* <div className="cell small-6 large-4">
        <DrinkFormContainer addNewDrink={addNewDrink} />
      </div> */}
    </div>
  );
};

export default DrinksIndexContainer;
