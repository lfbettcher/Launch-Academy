import express from "express"
import _ from "lodash"

import Recipe from "../../../models/Recipe.js"

const recipesRouter = new express.Router()

recipesRouter.get("/", (req, res) => {
  res.set({ 'Content-Type': 'application/json' }).status(200).json({ recipes: Recipe.findAll() })
})

recipesRouter.get("/random", (req, res) => {
  const recipes = Recipe.findAll()
  const randomRecipe = _.sample(recipes)
  res.set({ 'Content-Type': 'application/json' }).status(200).json({ recipe: randomRecipe })
})

recipesRouter.get("/longest", (req, res) => {
  const recipes = Recipe.findAll()
  const longestRecipe = recipes.reduce((a, b) => a.name.length > b.name.length ? a : b)
  res.set({ 'Content-Type': 'application/json' }).status(200).json({ recipe: longestRecipe })
})

export default recipesRouter