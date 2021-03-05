import React from "react";
import Grocery from "./Grocery";

const GroceryList = (props) => {
  const groceryList = props.groceries.map((grocery) => {
    return <Grocery key={grocery.id} name={grocery.name} />;
  });

  return <>{groceryList}</>;
};

export default GroceryList;
