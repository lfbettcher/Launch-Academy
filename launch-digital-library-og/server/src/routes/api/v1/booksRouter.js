import express from "express"

import { Book } from "../../../models/index.js"

const booksRouter = new express.Router()

booksRouter.get("/", async (req, res) => {
  // your code here
  try {
    const { id } = req.params
    const books = await Book.query()
  } catch (err) {
    console.error(err)
    res.status(500).json(err)
  }
})

export default booksRouter
