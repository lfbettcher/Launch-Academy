import React, { useState } from "react";

const ChocolateList = (props) => {
  const [chocolateVisibility, setChocolateVisibility] = useState(true);

  const chocolateData = [
    "Cadbury Chocolate Bar",
    "Lindt 90% Dark Chocolate",
    "Hershey's Special Dark",
    "Lily's Sugar Free Chocolate",
    "Crunch",
    "Toblerone",
  ];

  const chocolateClick = () => {
    setChocolateVisibility(!chocolateVisibility);
  };

  let chocolateListItems = null;
  if (chocolateVisibility) {
    chocolateListItems = chocolateData.map((chocolate, index) => {
      return <li key={index}>{chocolate}</li>;
    });
  }

  return (
    <div id="chocolate-app">
      <h1 onClick={chocolateClick}>CHOCOLATE ONLY CABINET</h1>
      {chocolateVisibility ? <ul>{chocolateListItems}</ul> : null}
    </div>
  );
};

export default ChocolateList;
