import logger from "morgan"
import bodyParser from "body-parser"
import hbsMiddleware from "express-handlebars"
import fs from "fs"
import _ from "lodash"
import express from "express"
import { fileURLToPath } from 'url'
import path from 'path'

const __filename = fileURLToPath(import.meta.url)
const __dirname = path.dirname(__filename)
const app = express()

// You should not need to change the code in this file

app.set("views", path.join(__dirname, "../views"))
app.engine(
  "hbs",
  hbsMiddleware({
    defaultLayout: "default",
    extname: ".hbs"
  })
)

app.set("view engine", "hbs")

app.use(logger("dev"))
app.use(express.json())

app.use(express.static(path.join(__dirname, "../public")))
app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())

const quotePath = path.join(__dirname, "../quotes.txt")
const getQuotes = () => {
  const quoteLines = fs.readFileSync(quotePath).toString().split("\n")
  quotes = quoteLines.map((line) => {
    const [quote, author, subject] = line.split(";")
    return {
      quote: quote,
      author: author,
      subject: subject
    }
  })
  return quotes
}


app.get("/quotes", (req, res) => {
  const quotes = getQuotes()
  res.render("quotes/index", {quotes: quotes})
})


app.listen(3000, "0.0.0.0", () => {
  console.log("Server is listening...")
})

export default app
