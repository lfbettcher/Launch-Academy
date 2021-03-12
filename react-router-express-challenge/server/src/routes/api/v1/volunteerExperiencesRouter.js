import express from "express"

import VolunteerExperience from "../../../models/VolunteerExperience.js"

const volunteerExperiencesRouter = new express.Router()

volunteerExperiencesRouter.get("/", (req, res) => {
  res.set({ 'Content-Type': 'application/json' }).status(200).json({ volunteerExperiences: VolunteerExperience.findAll() })
})

export default volunteerExperiencesRouter