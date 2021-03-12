import express from "express"
import articlesRouter from "./api/v1/articlesRouter.js"
import clientRouter from "./clientRouter.js"

const rootRouter = new express.Router() 

rootRouter.use("/api/v1/articles", articlesRouter)

rootRouter.use("/", clientRouter)

export default rootRouter
