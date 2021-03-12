import React from "react"
import { Route, Switch, Link } from "react-router-dom"

import About from "./About"
import Resume from "./Resume"
import ProjectsIndex from "./ProjectsIndex"
import ProjectShow from "./ProjectShow"

const NavBar = () => {
  return (
    <>
      <nav data-sticky-container>
        <div className="top-bar" data-sticky data-options="marginTop:0;" style={{ width: "100%" }}>
          <div className="top-bar-left">
            <ul className="menu">
              <li>
                <Link to="/">About</Link>
              </li>
              <li>
                <Link to="/resume">Jane&apos;s Resume</Link>
              </li>
              <li>
                <Link to="/projects">Jane&apos;s Projects</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="grid-container">
        <Switch>
          <Route exact path="/" component={About} />
          <Route exact path="/resume" component={Resume} />
          <Route exact path="/projects" component={ProjectsIndex} />
          <Route exact path="/projects/:id" component={ProjectShow} />
        </Switch>
      </div>
    </>
  )
}

export default NavBar
