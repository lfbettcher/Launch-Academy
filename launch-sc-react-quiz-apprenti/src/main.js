import "./main.scss";
import React from "react";
import ReactDOM from "react-dom";
import App from "./components/App";

import data from "./constants/exceedsData";

ReactDOM.render(<App data={data} />, document.getElementById("app"));
