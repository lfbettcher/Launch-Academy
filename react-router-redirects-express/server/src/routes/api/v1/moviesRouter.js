import express from "express"
import _ from "lodash"

import Movie from "../../../models/Movie.js"

const moviesRouter = new express.Router()

moviesRouter.get("/", (req, res) => {
  res.set({ 'Content-Type': 'application/json' }).status(200).json({ movies: Movie.findAll() })
})

moviesRouter.post("/", (req, res) => {
  const movie = new Movie(req.body)
  if(movie.save()) {
    res.status(201).json({ movie })
  } else {
    res.status(422).json({ errors: movie.errors })
  }
})

export default moviesRouter