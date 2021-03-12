import fs from "fs"

const workExperiencesPath = "workExperiences.json"

class WorkExperience {
  constructor({ id, company, position, description }) {
    this.id = id
    this.company = company
    this.position = position
    this.description = description
  }

  static findAll() {
    const workExperienceData = JSON.parse(fs.readFileSync(workExperiencesPath)).workExperiences
    const workExperiences = workExperienceData.map(workExperience => new WorkExperience(workExperience))
    return workExperiences
  }
}

export default WorkExperience