import React, { useState } from "react";
import AnswersList from "./AnswersList";

const App = (props) => {
  const { question } = props.data;
  const [selectedId, setSelectedId] = useState(null);

  let displayResult;
  if (!selectedId) {
    displayResult = null;
  } else if (selectedId === question.correctAnswerId) {
    displayResult = <h4 className="green">Correct!</h4>;
  } else {
    displayResult = <h4 className="red">Incorrect!</h4>;
  }

  return (
    <div className="grid-container app text-center">
      <h1 className="title">React Quiz</h1>
      <h3>{question.body}</h3>
      <AnswersList question={question} selectedId={selectedId} setSelectedId={setSelectedId} />
      {displayResult}
    </div>
  );
};

export default App;
