import React from "react";

const QuestionTile = (props) => {
  const { question, answer, isOpen, onClick } = props;
  const icon = isOpen ? " fa-minus-square" : " fa-plus-square";
  return (
    <div className="question-tile" onClick={onClick}>
      <i className={`fa fa-2x icon${icon}`} />
      <span className="question">{question}</span>
      {isOpen ? <p>{answer}</p> : null}
    </div>
  );
};

export default QuestionTile;
