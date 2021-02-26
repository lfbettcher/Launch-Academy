import express from "express"

import groceriesRouter from "./groceriesRouter.js"

const rootRouter = express.Router()

rootRouter.get("/", (req, res) => {
  res.redirect("/groceries")
})

rootRouter.use("/groceries", groceriesRouter)

// comment the below lines in for Exceeds
import apiGroceriesRouter from "./api/v1/apiGroceriesRouter.js"
rootRouter.use("/api/v1/groceries", apiGroceriesRouter)

export default rootRouter
