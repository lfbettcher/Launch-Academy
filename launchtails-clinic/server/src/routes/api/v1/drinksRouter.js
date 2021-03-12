import express from "express"

import Drink from "../../../models/Drink.js"

const drinksRouter = new express.Router()

drinksRouter.get("/", (req, res) => {
  res.set({ "Content-Type": "application/json" }).status(200).json({ drinks: Drink.findAll() })
})

drinksRouter.get("/:id", (req, res) => {
  const drink = Drink.findById(req.params.id)
  if (drink) {
    res.set({ "Content-Type": "application/json" }).status(200).json({ drink })
  } else {
    res.status(404).json({ error: "Drink Not Found!" })
  }
})

drinksRouter.post("/", (req, res) => {
  const drink = new Drink(req.body)
  if (drink.save()) {
    res.status(201).json({ drink })
  } else {
    res.status(422).json({ errors: drink.errors })
  }
})

export default drinksRouter
