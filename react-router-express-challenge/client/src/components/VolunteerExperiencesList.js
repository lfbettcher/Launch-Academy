import React, { useState, useEffect } from "react"

import VolunteerTile from "./VolunteerTile"

const VolunteerExperiencesList = props => {
  const [volunteerExperiences, setVolunteerExperiences] = useState([])

  const fetchData = async () => {
    try {
      const response = await fetch("/api/v1/volunteer-experiences")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const responseData = await response.json()
      setVolunteerExperiences(responseData.volunteerExperiences)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }
  
  useEffect(() => {
    fetchData()
  },[])

  const volunteerTiles = volunteerExperiences.map(volunteerExperience => {
    return (
      <VolunteerTile
        key={volunteerExperience.id}
        volunteerExperience={volunteerExperience}
      />
    )
  })

  return (
    <div className="volunteer_experiences">
      <h1>Volunteer Experience</h1>
      {volunteerTiles}
    </div>
  )
}

export default VolunteerExperiencesList
