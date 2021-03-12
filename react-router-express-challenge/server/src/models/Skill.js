import fs from "fs"

const skillsPath = "skills.json"

class Skill {
  constructor({ id, name, proficiency }) {
    this.id = id
    this.name = name
    this.proficiency = proficiency
  }

  static findAll() {
    const skillData = JSON.parse(fs.readFileSync(skillsPath)).skills
    const skills = skillData.map(skill => new Skill(skill))
    return skills
  }
}

export default Skill