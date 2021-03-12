import fs from "fs"

const educationExperiencesPath = "educationExperiences.json"

class EducationExperience {
  constructor({id, institution, courseTitle, degree}) {
    this.id = id
    this.institution = institution
    this.courseTitle = courseTitle
    this.degree = degree
  }

  static findAll() {
    const educationExperienceData = JSON.parse(fs.readFileSync(educationExperiencesPath)).educationExperiences
    const educationExperiences = educationExperienceData.map(educationExperience => new EducationExperience(educationExperience))
    return educationExperiences
  }
}

export default EducationExperience