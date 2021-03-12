import React from "react"

const SkillTile = ({ skill: { name, proficiency } }) => {
  return (
    <div className="skill">
      <h2>{name}</h2>
      <h3>Proficiency: {proficiency}</h3>
    </div>
  )
}

export default SkillTile
