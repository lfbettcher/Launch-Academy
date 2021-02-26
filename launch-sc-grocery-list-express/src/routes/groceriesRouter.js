import express from "express"

import Grocery from "../models/Grocery.js"

const groceriesRouter = express.Router()

groceriesRouter.get("/", (req, res) => {
  res.render("groceries/index", { groceries: Grocery.findAll() })
})

groceriesRouter.get("/new", (req, res) => {
  res.render("groceries/new")
})

groceriesRouter.get("/:groceryName", (req, res) => {
  res.render("groceries/show", { grocery: Grocery.find(req.params.groceryName) })
})

groceriesRouter.post("/", (req, res) => {
  const newGrocery = new Grocery({ name: req.body.name })
  if (newGrocery.save()) {
    res.redirect("/")
  } else {
    res.render("groceries/new", { error: "Name can't be blank" })
  }
})

export default groceriesRouter
