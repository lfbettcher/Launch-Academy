import express from "express"

import Adventure from "../../../models/Adventure.js"

const adventuresRouter = new express.Router()

adventuresRouter.get("/", async (req, res) => {
  try {
    // get and return the list of adventures using the model
    const adventures = await Adventure.findAll()
    return res.status(200).json({ adventures: adventures })
  } catch (error) {
    console.error(error)
    return res.status(500).json({ errors: error })
  }
})

adventuresRouter.get("/:id", async (req, res) => {
  try {
    // get and return a single adventure using the model
    const adventure = await Adventure.findById(req.params.id)
    return res.status(200).json({ adventure: adventure })
  } catch (error) {
    console.error(error)
    return res.status(500).json({ errors: error })
  }
})

adventuresRouter.post("/", async (req, res) => {
  try {
    // save the posted data to the database and return it, with the id it was given in the database, to the front end.
    const formData = req.body
    const newAdventure = new Adventure(formData)
    if (await newAdventure.save()) {
      return res.status(201).json({ adventure: newAdventure })
    } else {
      return res.status(422).json({ errors: newAdventure.errors })
    }
  } catch (error) {
    console.error(error)
    return res.status(500).json({ errors: error })
  }
})

export default adventuresRouter
