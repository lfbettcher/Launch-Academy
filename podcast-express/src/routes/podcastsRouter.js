import express from "express"

import Podcast from "../models/Podcast.js"

const podcastsRouter = express.Router()

podcastsRouter.get("/", (req, res) => {
  res.render("index", { podcasts: Podcast.findAll() })
})

podcastsRouter.get("/new", (req, res) => {
  res.render("new")
})

podcastsRouter.post("/new", (req, res) => {
  res.redirect("index")
})

export default podcastsRouter
