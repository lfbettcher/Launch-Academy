import React from "react";

const Answer = (props) => {
  const { body, onClick, isSelected } = props;

  return (
    <div>
      <button className={`answer${isSelected ? " selected" : ""}`} onClick={onClick}>
        {body}
      </button>
    </div>
  );
};

export default Answer;
