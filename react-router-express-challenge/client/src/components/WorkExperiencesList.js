import React, { useState, useEffect } from "react"

import WorkTile from "./WorkTile"

const WorkExperiencesList = props => {
  const [workExperiences, setWorkExperiences] = useState([])

  const fetchData = async () => {
    try {
      const response = await fetch("/api/v1/work-experiences")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const responseData = await response.json()
      setWorkExperiences(responseData.workExperiences)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }
  
  useEffect(() => {
    fetchData()
  },[])

  const workExperienceTiles = workExperiences.map(workExperience => {
    return <WorkTile key={workExperience.id} workExperience={workExperience} />
  })

  return (
    <div className="work_experiences">
      <h1>Past Work</h1>
      {workExperienceTiles}
    </div>
  )
}

export default WorkExperiencesList
