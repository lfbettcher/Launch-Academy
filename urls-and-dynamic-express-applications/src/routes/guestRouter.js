import express from "express"

const guestRouter = new express.Router()

const vipNerds = [
  "Grace Hopper",
  "Mark Zuckerberg",
  "Bill Gates",
  "Margaret Hamilton"
]

guestRouter.get("/", (req, res) => {
  const nerdsFound = vipNerds.filter((name) => {
    if (req.query.search) {
      return name.startsWith(req.query.search)
    }
    return true
  })
  if (nerdsFound.length > 0) {
    res.send(nerdsFound.join("<hr>"))
  } else {
    res.send("No nerds found.")
  }
})

export default guestRouter
