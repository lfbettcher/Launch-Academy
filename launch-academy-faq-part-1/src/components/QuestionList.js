import React, { useState } from "react";
import QuestionTile from "./QuestionTile";

const QuestionList = ({ data }) => {
  const [open, setOpen] = useState(null);

  const questionList = data.map((question) => {
    const handleClick = () => (question.id === open ? setOpen(null) : setOpen(question.id));
    return (
      <>
        <QuestionTile
          key={question.id}
          question={question.question}
          answer={question.answer}
          isOpen={open === question.id}
          onClick={handleClick}
        />
        <hr />
      </>
    );
  });
  return <div className="grid-container">{questionList}</div>;
};

export default QuestionList;
