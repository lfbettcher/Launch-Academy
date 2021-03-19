import express from "express"

import Movie from "../../../models/Movie.js"

const moviesRouter = express.Router()

moviesRouter.get("/", async (req, res) => {
  res.send(
    "Instead we should make use of a method on the Movie class to return all movie records as a json object."
  )
})

moviesRouter.get("/:id", (req, res) => {
  res.send(
    "Here's where the movie with a specific id should be returned as a JSON object by using a Movie method to query the database."
  )
})

export default moviesRouter
