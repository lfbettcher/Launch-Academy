import express from "express"

import Book from "../../../models/Book.js"

const booksRouter = new express.Router()

booksRouter.get("/", (req, res) => {
  res.set({ "Content-Type": "application/json" }).json(Book.findAll())
})

booksRouter.post("/", (req, res) => {
  const book = new Book(req.body.book)
  if(book.isValid()) {
    book.save()
    res.status(201).json({ book })
  } else {
    res.status(422).json(book.errors)
  }
})

export default booksRouter