import React, { useState, useEffect } from "react"

import EducationTile from "./EducationTile"

const EducationExperiencesList = props => {
  const [educationExperiences, setEducationExperiences] = useState([])

  const fetchData = async () => {
    try {
      const response = await fetch('/api/v1/education-experiences')
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const responseData = await response.json()
      setEducationExperiences(responseData.educationExperiences)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }
  
  useEffect(() => {
    fetchData()
  },[])

  const educationTiles = educationExperiences.map(educationExperience => {
    return (
      <EducationTile
        key={educationExperience.id}
        educationExperience={educationExperience}
      />
    )
  })

  return (
    <div className="education">
      <h1>Education</h1>
      {educationTiles}
    </div>
  )
}

export default EducationExperiencesList
