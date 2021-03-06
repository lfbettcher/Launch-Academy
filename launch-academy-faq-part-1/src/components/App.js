import React from "react";
import QuestionList from "./QuestionList";

const App = (props) => {
  return (
    <>
      <h1 className="title">We're here to help</h1>
      <QuestionList data={props.data} />
    </>
  );
};

export default App;
