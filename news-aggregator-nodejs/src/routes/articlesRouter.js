import express from "express"

import Article from "../models/Article.js"

const articlesRouter = new express.Router()

articlesRouter.get("/", (req, res) => {
  res.render("articles/index", { articles: Article.findAll() })
})

articlesRouter.get("/new", (req, res) => {
  res.render("articles/new")
})

articlesRouter.post("/new", (req, res) => {
  const newArticleObject = new Article(req.body);
  if (newArticleObject.save()) res.redirect("/articles");
  else res.render("articles/new", { article: newArticleObject })
})

export default articlesRouter