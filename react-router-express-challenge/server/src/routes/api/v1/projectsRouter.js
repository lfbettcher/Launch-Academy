import express from "express"

import Project from "../../../models/Project.js"

const projectsRouter = new express.Router()

projectsRouter.get("/", (req, res) => {
  res.set({ 'Content-Type': 'application/json' }).status(200).json({ projects: Project.findAll() })
})

projectsRouter.get("/:id", (req, res) => {
  const project = Project.findById(req.params.id)
  if(project) {
    res.set({ 'Content-Type': 'application/json' }).status(200).json({ project })
  } else {
    res.status(404).json({ error: "Project Not Found!" })
  }
})

export default projectsRouter