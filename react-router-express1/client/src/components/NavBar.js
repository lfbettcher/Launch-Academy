import React from "react"
import { Switch, Route, Link } from "react-router-dom"
import CerealsIndexPage from "./CerealsIndexPage"
import MilksIndexPage from "./MilksIndexPage"
import CerealShowPage from "./CerealShowPage"

const NavBar = (props) => {
  return (
    <div className="row column">
      <nav data-sticky-container>
        <div className="top-bar" data-sticky data-options="marginTop:0;">
          <div className="top-bar-left">
            <ul className="menu">
              <li>
                <Link to="/">All Cereals</Link>
              </li>
              <li>
                <Link to="/milks">All Milks</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="content">
        <h1 className="page-title">Cereals</h1>
      </div>

      <Switch>
        <Route exact path="/" component={CerealsIndexPage} />
        <Route exact path="/cereals/:id" component={CerealShowPage} />
        <Route exact path="/milks" component={MilksIndexPage} />
      </Switch>
      <footer data-sticky-container>
        <div className="sticky" data-sticky data-stick-to="bottom" data-options="marginBottom:0">
          <p>I am a footer!</p>
        </div>
      </footer>
    </div>
  )
}

export default NavBar
