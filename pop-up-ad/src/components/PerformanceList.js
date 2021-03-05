import React from "react";

import PerformanceComponent from "./PerformanceComponent";

const performances = [
  "See Weird Al Yankovic LIVE at the Somerville Theatre",
  "Keanu Reeves and Nick Offerman: A Fireside Chat",
  "Food-fight with the Foo Fighters vs. Flight of the Conchords vs. Flo-rida",
];

const PerformanceList = (props) => {
  return (
    <div>
      <ul>
        {performances.map((performance) => (
          <PerformanceComponent performance={performance} />
        ))}
      </ul>
    </div>
  );
};

export default PerformanceList;
