import React, { useState, useEffect } from "react";
import Answer from "./Answer";

const AnswersList = (props) => {
  const [selectedAnswerId, setSelectedAnswerId] = useState(null);
  const {
    questionId,
    answers,
    correctAnswerId,
    correctCounter,
    incorrectCounter,
    correctIds,
    setCorrectCounter,
    setIncorrectCounter,
    setCorrectIds,
  } = props;

  const answersList = answers.map((answer) => {
    const handleClick = () => {
      if (selectedAnswerId === answer.id) {
        setSelectedAnswerId(null);
      } else {
        setSelectedAnswerId(answer.id);
      }
    };

    return (
      <Answer
        key={answer.id}
        body={answer.body}
        onClick={handleClick}
        isSelected={selectedAnswerId === answer.id}
      />
    );
  });

  let resultMessage;
  if (!selectedAnswerId) {
    resultMessage = null;
  } else if (selectedAnswerId === correctAnswerId) {
    resultMessage = <h4 className="green">Correct!</h4>;
  } else {
    resultMessage = <h4 className="red">Incorrect!</h4>;
  }

  useEffect(() => {
    if (selectedAnswerId === correctAnswerId) {
      if (!correctIds.includes(questionId)) {
        setCorrectCounter(correctCounter + 1);
        setCorrectIds(correctIds.concat(questionId));
      }
    } else if (selectedAnswerId) {
      setIncorrectCounter(incorrectCounter + 1);
    }
  }, [selectedAnswerId]);

  return (
    <div>
      {answersList}
      {resultMessage}
    </div>
  );
};

export default AnswersList;
