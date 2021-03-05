import "./app.scss";

// import dependencies like React and ReactDOM
import React from "react";
import ReactDOM from "react-dom";

// import components, if rendered in ReactDOM.render
import Popup from "./components/Popup.js";

// call on ReactDOM.render with the correct arguments
ReactDOM.render(
  <Popup />,
  document.getElementById("app")
);
