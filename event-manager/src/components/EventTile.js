import React, { useState } from "react";

const EventTile = (props) => {
  const { title, timePeriod, description, eventType } = props;

  let background;
  switch (eventType) {
    case "personal":
      background = "green-bg";
      break;
    case "business-casual":
      background = "pink-bg";
      break;
    case "business":
      background = "blue-bg";
      break;
    default:
      background = "event-tile";
  }

  const [completed, setCompleted] = useState(false);
  const [buttonText, setButtonText] = useState("Mark Completed");
  const [backgroundColor, setBackgroundColor] = useState(background);

  const toggleCompleted = (event) => {
    event.preventDefault();
    if (completed) {
      setButtonText("Mark Completed");
      setBackgroundColor(background);
    } else {
      setButtonText("Unmark Completed");
      setBackgroundColor("gray-bg");
    }
    setCompleted(!completed);
  };

  return (
    <div className={backgroundColor}>
      <div className="inline">
        <h3>{title}</h3>
        <button className="completed-button" type="button" onClick={toggleCompleted}>
          {buttonText}
        </button>
      </div>
      <p>{timePeriod}</p>
      <p>{description}</p>
    </div>
  );
};

export default EventTile;
