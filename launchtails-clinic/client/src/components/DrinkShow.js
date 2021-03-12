import React, { useEffect, useState } from "react";

const DrinkShow = (props) => {
  const [drink, setDrink] = useState({});

  const fetchDrink = async () => {
    // Fetch a Single Drink
    const { id } = props.match.params;
    try {
      const response = await fetch(`/api/v1/drinks/${id}`);
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`;
        throw new Error(errorMessage);
      }
      const body = await response.json();
      setDrink(body.drink);
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`);
    }
  };

  useEffect(() => {
    fetchDrink();
  }, []);

  return (
    <div className="drink-show card" style={{width: "400px" }}>
      <div className="card-divider">
        <h2>{drink.title}</h2>
      </div>
      <img src="https://picsum.photos/400" alt="random" />
      <div className="card-section">
        <p>{drink.content}</p>
      </div>
    </div>
  );
};

export default DrinkShow;
