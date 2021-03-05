import React from "react";
import Answer from "./Answer";

const AnswersList = (props) => {
  const { question, selectedId, setSelectedId } = props;
  const { answers } = question;
  const answersList = answers.map((answer) => {
    const handleClick = () => setSelectedId(answer.id);
    return (
      <Answer
        key={answer.id}
        body={answer.body}
        onClick={handleClick}
        isSelected={selectedId === answer.id}
      />
    );
  });
  return <div>{answersList}</div>;
};

export default AnswersList;
