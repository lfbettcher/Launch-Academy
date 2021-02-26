import express from "express"
import podcastsRouter from "./podcastsRouter.js"

const rootRouter = new express.Router()

rootRouter.get("/", (req, res) => {
  res.redirect("/podcasts")
})

rootRouter.use("/podcasts", podcastsRouter)

export default rootRouter
