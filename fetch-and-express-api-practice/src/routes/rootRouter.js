import express from "express"
import booksRouter from "./api/v1/booksRouter.js"

const rootRouter = new express.Router()

rootRouter.get("/", (req, res) => {
  res.render("index")
})

rootRouter.use("/api/v1/books", booksRouter)

export default rootRouter