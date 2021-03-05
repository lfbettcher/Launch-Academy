import "./main.scss";
import React from "react";
import ReactDOM from "react-dom";
import App from "./components/App";
import groceryData from "./constants/data";

ReactDOM.render(<App groceryData={groceryData} />, document.getElementById("app"));
