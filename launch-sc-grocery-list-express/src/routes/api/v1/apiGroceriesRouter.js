import express from "express"

import Grocery from "../../../models/Grocery.js"

const apiGroceriesRouter = new express.Router()

apiGroceriesRouter.get("/random", (req, res) => {
  res.set({ "Content-Type": "application/json" }).status(200).json(Grocery.random())
})

export default apiGroceriesRouter
