import express from "express"
import articlesRouter from "./articlesRouter.js"
import apiArticlesRouter from "./api/v1/apiArticlesRouter.js"

const rootRouter = new express.Router()

rootRouter.use("/articles", articlesRouter)
rootRouter.use("/api/v1/articles", apiArticlesRouter)

export default rootRouter