import express from "express"
import guestsRouter from "./guestsRouter.js"

const rootRouter = new express.Router()

rootRouter.use("/guests", guestsRouter)

export default rootRouter