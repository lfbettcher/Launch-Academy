import express from "express"

import WorkExperience from "../../../models/WorkExperience.js"

const workExperiencesRouter = new express.Router()

workExperiencesRouter.get("/", (req, res) => {
  res.set({ 'Content-Type': 'application/json' }).status(200).json({ workExperiences: WorkExperience.findAll() })
})

export default workExperiencesRouter