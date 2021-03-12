import React from "react"
import { Route, Switch, BrowserRouter } from "react-router-dom"
import { hot } from "react-hot-loader/root"

import ArticleList from "./ArticlesList"
import ArticleShow from "./ArticleShow"

const App = (props) => {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={ArticleList} />
        <Route exact path="/articles/:id" component={ArticleShow} />
      </Switch>
    </BrowserRouter>
  )
}

// export default hot(App)
export default App