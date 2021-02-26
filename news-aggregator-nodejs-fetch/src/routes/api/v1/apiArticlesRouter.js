import express from "express"

import Article from "../../../models/Article.js"

const apiArticlesRouter = new express.Router()

apiArticlesRouter.get("/random", (req, res) => {
  res.set({ "Content-Type": "application/json" }).json(Article.random())
})

export default apiArticlesRouter
