import React, { useState } from "react";
import QuestionsList from "./QuestionsList";

const App = (props) => {
  const { questions } = props.data;

  return (
    <div className="grid-container app text-center">
      <h1 className="title">React Quiz</h1>
      <QuestionsList questions={questions} />
    </div>
  );
};

export default App;
