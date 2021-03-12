import fs from "fs"

const projectsPath = "projects.json"

class Project {
  constructor({ id, name, description }) {
    this.id = id
    this.name = name
    this.description = description
  }

  static findAll() {
    const projectData = JSON.parse(fs.readFileSync(projectsPath)).projects
    const projects = projectData.map(project => new Project(project))
    return projects
  }

  static findById(id) {
    const allProjects = this.findAll()
    const myProject = allProjects.find(project => project.id == id)
    return myProject
  }
}

export default Project