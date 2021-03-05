import React, { useState, useEffect } from "react";
import AnswersList from "./AnswersList";
import Score from "./Score";

const QuestionsList = (props) => {
  const { questions } = props
  const [correctCounter, setCorrectCounter] = useState(0);
  const [incorrectCounter, setIncorrectCounter] = useState(0);
  const [correctIds, setCorrectIds] = useState([]);

  const questionsList = questions.map((question) => {
    return (
      <div className="question" key={question.id}>
        <h3>{question.body}</h3>
        <AnswersList
          questionId={question.id}
          answers={question.answers}
          correctAnswerId={question.correctAnswerId}
          correctCounter={correctCounter}
          incorrectCounter={incorrectCounter}
          correctIds={correctIds}
          setCorrectCounter={setCorrectCounter}
          setIncorrectCounter={setIncorrectCounter}
          setCorrectIds={setCorrectIds}
        />
      </div>
    );
  });
  return (
    <div>
      <Score
        correctCounter={correctCounter}
        incorrectCounter={incorrectCounter}
        correctIds={correctIds}
      />
      {questionsList}
    </div>
  );
};

export default QuestionsList;
