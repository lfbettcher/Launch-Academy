import express from "express"

import Skill from "../../../models/Skill.js"

const skillsRouter = new express.Router()

skillsRouter.get("/", (req, res) => {
  res.set({ 'Content-Type': 'application/json' }).status(200).json({ skills: Skill.findAll() })
})

export default skillsRouter