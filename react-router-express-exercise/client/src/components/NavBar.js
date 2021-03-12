import React from "react"
import { Link, Switch, Route } from "react-router-dom";

import Greeting from "./Greeting"
import Goodbye from "./Goodbye"
import CustomGreeting from "./CustomGreeting"

const NavBar = props => {
  return (
    <div className="row column">
      <div className="navbar">
        <Link to="/greeting">Greeting</Link>
        <Link to="/goodbye">Goodbye</Link>
        <Link to="/custom-greeting">Custom Greeting</Link>
      </div>
      <Switch>
        <Route exact path="/greeting" component={Greeting} />
        <Route exact path="/goodbye" component={Goodbye} />
        <Route exact path="/custom-greeting" component={CustomGreeting} />
      </Switch>
    </div>
  );
}

export default NavBar
