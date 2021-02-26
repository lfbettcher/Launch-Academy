import express from "express"

const guestsRouter = new express.Router()

const vipNerds = [
  "Grace Hopper",
  "Mark Zuckerberg",
  "Bill Gates",
  "Margaret Hamilton"
]

guestsRouter.get("/", (req, res) => {
  const nerdsFound = vipNerds.filter((name) => {
    if (req.query.search) {
      return name.startsWith(req.query.search)
    }
    return true
  })
  res.render("search", { nerdsFound: nerdsFound || [] })
})

export default guestsRouter
