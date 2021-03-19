import express from "express"

import Movie from "../../../models/Movie.js"

const moviesRouter = express.Router()

moviesRouter.get("/", async (req, res) => {
  try {
    const movies = await Movie.findAll()
    res.json({ movies: movies })
  } catch (err) {
    console.error(`Error: ${err.message}`)
  }
})

moviesRouter.get("/:id", async (req, res) => {
  try {
    const movie = await Movie.findById(req.params.id)
    if (movie) {
      res.status(200).json({ movie: movie })
    } else {
      res.status(404)
    }
  } catch (err) {
    console.error(`Error: ${err.message}`)
  }
})

export default moviesRouter
