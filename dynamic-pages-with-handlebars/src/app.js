import express from 'express'
import logger from 'morgan'
import bodyParser from 'body-parser'
import path from 'path'
import { fileURLToPath } from 'url'
import hbsMiddleware from "express-handlebars"
import rootRouter from "./routes/rootRouter.js"

const __filename = fileURLToPath(import.meta.url)
const __dirname = path.dirname(__filename)

const app = express()

app.use(logger('dev'))
app.use(bodyParser.urlencoded({ extended: false }))
app.use(express.static(path.join(__dirname, '../public')))

app.set("views", path.join(__dirname, "../views"))
app.engine(
  "hbs",
  hbsMiddleware({
    defaultLayout: "default",
    extname: ".hbs"
  })
)
app.set("view engine", "hbs")

app.use(rootRouter)

app.listen(3000, '0.0.0.0', () => {
  console.log('Server is listening...')
})

export default app
