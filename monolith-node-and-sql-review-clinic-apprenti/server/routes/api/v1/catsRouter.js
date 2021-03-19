import express from "express"

import Cat from "../../../models/Cat.js"

const catsRouter = new express.Router()

catsRouter.get("/", async (req, res) => {
  try {
    const cats = await Cat.findAll()
    res.status(200).json({ cats: cats })
  } catch (error) {
    res.status(500).json({ errors: error })
  }
})

catsRouter.get("/:id", async (req, res) => {
  try {
    const cat = await Cat.findById(req.params.id)
    res.status(200).json({ cat: cat })
  } catch (error) {
    res.status(500).json({ errors: error })
  }
})

catsRouter.post("/", async (req, res) => {
  try {
    const formData = req.body
    const newCat = new Cat(formData)
    await newCat.save()
    res.status(304)
  } catch (error) {
    res.status(500).json({ errors: error })
  }
})

export default catsRouter
