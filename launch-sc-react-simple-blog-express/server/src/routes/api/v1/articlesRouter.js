import express from "express"

import Article from "../../../models/Article.js"

const articlesRouter = express.Router()

articlesRouter.get("/", (req, res) => {
  res.set({ 'Content-Type': 'application/json' }).status(200).json({articles:Article.findAll()})
})

articlesRouter.get("/:id", (req, res) => {
  console.log(req.params);
  const article = Article.findById(req.params.id)
  if(article) {
    res.status(200).json({ article: article })
  } else {
    res.status(404)
  }
})

articlesRouter.post("/", (req, res) => {
  const {title, content} = req.body
  const article = new Article({ title, content })
  if(article.save()) {
    res.status(201).json({ article: article })
  } else {
    res.status(422).json({ errors: article.errors })
  }
})

export default articlesRouter
