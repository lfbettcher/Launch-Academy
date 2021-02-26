import express from "express"

import Animal from "../models/Animal.js"

const animalsRouter = new express.Router()

animalsRouter.get("/", (req, res) => {
  res.render("index", { animals: Animal.findAll() })
})

animalsRouter.post("/", (req, res) => {
  const animalName = req.body.name
  const animalType = req.body.type
  const newAnimal = new Animal({name: animalName, type: animalType})
  newAnimal.save()
  res.redirect("/animals")
})

export default animalsRouter