import React, { useEffect } from "react";
import { Route, BrowserRouter } from "react-router-dom";
import "../assets/scss/main.scss";
import "foundation-sites";
import $ from "jquery";

import NavBar from "./NavBar";

const App = (props) => {
  useEffect(() => {
    $(document).foundation();
  }, []);

  return (
    <BrowserRouter>
      <Route path="/" component={NavBar} />
    </BrowserRouter>
  );
};

export default App;
