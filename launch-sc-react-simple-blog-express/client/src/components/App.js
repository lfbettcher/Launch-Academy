import React from "react"
import { Route, Switch, BrowserRouter } from "react-router-dom"
import { hot } from "react-hot-loader/root"

import ArticlesList from "./ArticlesList"
import ArticlesShow from "./ArticleShow"

const App = (props) => (
  <BrowserRouter>
    <Switch>
      <Route exact path="/" component={ArticlesList} />
      <Route exact path="/articles/:id" component={ArticlesShow} />
    </Switch>
  </BrowserRouter>
)

export default hot(App)
