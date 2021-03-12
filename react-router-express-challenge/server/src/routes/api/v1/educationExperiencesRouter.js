import express from "express"
import _ from "lodash"

import EducationExperience from "../../../models/EducationExperience.js"

const educationExperiencesRouter = new express.Router()

educationExperiencesRouter.get("/", (req, res) => {
  res.set({ 'Content-Type': 'application/json' }).status(200).json({ educationExperiences: EducationExperience.findAll() })
})

export default educationExperiencesRouter