import express from "express";

import Book from "../../../models/Book.js";
import ReadingNote from "../../../models/ReadingNote.js";

const booksRouter = new express.Router();

booksRouter.get("/", async (req, res) => {
  try {
    const books = await Book.findAll();
    res.status(200).json({ books: books });
  } catch (error) {
    res.status(500).json({ errors: error });
  }
});

booksRouter.get("/:id", async (req, res) => {
  try {
    const book = await Book.findById(req.params.id);
    res.status(200).json({ book: book });
  } catch (error) {
    res.status(500).json({ errors: error });
  }
});

booksRouter.post("/", async (req, res) => {
  try {
    const formData = req.body;
    const newBook = new Book(formData);
    await newBook.save();
    res.status(304);
  } catch (error) {
    res.status(500).json({ errors: error });
  }
});

booksRouter.post("/:bookId/reading-notes", async (req, res) => {
  try {
    const formData = req.body;
    formData.bookId = req.params.bookId
    const newReadingNote = new ReadingNote(formData);
    console.log(newReadingNote)
    await newReadingNote.save();
    res.status(304);
  } catch (error) {
    res.status(500).json({ errors: error });
  }
});

export default booksRouter;
