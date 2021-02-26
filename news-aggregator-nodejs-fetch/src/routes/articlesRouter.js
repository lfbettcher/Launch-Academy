import express from "express"

import Article from "../models/Article.js"

const articlesRouter = new express.Router()

articlesRouter.get("/", (req, res) => {
  const articles = Article.findAll()
  res.render("articles/index", { articles })
})

articlesRouter.get("/new", (req, res) => {
  res.render("articles/new")
})

articlesRouter.get("/random", (req, res) => {
  res.render("articles/random")
})

articlesRouter.post("/", (req, res) => {
  const newArticle = new Article(req.body)
  newArticle.save()
  res.redirect("/articles")
})

export default articlesRouter
