import React, { useState, useEffect } from "react"

import SkillTile from "./SkillTile"

const SkillsList = props => {
  const [skills, setSkills] = useState([])

  const fetchData = async () => {
    try {
      const response = await fetch("/api/v1/skills")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const responseData = await response.json()
      setSkills(responseData.skills)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }
  
  useEffect(() => {
    fetchData()
  },[])

  const skillTiles = skills.map(skill => {
    return <SkillTile key={skill.id} skill={skill} />
  })

  return (
    <div className="skills">
      <h1>Skills</h1>
      {skillTiles}
    </div>
  )
}

export default SkillsList
