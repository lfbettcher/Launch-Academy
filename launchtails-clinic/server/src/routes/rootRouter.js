import express from "express"
import clientRouter from "./clientRouter.js"
import drinksRouter from "./api/v1/drinksRouter.js"

const rootRouter = new express.Router()

rootRouter.use("/api/v1/drinks", drinksRouter)

rootRouter.use("/", clientRouter)

export default rootRouter
