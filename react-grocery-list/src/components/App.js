import React from "react";
import GroceryList from "./GroceryList";

const App = (props) => {
  const { groceryData } = props;
  return (
    <div>
      <h1>Grocery List</h1>
      <GroceryList groceries={groceryData} />
    </div>
  );
};

export default App;
