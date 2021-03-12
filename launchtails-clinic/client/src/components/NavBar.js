import React from "react";
import { Route, Switch, Link } from "react-router-dom";

import DrinksIndexContainer from "./DrinksIndexContainer";
import DrinkFormContainer from "./DrinkFormContainer";
import DrinkShow from "./DrinkShow";

const NavBar = () => {
  return (
    <>
      <nav data-sticky-container>
        <div className="top-bar" data-sticky data-options="marginTop:0;">
          <div className="top-bar-left">
            <ul className="menu">
              <li>
                <Link to="/">All Drinks</Link>
              </li>
              <li>
                <Link to="/drinks/new">Add New Drink</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="grid-container">
        <Switch>
          <Route exact path="/" component={DrinksIndexContainer} />
          <Route exact path="/drinks/new" component={DrinkFormContainer} />
          <Route exact path="/drinks/:id" component={DrinkShow} />
        </Switch>
      </div>
    </>
  );
};

export default NavBar;
