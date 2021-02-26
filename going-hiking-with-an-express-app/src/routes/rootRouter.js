import express from "express"

import suppliesRouter from "./suppliesRouter.js"

const rootRouter = new express.Router()

rootRouter.use("/supplies", suppliesRouter)

export default rootRouter