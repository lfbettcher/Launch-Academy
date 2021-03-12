import React from "react";
import { hot } from "react-hot-loader/root";
import { BrowserRouter, Route } from "react-router-dom";

import "../assets/scss/main.scss";

import MoviesIndex from "./MoviesIndex";
import NewMovieForm from "./NewMovieForm";

const App = (props) => {
  return (
    <BrowserRouter>
      <Route exact path="/movies" component={MoviesIndex} />
      <Route exact path="/movies/new" component={NewMovieForm} />
    </BrowserRouter>
  );
};

export default hot(App);
