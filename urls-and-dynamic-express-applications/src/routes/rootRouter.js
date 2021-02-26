import express from "express"
import productsRouter from "./productsRouter.js"
import categoriesRouter from "./categoriesRouter.js"
import guestRouter from "./guestRouter.js";

const rootRouter = new express.Router()

rootRouter.use("/products", productsRouter)
rootRouter.use("/categories", categoriesRouter)
rootRouter.use("/guests", guestRouter)

export default rootRouter