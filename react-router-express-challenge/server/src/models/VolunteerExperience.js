import fs from "fs"

const volunteerExperiencesPath = "volunteerExperiences.json"

class VolunteerExperience {
  constructor({ id, organization, position, cause }) {
    this.id = id
    this.organization = organization
    this.position = position
    this.cause = cause
  }

  static findAll() {
    const volunteerExperienceData = JSON.parse(fs.readFileSync(volunteerExperiencesPath)).volunteerExperiences
    const volunteerExperiences = volunteerExperienceData.map(volunteerExperience => new VolunteerExperience(volunteerExperience))
    return volunteerExperiences
  }
}

export default VolunteerExperience