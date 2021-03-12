import React from "react"
import { Switch, Route, Link } from "react-router-dom"

import FAQList from "./FAQList"
import LauncherList from "./LauncherList"
import LauncherShow from "./LauncherShow"

const NavBar = () => {
  return (
    <>
      <nav data-sticky-container>
        <div className="top-bar" data-sticky data-options="marginTop:0;" style={{ width: "100%" }}>
          <div className="top-bar-left">
            <ul className="menu">
              <li>
                <Link to="/">FAQ</Link>
              </li>
              <li>
                <Link to="/launchers">Launchers</Link>
              </li>
              <li>
                <Link to="/launchers/new">Add New Launcher</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="grid-container">
        <Switch>
          <Route exact path="/" component={FAQList} />
          <Route exact path="/launchers" component={LauncherList} />
          <Route exact path="/launchers/:id" component={LauncherShow} />
          <Route exact path="/launchers/new" component={LauncherShow} />
        </Switch>
      </div>
    </>
  )
}

export default NavBar
